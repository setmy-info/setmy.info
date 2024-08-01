package info.setmy.web.services;

import info.setmy.exceptions.web.ForbiddenWebException;
import static info.setmy.web.services.HttpService.DEFAULT_CSRF_COOKIE_NAME;
import static info.setmy.web.services.HttpService.DEFAULT_JWT_COOKIE_NAME;
import static info.setmy.web.services.HttpService.DELETABLE_COOKIE_MAX_AGE;
import static info.setmy.web.services.HttpService.SESSION_COOKIE_MAX_AGE;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class HttpServiceTest {

    private HttpService httpService;

    private HttpServletRequest request;

    private HttpServletResponse response;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        httpService = spy(new HttpService(request, response));
    }

    @Test
    public void someDefaults() {
        assertThat(httpService.getJwtCookieName()).isEqualTo("JWT");
        assertThat(httpService.getCsrfCookieName()).isEqualTo("CSRF");
        assertThat(httpService.getCsrfHeaderName()).isEqualTo("X-Csrf-Token");
    }

    @Test
    public void setHeader_shouldSetHeaderNameAndValue() {
        final String name = "Name";
        final String value = "Value";
        httpService.setHeader(name, value);
        verify(response).setHeader(name, value);
    }

    @Test
    public void setCookieExpirationMinutes() {
        final String name = "nameX";
        final String value = "valueX";
        final Cookie cookie = new Cookie(name, value);
        final int minutes = 15;
        final int expectedSeconds = minutes * 60;

        final Cookie resultCookie = httpService.setCookieExpirationMinutes(cookie, minutes);

        assertThat(resultCookie.getMaxAge()).isEqualTo(expectedSeconds);
    }

    @Test
    public void getJWTCookieString_shouldThrowExceptionWhenCookieIsNotFound() {
        final Optional<Cookie> cookie = Optional.ofNullable(null);
        doReturn(cookie).when(httpService).getJWTCookie();
        Throwable exception = Assertions.assertThrows(ForbiddenWebException.class, () -> {
            httpService.getJWTCookieString();
        });
        assertEquals("http.service.noJWTCookie", exception.getMessage());
    }

    @Test
    public void getJWTCookieString_shouldReturnCookieValue() {
        final String cookieValue = "This is Hello World Cookie!";
        final Cookie cookie = new Cookie(DEFAULT_JWT_COOKIE_NAME, cookieValue);
        final Optional<Cookie> cookieOptional = Optional.of(cookie);
        doReturn(cookieOptional).when(httpService).getJWTCookie();

        final String result = httpService.getJWTCookieString();

        assertThat(result).isEqualTo(cookieValue);
    }

    @Test
    public void getJWTCookie() {
        setTestRequestHeaders();

        final Optional<Cookie> jwtCookie = httpService.getJWTCookie();

        assertThat(jwtCookie.get().getValue()).isEqualTo("jwt.cookie.value");
    }

    @Test
    public void getCSRFCookie() {
        setTestRequestHeaders();

        final Optional<Cookie> jwtCookie = httpService.getCSRFCookie();

        assertThat(jwtCookie.get().getValue()).isEqualTo("csrf.cookie.value");
    }

    @Test
    public void getCSRFCookieString_shouldThrowExceptionWhenCookieIsNotFound() {
        final Optional<Cookie> cookie = Optional.ofNullable(null);
        doReturn(cookie).when(httpService).getCSRFCookie();
        Throwable exception = Assertions.assertThrows(ForbiddenWebException.class, () -> {
            httpService.getCSRFCookieString();
        });
        assertEquals("http.service.noCSRFCookie", exception.getMessage());
    }

    @Test
    public void getCSRFCookieString_shouldReturnCookieValue() {
        final String cookieValue = "This is Hello World Cookie!";
        final Cookie cookie = new Cookie(DEFAULT_JWT_COOKIE_NAME, cookieValue);
        final Optional<Cookie> cookieOptional = Optional.of(cookie);
        doReturn(cookieOptional).when(httpService).getCSRFCookie();

        final String result = httpService.getCSRFCookieString();

        assertThat(result).isEqualTo(cookieValue);
    }

    @Test
    public void getHeader_shouldNotPresentWhenNullIsGotFromHeaders() {
        String headerName = "abc";
        String value = null;
        when(request.getHeader(headerName)).thenReturn(value);

        Optional<String> result = httpService.getHeader(headerName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getHeader_shouldNotPresentWhenEmptyStringIsGotFromHeaders() {
        String headerName = "abc";
        String value = "";
        when(request.getHeader(headerName)).thenReturn(value);

        Optional<String> result = httpService.getHeader(headerName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getHeader_shouldNotPresentWhenEmptyString2IsGotFromHeaders() {
        String headerName = "abc";
        String value = "  ";
        when(request.getHeader(headerName)).thenReturn(value);

        Optional<String> result = httpService.getHeader(headerName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getHeader_shouldPresentWhenNonEmptyStringIsGotFromHeaders() {
        String headerName = "abc";
        String value = "x";
        when(request.getHeader(headerName)).thenReturn(value);

        Optional<String> result = httpService.getHeader(headerName);

        assertThat(result.isPresent()).isEqualTo(true);
    }

    @Test
    public void getCookie_shouldNotPresentWhenThereIsNullCookiesArray() {
        final String cookieName = "expected cookie name";
        final Cookie[] cookies = null;
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getCookie_shouldNotPresentWhenThereIsEmptyCookiesArray() {
        final String cookieName = "expected cookie name";
        final Cookie[] cookies = new Cookie[0];
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getCookie_shouldNotPresentWhenThereIsNonEemptyListButExpectedCookieIsMissingInArray() {
        final String cookieName = "expected_name";
        final Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie("x", "value");
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getCookie_shouldNotPresentWhenThereIsExpectedCookieButWithEmptyValueInArray_1() {
        final String cookieName = "expected_name";
        final Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie(cookieName, "");
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getCookie_shouldNotPresentWhenThereIsExpectedCookieButWithEmptyValueInArray_2() {
        final String cookieName = "expected_name";
        final Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie(cookieName, "  ");
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getCookie_shouldNotPresentWhenThereIsExpectedCookieButWithEmptyValueInArray_3() {
        final String cookieName = "expected_name";
        final Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie(cookieName, null);
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(false);
    }

    @Test
    public void getCookie_shouldPresentWhenThereIsExpectedCookieInArray() {
        final String cookieName = "expected_name";
        final Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie(cookieName, "value");
        when(request.getCookies()).thenReturn(cookies);

        final Optional<Cookie> result = httpService.getCookie(cookieName);

        assertThat(result.isPresent()).isEqualTo(true);
    }

    @Test
    public void sessionCookieMaxAge() {
        assertThat(SESSION_COOKIE_MAX_AGE).isEqualTo(-1);
    }

    @Test
    public void deletableCookieMaxAge() {
        assertThat(DELETABLE_COOKIE_MAX_AGE).isEqualTo(0);
    }

    @Test
    public void createDefaultCookie_requirements() {
        final String name = "nameX";
        final String value = "valueX";
        final String serverName = "server.name";
        when(request.getHeader("X-Forwarded-Host")).thenReturn(serverName);

        final Cookie cookie = httpService.createDefaultCookie(name, value);

        assertNotNull(cookie);
        assertThat(cookie.getName()).isEqualTo(name);
        assertThat(cookie.getValue()).isEqualTo(value);
        assertThat(cookie.isHttpOnly()).isEqualTo(true);
        assertThat(cookie.getSecure()).isEqualTo(true);
        assertThat(cookie.getMaxAge()).isEqualTo(SESSION_COOKIE_MAX_AGE);
        assertThat(cookie.getDomain()).isEqualTo(serverName);
        assertThat(cookie.getPath()).isEqualTo("/");
    }

    @Test
    public void setCookieForDeletion() {
        final String name = "nameX";
        final String value = "valueX";
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(123);

        final Cookie resultCookie = httpService.setCookieForDeletion(cookie);

        assertNotNull(resultCookie);
        assertThat(resultCookie.getMaxAge()).isEqualTo(DELETABLE_COOKIE_MAX_AGE);
    }

    @Test
    public void setCookieForSession() {
        final String name = "nameX";
        final String value = "valueX";
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(123);

        final Cookie resultCookie = httpService.setCookieForSession(cookie);

        assertNotNull(resultCookie);
        assertThat(resultCookie.getMaxAge()).isEqualTo(SESSION_COOKIE_MAX_AGE);
    }

    @Test
    public void newJWTCookie() {
        final String value = "valueX";
        final String serverName = "server.name";
        when(request.getHeader("X-Forwarded-Host")).thenReturn(serverName);

        final Cookie cookie = httpService.newJWTCookie(value);

        assertNotNull(cookie);
        assertThat(cookie.getName()).isEqualTo(DEFAULT_JWT_COOKIE_NAME);
        assertThat(cookie.getValue()).isEqualTo(value);
        assertThat(cookie.isHttpOnly()).isEqualTo(true);
        assertThat(cookie.getSecure()).isEqualTo(true);
        assertThat(cookie.getMaxAge()).isEqualTo(SESSION_COOKIE_MAX_AGE);
        assertThat(cookie.getDomain()).isEqualTo(serverName);
        assertThat(cookie.getPath()).isEqualTo("/");
    }

    @Test
    public void newCSRFCookie() {
        final String value = "valueX";
        final String serverName = "server.name";
        when(request.getHeader("X-Forwarded-Host")).thenReturn(serverName);

        final Cookie cookie = httpService.newCSRFCookie(value);

        assertNotNull(cookie);
        assertThat(cookie.getName()).isEqualTo(DEFAULT_CSRF_COOKIE_NAME);
        assertThat(cookie.getValue()).isEqualTo(value);
        assertThat(cookie.isHttpOnly()).isEqualTo(false);// Should be readable from JS
        assertThat(cookie.getSecure()).isEqualTo(true);
        assertThat(cookie.getMaxAge()).isEqualTo(SESSION_COOKIE_MAX_AGE);
        assertThat(cookie.getDomain()).isEqualTo(serverName);
        assertThat(cookie.getPath()).isEqualTo("/");
    }

    @Test
    public void getRequestedServerName_shouldTakeValueFromHeaderFirst() {
        final String serverName = "   server.name   ";
        when(request.getHeader("X-Forwarded-Host")).thenReturn(serverName);
        when(request.getServerName()).thenReturn("srfgdfyhsreursghqw0rei");

        final String resultName = httpService.getRequestedServerName();

        assertThat(resultName).isEqualTo("server.name");
    }

    @Test
    public void getRequestedServerName_shouldTakeValueFromRequestWhenHeaderIsMissing() {
        final String serverName = null;
        when(request.getHeader("X-Forwarded-Host")).thenReturn(serverName);
        when(request.getServerName()).thenReturn("server.name.from.request");

        final String resultName = httpService.getRequestedServerName();

        assertThat(resultName).isEqualTo("server.name.from.request");
    }

    @Test
    public void getRemoteIp_shouldTakeValueFromHeaderFirst() {
        final String clientIp = "   192.168.40.10   ";
        when(request.getHeader("X-Real-IP")).thenReturn(clientIp);
        when(request.getRemoteAddr()).thenReturn("11.11.11.11");

        final String resultIp = httpService.getRemoteIp();

        assertThat(resultIp).isEqualTo("192.168.40.10");
    }

    @Test
    public void getRemoteIp_shouldTakeValueFromRequestWhenHeaderIsMissing() {
        final String clientIp = null;
        when(request.getHeader("X-Real-IP")).thenReturn(clientIp);
        when(request.getRemoteAddr()).thenReturn("192.168.40.10");

        final String resultIp = httpService.getRemoteIp();

        assertThat(resultIp).isEqualTo("192.168.40.10");
    }

    @Test
    public void deleteJWTCookie() {
        final Cookie cookie = new Cookie("a", "b");
        cookie.setMaxAge(1000);
        doReturn(cookie).when(httpService).newJWTCookie("");

        httpService.deleteJWTCookie();

        verify(httpService).newJWTCookie("");
        verify(httpService).setCookieForDeletion(cookie);
        verify(httpService).addCookie(cookie);
    }

    @Test
    public void setCSRFCookie() {
        final Cookie cookie = new Cookie("a", "b");
        final String cookiePayload = "data";
        doReturn(cookie).when(httpService).newCSRFCookie(cookiePayload);

        httpService.setCSRFCookie(cookiePayload);

        verify(httpService).newCSRFCookie(cookiePayload);
        verify(httpService).addCookie(cookie);
    }

    @Test
    public void setJWTCookie() {
        final Cookie cookie = new Cookie("a", "b");
        final String cookiePayload = "data";
        doReturn(cookie).when(httpService).newJWTCookie(cookiePayload);

        httpService.setJWTCookie(cookiePayload);

        verify(httpService).newJWTCookie(cookiePayload);
        verify(httpService).addCookie(cookie);
    }

    @Test
    public void addCookie() {
        final Cookie cookie = new Cookie("a", "b");

        httpService.addCookie(cookie);

        verify(response).addCookie(cookie);
    }

    @Test
    public void getOrigin() {
        setTestRequestHeaders();

        final Optional<String> origin = httpService.getOrigin();

        assertThat(origin.get()).isEqualTo("origin.address");
    }

    @Test
    public void getReferer() {
        setTestRequestHeaders();

        final Optional<String> referer = httpService.getReferer();

        assertThat(referer.get()).isEqualTo("referrer.address");
    }

    @Test
    public void getUserAgent() {
        setTestRequestHeaders();

        final Optional<String> userAgent = httpService.getUserAgent();

        assertThat(userAgent.get()).isEqualTo("Mozilla/5.0 (X11; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0");
    }

    @Test
    public void getCSRFHeader() {
        setTestRequestHeaders();

        final Optional<String> csrfHeaderValue = httpService.getCSRFHeader();

        assertThat(csrfHeaderValue.get()).isEqualTo("csrf.value");
    }

    @Test
    public void setResponseStatus() {
        final int statusCode = 123;

        httpService.setResponseStatus(statusCode);

        verify(httpService).setResponseStatus(statusCode);
        verify(response).setStatus(statusCode);
        verifyNoMoreInteractions(response);
        verifyNoMoreInteractions(httpService);
    }

    void setTestRequestHeaders() {
        final Cookie[] cookies = new Cookie[2];
        cookies[0] = new Cookie("JWT", "jwt.cookie.value");
        cookies[1] = new Cookie("CSRF", "csrf.cookie.value");
        when(request.getCookies()).thenReturn(cookies);
        when(request.getHeader("Origin")).thenReturn("origin.address");
        when(request.getHeader("Referer")).thenReturn("referrer.address");
        when(request.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (X11; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0");
        when(request.getHeader("Host")).thenReturn("host.address");
        when(request.getHeader("X-Csrf-Token")).thenReturn("csrf.value");
    }
}
