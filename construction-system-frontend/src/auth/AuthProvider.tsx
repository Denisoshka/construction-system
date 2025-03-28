import React, {createContext, useContext, useEffect, useState} from 'react';
import {ReactKeycloakProvider} from '@react-keycloak/web';
import keycloak from './keycloak';
import {ApolloProvider} from '@apollo/client';
import client from '../graphql/client.ts';
import {useNavigate} from 'react-router-dom';

type AuthProviderProps = {
  children: React.ReactNode;
};

type UserInfo = {
  roles: string[];
  id: string;
  isAdmin: boolean;
};

type AuthContextType = {
  userInfo: UserInfo | null;
  isAuthenticated: boolean;
  loading: boolean;
  updateToken: () => Promise<boolean>;
};

const AuthContext = createContext<AuthContextType>({
  userInfo: null,
  isAuthenticated: false,
  loading: true,
  updateToken: async () => false,
});


export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};

const AuthProvider: React.FC<AuthProviderProps> = ({children}) => {
  const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const updateToken = async () => {
    try {
      const refreshed = await keycloak.updateToken(70); // Обновляем токен, если до истечения осталось меньше 70 секунд
      if (refreshed) {
        console.log('Token was successfully refreshed');
      }
      return refreshed;
    } catch (error) {
      console.error('Failed to refresh token:', error);
      void keycloak.logout();
      return false;
    }
  };

  useEffect(() => {
    const setupTokenRefresh = () => {
      const checkToken = async () => {
        if (keycloak.authenticated) {
          await updateToken();
        }
      };

      // Проверяем токен каждые 60 секунд
      const interval = setInterval(checkToken, 60000);
      return () => clearInterval(interval);
    };

    setupTokenRefresh();
  }, []);


  const handleOnEvent = (event: string) => {
    if (event === 'onAuthSuccess') {
      const roles = keycloak.tokenParsed?.realm_access?.roles || [];
      const id = keycloak.tokenParsed?.sub || '';
      setUserInfo({
        roles,
        id,
        isAdmin: roles.includes('admin')
      });
      setLoading(false);
    }

    if (event === 'onAuthLogout') {
      setUserInfo(null);
      navigate('/');
    }
  };
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      onEvent={handleOnEvent}
    >
      <ApolloProvider client={client}>
        <AuthContext.Provider value={{
          userInfo,
          isAuthenticated: !!userInfo,
          loading,
          updateToken
        }}>
          {children}
        </AuthContext.Provider>
      </ApolloProvider>
    </ReactKeycloakProvider>
  );
};

export default AuthProvider;