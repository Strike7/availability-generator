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
    public void testDisponibilidade(){
        jogo.setDisponibilidade(true);
        assertThat(jogo.isDisponiblidade(), equalTo(Boolean.TRUE));
        jogo.setDisponibilidade(false);
        assertThat(jogo.isDisponiblidade(), equalTo(Boolean.FALSE));
    }

}
