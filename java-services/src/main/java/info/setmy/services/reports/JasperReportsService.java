package info.setmy.services.reports;

import info.setmy.exceptions.reports.CompilationException;
import info.setmy.exceptions.reports.ExportingException;
import info.setmy.models.reports.ReportModel;
import info.setmy.services.ReportsService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JasperReportsService implements ReportsService {

    private static final Logger LOG = LoggerFactory.getLogger(JasperReportsService.class);

    private final String reportsLocation;

    private final Map<String, JasperReport> compiledReports = new HashMap<>();

    private List<String> reportNames;

    public JasperReportsService(final String reportsLocation) {
        this.reportsLocation = reportsLocation;
    }

    public void init() {
        LOG.debug("Initializing JasperReports service.");
        if (getReportNames() == null) {
            return;
        }
        getReportNames().forEach((reportName) -> {
            compile(reportName);
        });
    }

    public void export(final ReportModel model, final File file) {
        LOG.debug("Exporting File object to: {}", file.getName());
        try {
            final FileOutputStream os = new FileOutputStream(file);
            this.export(model, os);
        } catch (FileNotFoundException ex) {
        }
    }

    public void export(final ReportModel model, final OutputStream outputStream) {
        LOG.debug("Exporting stream");
        final String reportName = model.getReportName();
        JasperReport compiledReport = compiledReports.get(reportName);
        if (compiledReport == null) {
            compile(reportName);
        }
        compiledReport = compiledReports.get(reportName);
        final List<ReportModel> modelList = new ArrayList<>();
        modelList.add(model);
        final JRDataSource dataSource = new JRBeanCollectionDataSource(modelList);
        try {
            final JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, model.getParameters(), dataSource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (JRException ex) {
            LOG.debug("Error: " + ex.getMessage());
            throw new ExportingException("", ex);
        }
    }

    public void compile(final String reportName) {
        LOG.debug("Compiling report: {}", reportName);
        final Optional<JasperReport> report = compileJasperReportFile(reportName);
        if (report.isPresent()) {
            compiledReports.put(reportName, report.get());
        }
    }

    public Optional<JasperReport> compileJasperReportFile(final String reportName) {
        // TODO : classpath support to add?
        final String absoluteReportFileName = getAbsoluteReportFileName(reportName);
        try {
            final JasperReport jasperReport = JasperCompileManager.compileReport(absoluteReportFileName);
            return Optional.ofNullable(jasperReport);
        } catch (JRException ex) {
            LOG.debug("Compilation error: " + ex.getMessage());
            throw new CompilationException("Compilation error", ex);
        }
    }

    public JasperReport getCompiledReport(final String reportName) {
        final Optional<JasperReport> report = getJasperReport(reportName);
        if (!report.isPresent()) {
            compile(reportName);
        }
        return getReport(reportName);
    }

    public JasperReport getReport(final String reportName) {
        return this.getJasperReport(reportName).get();
    }

    Optional<JasperReport> getJasperReport(final String reportName) {
        return Optional.ofNullable(compiledReports.get(reportName));
    }

    public Map<String, JasperReport> getCompiledReports() {
        return compiledReports;
    }

    public String getReportsLocation() {
        return reportsLocation;
    }

    private String getAbsoluteReportFileName(String reportName) {
        return reportsLocation + "/" + reportName;
    }

    public JRBeanCollectionDataSource createDataSource(final List<?> object) {
        return new JRBeanCollectionDataSource(object);
    }

    public List<String> getReportNames() {
        return reportNames;
    }

    public void setReportNames(List<String> reportNames) {
        this.reportNames = reportNames;
    }
}
