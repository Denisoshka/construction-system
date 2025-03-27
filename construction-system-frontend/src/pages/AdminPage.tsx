import React from "react";
import { useQuery } from "@apollo/client";
import { GET_ADMIN_DATA } from "../graphql/queries";
import { useAuth } from "../auth/auth-context";
import { Box, Typography, CircularProgress, Alert, Button } from "@mui/material";

const AdminPage: React.FC = () => {
  const { keycloak } = useAuth();
  const { loading, error, data } = useQuery(GET_ADMIN_DATA);

  const handleLogout = () => {
    keycloak.logout();
  };

  if (loading) {
    return (
      <Box display="flex" justifyContent="center">
      <CircularProgress />
      </Box>
  );
  }

  if (error) {
    return <Alert severity="error">Error: {error.message}</Alert>;
  }

  return (
    <Box p={4}>
    <Typography variant="h4" gutterBottom>
  Admin Dashboard
  </Typography>
  <Typography variant="body1" paragraph>
  Welcome, {keycloak.tokenParsed?.preferred_username}!
  </Typography>

  <Box mt={4}>
  <Typography variant="h6">Admin Data:</Typography>
  <pre>{JSON.stringify(data?.adminData, null, 2)}</pre>
  </Box>

  <Button
  variant="contained"
  color="secondary"
  onClick={handleLogout}
  sx={{ mt: 4 }}
>
  Logout
  </Button>
  </Box>
);
};

export default AdminPage;