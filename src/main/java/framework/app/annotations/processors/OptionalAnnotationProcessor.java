package framework.app.annotations.processors;

import framework.app.BasePage;
import framework.app.annotations.Collection;
import framework.app.annotations.Name;
import framework.app.annotations.Optional;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OptionalAnnotationProcessor implements AnnotationProcessor {

    @Override
    public void process(BasePage basePage) {
        basePage.setPrimaryElements(initPrimaryElements(basePage));
        basePage.setStateElements(initStates(basePage));
    }

    private List<String> initPrimaryElements(Object object){
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Optional.class) == null && f.getDeclaredAnnotation(Name.class) != null
                        && f.getDeclaredAnnotation(Collection.class) == null
                        && String.class.isAssignableFrom(f.getType()))
                .map(f -> extractFieldValue(f, object))
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> initStates(Object object) {
        Map<String, List<String>> stateMap = new HashMap<>();
        Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.getDeclaredAnnotation(Optional.class) != null && f.getDeclaredAnnotation(Name.class) != null
                        && f.getDeclaredAnnotation(Collection.class) == null
                        && String.class.isAssignableFrom(f.getType()))
                .forEach(field -> {
                    List<String> states = Arrays.asList(field.getDeclaredAnnotation(Optional.class).state());
                    if (!states.equals(List.of(""))) {
                        for (String state : states) {
                            String element = extractFieldValue(field, object);
                            if (!stateMap.containsKey(state)) {
                                stateMap.put(state, List.of(element));
                            } else {
                                stateMap.get(state).add(element);
                            }
                        }
                    }
                });
        return stateMap;
    }

    @SneakyThrows
    private String extractFieldValue(Field field, Object owner){
        field.setAccessible(true);
        return field.get(owner).toString();
    }

}
