package search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author nilstes
 */
public class SearchEngineTest {

    @Mock
    private String url1 = "www.url1.com";
    private String url2 = "www.url2.com";
    private String url3 = "www.url3.com";
    private String[] list1 = {"Trondheim", "Oslo", "Bergen", "Testord", "Testord", "Testord"};
    private String[] list2 = {"Fisk", "Laks", "Biff", "Testord", "Testord"};
    private String[] list3 = {"Kaffe", "Te", "Vann", "Testord"};

    UrlPageReader pr;
    SearchEngine se;




    @Before
    public void setUp() {
        pr = mock(UrlPageReader.class);
        se = new SearchEngine(pr);

        when(pr.readPage(url1)).thenReturn(list1);
        when(pr.readPage(url2)).thenReturn(list2);
        when(pr.readPage(url3)).thenReturn(list3);

        se.indexPage(url1);
        se.indexPage(url2);
        se.indexPage(url3);
    }

    @Test
    public void readPageTest() {
        List res = se.search("Testord");
        assert(res.get(0)==url1);

        assert(res.get(1)==url2);

        assert(res.get(2)==url3);
    }

    @Test
    public void noResultTest(){
        assert(se.search("Ikke et testord")==null);
    }
    
}
