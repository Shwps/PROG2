import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

record DateRange(LocalDate from, LocalDate to) {
    DateRange {
        Objects.requireNonNull(from, "'from' date is required");

        if (to != null && from.isAfter(to)) {
            throw new IllegalArgumentException(
                    "'from' date must be earlier than 'to' date"
            );
        }
    }

    DateRange(LocalDate from) {
        this(from, null); // will call compact constructor validations too!
    }
}

record PostTags(UUID postId, List<String> tags) {
    PostTags {
        tags = List.copyOf(tags);
    }

    void test(Celestial c) {
        switch (c) {
            case Planet p:
                System.out.println("planet");
                break;
            case Star s:
                System.out.println("star");
                break;
            case Comet x:
                System.out.println("comet");
                break;
        }
    }
}

record Tag(String name, int priority) {}

sealed class Downloader permits URLDownloader, FTPDownloader{ }
final class URLDownloader extends Downloader { }
final class FTPDownloader extends Downloader { }



sealed interface Celestial permits Planet, Star, Comet { }
final class Planet implements Celestial { }
final class Star   implements Celestial {  }
final class Comet  implements Celestial {  }

