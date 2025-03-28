import React from 'react';
import {Route, Routes} from 'react-router-dom';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import LoginPage from './pages/LoginPage';
import Unauthorized from './pages/Unauthorized';

const AppRoutes: React.FC = () => {
  return (
    <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/login" element={<LoginPage/>}/>
      <Route path="/dashboard" element={<Dashboard/>}/>
      <Route path="/unauthorized" element={<Unauthorized/>}/>
    </Routes>
  );
};

export default AppRoutes;