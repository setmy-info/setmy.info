package info.setmy.jwt.models;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 *
 * Model for creating and verificating (parsing) extended (non-RFC part) JWT tokens with claims.
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExtendedJWTToken extends JWTToken {

    public static final String UID = "uid";

    public static final String ROLES = "rls";

    public static final String FIRST_NAME = "fnm";

    public static final String MIDDLE_NAME = "mnm";

    public static final String LAST_NAME = "lnm";

    // uid - User ID
    private String userId;

    // rls - user roles
    private String[] roles;

    // fnm - non standard
    private String firstName;

    // mnm - non standard
    private String middleName;

    // lnm - non standard
    private String lastName;

    public ExtendedJWTToken(final String serviceName) {
        super(serviceName);
    }

    @Override
    protected void setClaims() {
        setClaim(UID, getUserId());
        setClaim(ROLES, getRoles());
        setClaim(FIRST_NAME, getFirstName());
        setClaim(MIDDLE_NAME, getMiddleName());
        setClaim(LAST_NAME, getLastName());
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void populate(final DecodedJWT jwt) {
        super.populate(jwt);
        setUserId(jwt.getClaim(UID).asString());
        setRoles(jwt.getClaim(ROLES).asArray(String.class));
        setFirstName(jwt.getClaim(FIRST_NAME).asString());
        setMiddleName(jwt.getClaim(MIDDLE_NAME).asString());
        setLastName(jwt.getClaim(LAST_NAME).asString());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        makeDirty();
        this.userId = userId;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(final String[] roles) {
        makeDirty();
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        makeDirty();
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        makeDirty();
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        makeDirty();
        this.lastName = lastName;
    }
}
