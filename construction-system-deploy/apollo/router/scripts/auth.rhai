load "scripts/utils.rhai";

fn get_frontend_roles(claims) {
    const RESOURCE_ACCESS = "resource_access";
    const CONSTRUCTION_SYSTEM = "construction_system";
    const ROLES = "roles";

    if !claims.has(RESOURCE_ACCESS) {
        return [];
    }

    let resource_access = claims.get(RESOURCE_ACCESS);

    if !resource_access.has(CONSTRUCTION_SYSTEM) {
        return [];
    }

    let construction_system = resource_access.get(CONSTRUCTION_SYSTEM);

    construction_system.get(ROLES) ?? []
}

fn process_request(request) {
    let claims = request.context.jwt.claims;

    request.headers.remove("Authorization");

    if claims.has("sub") {
        request.headers["X-User-Id"] = claims.get("sub").to_string();
    }

    const EMAIL_CLAIM = "email";
    if claims.has(EMAIL_CLAIM) {
        request.headers["X-User-Email"] = claims.get(EMAIL_CLAIM).to_string();
    }

    let roles = get_frontend_roles(claims);
    if roles.len() > 0 {
        request.headers["X-User-Roles"] = roles.join(",");
    }
}