package ee.pub.web;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class CommentValidator extends FieldValidatorSupport {

    private static final Logger LOG = Logger.getLogger(CommentValidator.class);

    @Override
    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, object);
        if (!(value instanceof String)) {
            return;
        }
        String str = ((String) value).trim();
        if (str.length() == 0) {
            return;
        }
        try {
            boolean isValidName = ESAPI.validator().isValidInput("user", str,
                    "ValidatorName", 255, false);
            if (isValidName) {
                LOG.debug("-----------------Clean against XSS Attack!----------------");
                str = ESAPI.encoder().encodeForHTML(str);
            } else {
                LOG.debug("---------------XSS Attack----------------");
            }
        } catch (NumberFormatException nfe) {
            addFieldError(fieldName, object);
        }
    }
}
