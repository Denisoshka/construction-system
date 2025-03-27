import React from "react";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import { ApolloProvider } from "@apollo/client";
import keycloak from "./auth/keycloak";
import { client } from "./graphql/client";
import Router from "./Router";
import { AuthContext } from "./auth/auth-context";
import { CssBaseline, ThemeProvider, createTheme } from "@mui/material";

const theme = createTheme();

const App: React.FC = () => {
  return (
    <ReactKeycloakProvider
      authClient={keycloak}
      initOptions={{ onLoad: "login-required" }}
    >
      <ApolloProvider client={client}>
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <AuthContext.Provider
            value={{
              keycloak,
              isAdmin: keycloak.hasRealmRole("admin"),
              isUser: keycloak.hasRealmRole("user"),
            }}
          >
            <Router />
          </AuthContext.Provider>
        </ThemeProvider>
      </ApolloProvider>
    </ReactKeycloakProvider>
  );
};

export default App;