package test.service;

import monzo.Service.impl.WebcrawlerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class WebcrawlerServiceImplTest {
    WebcrawlerServiceImpl service = new WebcrawlerServiceImpl();

    @Test
    public void testCrawl() {
        String url = "http://example.com";
        String requestId = service.crawl(url);

        Assertions.assertNotNull(requestId);
    }

    @Test(expected = Exception.class)
    public void testCrawlWithInvalidUrl() {
        String invalidUrl = "invalid_url";
        WebcrawlerServiceImpl service = new WebcrawlerServiceImpl();
        service.crawl(invalidUrl);
    }
}

