import React from 'react';
import { useAuth } from '../auth/AuthProvider.tsx';
import { Link, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

const Home: React.FC = () => {
  const {isAuthenticated, loading } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      navigate('/dashboard');
    }
  }, [isAuthenticated, navigate]);

  if (loading) {
    return <div className="loading">Checking authentication...</div>;
  }

  return (
    <div className="home-container">
      <h1>Construction Site Management</h1>

      {!isAuthenticated && (
        <div className="auth-options">
          <Link to="/login" className="login-button">
            Login
          </Link>
        </div>
      )}
    </div>
  );
};

export default Home;