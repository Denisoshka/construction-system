import { createContext, useContext } from "react";
import Keycloak from "keycloak-js";

interface AuthContextType {
  keycloak: Keycloak;
  isAdmin: boolean;
  isUser: boolean;
}

export const AuthContext = createContext<AuthContextType | null>(null);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider");
  }
  return context;
};