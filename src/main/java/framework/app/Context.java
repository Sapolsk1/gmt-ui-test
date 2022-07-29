package framework.app;

import framework.app.annotations.processors.AnnotationProcessor;
import framework.app.annotations.processors.NameAnnotationProcessor;
import framework.app.annotations.processors.OptionalAnnotationProcessor;
import framework.app.factory.PageFactory;
import framework.property.PropertyReader;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class Context {

    private final PropertyReader propertyReader = PropertyReader.getInstance();
    private final Map<String, BasePage> pages = PageFactory.getInstance().createPages(propertyReader.getProperty("pagesPackage"));
    private final Map<String, BasePage> blocks = PageFactory.getInstance().createPages(propertyReader.getProperty("blockPackage"));
    private final List<AnnotationProcessor> processors = List.of(new NameAnnotationProcessor(), new OptionalAnnotationProcessor());
    private final static Context INSTANCE = new Context();

    private Context() {
        pages.forEach((k, v) -> processors.forEach(p -> p.process(v)));
        blocks.forEach((k, v) -> processors.forEach(p -> p.process(v)));
    }

    public static Context getInstance() {
        return INSTANCE;
    }

}
