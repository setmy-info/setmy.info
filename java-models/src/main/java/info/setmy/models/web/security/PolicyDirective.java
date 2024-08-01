package info.setmy.models.web.security;

import java.util.Base64;
import info.setmy.models.VariableValues;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class PolicyDirective extends VariableValues {

    public class DirectiveNames {

        public static final String CONNECT_SRC = "connect-src";
        public static final String DEFAULT_SRC = "default-src";
        public static final String FONT_SRC = "font-src";
        public static final String FRAME_SRC = "frame-src";
        public static final String IMG_SRC = "img-src";
        public static final String MANIFEST_SRC = "manifest-src";
        public static final String MEDIA_SRC = "media-src";
        public static final String OBJECT_SRC = "object-src";
        public static final String SCRIPT_SRC = "script-src";
        public static final String STYLE_SRC = "style-src";
        public static final String WORKER_SRC = "worker-src";
        public static final String BASE_URI = "base-uri";
        public static final String PLUGIN_TYPES = "plugin-types";
        public static final String SANDBOX = "sandbox";
        public static final String FORM_ACTION = "form-action";
        public static final String FRAME_ANCESTORS = "frame-ancestors";
        public static final String BLOCK_ALL_MIXED_CONTENT = "block-all-mixed-content";
        public static final String REQUIRE_SRI_FOR = "require-sri-for";
        public static final String UPGRADE_INSECURE_REQUESTS = "upgrade-insecure-requests";
    }

    public class DirectiveSourceNames {

        public static final String SELF = "'self'";
        public static final String UNSAFE_INLINE = "'unsafe-inline'";
        public static final String UNSAFE_EVAL = "'unsafe-eval'";
        public static final String NONE = "'none'";
    }

    public PolicyDirective() {
        super(null);
    }

    public PolicyDirective(final String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (getObjectOptional().isPresent() && getObjectOptional().get().isEmpty()) {
            return "";
        }
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getName()).append(" ");
        boolean add = false;
        for (String value : this.getObject()) {
            if (add) {
                stringBuilder.append(" ");
            } else {
                add = true;
            }
            stringBuilder.append(value);
        }
        return stringBuilder.toString();
    }

    public PolicyDirective addSHA256(final String sha256) {
        return addSHA("sha256", sha256);
    }

    public PolicyDirective addSHA384(final String sha384) {
        return addSHA("sha384", sha384);
    }

    public PolicyDirective addSHA512(final String sha512) {
        return addSHA("sha512", sha512);
    }

    private PolicyDirective addSHA(final String hashType, final String has) {
        try {
            final StringBuilder stringBuilder = new StringBuilder();
            final Base64.Encoder encoder = Base64.getEncoder();
            final byte[] encoded = encoder.encode(has.getBytes("utf-8"));
            stringBuilder.append("'");
            stringBuilder.append(hashType);
            stringBuilder.append("-");
            stringBuilder.append(new String(encoded));
            stringBuilder.append("'");
            return add(stringBuilder.toString());
        } catch (UnsupportedEncodingException ex) {
            return this;
        }
    }

    public PolicyDirective addNonce(final String nonceValue) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'nonce-");
        stringBuilder.append(nonceValue);
        stringBuilder.append("'");
        add(stringBuilder.toString());
        return this;
    }

    public PolicyDirective add(final String value) {
        getObject().add(value);
        return this;
    }

    public PolicyDirective add(final String[] array) {
        return add(Arrays.asList(array));
    }

    public PolicyDirective add(final List<String> aList) {
        getObject().addAll(aList);
        return this;
    }

    public boolean isEmpty() {
        final Optional<List<String>> objectOptional = getObjectOptional();
        if (objectOptional.isPresent()) {
            return objectOptional.get().isEmpty();
        }
        return true;
    }
}
