package info.setmy.services;

import info.setmy.exceptions.web.ForbiddenWebException;
import info.setmy.models.VariableValue;
import info.setmy.models.web.security.ContentSecurityPolicy;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.BASE_URI;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.BLOCK_ALL_MIXED_CONTENT;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.CONNECT_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.DEFAULT_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.FRAME_ANCESTORS;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.SCRIPT_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveSourceNames.SELF;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.STYLE_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveSourceNames.NONE;
import static info.setmy.models.web.security.PolicyDirective.DirectiveSourceNames.UNSAFE_EVAL;
import static info.setmy.models.web.security.PolicyDirective.DirectiveSourceNames.UNSAFE_INLINE;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class HttpService {

    public static final String DEFAULT_JWT_COOKIE_NAME = "JWT";

    public static final String DEFAULT_CSRF_COOKIE_NAME = "CSRF";

    public static final String X_CSRF_TOKEN_HEADER = "X-Csrf-Token";

    public static final int SESSION_COOKIE_MAX_AGE = -1;

    public static final int DELETABLE_COOKIE_MAX_AGE = 0;

    private static final int MINUTE_SECONDS = 60;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    private String jwtCookieName = DEFAULT_JWT_COOKIE_NAME;

    private String csrfCookieName = DEFAULT_CSRF_COOKIE_NAME;

    private String csrfHeaderName = X_CSRF_TOKEN_HEADER;

    private boolean sslCookies = true;

    public final static ContentSecurityPolicy DEFAULT_POLICIES = new ContentSecurityPolicy();

    static {
        DEFAULT_POLICIES.newPolicyDirective(DEFAULT_SRC).add(UNSAFE_INLINE).add(SELF);
        DEFAULT_POLICIES.newPolicyDirective(FRAME_ANCESTORS).add(NONE);
        DEFAULT_POLICIES.newPolicyDirective(BASE_URI).add(SELF);
        DEFAULT_POLICIES.newPolicyDirective(SCRIPT_SRC).add(UNSAFE_INLINE).add(UNSAFE_EVAL).add(SELF);
        DEFAULT_POLICIES.newPolicyDirective(STYLE_SRC).add(UNSAFE_INLINE).add(SELF);
        DEFAULT_POLICIES.newPolicyDirective(BLOCK_ALL_MIXED_CONTENT);
        DEFAULT_POLICIES.newPolicyDirective(CONNECT_SRC).add(SELF);
    }

    public HttpService(final HttpServletRequest request, final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void setHeader(final ContentSecurityPolicy contentSecurityPolicy) {
        setHeader(contentSecurityPolicy.getVariableValue());
    }

    public void setHeader(final VariableValue variableValue) {
        response.setHeader(variableValue.getName(), variableValue.getValue());
    }

    public void setHeader(final String headerName, final String headerValue) {
        response.setHeader(headerName, headerValue);
    }

    public void checkCSRFCookie() {
        // TODO : CSRF check todo.
        throw new ForbiddenWebException("http.wronf.csrf");
    }

    public String getJWTCookieString() {
        final Optional<Cookie> cookie = getJWTCookie();
        if (cookie.isPresent()) {
            return cookie.get().getValue();
        }
        throw new ForbiddenWebException("http.service.noJWTCookie");
    }

    public String getCSRFCookieString() {
        final Optional<Cookie> cookie = getCSRFCookie();
        if (cookie.isPresent()) {
            return cookie.get().getValue();
        }
        throw new ForbiddenWebException("http.service.noCSRFCookie");
    }

    public void deleteJWTCookie() {
        final Cookie cookie = setCookieForDeletion(newJWTCookie(""));
        addCookie(cookie);
    }

    public void setCSRFCookie(final String cookiePayload) {
        final Cookie cookie = newCSRFCookie(cookiePayload);
        addCookie(cookie);
    }

    public void setJWTCookie(final String cookiePayload) {
        final Cookie cookie = newJWTCookie(cookiePayload);
        addCookie(cookie);
    }

    public void addCookie(final Cookie cookie) {
        response.addCookie(cookie);
    }

    public Cookie newCSRFCookie(final String cookiePayload) {
        final Cookie csrfCookie = createDefaultCookie(csrfCookieName, cookiePayload);
        csrfCookie.setHttpOnly(false);// Override defaults: is accessable from JS
        return csrfCookie;
    }

    public Cookie newJWTCookie(final String cookiePayload) {
        final Cookie jwtCookie = createDefaultCookie(jwtCookieName, cookiePayload);
        return jwtCookie;
    }

    public Cookie createDefaultCookie(final String name, final String value) {
        final Cookie cookie = setCookieForSession(new Cookie(name, value));
        cookie.setHttpOnly(true);// No access from JS
        cookie.setSecure(isSslCookies());// Only https or not. By default true
        cookie.setDomain(getRequestedServerName());// from requested domain
        cookie.setPath("/");//all paths
        return cookie;
    }

    public Cookie setCookieExpirationMinutes(final Cookie cookie, final int minutes) {
        cookie.setMaxAge(minutes * MINUTE_SECONDS);
        return cookie;
    }

    public Cookie setCookieForSession(final Cookie cookie) {
        cookie.setMaxAge(SESSION_COOKIE_MAX_AGE);
        return cookie;
    }

    public Cookie setCookieForDeletion(final Cookie cookie) {
        cookie.setMaxAge(DELETABLE_COOKIE_MAX_AGE);
        return cookie;
    }

    public Optional<Cookie> getJWTCookie() {
        return getCookie(jwtCookieName);
    }

    public Optional<Cookie> getCSRFCookie() {
        return getCookie(csrfCookieName);
    }

    public Optional<Cookie> getCookie(final String cookieName) {
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    final Optional<String> cookieValue = toBlankTrimOptional(cookie.getValue());
                    if (cookieValue.isPresent()) {
                        return Optional.of(cookie);
                    }
                }
            }
        }
        return Optional.ofNullable(null);
    }

    public void setResponseStatus(final int statusCode) {
        response.setStatus(statusCode);
    }

    public String getRemoteIp() {
        final Optional<String> ip = getHeader("X-Real-IP");
        if (ip.isPresent()) {
            return ip.get();
        }
        return request.getRemoteAddr();
    }

    public String getRequestedServerName() {
        final Optional<String> hostName = getHeader("X-Forwarded-Host");
        if (hostName.isPresent()) {
            return hostName.get();
        }
        return getServerName();
    }

    public String getServerName() {
        return request.getServerName();
    }

    public Optional<String> getOrigin() {
        return getHeader("Origin");
    }

    public Optional<String> getReferer() {
        return getHeader("Referer");
    }

    public Optional<String> getUserAgent() {
        return getHeader("User-Agent");
    }

    public Optional<String> getCSRFHeader() {
        return getHeader(csrfHeaderName);
    }

    public Optional<String> getHeader(final String headerName) {
        return toBlankTrimOptional(request.getHeader(headerName));
    }

    public Optional<String> toBlankTrimOptional(final String in) {
        if (in == null) {
            return Optional.ofNullable(null);
        }
        final String trimmedIn = in.trim();
        if (trimmedIn.isEmpty()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(trimmedIn);
    }

    public void setDefaultHeaders() {
        setHeader("X-Frame-Options", "DENY");
        setHeader("Referrer-Policy", "no-referrer");
        setHeader("X-XSS-Protection", "1; mode=block");
        setHeader("X-Content-Type-Options", "nosniff");
        setHeader("Strict-Transport-Security", "max-age=86400; includeSubDomains");
        setHeader(getDefaultPolicies());
    }

    public ContentSecurityPolicy getDefaultPolicies() {
        return DEFAULT_POLICIES;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public String getJwtCookieName() {
        return jwtCookieName;
    }

    public void setJwtCookieName(String jwtCookieName) {
        this.jwtCookieName = jwtCookieName;
    }

    public String getCsrfCookieName() {
        return csrfCookieName;
    }

    public void setCsrfCookieName(String csrfCookieName) {
        this.csrfCookieName = csrfCookieName;
    }

    public boolean isSslCookies() {
        return sslCookies;
    }

    public void setSslCookies(boolean sslCookies) {
        this.sslCookies = sslCookies;
    }

    public String getCsrfHeaderName() {
        return csrfHeaderName;
    }

    public void setCsrfHeaderName(String csrfHeaderName) {
        this.csrfHeaderName = csrfHeaderName;
    }
}
