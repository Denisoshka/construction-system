import React from 'react';
import AuthProvider from './auth/AuthProvider';
import AppRoutes from './Routes';
import { BrowserRouter } from 'react-router-dom';
import './App.css';

const App: React.FC = () => {
  return (
    <BrowserRouter>
      <AuthProvider>
        <div className="App">
          <AppRoutes />
        </div>
      </AuthProvider>
    </BrowserRouter>
  );
};

export default App;