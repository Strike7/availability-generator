import static org.junit.Assert.*;

import org.junit.Test;
import models.Jogo;
import play.db.jpa.JPA;
import play.libs.ws.*;
import play.test.*;
import repository.JogoRepository;
import repository.impl.JogoRepositoryImpl;

import static org.hamcrest.CoreMatchers.*;
import javax.inject.Inject;
import java.util.List;

public class ModelJogoTest extends WithApplication {

    final Jogo jogo = new Jogo("Mortal Kombat X","mkx.png" ,false);

    @Inject WSClient ws;

    @Test
    public void testDados(){
        String expectTitulo = "Mortal Kombat X";
        assertThat(jogo.titulo, equalTo(expectTitulo));
    }

    @Test
    public void testAppPersistence(){
        JPA.withTransaction(() -> {
            JogoRepository repo = new JogoRepositoryImpl();
            Long id = repo.save(jogo);
            assertThat(id.compareTo(0L), equalTo(1));
            assertThat(jogo.id, allOf(notNullValue(), sameInstance(id)));
        });
    }

    @Test
    public void testJogoList(){
        JPA.withTransaction(()->{
            JogoRepository repo = new JogoRepositoryImpl();
            repo.save(jogo);
            List<Jogo> result = repo.list();
            assertThat(result.size(), equalTo(1));
            assertThat(result.contains(jogo), equalTo(Boolean.TRUE));
        });
    }

    @Test
    public void testJogoUpdate(){
        JPA.withTransaction(()->{
            JogoRepository repo = new JogoRepositoryImpl();
            repo.save(jogo);
            Jogo expected = jogo.clone();
            expected.disponibilidade = !expected.disponibilidade;
            repo.update(expected);
            Jogo result = repo.find(jogo.id);
            assertThat(result, equalTo(expected));
        });
    }

    @Test
    public void testCover(){
        String expected = "mkx.png";
        assertThat(jogo.capa, equalTo(expected));
    }

    @Test
    public void testDisponibilidade(){
        jogo.disponibilidade = true;
        assertThat(jogo.disponibilidade, equalTo(Boolean.TRUE));
        jogo.disponibilidade = false;
        assertThat(jogo.disponibilidade, equalTo(Boolean.FALSE));
    }

}
