import {ApolloClient, createHttpLink, InMemoryCache} from "@apollo/client";
import {setContext} from "@apollo/client/link/context";
import keycloak from "../auth/keycloak";

const httpLink = createHttpLink({
  uri: "http://your-api-url/graphql",
});

const authLink = setContext(async (_, {headers}) => {
  if (keycloak.isTokenExpired(30)) {
    try {
      await keycloak.updateToken(30);
    } catch (error) {
      console.error("Failed to refresh token:", error);
      await keycloak.login();
    }
  }

  return {
    headers: {
      ...headers,
      authorization: keycloak.token ? `Bearer ${keycloak.token}` : "",
    },
  };
});

export const client = new ApolloClient({
  link: authLink.concat(httpLink),
  cache: new InMemoryCache(),
});