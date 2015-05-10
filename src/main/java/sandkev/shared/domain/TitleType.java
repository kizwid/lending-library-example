package sandkev.shared.domain;

import sandkev.shared.dao.Identifiable;

/**
 * Created by kevin on 10/05/2015.
 */
public class TitleType implements Identifiable<Integer> {
    private int id;
    private final String name;

    public TitleType(String name) {
        this(-1, name);
    }
    public TitleType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleType titleType = (TitleType) o;

        if (id != titleType.id) return false;
        return !(name != null ? !name.equals(titleType.name) : titleType.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TitleType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
