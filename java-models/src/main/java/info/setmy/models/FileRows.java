package info.setmy.models;

import info.setmy.exceptions.NotFoundException;
import info.setmy.exceptions.UncheckedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import static info.setmy.services.UrlService.urlService;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.*;

public class FileRows {

    private Optional<URL> url = empty();

    private List<String> result;

    public static Optional<FileRows> newFileRows(final String fileOrUrl) {
        final LambdaReturn<FileRows> lambdaReturn = new LambdaReturn<>();
        urlService.toUrl(fileOrUrl).ifPresentOrElse(
            url -> lambdaReturn.setValue(newFileRows(url)),
            () -> lambdaReturn.setValue(newFileRows(new File(fileOrUrl)))
        );
        return lambdaReturn.getValue();
    }

    public static FileRows newFileRows(final URL url) {
        return new FileRows(url);
    }

    public static FileRows newFileRows(final File file) {
        return new FileRows(file);
    }

    private FileRows(final URL url) {
        this.url = ofNullable(url);
    }

    private FileRows(final File file) {
        if (isNull(file)) {
            return;
        }
        try {
            url = of(file.toURI().toURL());
        } catch (MalformedURLException e) {
            throw new UncheckedException(e);
        }
    }

    public List<String> getRows() {
        if (nonNull(result)) {
            return result;
        }
        final List<String> newResult = new ArrayList<>();
        getRows(row -> newResult.add(row));
        result = unmodifiableList(newResult);
        return result;
    }

    public void getRows(final Consumer<String> consumer) {
        newScanner().ifPresent(scanner -> {
            try (final Scanner scannerToClose = scanner) {
                while (scannerToClose.hasNextLine()) {
                    consumer.accept(scannerToClose.nextLine());
                }
            }
        });
    }

    private Optional<Scanner> newScanner() {
        LambdaReturn<Scanner> lambdaReturn = new LambdaReturn<>();
        this.url.ifPresent(url -> {
            try {
                lambdaReturn.setValue(of(new Scanner(url.openStream())));
            } catch (FileNotFoundException fileNotFoundException) {
                throw new NotFoundException(fileNotFoundException);
            } catch (IOException ioException) {
                throw new UncheckedException(ioException);
            }
        });
        return lambdaReturn.getValue();
    }
}
