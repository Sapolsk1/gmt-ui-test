package framework.app.annotations.processors;

import framework.app.BasePage;
import framework.app.annotations.Collection;
import framework.app.annotations.Name;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class NameAnnotationProcessor implements AnnotationProcessor{

    @Override
    public void process(BasePage basePage) {
        basePage.setElements(initElements(basePage));
        basePage.setElementsCollection(initElementsCollection(basePage));
    }

    private Map<String, String> initElements(Object object){
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null && String.class.isAssignableFrom(f.getType()) && f.getDeclaredAnnotation(Collection.class) == null)
                .collect(Collectors.toMap(f -> f.getDeclaredAnnotation(Name.class).value(),
                        f -> extractFieldValue(f, object)));
    }

    private Map<String, String> initElementsCollection(Object object){
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Name.class) != null && String.class.isAssignableFrom(f.getType())&& f.getDeclaredAnnotation(Collection.class) != null)
                .collect(Collectors.toMap(f -> f.getDeclaredAnnotation(Name.class).value(),
                        f -> extractFieldValue(f, object)));
    }

    @SneakyThrows
    private String extractFieldValue(Field field, Object owner){
        field.setAccessible(true);
        return field.get(owner).toString();
    }

}
