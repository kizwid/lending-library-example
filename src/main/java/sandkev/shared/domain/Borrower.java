package sandkev.shared.domain;

import sandkev.shared.dao.Identifiable;

/**
 * Created by kevin on 10/05/2015.
 */
public class Borrower implements Identifiable<Long> {
    private long id;
    private final String name;

    public Borrower(String name) {
        this(-1, name);
    }
    public Borrower(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrower borrower = (Borrower) o;

        if (id != borrower.id) return false;
        return !(name != null ? !name.equals(borrower.name) : borrower.name != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
