import React from "react";
import { useAuth } from "../auth/auth-context";
import { Navigate, useLocation } from "react-router-dom";

interface PrivateRouteProps {
  children: React.ReactElement;
  requiredRoles?: string[];
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ children, requiredRoles }) => {
  const { keycloak, isAdmin, isUser } = useAuth();
  const location = useLocation();

  if (!keycloak.authenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  if (requiredRoles) {
    const hasRequiredRole = requiredRoles.some(role => {
      if (role === "admin") return isAdmin;
      if (role === "user") return isUser;
      return false;
    });

    if (!hasRequiredRole) {
      return <Navigate to="/" replace />;
    }
  }

  return children;
};

export default PrivateRoute;