package program.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetColorAndChar {
    private final String filePathProd = "src/main/resources/ application-production.properties";
    private final String filePathDev = "src/main/resources/ application-development.properties";
    private String filePath;
    private static final Properties properties = new Properties();

    private static SetColorAndChar instance;

    private SetColorAndChar(String mod) {
        if (mod.equals("production")) filePath = filePathProd;
        else if (mod.equals("development"))filePath = filePathDev;
    }

    public static SetColorAndChar getInstance(String mod) {
        if (instance == null) {
            instance = new SetColorAndChar(mod);
        }
        return instance;
    }

    public void setParamProperties() {
        if (fileExist()) {
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                properties.load(fileInputStream);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean fileExist() {
        File file = new File(filePath);
        return file.exists();
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
