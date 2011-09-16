package org.digitalact.engine.params;

/**
 *
 * @author Marcin Pieciukiewicz
 */
public enum Pages {
    
    
    USER_REGISTERED("user-registered"),
    SUPERUSER_CREATED("superuser-created");
    
    private static final String REDIRECT_SUFFIX = "?faces-redirect=true";

    private String viewId;
    private String redirect;

    private Pages(String viewId) {
        this.viewId = viewId;
        redirect = viewId+REDIRECT_SUFFIX;
    }

    public String getViewId() {
        return viewId;
    }
    
    public String redirect() {
        return redirect;
    }
    
    
}
