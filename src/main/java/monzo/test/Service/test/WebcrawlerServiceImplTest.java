package monzo.test.Service.test;

import monzo.test.Service.impl.WebcrawlerServiceImpl;
import monzo.test.util.HTMLParser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class WebcrawlerServiceImplTest {

    @Mock
    HTMLParser parser;
    private final String startingUrl = "https://www.example.com";
    private WebcrawlerServiceImpl crawler;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        crawler = new WebcrawlerServiceImpl(startingUrl);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartingUrl() {
        WebcrawlerServiceImpl invalidCrawler = new WebcrawlerServiceImpl("invalid url");
    }

    @Test
    public void testCrawl() throws IOException {
        Set<String> visitedUrls = new HashSet<>();
        visitedUrls.add(startingUrl);

        Set<String> links1 = new HashSet<>();
        links1.add("https://www.example.com/page1");
        links1.add("https://www.example.com/page2");

        Set<String> links2 = new HashSet<>();
        links2.add("https://www.example.com/page3");

        when(parser.parseLinks(startingUrl)).thenReturn(links1);
        when(parser.parseLinks("https://www.example.com/page1")).thenReturn(links2);

        crawler.crawl();

        assertEquals(visitedUrls, crawler.getVisitedUrls());
        verify(parser, times(1)).parseLinks(startingUrl);
        verify(parser, times(1)).parseLinks("https://www.example.com/page1");
        verify(parser, times(1)).parseLinks("https://www.example.com/page2");
        verify(parser, times(0)).parseLinks("https://www.example.com/page3");
    }

    @Test
    public void testVisitUrl() throws Exception {
        Set<String> links = new HashSet<>();
        links.add("https://www.example.com/page1");
        links.add("https://www.example.com/page2");
        when(parser.parseLinks(startingUrl)).thenReturn(links);

        crawler.visitUrl(startingUrl);

        Set<String> expectedUrlsToVisit = new HashSet<>();
        expectedUrlsToVisit.add("https://www.example.com/page1");
        expectedUrlsToVisit.add("https://www.example.com/page2");
        assertEquals(expectedUrlsToVisit, crawler.getUrlsToVisit());
        verify(parser, times(1)).parseLinks(startingUrl);
    }
}

