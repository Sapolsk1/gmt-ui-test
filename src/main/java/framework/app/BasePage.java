package framework.app;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public abstract class BasePage {

    private Map<String, String> elements;
    private Map<String, String> elementsCollection;
    private List<String> primaryElements;
    private Map<String, List<String>> stateElements;
    private String url;

    public String getElementByName(String name){
        return elements.get(name);
    }

    public String getElementsCollectionByName(String name){
        return elementsCollection.get(name);
    }

}
