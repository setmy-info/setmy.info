package info.setmy.exec;

import info.setmy.exec.exceptions.ExecutionError;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class Executor {

    private int timeout = 60;

    private boolean blocking = true;

    private DefaultExecuteResultHandler resultHandler;

    private int exitValue;

    private int programSuccessReturnValue;

    private File workingDirectory;

    public void exec(final String... params) {
        if (params == null) {
            throw new ExecutionError("Parameters are not passed for execution");
        }
        if (params.length < 1) {
            throw new ExecutionError("Parameters passed have zero items");
        }
        exitValue = 0;
        final String programName = toCommandLine(params);
        final CommandLine cmdLine = CommandLine.parse(programName);
        if (isNotBlocking()) {
            resultHandler = new DefaultExecuteResultHandler();
        }
        final DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(programSuccessReturnValue); // Setting 1 when executed program is returning 1 in case of success (usually notn null like 1 means error)
        if (timeout > 0) {
            executor.setWatchdog(new ExecuteWatchdog(timeout * 1000));
        }
        if (haveWorkingDirectorySet()) {
            executor.setWorkingDirectory(workingDirectory);
        }
        /*
        TODO : design tools for calles, to get results
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
        executor.setStreamHandler(pumpStreamHandler);
        ...
        System.out.println(outputStream.toString());
         */
        try {
            if (isBlocking()) {
                exitValue = executor.execute(cmdLine);
            } else {
                executor.execute(cmdLine, resultHandler);
            }
        } catch (IOException ex) {
            throw new ExecutionError("Coldn't execute command", ex);
        }
    }

    public void waitFor() {
        if (isBlocking()) {
            throw new ExecutionError("Is blocking call");
        }
        if (resultHandler == null) {
            throw new ExecutionError("Programming error, result handler is null");
        }
        try {
            resultHandler.waitFor();
            exitValue = resultHandler.getExitValue();
        } catch (InterruptedException ex) {
            throw new ExecutionError("Coldn't wait for executed program", ex);
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public Executor setTimeout(final int timeout) {
        this.timeout = timeout;
        return this;
    }

    public boolean isNotBlocking() {
        return !isBlocking();
    }

    public boolean isBlocking() {
        return blocking;
    }

    public Executor setBlocking(final boolean blocking) {
        this.blocking = blocking;
        return this;
    }

    public int getExitValue() {
        return exitValue;
    }

    public int getProgramSuccessReturnValue() {
        return programSuccessReturnValue;
    }

    public Executor setProgramSuccessReturnValue(final int programSuccessReturnValue) {
        this.programSuccessReturnValue = programSuccessReturnValue;
        return this;
    }

    public boolean haveWorkingDirectorySet() {
        return workingDirectory != null;
    }

    public File getWorkingDirectory() {
        return workingDirectory;
    }

    public Executor setWorkingDirectory(final String workingDirectory) {
        return setWorkingDirectory(new File(workingDirectory));
    }

    public Executor setWorkingDirectory(final File workingDirectory) {
        this.workingDirectory = workingDirectory;
        return this;
    }

    private String toCommandLine(final String[] params) {
        final StringBuilder stringBuilder = new StringBuilder();
        boolean add = false;
        for (var param : params) {
            if (add) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(param);
            add = true;
        }
        return stringBuilder.toString();
    }
}
