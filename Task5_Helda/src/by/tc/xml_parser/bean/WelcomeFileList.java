package by.tc.xml_parser.bean;

import java.io.Serializable;
import java.util.List;

public class WelcomeFileList implements Serializable{
    private static final long serialVersionUID=1L;

    private List<String> welcomeFile;

    public WelcomeFileList() {
    }

    public List<String> getWelcomeFile() {
        return welcomeFile;
    }

    public void setWelcomeFile(List<String> welcomeFile) {
        this.welcomeFile = welcomeFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WelcomeFileList that = (WelcomeFileList) o;

        return welcomeFile != null ? welcomeFile.equals(that.welcomeFile) : that.welcomeFile == null;

    }

    @Override
    public int hashCode() {
        return welcomeFile != null ? welcomeFile.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WelcomeFileList{" +
                "welcomeFile=" + welcomeFile +
                '}';
    }
}
