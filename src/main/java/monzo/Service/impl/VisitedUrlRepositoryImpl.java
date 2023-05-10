package monzo.Service.impl;

import monzo.Service.VisitedUrlRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VisitedUrlRepositoryImpl implements VisitedUrlRepository {
    private static final Map<String, Set<String>> requestToVisitedUrlMap = new HashMap<>();

    public void addToMap(String requestId, Set<String> visitedUrl) {
        requestToVisitedUrlMap.put(requestId, visitedUrl);
    }

    public Set<String> getVisitedUrl(String requestId) {
        return requestToVisitedUrlMap.get(requestId);
    }
}
