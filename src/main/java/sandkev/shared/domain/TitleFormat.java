package sandkev.shared.domain;

/**
 * Created by kevin on 10/05/2015.
 */
public enum TitleFormat {
    Book(0),
    DVD(1),
    VHS(2),
    CD(3);
    private final int id;
    TitleFormat(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    TitleFormat valueOf(int id){
        for (TitleFormat titleFormat : values()) {
            if(titleFormat.getId() == id){
                return titleFormat;
            }
        }
        throw new IllegalArgumentException("Unknown ID: " + id);
    }

}
