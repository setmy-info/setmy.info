package info.setmy.crawler.picocli;

import lombok.extern.log4j.Log4j2;
import picocli.CommandLine.Command;

@Log4j2
@Command(name = "csvDb", description = "Pumps CSV files from input directory to single DB file")
public class CsvDb extends SubBase implements Runnable {

    @Override
    public void run() {
        init();
        scvDb.run();
    }
}
