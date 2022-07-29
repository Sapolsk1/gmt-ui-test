package framework.app.factory;

import framework.app.BasePage;
import framework.app.annotations.Name;
import framework.app.annotations.URL;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class PageFactory {

    private static final PageFactory INSTANCE = new PageFactory();

    private PageFactory(){}

    public static PageFactory getInstance(){
        return INSTANCE;
    }

    public Map<String, BasePage> createPages(String packageName){
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Name.class);
        return classes.stream().collect(Collectors.toMap(c -> c.getDeclaredAnnotation(Name.class).value(), this::createPage));
    }

    private BasePage createPage(Class<?> clazz){
        try {
            BasePage basePage = (BasePage) clazz.getConstructor().newInstance();
            if (clazz.isAnnotationPresent(URL.class)) {
                basePage.setUrl(clazz.getDeclaredAnnotation(URL.class).value());
            } else {
                basePage.setUrl("");
            }
            return basePage;
        } catch (Exception e) {
            throw new RuntimeException("Page init exception for class " + clazz.getName());
        }
    }

}
