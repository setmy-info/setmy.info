package info.setmy.vcs;

import info.setmy.vcs.models.CommandData;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Constants {

    public static final CommandData DEFAULT_COMMAND_DATA = CommandData.builder()
        .cloneSubCommand("clone")
        .checkoutSubCommand("checkout")
        .fetchCommand("fetch")
        .pushCommand("push")
        .pullCommand("pull")
        .build();
}
