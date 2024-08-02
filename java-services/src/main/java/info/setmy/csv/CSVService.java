package info.setmy.csv;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.*;

@Getter
@RequiredArgsConstructor
public class CSVService<T> {

    private final SCVConfig config;

    public T parse(final File file, final String enCoding) {
        return null;
    }
}
