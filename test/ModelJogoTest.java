import static org.junit.Assert.*;

import models.CoverUrlServiceAmazonS3;
import org.junit.Test;
import models.Jogo;
import play.libs.F.*;
import play.libs.ws.*;
import models.CoverUrlService;
import play.test.*;
import static play.test.Helpers.*;
import static org.hamcrest.CoreMatchers.*;


import javax.inject.Inject;

public class ModelJogoTest extends WithApplication {

    final Jogo jogo = new Jogo("Mortal Kombat X","mkx.png" ,false);

    @Inject WSClient ws;

    @Test
    public void testDados(){
        String expectTitulo = "Mortal Kombat X";
        assertThat(jogo.getTitulo(), equalTo(expectTitulo));
    }

    @Test
    public void testCover(){
        String expected = "mkx.png";
        assertThat(jogo.getCover(), equalTo(expected));
    }


    @Test
    public void verificaDisponibilidadeUrl() {
        CoverUrlService service = new CoverUrlServiceAmazonS3();

        String folder = "/cover/";
        String result = service.urlFrom(jogo);
        result = result + folder + jogo.getCover();
        assertThat(result, containsString(folder));
    }

    @Test
    public void verificaIndisponibilidadeUrl() {
        CoverUrlService service = new CoverUrlServiceAmazonS3();

        String folder = "/cover+alugados/";
        String result = service.urlFrom(jogo);
        result = result + folder + jogo.getCover();
        assertThat(result, containsString(folder));
    }



    @Test
    public void testUrlJogoDisponivelOkCode() {
        CoverUrlService service = new CoverUrlServiceAmazonS3();
        String urltesteJogoDisponivel = service.urlFrom(jogo) + "cover/" + jogo.getCover();
        System.out.println(urltesteJogoDisponivel);
        Promise<WSResponse> promise = WS.url(urltesteJogoDisponivel).get();
        assertThat(promise.get(1000).getStatus(), equalTo(OK));
    }

    @Test
    public void testUrlJogoIndisponivelOkCode() {
        CoverUrlService service = new CoverUrlServiceAmazonS3();
        String urltesteJogoDisponivel = service.urlFrom(jogo) + "cover+alugados/" + jogo.getCover();
        System.out.println(urltesteJogoDisponivel);
        Promise<WSResponse> promise = WS.url(urltesteJogoDisponivel).get();
        assertThat(promise.get(1000).getStatus(), equalTo(OK));
     }

}
