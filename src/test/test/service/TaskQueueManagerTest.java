package test.service;

import monzo.Service.TaskQueueManager;
import monzo.Service.VisitedUrlRepository;
import monzo.Service.impl.VisitedUrlRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Set;

public class TaskQueueManagerTest {

    private VisitedUrlRepository visitedUrlRepository  = new VisitedUrlRepositoryImpl();

    @Test
    public void testGetRequestReturnValidId() {
        String url = "http://example.com";
        TaskQueueManager taskQueueManager = new TaskQueueManager(url);
        String requestId = taskQueueManager.getRequestId();

        Assertions.assertNotEquals("", requestId);
    }

    @Test
    public void getVisitedUrlsReturnsNullSetForNewId() {
        String url = "http://example.com";
        TaskQueueManager taskQueueManager = new TaskQueueManager(url);
        String requestId = taskQueueManager.getRequestId();
        Set<String> visitedUrls = visitedUrlRepository.getVisitedUrl(requestId);

        Assertions.assertEquals(0, visitedUrls.size());;
    }
}
