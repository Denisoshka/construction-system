import React from 'react';
import { useAuth } from '../auth/AuthProvider.tsx';
import { Link} from 'react-router-dom';
// import { useEffect } from 'react';

const Home: React.FC = () => {
  const {isAuthenticated} = useAuth();
  // const navigate = useNavigate();

  // useEffect(() => {
  //   if (isAuthenticated) {
  //     navigate('/dashboard');
  //   }
  // }, [isAuthenticated, navigate]);

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