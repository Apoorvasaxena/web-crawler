package test.util;

import monzo.util.HTMLParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HTMLParserTest {
    @Test
        public void testParseLinks() throws Exception {
            HTMLParser parser = new HTMLParser();
            String url = "https://example.com";
            Assertions.assertEquals(1,parser.parseLinks(url).size());
        }
    }

