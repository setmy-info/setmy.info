package info.setmy.models.web.security;

import info.setmy.models.VariableValue;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ContentSecurityPolicy extends ArrayList<PolicyDirective> {

    public static final String NAME = "Content-Security-Policy";

    public VariableValue getVariableValue() {
        return new VariableValue(NAME, toString());
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        String add = null;
        for (PolicyDirective policy : this) {
            if (!policy.isEmpty()) {
                if (add == null) {
                    add = "; ";
                } else {
                    stringBuilder.append(add);
                }
                stringBuilder.append(policy.toString());
            }
        }
        return stringBuilder.toString();
    }

    public PolicyDirective newPolicyDirective(final String name) {
        final Optional<PolicyDirective> optionalPolicyDirective = addPolicyDirective(new PolicyDirective(name));
        return optionalPolicyDirective.get();
    }

    public Optional<PolicyDirective> addPolicyDirective(final PolicyDirective policyDirective) {
        final Optional<PolicyDirective> optional = Optional.of(policyDirective);
        final boolean addResult = add(optional.get());
        if (addResult) {
            return optional;
        }
        return Optional.empty();
    }

    public Optional<PolicyDirective> getByDirectiveName(final String directiveName) {
        return this.stream().filter((directive -> directive.getName().equalsIgnoreCase(directiveName))).findFirst();
    }
}
