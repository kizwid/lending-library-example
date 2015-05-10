package sandkev.shared.domain;

import sandkev.shared.dao.Identifiable;

/**
 * Created by kevin on 10/05/2015.
 */
public class TitleItem implements Identifiable<Long> {

    private long id;
    private final Title title;

    public TitleItem(Title title) {
        this(-1, title);
    }
    public TitleItem(long id, Title title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Title getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleItem titleItem = (TitleItem) o;

        if (id != titleItem.id) return false;
        return !(title != null ? !title.equals(titleItem.title) : titleItem.title != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TitleItem{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
