import Keycloak from 'keycloak-js';

export const keycloak = new Keycloak({
  url: 'http://localhost:9090/auth',
  realm: 'myrealm',
  clientId: 'frontend'
});

await keycloak.init(
  {onLoad: "check-sso", checkLoginIframe: false}
).then(authenticated => {
  if (authenticated) {
    console.log('User is authenticated');
  } else {
    console.log('User is not authenticated');
  }

}).catch(error => console.error('Failed to initialize adapter:', error));

