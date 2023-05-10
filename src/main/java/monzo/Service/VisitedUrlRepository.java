package monzo.Service;

import java.util.Set;

public interface VisitedUrlRepository {

    void addToMap(String requestId, Set<String> visitedUrl);
    Set<String> getVisitedUrl(String requestId);
}
