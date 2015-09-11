package by.gurianchyck.webapp.model;

public class Link  {
    public static Link EMPTY = new Link();
    private final String name;
    private final String url;

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Link(Link other) {
        this(other.name, other.url);
    }

    public Link() {
        this("", null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (name != null ? !name.equals(link.name) : link.name != null) return false;
        return !(url != null ? !url.equals(link.url) : link.url != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
