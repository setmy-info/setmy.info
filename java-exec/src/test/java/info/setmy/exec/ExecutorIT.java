package info.setmy.exec;

import info.setmy.exec.exceptions.ExecutionError;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
public class ExecutorIT {

    private Executor executor;

    private final String NORMAL_PROGRAM = "./src/test/sh/normal.sh";
    private final String NORMAL_TIMEOUT_PROGRAM = "./src/test/sh/timeout.sh";
    private final String ERROR_PROGRAM = "./src/test/sh/error.sh";
    private final String WITHOUT_EXEC_BIT_PROGRAM = "./src/test/sh/withoutexec.sh";

    @BeforeEach
    public void before() {
        executor = new Executor().setTimeout(1);
    }

    @Test
    public void exec_when_parameters_are_not_passed() {
        final Exception thownException = assertThrows(ExecutionError.class, () -> {
            executor.exec((String[]) null);
        });
        assertThat(thownException).isExactlyInstanceOf(ExecutionError.class);
        assertThat(thownException.getMessage()).isEqualTo("Parameters are not passed for execution");
    }

    @Test
    public void exec_when_passed_parameters_are_empty() {
        final Exception thownException = assertThrows(ExecutionError.class, () -> {
            executor.exec(new String[]{});
        });
        assertThat(thownException).isExactlyInstanceOf(ExecutionError.class);
        assertThat(thownException.getMessage()).isEqualTo("Parameters passed have zero items");
    }

    @Test
    public void exec_when_executing_non_existing_program() {
        final Exception thownException = assertThrows(ExecutionError.class, () -> {
            executor.exec("nonExisting");
        });
        assertThat(thownException).isExactlyInstanceOf(ExecutionError.class);
        assertThat(thownException.getMessage()).isEqualTo("Coldn't execute command");
    }

    @Test
    public void exec_when_executing_program_without_exec_bit() {
        final Exception thownException = assertThrows(ExecutionError.class, () -> {
            executor.exec(WITHOUT_EXEC_BIT_PROGRAM);
        });
        assertThat(thownException).isExactlyInstanceOf(ExecutionError.class);
        assertThat(thownException.getMessage()).isEqualTo("Coldn't execute command");
        assertThat(thownException.getCause().getMessage()).isEqualTo("Cannot run program \"./src/test/sh/withoutexec.sh\" (in directory \".\"): error=13, Permission denied");
    }

    @Test
    public void exec_watchdog() {
        final Exception thownException = assertThrows(ExecutionError.class, () -> {
            final String[] params = {NORMAL_TIMEOUT_PROGRAM, "parameter1", "parameter2"};
            executor.exec(params);
        });
        assertThat(thownException).isExactlyInstanceOf(ExecutionError.class);
        assertThat(thownException.getMessage()).isEqualTo("Coldn't execute command");
        assertThat(thownException.getCause().getMessage()).isEqualTo("Process exited with an error: 143 (Exit value: 143)");
    }

    @Test
    public void exec_normal() {
        final String[] params = {NORMAL_PROGRAM, "parameter1", "parameter2"};
        executor.exec(params);
        assertThat(executor.getExitValue()).isEqualTo(0);
    }

    @Test
    public void exec_normal_nonblocking() {
        executor = new Executor().setTimeout(60).setBlocking(false);
        final String[] params = {NORMAL_TIMEOUT_PROGRAM, "parameter1", "parameter2"};
        executor.exec(params);
        executor.waitFor();
        assertThat(executor.getExitValue()).isEqualTo(0);
    }

    @Test
    public void exec_error_overiding_normal_return() {
        executor = executor.setProgramSuccessReturnValue(1);
        final String[] params = {ERROR_PROGRAM, "parameter1", "parameter2"};
        executor.exec(params);
        assertThat(executor.getExitValue()).isEqualTo(1);
    }

    @Test
    public void exec_when_error() {
        final Exception thownException = assertThrows(ExecutionError.class, () -> {
            final String[] params = {ERROR_PROGRAM, "parameter1", "parameter2"};
            executor.exec(params);
        });
        assertThat(thownException).isExactlyInstanceOf(ExecutionError.class);
    }
}