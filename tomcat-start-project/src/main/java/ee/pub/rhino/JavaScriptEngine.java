package ee.pub.rhino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * http://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/
 * http://docs.oracle.com/javase/7/docs/technotes/guides/scripting/programmer_guide/#invoke
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class JavaScriptEngine {

    public final static String JAVA_RT_NAME = System.getProperty("java.runtime.name");
    public final static String JAVA_SCRIPT = "JavaScript";
    private ScriptEngineManager manager;
    private ScriptEngine engine;
    private ScriptContext scriptingContext;

    enum JavaVendor {

        UNKNOWN, OPENJDK, ORACLEJDK
    }

    private JavaVendor getVendor() {
        if (JAVA_RT_NAME.contains("OpenJDK") || JAVA_RT_NAME.contains("Java(TM) SE Runtime Environment")) {
            return JavaVendor.OPENJDK;
        } else if (JAVA_RT_NAME.contains("Oracle")) {
            return JavaVendor.ORACLEJDK;
        }
        return JavaVendor.UNKNOWN;
    }

    public ScriptEngineManager getManager() {
        if (manager == null) {
            manager = new ScriptEngineManager();
        }
        return manager;
    }

    public void setManager(ScriptEngineManager manager) {
        this.manager = manager;
    }

    public ScriptEngine getEngine() {
        if (engine == null) {
            engine = getManager().getEngineByName(JAVA_SCRIPT);
        }
        return engine;
    }

    public void setEngine(ScriptEngine engine) {
        this.engine = engine;
    }

    public ScriptContext getScriptingContext() {
        if (scriptingContext == null) {
            scriptingContext = getEngine().getContext();
        }
        return scriptingContext;
    }

    public void setScriptingContext(ScriptContext scriptingContext) {
        this.scriptingContext = scriptingContext;
    }

    public void runFile(final String fileName) throws ScriptException, FileNotFoundException {
        if (getVendor() == JavaVendor.OPENJDK || getVendor() == JavaVendor.ORACLEJDK) {
            getEngine().put("nameForCurrencJavaClass", this);
            getEngine().eval(new FileReader(fileName));
        } else {
            //this.getScope().put("nameForCurrencJavaClass", this.getScope(), this);
            Charset charset = Charset.forName("UTF-8");
            File f = new File(fileName);
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = Files.newBufferedReader(f.toPath(), charset)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException x) {
            }
            final String js = sb.toString();
        }
    }

    public void callbackLog(final String msg) {
        System.out.println("callbackLog: " + msg);
    }

    /*
     * Call JS function.
     */
    public Integer jsAdd(int a, int b) throws ScriptException, NoSuchMethodException {
        if (getVendor() == JavaVendor.OPENJDK) {
            final String methodName = "jsAdd";
            Object result = ((Invocable) this.getEngine()).invokeFunction(methodName, a, b);
            if (result != null) {
                return ((Double) result).intValue();
            }
        } else if (getVendor() == JavaVendor.ORACLEJDK) {
            /*Object fObj = this.getScope().get("jsAdd", this.getScope());
             if (fObj instanceof Function) {
             Object functionArgs[] = {a, b};
             Function f = (Function) fObj;
             Object result = f.call(this.getContext(), this.getScope(), this.getScope(), functionArgs);
             if (result != null) {
             return ((Double) result).intValue();
             }
             }*/
        }
        return null;//return ((Double) getEngine().eval("jsAdd(" + a + ", " + a + ");")).intValue();
    }

    public void update() throws ScriptException, NoSuchMethodException {
        if (getVendor() == JavaVendor.OPENJDK) {
            final String objName = "obj";
            Object x = this.getEngine().get(objName);
            ScriptObjectMirror obj = (ScriptObjectMirror) this.getEngine().get(objName);
            obj.setMember("anotherAttribute", "Another Value From Java");
            ((Invocable) this.getEngine()).invokeFunction("showObj");
        } else if (getVendor() == JavaVendor.ORACLEJDK) {
        }
    }

    public ScriptObjectMirror getObject(final String name) throws ScriptException, NoSuchMethodException {
        if (getVendor() == JavaVendor.OPENJDK) {
            ScriptEngine eng = this.getEngine();
            Object retObject = eng.get(name);
            ScriptObjectMirror obj = (ScriptObjectMirror) retObject;
            return obj;
        } else if (getVendor() == JavaVendor.ORACLEJDK) {
            // TODO : 
            /*Object[] elems = this.getContext().getElements(this.getScope());
             Object x = this.getScope().get(name, this.getScope());
             UniqueTag ut = (UniqueTag) x;
             Object ob = ut.readResolve();*/
        }
        return null;
    }

    public ScriptObjectMirror getArray(final String name) {
        if (getVendor() == JavaVendor.OPENJDK) {
            ScriptEngine eng = this.getEngine();
            Object retObject = eng.get(name);
            ScriptObjectMirror obj = (ScriptObjectMirror) retObject;
            return obj;
        } else if (getVendor() == JavaVendor.ORACLEJDK) {
        }
        return null;
    }
}
