package monzo.Service;

import java.util.Set;

public interface WebcrawlerService {

    String crawl(String url);
    Set<String> getVisitedUrls(String requestId);
}
