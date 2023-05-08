package monzo.test.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTMLParser {
    private static final Logger LOGGER = Logger.getLogger(HTMLParser .class.getName());

    public Set<String> parseLinks(String url) throws IOException {
        LOGGER.log(Level.INFO, "Parsing sub-links from URL: " + url);
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");

        Set<String> linkSet = new HashSet<>();
        for (Element link : links) {
            String linkHref = link.attr("href");
            linkSet.add(linkHref);
        }
        LOGGER.log(Level.INFO, "Found " + linkSet.size() + " links on page: " + url);
        return linkSet;
    }
}
