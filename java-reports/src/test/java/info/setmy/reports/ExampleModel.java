package info.setmy.reports;

import info.setmy.models.reports.ReportModel;
import info.setmy.services.ReportsService;
import java.util.List;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class ExampleModel extends ReportModel {

    private String title;

    private SubReportExampleModel subReport;

    public ExampleModel(final ReportsService jasperService) {
        super(jasperService);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubReportExampleModel> getSubReport() {
        return createSubReport(subReport);
    }

    public void setSubReport(final SubReportExampleModel subReport) {
        initSub(subReport);
        this.subReport = subReport;
    }
}
