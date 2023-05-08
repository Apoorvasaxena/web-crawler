package monzo.test.util.test;

import monzo.test.util.HTMLParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class HTMLParserTest {

    @Test
    void testParseLinks() throws IOException {
        HTMLParser parser = new HTMLParser();
        // Mock the Jsoup.connect method to return a mocked Connection object
        Connection mockedConnection = Mockito.mock(Connection.class);
        when(Jsoup.connect(anyString())).thenReturn(mockedConnection);

        // Mock the Connection object to return a mocked Document object
        Document mockedDocument = Mockito.mock(Document.class);
        when(mockedConnection.get()).thenReturn(mockedDocument);

        // Mock the Document object to return a set of mocked Elements
        Elements mockedElements = new Elements();
        mockedElements.add(Mockito.mock(Element.class));
        mockedElements.add(Mockito.mock(Element.class));
        mockedElements.add(Mockito.mock(Element.class));
        when(mockedDocument.select("a[href]")).thenReturn(mockedElements);

        Set<String> links = parser.parseLinks("http://www.example.com");

        // Verify that the parseLinks method returns the expected set of links
        Set<String> expectedLinks = new HashSet<>();
        expectedLinks.add("http://www.example.com/link1");
        expectedLinks.add("http://www.example.com/link2");
        expectedLinks.add("http://www.example.com/link3");
        assertEquals(expectedLinks, links);
    }
}
