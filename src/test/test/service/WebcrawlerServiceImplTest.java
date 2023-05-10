package test.service;

import monzo.Service.VisitedUrlRepository;
import monzo.Service.impl.VisitedUrlRepositoryImpl;
import monzo.Service.impl.WebcrawlerServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class WebcrawlerServiceImplTest {
    //@Mock
  //  VisitedUrlRepository visitedUrlRepository = Mockito.mock(VisitedUrlRepositoryImpl.class);
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

