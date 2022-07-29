package framework.local.objects;

import java.util.Objects;

public final class LocalObject <T>{

    private final ThreadLocal<T> local = new ThreadLocal<>();

    LocalObject(){}

    public T setLocal(T localObject) {
        local.set(localObject);
        return getLocal();
    }

    public T initLocal(T localObject) {
        if (Objects.isNull(local.get())){
            local.set(localObject);
        }
        return getLocal();
    }

    public T getLocal() {
        T current = local.get();
        if (Objects.isNull(current)) {
            throw new AssertionError("Local object is null");
        }
        return current;
    }

}
