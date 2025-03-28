import React, { useEffect } from 'react';
import {keycloak} from '../auth/Keycloak.ts';

const LoginPage: React.FC = () => {
  useEffect(() => {
    // Если пользователь не авторизован - покажем форму логина
    if (!keycloak.authenticated) {
      void keycloak.login() // Это покажет стандартную форму Keycloak
    }
  }, []);

  const handleRegister = () => {
    keycloak.register(); // Открывает форму регистрации
  };

  return (
    <div className="login-container">
      <h2>Перенаправление на страницу входа...</h2>
      <button onClick={handleRegister}>Зарегистрироваться</button>
    </div>
  );
};

export default LoginPage;