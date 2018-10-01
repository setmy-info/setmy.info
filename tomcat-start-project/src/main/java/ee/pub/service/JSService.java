package ee.pub.service;

import ee.pub.rhino.JavaScriptEngine;
import java.io.File;
import java.io.FileNotFoundException;
//import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JSService {

    private final JavaScriptEngine jsEngine = new JavaScriptEngine();
    //private ScriptEngine engine;
    private String fileName;

    public void init() throws ScriptException, FileNotFoundException {
        //engine = jsEngine.getEngine();
        File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
        jsEngine.runFile(myClass.getAbsolutePath() + fileName);
    }

    public JavaScriptEngine getJsEngine() {
        return jsEngine;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
