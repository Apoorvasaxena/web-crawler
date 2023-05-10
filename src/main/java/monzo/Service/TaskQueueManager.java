package monzo.Service;

import monzo.Service.impl.VisitedUrlRepositoryImpl;
import monzo.util.HTMLParser;
import org.jsoup.helper.StringUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskQueueManager {
    private final Queue<String> urlsToVisit = new LinkedBlockingQueue<>();
    private final Set<String> visitedUrls = new HashSet<>();
    private int EXECUTORS = 5;
    private final String domain;
    private final String requestId;
    ExecutorService executorService = Executors.newFixedThreadPool(EXECUTORS);

    private static final Logger LOGGER = Logger.getLogger(TaskQueueManager.class.getName());

    public TaskQueueManager(String startingUrl) {
        try {
            VisitedUrlRepository visitedUrlRepository = new VisitedUrlRepositoryImpl();
            requestId = UUID.randomUUID().toString();
            URL url = new URL(startingUrl);
            domain = url.getHost();
            urlsToVisit.offer(startingUrl);
            while (EXECUTORS > 0) {
                executorService.submit(new VisitUrlTask());
                EXECUTORS--;
            }
            visitedUrlRepository.addToMap(requestId, visitedUrls);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid starting URL: " + startingUrl, e);
        }
    }

    class VisitUrlTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                String url = urlsToVisit.poll();
                if (!StringUtil.isBlank(url)) {
                    if (visitedUrls.contains(url)) {
                        continue;
                    }
                    try {
                        HTMLParser parser = new HTMLParser();
                        Set<String> links = parser.parseLinks(url);
                        LOGGER.log(Level.INFO, "Visiting url " + url);
                        for (String link : links) {
                            if (isSameDomain(link) && !visitedUrls.contains(link)) {
                                urlsToVisit.add(link);
                            }
                        }
                        visitedUrls.add(url);
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Error visiting URL: " + url + " - " + e.getMessage());
                    }
                }
            }
        }
    }

    private boolean isSameDomain(String url) {
        try {
            URL linkUrl = new URL(url);
            return domain.equals(linkUrl.getHost());
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public String getRequestId() {
        return requestId;
    }

}
