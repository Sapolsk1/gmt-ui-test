package framework.local.objects;

import framework.app.App;

public final class LocalObjects {

    private final static LocalObject<App> localApp = new LocalObject<>();

    public static LocalObject<App> getLocalApp(){
        return localApp;
    }

}
