package info.setmy.models.web.security;

import static info.setmy.models.web.security.PolicyDirective.DirectiveSourceNames.SELF;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PolicyDirectiveTest {

    PolicyDirective policyDirective;

    public final static String NAME = "script-src";

    @BeforeEach
    public void setUp() {
        policyDirective = new PolicyDirective(NAME);
    }

    @Test
    public void nameForNullNameConstructor() {
        policyDirective = new PolicyDirective();
        assertThat(policyDirective.getName(), is(equalTo("")));
    }

    @Test
    public void makeingString() {
        policyDirective.add(SELF);
        assertThat(policyDirective.toString(), is(equalTo("script-src 'self'")));
    }

    @Test
    public void makeingStringFromMultipleString() {
        policyDirective.add(SELF);
        policyDirective.add("setmy.info");
        policyDirective.add("www.hearandseesystems.com");
        assertThat(policyDirective.toString(), is(equalTo("script-src 'self' setmy.info www.hearandseesystems.com")));
    }

    @Test
    public void clearingFromAddedValuesShouldCreateEmptyString() {
        policyDirective.add(SELF);
        policyDirective.add("setmy.info");
        policyDirective.add("www.hearandseesystems.com");
        policyDirective.clearValues();
        assertThat(policyDirective.toString(), is(equalTo("")));
    }

    @Test
    public void makeingSHA256String() {
        //BASE64: NTM5ZGQwM2JhZTE0NDRmM2NhZDA1ODdiYjZjYWI0NDAwMDgwOWU4ZWJjMmViM2RjZTQ2ZTgwYmYyY2I0ZWYxYg==
        final String sha256 = "539dd03bae1444f3cad0587bb6cab44000809e8ebc2eb3dce46e80bf2cb4ef1b";//alert('Hello world!');
        policyDirective.addSHA256(sha256);
        assertThat(policyDirective.toString(), is(equalTo("script-src 'sha256-NTM5ZGQwM2JhZTE0NDRmM2NhZDA1ODdiYjZjYWI0NDAwMDgwOWU4ZWJjMmViM2RjZTQ2ZTgwYmYyY2I0ZWYxYg=='")));
    }

    @Test
    public void makeingSHA384String() {
        //BASE64: QjM3RjIwMkM5MUE1ODE3NTRDNjdBNTExMTFGRkI3MEU4MkFBNzA5QURFRjEyNDM4ODkwMEEyMzEyNDRGRTg4NjM2MzA1OTg5MDE5QTQzODMyRDQ5NzYwRTIyODQxMzQ5
        final String sha384 = "B37F202C91A581754C67A51111FFB70E82AA709ADEF124388900A231244FE88636305989019A43832D49760E22841349";//alert('Hello world!');
        policyDirective.addSHA384(sha384);
        assertThat(policyDirective.toString(), is(equalTo("script-src 'sha384-QjM3RjIwMkM5MUE1ODE3NTRDNjdBNTExMTFGRkI3MEU4MkFBNzA5QURFRjEyNDM4ODkwMEEyMzEyNDRGRTg4NjM2MzA1OTg5MDE5QTQzODMyRDQ5NzYwRTIyODQxMzQ5'")));
    }

    @Test
    public void makeingSHA512String() {
        //BASE64: QjM3RjIwMkM5MUE1ODE3NTRDNjdBNTExMTFGRkI3MEU4MkFBNzA5QURFRjEyNDM4ODkwMEEyMzEyNDRGRTg4NjM2MzA1OTg5MDE5QTQzODMyRDQ5NzYwRTIyODQxMzQ5
        final String sha512 = "151FBD9FDAD5ED35E42D82A92BD5A13A1075219B9480A389E3DB0CDF68639A44B8E2007E887025CB907814211BF966458BF3B7978CB4901E92AA785CE5D51237";//alert('Hello world!');
        policyDirective.addSHA512(sha512);
        assertThat(policyDirective.toString(), is(equalTo("script-src 'sha512-MTUxRkJEOUZEQUQ1RUQzNUU0MkQ4MkE5MkJENUExM0ExMDc1MjE5Qjk0ODBBMzg5RTNEQjBDREY2ODYzOUE0NEI4RTIwMDdFODg3MDI1Q0I5MDc4MTQyMTFCRjk2NjQ1OEJGM0I3OTc4Q0I0OTAxRTkyQUE3ODVDRTVENTEyMzc='")));
    }

    @Test
    public void makeingNonceString() {
        final String nonce = "2726c7f26c";
        policyDirective.addNonce(nonce);
        assertThat(policyDirective.toString(), is(equalTo("script-src 'nonce-2726c7f26c'")));
    }
}
