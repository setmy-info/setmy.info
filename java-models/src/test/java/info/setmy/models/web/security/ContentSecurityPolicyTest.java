package info.setmy.models.web.security;

import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.DEFAULT_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.SCRIPT_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveNames.STYLE_SRC;
import static info.setmy.models.web.security.PolicyDirective.DirectiveSourceNames.SELF;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ContentSecurityPolicyTest {

    ContentSecurityPolicy contentSecurityPolicy;
    PolicyDirective policyDirective;

    @BeforeEach
    public void setUp() {
        contentSecurityPolicy = spy(new ContentSecurityPolicy());
        policyDirective = new PolicyDirective(PolicyDirectiveTest.NAME);
    }

    @Test
    public void nameConstantShouldBeCorrect() {
        assertThat(ContentSecurityPolicy.NAME).isEqualTo("Content-Security-Policy");
    }

    @Test
    public void nameShouldBeCorrect() {
        assertThat(contentSecurityPolicy.getVariableValue().getName()).isEqualTo(ContentSecurityPolicy.NAME);
    }

    @Test
    public void full() {
        contentSecurityPolicy.newPolicyDirective("abc").
                addNonce("def").
                add("ghi").
                add("jkl");
        contentSecurityPolicy.newPolicyDirective(SCRIPT_SRC).
                addNonce("2726c7f26c").
                add(SELF).
                add("setmy.info");
        contentSecurityPolicy.newPolicyDirective("mno").
                addNonce("prs").
                add("tuv").
                add("xyz");
        contentSecurityPolicy.newPolicyDirective(STYLE_SRC).
                addNonce("2726c7f26c").
                add(SELF).
                add("setmy.info");
        contentSecurityPolicy.getByDirectiveName("abc").get().clearValues();//Removing one directive and result string should bee without;
        contentSecurityPolicy.getByDirectiveName("mno").get().clearValues();
        assertThat(contentSecurityPolicy.getVariableValue().getValue()).isEqualTo(contentSecurityPolicy.toString());
        assertThat(contentSecurityPolicy.toString()).isEqualTo("script-src 'nonce-2726c7f26c' 'self' setmy.info; style-src 'nonce-2726c7f26c' 'self' setmy.info");
        assertTrue(contentSecurityPolicy.getByDirectiveName(SCRIPT_SRC).isPresent());
        assertTrue(contentSecurityPolicy.getByDirectiveName(STYLE_SRC).isPresent());
        assertFalse(contentSecurityPolicy.getByDirectiveName(DEFAULT_SRC).isPresent());
    }

    @Test
    public void addingNullPolicyDirective() {
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> contentSecurityPolicy.addPolicyDirective(null),
                "Expected exception, but it didn't throw"
        );
        assertNull(thrown.getMessage());
    }

    @Test
    public void addingDoesnNotSucceed() {
        doReturn(false).when(contentSecurityPolicy).add(policyDirective);

        final Optional<PolicyDirective> resultOptional = contentSecurityPolicy.addPolicyDirective(policyDirective);

        assertFalse(resultOptional.isPresent());
    }
}
