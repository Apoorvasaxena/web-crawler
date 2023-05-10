package monzo.Controller;

import monzo.Service.WebcrawlerService;
import monzo.Service.impl.WebcrawlerServiceImpl;
import monzo.util.Constants;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class WebcrawlerController {

    private static final WebcrawlerService webcrawlerService = new WebcrawlerServiceImpl();
    /**
     * Given a url, the api visits this and all the urls with same domain,
     * generated a unique requestId
     * and return it which can be used to track the request
     */
    //TODO: Implement a mechanism to identify and notify when a request is complete
    @PostMapping(path = "/api/crawler/")
    public String visitUrl(@RequestBody String url){
        String  requestId = webcrawlerService.crawl(url);
        return Constants.RESPONSE_MESSAGE + requestId;
    }

    /**
     * @Param: RequestId of a submitted request.
     * Return the set of all url's visited as part of this request.
     */
    @GetMapping(path = "/api/visitedUrl/{requestId}")
    public Set<String> getVisitedUrls(@PathVariable(name = "requestId") String requestId){
       return webcrawlerService.getVisitedUrls(requestId);
    }

}
