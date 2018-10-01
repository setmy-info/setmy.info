/**
 * Copyright (c) Imre Tabur
 * All rights reserved.
 *
 * @author Imre Tabur
 */
package ee.pub.platform.web.app.struts;

import java.io.File;
import org.apache.commons.io.FileUtils;

public class FileUpload extends BaseAction {

    private File upload;//The actual file
    private String uploadContentType; //The content type of the file
    private String uploadFileName; //The uploaded file name
    private String fileCaption;//The caption of the file entered by user

    @Override
    public String execute() throws Exception {
        try {
            // TODO : J2EE Way!
            File file = new File("tmp");// TODO : generate Unique file
            String fullFileName = "myfile.txt";
            File theFile = new File(fullFileName);
            FileUtils.copyFile(getUpload(),theFile);
        } catch (Exception e) {
            addActionError(e.getMessage());
            return INPUT;
        }
        return SUCCESS;
    }

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
