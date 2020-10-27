import java.util.Random;

public enum Configuration {
    instance;

    // i/o
    public final String fileSeparator = System.getProperty("file.separator");
    public final String userDirectory = System.getProperty("user.dir");
    public final String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
}