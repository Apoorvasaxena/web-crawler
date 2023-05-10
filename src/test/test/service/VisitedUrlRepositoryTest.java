package test.service;

import monzo.Service.VisitedUrlRepository;
import monzo.Service.impl.VisitedUrlRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

public class VisitedUrlRepositoryTest {

    private VisitedUrlRepository repository = new VisitedUrlRepositoryImpl();

    @Test
    public void testGetVisitedUrlReturnsNullForInvalidRequestId() {
        String requestId = "invalid_request_id";
        Set<String> visitedUrls = repository.getVisitedUrl(requestId);
        Assertions.assertNull(visitedUrls);
    }

    @Test
    public void testAddToMapAddsVisitedUrlsToMap() {
        String requestId = "test_request_id";
        Set<String> visitedUrls = new HashSet<>();
        visitedUrls.add("http://example.com/page1");
        visitedUrls.add("http://example.com/page2");

        repository.addToMap(requestId, visitedUrls);

        Set<String> storedUrls = repository.getVisitedUrl(requestId);
        Assertions.assertNotNull(storedUrls);
        Assertions.assertEquals(visitedUrls.size(), storedUrls.size());
        Assertions.assertTrue(storedUrls.containsAll(visitedUrls));
    }
}
