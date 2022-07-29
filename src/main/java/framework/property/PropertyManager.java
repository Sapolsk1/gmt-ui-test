package framework.property;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class PropertyManager {

    private static final PropertyManager INSTANCE = new PropertyManager();
    private final PropertyReader propertyReader = PropertyReader.getInstance();
    private final Map<String, String> properties = new ConcurrentHashMap<>();

    private PropertyManager(){}

    public static PropertyManager getInstance() {
        return INSTANCE;
    }

    public String getProperty(String propertyName) {
        if (properties.containsKey(propertyName)) {
            return properties.get(propertyName);
        }
        String property = System.getProperty(propertyName);
        if (Objects.isNull(property)){
            return propertyReader.getProperty(propertyName);
        }
        properties.put(propertyName, property);
        return property;
    }

}
