package monzo.test.Service.impl;

import monzo.test.Service.WebcrawlerService;
import monzo.test.util.HTMLParser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebcrawlerServiceImpl implements WebcrawlerService {
    private final Set<String> visitedUrls = new HashSet<>();
    private final Queue<String> urlsToVisit = new LinkedList<>();
    private final String domain;
    private final Object lock = new Object();
    private static final Logger LOGGER = Logger.getLogger(WebcrawlerServiceImpl.class.getName());

    public WebcrawlerServiceImpl(String startingUrl) {
        try {
            URL url = new URL(startingUrl);
            this.domain = url.getHost();
            urlsToVisit.add(startingUrl);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid starting URL: " + startingUrl, e);
        }
    }
    public void visitUrl(String url) {
        if (visitedUrls.contains(url)) {
            return;
        }

        try {
          HTMLParser parser = new HTMLParser();
            Set<String> links = parser.parseLinks(url);
            LOGGER.log(Level.INFO,"Visiting url " + url);
            for (String link : links) {
                if (isSameDomain(link) && !visitedUrls.contains(link)) {
                    urlsToVisit.add(link);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Error visiting URL: " + url + " - " + e.getMessage());
        }
    }
    public void crawl() {
        while (!urlsToVisit.isEmpty()) {
            String url = getNextUrlToVisit();
            visitUrl(url);
        }
    }

    private String getNextUrlToVisit() {
        synchronized (lock) {
            while (urlsToVisit.isEmpty()) {
                try {
                    lock.wait(6000);
                } catch (InterruptedException e) {
                    // ignore and keep waiting
                }
            }
            return urlsToVisit.poll();
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
    public Set<String> getVisitedUrls() {
        return visitedUrls;
    }

    public Set<String> getUrlsToVisit() {
        return new HashSet<>(urlsToVisit);
    }
}
