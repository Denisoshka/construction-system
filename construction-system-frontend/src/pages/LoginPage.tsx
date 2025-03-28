import React, { useEffect } from 'react';
import keycloak from '../auth/keycloak';
import { useNavigate } from 'react-router-dom';

const LoginPage: React.FC = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const initKeycloak = async () => {
      try {
        const authenticated = await keycloak.init({
          onLoad: 'login-required',
          pkceMethod: 'S256'
        });

        if (authenticated) {
          navigate('/dashboard');
        }
      } catch (error) {
        console.error('Keycloak initialization error:', error);
      }
    };

    initKeycloak();
  }, [navigate]);

  return (
    <div className="login-container">
      <h2>Redirecting to login page...</h2>
      <p>Please wait while we redirect you to the authentication service.</p>
    </div>
  );
};

export default LoginPage;