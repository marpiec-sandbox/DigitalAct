package org.digitalact.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa służy do zastąpienia request scope i session scope na potrzeby testów,
 * ponieważ testy nie są uruchamiane w środowisku webowym.
 */
public class ScopeMock implements Scope {

    private Map<String, Object> objectMap = new HashMap<String, Object>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object object = objectMap.get(name);
        if (null == object) {
          object = objectFactory.getObject();
          objectMap.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        return objectMap.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //Do nothing
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
