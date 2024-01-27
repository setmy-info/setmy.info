package info.setmy.models;

import info.setmy.exceptions.NotFoundException;
import info.setmy.exceptions.UncheckedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

    private Optional<Object> urlOrInputStream = empty();

    private List<String> result;

    public static Optional<FileRows> newClasspathFileRows(final String classPath) {
        final InputStream inputStream = FileRows.class.getClassLoader().getResourceAsStream(classPath);
        return of(new FileRows(inputStream));
    }

    public static Optional<FileRows> newFileRows(final String fileOrUrl) {
        final LambdaReturn<FileRows> lambdaReturn = new LambdaReturn<>();
        urlService.toUrl(fileOrUrl).ifPresentOrElse(
            url -> lambdaReturn.setValue(new FileRows(url)),
            () -> lambdaReturn.setValue(new FileRows(new File(fileOrUrl)))
        );
        return lambdaReturn.getValue();
    }

    public FileRows(final URL url) {
        this.urlOrInputStream = ofNullable(url);
    }

    public FileRows(final InputStream inputStream) {
        this.urlOrInputStream = ofNullable(inputStream);
    }

    public FileRows(final File file) {
        if (isNull(file)) {
            return;
        }
        try {
            urlOrInputStream = of(file.toURI().toURL());
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
        final LambdaReturn<Scanner> lambdaReturn = new LambdaReturn<>();
        this.urlOrInputStream.ifPresent(stream -> lambdaReturn.setValue(
                of(new Scanner(getStream(stream)))
            )
        );
        return lambdaReturn.getValue();
    }

    private InputStream getStream(final Object urlOrInputStream) {
        switch (urlOrInputStream) {
            case URL url -> {
                try {
                    return url.openStream();
                } catch (FileNotFoundException e) {
                    throw new NotFoundException(e);
                } catch (IOException e) {
                    throw new UncheckedException(e);
                }
            }
            case InputStream inputStream -> {
                return inputStream;
            }
            default -> {
            }
        }
        return null;
    }
}
