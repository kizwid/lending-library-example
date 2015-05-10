package sandkev.shared.domain;

import sandkev.shared.dao.Identifiable;

/**
 * Created by kevin on 09/05/2015.
 */
public class Title implements Identifiable<Long> {

    private long id;
    private final String name;
    private final String type;

    public Title(String name, String type) {
        this(-1, name, type);
    }
    public Title(long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Title title1 = (Title) o;

        if (id != title1.id) return false;
        if (type != null ? !type.equals(title1.type) : title1.type != null) return false;
        return !(name != null ? !name.equals(title1.name) : title1.name != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
