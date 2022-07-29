package framework.property;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    private final static PropertyReader INSTANCE = new PropertyReader();
    private final Properties properties= new Properties();

    private PropertyReader() {
        init();
    }

    public static PropertyReader getInstance() {
        return INSTANCE;
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    @SneakyThrows
    private void init() {
        properties.load(new FileInputStream("src/main/resources/application.properties"));
    }

}
