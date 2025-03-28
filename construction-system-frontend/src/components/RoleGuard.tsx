import React from 'react';
import { useAuth } from '../auth/AuthProvider';
import { Navigate } from 'react-router-dom';

type RoleGuardProps = {
  children: React.ReactNode;
  requiredRoles: string[];
};

const RoleGuard: React.FC<RoleGuardProps> = ({ children, requiredRoles }) => {
  const { userInfo, loading } = useAuth();

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!userInfo || !requiredRoles.some(role => userInfo.roles.includes(role))) {
    return <Navigate to="/unauthorized" replace />;
  }

  return <>{children}</>;
};

export default RoleGuard;