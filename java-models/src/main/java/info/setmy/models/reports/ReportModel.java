package info.setmy.models.reports;

import info.setmy.services.ReportsService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public abstract class ReportModel implements Serializable {

    private String reportName;

    private ReportsService reportsService;

    private final Map<String, Object> parameters = new HashMap<>();

    public ReportModel() {
    }

    public ReportModel(final ReportsService jasperService) {
        this.reportsService = jasperService;
    }

    protected void initSub(final ReportModel subReport) {
        subReport.setReportsService(getReportsService());
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public ReportsService getReportsService() {
        return reportsService;
    }

    public <T> List<T> createSubReport(final T object) {
        return Collections.unmodifiableList(Arrays.asList(object));
    }

    public void setReportsService(ReportsService reportsService) {
        this.reportsService = reportsService;
    }
}
