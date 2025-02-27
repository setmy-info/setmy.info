package info.setmy.reports;

import info.setmy.models.storage.DirectoryStructureFileCreationPattern;
import info.setmy.models.storage.Storage;
import info.setmy.models.storage.StorageFile;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Disabled // TODO : update reports files version, remake these. reload.
public class JasperReportsServiceIT {

    JasperReportsService jasperReportsService;

    final String location = "src/test/resources/reports";

    final String reportName = "test.jrxml";

    final String subReportName = "sub.jrxml";

    Storage storage;

    ExampleModel model;

    SubReportExampleModel subReportModel;

    @BeforeEach
    public void setUp() {
        final DirectoryStructureFileCreationPattern pattern = DirectoryStructureFileCreationPattern.builder()
            .owner("target")
            .subOwner("reports")
            .build();
        storage = new Storage(pattern.toString());
        jasperReportsService = new JasperReportsService(location);
        jasperReportsService.setReportNames(Arrays.asList(reportName));
        jasperReportsService.init();
        model = new ExampleModel(jasperReportsService);
        model.setReportName(reportName);
        model.setTitle("Jasper reports example");
        subReportModel = new SubReportExampleModel();
        subReportModel.setReportName(subReportName);
        subReportModel.setSubData("Sub report data!");
        model.setSubReport(subReportModel);
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.WINDOWS, OS.MAC})
    public void testExport() {
        final Optional<StorageFile> file = storage.createStorageFile(
            DirectoryStructureFileCreationPattern.builder()
            .build()
        );
        final File child = file.get().getChild();
        assertThat(child).isNotNull();
        jasperReportsService.export(model, child);
    }
}
