package by.tc.xml_parser.bean;

import java.io.Serializable;

public class Listener implements Serializable{
    private static final long serialVersionUID=1L;
    private String listenerClass;

    public Listener() {
    }

    public String getListenerClass() {
        return listenerClass;
    }

    public void setListenerClass(String listenerClass) {
        this.listenerClass = listenerClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Listener listener = (Listener) o;

        return listenerClass != null ? listenerClass.equals(listener.listenerClass) : listener.listenerClass == null;

    }

    @Override
    public int hashCode() {
        return listenerClass != null ? listenerClass.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Listener{" +
                "listenerClass='" + listenerClass + '\'' +
                '}';
    }
}
