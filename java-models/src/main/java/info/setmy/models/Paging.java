package info.setmy.models;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Paging {

    private int fromPage = 1;

    private int resultsOnPage = 10;

    private int results = 0;// Results got from query

    public int getFromPage() {
        return fromPage;
    }

    public void setFromPage(int fromPage) {
        this.fromPage = fromPage;
    }

    public int getResultsOnPage() {
        return resultsOnPage;
    }

    public void setResultsOnPage(int resultsOnPage) {
        this.resultsOnPage = resultsOnPage;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }
}
