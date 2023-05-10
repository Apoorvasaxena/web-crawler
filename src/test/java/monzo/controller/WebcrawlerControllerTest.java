//package monzo.controller;
//
//import monzo.Controller.WebcrawlerController;
//import monzo.Service.WebcrawlerService;
//import monzo.Service.impl.WebcrawlerServiceImpl;
//import monzo.util.Constants;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.when;
//
//public class WebcrawlerControllerTest {
//    @Mock
//    private WebcrawlerService webcrawlerService;
//    private WebcrawlerController webcrawlerController;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        webcrawlerController =  new WebcrawlerController();
//    }
//
//    @Test
//    public void testVisitUrl(){
//        String testRequestId = "testId";
//        when(webcrawlerService.crawl(anyString())).thenReturn(testRequestId);
//        String response = webcrawlerController.visitUrl("http://www.example.com");
//        assertEquals(response, Constants.RESPONSE_MESSAGE + testRequestId);
//    }
//}
