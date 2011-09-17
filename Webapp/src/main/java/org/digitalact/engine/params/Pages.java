package org.digitalact.engine.params;

/**
 * Enum zawierający listę stron, do których można nawigować.
 * Standardowo dodaje opcję powodującą wywołanie JSFowego redirect.
 * Do wykorzystania w backing beanach.
 * @author Marcin Pieciukiewicz
 */
public enum Pages {

    /** Strona potwierdzająca zarejestrowanie użytkownika. */
    USER_REGISTERED("user-registered"),
    /** Strona potwierdzająca zarejestrowanie superużytkownika. */
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

    /**
     * Zwraca identyfikator strony wraz z parametrem oznaczającym przekierowanie w ramach JSF.
     * @return view id strony z parametrem redirect
     */
    public String redirect() {
        return redirect;
    }
    
    
}
