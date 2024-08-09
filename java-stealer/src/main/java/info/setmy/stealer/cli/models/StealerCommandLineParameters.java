package info.setmy.stealer.cli.models;

import lombok.Getter;
import lombok.Setter;
import picocli.CommandLine;

@Getter
@Setter
@CommandLine.Command(
    name = "stealer",
    mixinStandardHelpOptions = true,
    version = "stealer 0.0.1",
    description = "Git software stealer"
)
public class StealerCommandLineParameters {
}
