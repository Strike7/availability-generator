import models.CoverUrlService;
import models.CoverUrlServiceAmazonS3;
        import models.Jogo;
        import org.junit.Test;
        import play.libs.F;
        import play.libs.ws.*;
        import play.test.*;

        import static org.hamcrest.CoreMatchers.*;
        import static org.junit.Assert.*;
        import static play.test.Helpers.*;

        import static org.mockito.Mockito.*;

/**
 * Created by heatd_000 on 10/08/2015.
 */
public class AmazonS3ResponseTest extends WithApplication {

    private Jogo jogo = new Jogo("Mortal Kombat X", "mkx.png", true);

    @Test
    public void testUrlCoverExistsS3() {
        CoverUrlService service = mock(CoverUrlService.class);
        when(service.urlFrom(anyObject())).thenReturn("http://s3-sa-east-1.amazonaws.com/strike7-image/cover/mkx.png");
        String urlCover = service.urlFrom(jogo);
        F.Promise<WSResponse> promise = WS.url(urlCover).get();
        assertThat(promise.get(1000).getStatus(), equalTo(OK));
    }

    @Test
    public void testUnavailabilityUrlCoverExistsS3() {
        CoverUrlService service = mock(CoverUrlService.class);
        when(service.urlFrom(anyObject())).thenReturn("http://s3-sa-east-1.amazonaws.com/strike7-image/cover+alugados/mkx.png");
        String urlCover = service.urlFrom(jogo);
        System.out.println(urlCover);
        F.Promise<WSResponse> promise = WS.url(urlCover).get();
        assertThat(promise.get(1000).getStatus(), equalTo(OK));
    }
}
