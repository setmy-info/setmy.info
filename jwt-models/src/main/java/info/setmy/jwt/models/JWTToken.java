package info.setmy.jwt.models;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import info.setmy.exceptions.ExpiredException;
import info.setmy.exceptions.ForbiddenException;
import info.setmy.jwt.exceptions.NotParsedException;
import info.setmy.jwt.services.JWTService;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Model for creating and verificating (parsing) standard (RFC-7519 :
 * https://tools.ietf.org/html/rfc7519#section-4.1) JWT tokens with claims.
 *
 * sub - Subject
 *
 * aud - Audience
 *
 * jti - JWT ID
 *
 * nbf - Not Before
 *
 * exp - Expiration
 *
 * iat - Issued At
 *
 * TODO :
 * https://connect2id.com/products/nimbus-jose-jwt/examples/jws-with-rsa-signature
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JWTToken {

    private final static String EMPTY_STRING = "";

    private final String serviceName;

    protected final Builder builder;

    // sub - Subject
    private String subject;

    // aud - Audience
    private String[] audience;

    // jti - JWT ID
    private String jwtId;

    // nbf - Not Before
    private Date notBefore;

    // exp - Expiration
    private Date expiresAt;

    // iat - Issued At
    private Date issuedAt;

    // For parsing
    private DecodedJWT jwt;

    private JWTVerifier verifier;

    protected boolean isDirty = true;

    private String jwtString = EMPTY_STRING;

    public JWTToken(final String serviceName) {
        this.serviceName = serviceName;
        builder = createBuilder();
    }

    protected void setClaims() {
    }

    @Override
    public String toString() {
        if (isDirty) {
            setClaims();
            setRFCClaims();
            jwtString = sign();
            makeClean();
        }
        return jwtString;
    }

    protected void setRFCClaims() {
        builder.withIssuer(getIssuer())
                .withSubject(getSubject())
                .withIssuedAt(getIssuedAt())
                .withAudience(getAudience())
                .withJWTId(getJwtId())
                .withNotBefore(getNotBefore())
                .withExpiresAt(getExpiresAt());
    }

    protected String sign() {
        return builder.sign(getAlgorithm());
    }

    public void setClaim(final String key, final Boolean aBoolean) {
        builder.withClaim(key, aBoolean);
    }

    public void setClaim(final String key, final Integer aInteger) {
        builder.withClaim(key, aInteger);
    }

    public void setClaim(final String key, final Long aLong) {
        builder.withClaim(key, aLong);
    }

    public void setClaim(final String key, final Double aDouble) {
        builder.withClaim(key, aDouble);
    }

    public void setClaim(final String key, final String aString) {
        builder.withClaim(key, aString);
    }

    public void setClaim(final String key, final Date aDate) {
        builder.withClaim(key, aDate);
    }

    public void setClaim(final String key, final String[] strings) {
        builder.withArrayClaim(key, strings);
    }

    public String getClaimAsString(final String key) {
        return getClaimObject(key).asString();
    }

    public String[] getClaimAsStringArray(final String key) {
        return getClaimObject(key).asArray(String.class);
    }

    public Boolean getClaimAsBoolean(final String key) {
        return getClaimObject(key).asBoolean();
    }

    public Date getClaimAsDate(final String key) {
        return getClaimObject(key).asDate();
    }

    public Double getClaimAsDouble(final String key) {
        return getClaimObject(key).asDouble();
    }

    public Integer getClaimAsInteger(final String key) {
        return getClaimObject(key).asInt();
    }

    public Long getClaimAsLong(final String key) {
        return getClaimObject(key).asLong();
    }

    private Claim getClaimObject(final String key) {
        validateForParsing();
        final Claim cl = jwt.getClaim(key);
        return cl;
    }

    void validateForParsing() {
        if (this.jwt == null) {
            throw new NotParsedException("Token is not parsed!");
        }
    }

    private Builder createBuilder() {
        return getService().create();
    }

    protected Algorithm getAlgorithm() {
        return getService().getAlgorithm();
    }

    public Date now() {
        return new Date();
    }

    public String getKey() {
        return getService().getKey();
    }

    private JWTService getService() {
        return JWTService.instance(serviceName);
    }

    public String getIssuer() {
        return getService().getIssuer();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        makeDirty();
        this.subject = subject;
    }

    public String[] getAudience() {
        return audience;
    }

    public void setAudience(final String[] audience) {
        makeDirty();
        this.audience = audience;
    }

    public String getJwtId() {
        return jwtId;
    }

    public UUID getUUIDJwtId() {
        return UUID.fromString(jwtId);
    }

    public void setJwtId(final String jwtId) {
        makeDirty();
        this.jwtId = jwtId;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(final Date notBefore) {
        makeDirty();
        this.notBefore = notBefore;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(final Date expiresAt) {
        makeDirty();
        this.expiresAt = expiresAt;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(final Date issuedAt) {
        makeDirty();
        this.issuedAt = issuedAt;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void parse(final String tokenString) {
        try {
            verifier = getVerifier();
            setJwt(verifier.verify(tokenString));
            populate(getJwt());
        } catch (InvalidClaimException exception) {
            throw new ForbiddenException(exception.toString());
        } catch (TokenExpiredException exception) {
            throw new ExpiredException(exception.toString());
        } catch (JWTVerificationException exception) {
            throw new ForbiddenException(exception.toString());
        }
    }

    private JWTVerifier getVerifier() {
        if (verifier != null) {
            return verifier;
        }
        final Algorithm algorithm = getAlgorithm();
        verifier = JWT.require(algorithm)
                .withIssuer(getIssuer())
                .acceptExpiresAt(1) // exp
                .acceptNotBefore(1) // nbf
                .acceptIssuedAt(1) // iat
                .build();
        return verifier;
    }

    protected void populate(final DecodedJWT jwt) {
        setSubject(jwt.getSubject());
        if (jwt.getAudience() != null) {
            setAudience(getClaimAsStringArray("aud"));
        }
        setJwtId(jwt.getId());
        setNotBefore(jwt.getNotBefore());
        setExpiresAt(jwt.getExpiresAt());
        setIssuedAt(jwt.getIssuedAt());
    }

    public void setDefaults() {
        setDefaultDates();
    }

    private void setDefaultDates() {
        final Date now = this.now();
        setNotBefore(now);
        setIssuedAt(now);
        setExpiresAt(dateAddMinutes(now, getExpirationMinutes()));
    }

    public void setUUIDJwtId() {
        this.setJwtId(newUUIDString());
    }

    String newUUIDString() {
        final UUID uuid = newUUID();
        return uuid.toString();
    }

    UUID newUUID() {
        final UUID uuid = UUID.randomUUID();
        return uuid;
    }

    public Date dateAddMinutes(final Date now, final int minutes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    Integer getExpirationMinutes() {
        return getService().getExpirationMinutes();
    }

    private DecodedJWT getJwt() {
        return jwt;
    }

    private void setJwt(final DecodedJWT jwt) {
        this.jwt = jwt;
    }

    protected void makeClean() {
        isDirty = false;
    }

    protected void makeDirty() {
        isDirty = true;
        this.jwtString = EMPTY_STRING;
    }
}
