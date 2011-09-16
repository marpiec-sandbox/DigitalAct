/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.digitalact.spring;

import java.util.Map;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * Implementacja scope View dla Beanow obslugiwanych przez Spring.
 * @author mpieciukiewicz
 */
public class ViewScope implements Scope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        if (viewRoot != null) {
            Map<String, Object> viewMap = viewRoot.getViewMap();
            if (viewMap.containsKey(name)) {
                return viewMap.get(name);
            } else {
                Object object = objectFactory.getObject();
                viewMap.put(name, object);
                return object;
            }
        } else {
            return null;
        }
    }

    @Override
    public Object remove(String name) {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        if (viewRoot != null) {
            return viewRoot.getViewMap().remove(name);
        } else {
            return null;
        }
    }

    @Override
    public void registerDestructionCallback(String string, Runnable r) {
        // Do nothing
    }

    @Override
    public Object resolveContextualObject(String string) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
