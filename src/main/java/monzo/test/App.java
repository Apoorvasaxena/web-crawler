package monzo.test;

import monzo.test.Service.WebcrawlerService;
import monzo.test.Service.impl.WebcrawlerServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WebcrawlerService webcrawlerService = new WebcrawlerServiceImpl("https://example.com/");
        webcrawlerService.crawl();
    }
}
