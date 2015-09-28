package by.gurianchyck.webapp.model;

import java.util.Objects;

/**
 * Created by Alexey Gurianchyck
 * 09.09.2015.
 */
public class TextSection extends Section{
    static final long serialVersionUID = 1L;

    private String value;

    public TextSection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TextSection other = (TextSection) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return  value;
    }
}
