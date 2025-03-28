import React, {createContext, useContext, useState} from 'react';
import {ReactKeycloakProvider} from '@react-keycloak/web';
import {ApolloProvider} from '@apollo/client';
import client from '../graphql/client.ts';
import {useNavigate} from 'react-router-dom';
import {keycloak} from "./Keycloak.ts";

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
};

const AuthContext = createContext<AuthContextType>({
  userInfo: null,
  isAuthenticated: false,
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
  const navigate = useNavigate();

  const updateUserInfo = () => {
    const roles = keycloak.tokenParsed?.realm_access?.roles || [];
    const id = keycloak.tokenParsed?.sub || '';

    setUserInfo({
      roles,
      id,
      isAdmin: roles.includes('admin')
    });
  };

  const handleOnEvent = (event: string) => {
    if (event === 'onAuthSuccess') {
      updateUserInfo()
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
        }}>
          {children}
        </AuthContext.Provider>
      </ApolloProvider>
    </ReactKeycloakProvider>
  );
};

export default AuthProvider;