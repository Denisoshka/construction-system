import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:9090/auth",
  realm: "myrealm",
  clientId: "frontend",
});

export default keycloak;