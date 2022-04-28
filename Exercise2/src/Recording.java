import java.util.Collection;
import java.util.Set;

public class Recording {
    private final int year;
    private final String artist;
    private final String title;
    private final String type;
    private final Set<String> genre;

    public Recording(String title, String artist, int year, String type, Set<String> genre) {
        this.title = title;
        this.year = year;
        this.artist = artist;
        this.type = type;
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public Collection<String> getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object rec) {
        if (rec instanceof Recording) {
            Recording recCasted = (Recording) rec;
            return this.getArtist().equals(recCasted.getArtist()) && this.getYear() == recCasted.getYear() && this.getTitle().equals(recCasted.getTitle());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;

        return 31 * result + (title.hashCode() + artist.hashCode() + getYear());
    }


    @Override
    public String toString() {
        return String.format("{ %s | %s | %s | %d | %s }", artist, title, genre, year, type);
    }
}
