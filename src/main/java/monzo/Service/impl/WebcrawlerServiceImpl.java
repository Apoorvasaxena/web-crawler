package monzo.Service.impl;

import monzo.Service.TaskQueueManager;
import monzo.Service.VisitedUrlRepository;
import monzo.Service.WebcrawlerService;

import java.util.Set;

public class WebcrawlerServiceImpl implements WebcrawlerService {

    private static final VisitedUrlRepository visitedUrlRepository = new VisitedUrlRepositoryImpl();
    @Override
    public String crawl(String url) {
        TaskQueueManager taskQueueManager = new TaskQueueManager(url);
        return taskQueueManager.getRequestId();
    }

    @Override
    public Set<String> getVisitedUrls(String requestId) {
        return visitedUrlRepository.getVisitedUrl(requestId);
    }
}
