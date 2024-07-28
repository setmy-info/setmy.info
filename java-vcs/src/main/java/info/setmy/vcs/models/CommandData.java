package info.setmy.vcs.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class CommandData {

    private final String command;
    private final String cloneSubCommand;
    private final String checkoutSubCommand;
    private final String fetchCommand;
    private final String pushCommand;
    private final String pullCommand;

    public String[] buildFetchCommand() {
        final String[] params = {
            command,
            fetchCommand
        };
        return params;
    }

    public String[] buildPullCommand() {
        final String[] params = {
            command,
            pullCommand
        };
        return params;
    }

    public String[] buildPushCommand() {
        final String[] params = {
            command,
            pullCommand
        };
        return params;
    }

    public String[] buildCheckoutCommand(final String branchName) {
        final String[] params = {
            command,
            checkoutSubCommand,
            branchName
        };
        return params;
    }
}
