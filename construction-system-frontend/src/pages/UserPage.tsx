import React from "react";
import {useQuery} from "@apollo/client";
import {GET_USER_DATA} from "../graphql/queries";
import {useAuth} from "../auth/auth-context";
import {Alert, Box, Button, CircularProgress, Typography} from "@mui/material";

const UserPage: React.FC = () => {
  const {keycloak} = useAuth();
  const {loading, error, data} = useQuery(GET_USER_DATA);

  const handleLogout = () => {
    keycloak.logout();
  };

  if (loading) {
    return (
      <Box display="flex" justifyContent="center">
        <CircularProgress/>
      </Box>
    );
  }

  if (error) {
    return <Alert severity="error">Error: {error.message}</Alert>;
  }

  return (
    <Box p={4}>
      <Typography variant="h4" gutterBottom>
        User Dashboard
      </Typography>
      <Typography variant="body1">
        Welcome, {keycloak.tokenParsed?.preferred_username}!
      </Typography>

      <Box mt={4}>
        <Typography variant="h6">Your Data:</Typography>
        <pre>{JSON.stringify(data?.user, null, 2)}</pre>
      </Box>

      <Button
        variant="contained"
        color="secondary"
        onClick={handleLogout}
        sx={{mt: 4}}
      >
        Logout
      </Button>
    </Box>
  );
};

export default UserPage;