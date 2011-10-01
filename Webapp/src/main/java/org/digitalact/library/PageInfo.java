package org.digitalact.library;

/**
 * Plik zawierający podstawowe informacje potrzebne do wyświetlenia strony.
 */
public class PageInfo {

    private String url;
    private String number;
    private String name;

    public PageInfo() {
    }

    public PageInfo(String name, String number, String url) {
        this.name = name;
        this.number = number;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
