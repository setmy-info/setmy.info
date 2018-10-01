package ee.pub.web.actions;

import java.io.Serializable;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Request implements Serializable {

    private Long id;
    private Long fromResult;
    private Long resultsNumber;

    /**
     * @return the fromResult
     */
    public Long getFromResult() {
        return fromResult;
    }

    /**
     * @param fromResult the fromResult to set
     */
    public void setFromResult(Long fromResult) {
        this.fromResult = fromResult;
    }

    /**
     * @return the resultsNumber
     */
    public Long getResultsNumber() {
        return resultsNumber;
    }

    /**
     * @param resultsNumber the resultsNumber to set
     */
    public void setResultsNumber(Long resultsNumber) {
        this.resultsNumber = resultsNumber;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
