package info.setmy.crawler.scraper.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;

@Getter
@RequiredArgsConstructor
public final class FirefoxProfileFile {

    private final String name;

    public File getProfileFile() {
        final String os = System.getProperty("os.name").toLowerCase();
        final File profilesDir = new File(getBaseDir(os));
        if (!profilesDir.exists() || !profilesDir.isDirectory()) {
            throw new RuntimeException("Firefox profile dir not found at: " + profilesDir);
        }
        for (File profile : profilesDir.listFiles()) {
            if (profile.isDirectory() && profile.getName().endsWith(name)) {
                return profile;
            }
        }
        throw new RuntimeException("Firefox profile not found by name: " + name);
    }

    private String getBaseDir(String os) {
        final String baseDir;
        if (os.contains("win")) {
            baseDir = System.getenv("APPDATA") + "\\Mozilla\\Firefox\\Profiles";
        } else {
            baseDir = System.getProperty("user.home") + "/.mozilla/firefox";
        }
        return baseDir;
    }
}
