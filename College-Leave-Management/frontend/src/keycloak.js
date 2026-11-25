
import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
  url: 'http://localhost:8180',
  realm: 'campusleave',
  clientId: 'campusleave-frontend',
})

let initPromise

export function initializeKeycloak() {
  if (!initPromise) {
    initPromise = keycloak.init({
      onLoad: 'check-sso',
      pkceMethod: 'S256',
      checkLoginIframe: false,
    })
  }

  return initPromise
}

export default keycloak
