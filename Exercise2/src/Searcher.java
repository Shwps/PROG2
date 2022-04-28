import java.util.*;

public class Searcher implements Stats, SearchOperations {

    private Set<String> artists = new HashSet<>();
    private Map<String, Recording> recordingsByTitle = new HashMap<>();
    private Map<String, Set<Recording>> recordingsByGenre = new HashMap<>();

    public Searcher(Collection<Recording> data) {
        for (Recording r : data) {
            artists.add(r.getArtist());
            recordingsByTitle.put(r.getTitle(), r);
            for (String genre : r.getGenre()) {
                Set<Recording> temp = recordingsByGenre.computeIfAbsent(genre, k -> new HashSet<>());
                temp.add(r);
            }
        }
    }

    @Override
    public boolean doesArtistExist(String name) {
        return artists.contains(name);
    }

    @Override
    public Collection<String> getGenres() {
        return Collections.unmodifiableSet(recordingsByGenre.keySet());
    }

    @Override
    public Optional<Recording> getRecordingByName(String title) {
        return Optional.ofNullable(recordingsByTitle.get(title));
    }

    @Override
    public Collection<Recording> getRecordingsAfter(int year) {
        Collection<Recording> recordings = recordingsByTitle.values();
        Set<Recording> recordingsFromYear = new HashSet<>();
        for (Recording rec : recordings) {
            if (rec.getYear() >= year) {
                recordingsFromYear.add(rec);
            }
        }
        return Collections.unmodifiableSet(recordingsFromYear);
    }

    @Override
    public SortedSet<Recording> getRecordingsByArtistOrderedByYearAsc(String artist) {
        Collection<Recording> records = recordingsByTitle.values();
        TreeSet<Recording> sortedByYear = new TreeSet<>(new YearCmp());
        for (Recording rec : records) {
            if (rec.getArtist().equals(artist)) {
                sortedByYear.add(rec);
            }
        }
        return Collections.unmodifiableSortedSet(sortedByYear);
    }

    @Override
    public Collection<Recording> getRecordingsByGenre(String genre) {
        Collection<Recording> result = recordingsByGenre.get(genre);
        if (result == null) {
            recordingsByGenre.put(genre, new HashSet<>());
            result = recordingsByGenre.get(genre);
        }
        return Collections.unmodifiableCollection(result);
    }

    @Override
    public Collection<Recording> getRecordingsByGenreAndYear(String genre, int yearFrom, int yearTo) {
        Collection<Recording> recordings = getRecordingsByGenre(genre);
        Set<Recording> recordingsInRange = new HashSet<>();
        for (Recording rec : recordings) {
            if (rec.getYear() >= yearFrom && rec.getYear() <= yearTo) {
                recordingsInRange.add(rec);
            }
        }
        return Collections.unmodifiableSet(recordingsInRange);
    }

    @Override
    public Collection<Recording> offerHasNewRecordings(Collection<Recording> offered) {
        Collection<Recording> recordingsInStock = recordingsByTitle.values();
        Set<Recording> ofInterest = new HashSet<>();
        for (Recording rec : offered) {
            if (!recordingsByTitle.containsKey(rec.getTitle())) {
                ofInterest.add(rec);
            }
        }
        return Collections.unmodifiableSet(ofInterest);
    }

    @Override
    public Collection<Recording> optionalGetRecordingsBefore(int year) {
        Collection<Recording> recordings = recordingsByTitle.values();
        Set<Recording> recordingsFromYear = new HashSet<>();
        for (Recording rec : recordings) {
            if (rec.getYear() < year) {
                recordingsFromYear.add(rec);
            }
        }
        return Collections.unmodifiableSet(recordingsFromYear);
    }

    @Override
    public SortedSet<Recording> optionalGetRecordingsByArtistOrderedByTitleAsc(String artist) {
        Collection<Recording> records = recordingsByTitle.values();
        TreeSet<Recording> sortedByTitle = new TreeSet<>(new TitleCmp());
        for (Recording rec : records) {
            if (rec.getArtist().equals(artist)) {
                sortedByTitle.add(rec);
            }
        }
        return Collections.unmodifiableSortedSet(sortedByTitle);
    }

    @Override
    public Collection<Recording> optionalGetRecordingsFrom(int year) {
        Collection<Recording> recordings = recordingsByTitle.values();
        Set<Recording> recordingsFromYear = new HashSet<>();
        for (Recording rec : recordings) {
            if (rec.getYear() == year) {
                recordingsFromYear.add(rec);
            }
        }
        return Collections.unmodifiableSet(recordingsFromYear);
    }

    @Override
    public long numberOfArtists() {
        return artists.size();
    }

    @Override
    public long numberOfGenres() {
        return recordingsByGenre.keySet().size();
    }

    @Override
    public long numberOfTitles() {
        return recordingsByTitle.keySet().size();
    }

    public static class YearCmp implements Comparator<Recording> {
        @Override
        public int compare(Recording r1, Recording r2) {
            if (r1.getYear() > r2.getYear()) {
                return 1;
            } else if (r1.getYear() == r2.getYear()) {
                return r1.getArtist().compareTo(r2.getArtist());
            }
            return -1;
        }
    }


    public static class TitleCmp implements Comparator<Recording> {
        @Override
        public int compare(Recording r1, Recording r2) {
            int result = r1.getTitle().compareTo(r2.getTitle());
            if (result == 0) {
                if (r1.getYear() > r2.getYear()) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return result;
        }
    }

}

