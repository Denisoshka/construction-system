import React, {useEffect} from "react";
import {useAuth} from "../auth/auth-context";
import {Box, CircularProgress, Typography} from "@mui/material";

const LoginPage: React.FC = () => {
  const {keycloak} = useAuth();

  useEffect(() => {
    if (!keycloak.authenticated) {
      keycloak.login();
    }
  }, [keycloak]);

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      justifyContent="center"
      minHeight="100vh"
    >
      <Typography variant="h4" gutterBottom>
        Redirecting to login...
      </Typography>
      <CircularProgress/>
    </Box>
  );
};

export default LoginPage;