package info.setmy.services.reports;

import info.setmy.models.reports.ReportModel;
import info.setmy.services.ReportsService;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class SubReportExampleModel extends ReportModel {

    private String subData;

    public SubReportExampleModel() {
        super();
    }

    public SubReportExampleModel(final ReportsService jasperService) {
        super(jasperService);
    }

    public String getSubData() {
        return subData;
    }

    public void setSubData(String subData) {
        this.subData = subData;
    }
}
