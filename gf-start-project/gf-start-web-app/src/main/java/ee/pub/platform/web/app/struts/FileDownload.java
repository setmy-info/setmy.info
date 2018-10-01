/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.web.app.struts;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileDownload extends BaseAction {

    private InputStream fileInputStream;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    @Override
    public String execute() throws Exception {
        // TODO : Issue 8
        String workingDir = System.getProperty("user.dir");
        File f = new File(workingDir + "/target/classes/text.txt");
        fileInputStream = new FileInputStream(f);
        return SUCCESS;
    }
}
