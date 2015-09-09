import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import models.Jogo;
import org.junit.*;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.mvc.*;
import play.test.*;
import repository.JogoRepository;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static play.test.Helpers.*;
import static org.mockito.Mockito.*;

public class CoverRouteTest {

    @Inject
    Application application;

    @Before
    public void setup() {
        Jogo jogoDisponivel = new Jogo("a", "b", true);
        Jogo jogoIndisponivel = new Jogo("c", "d", false);

        JogoRepository repo = mock(JogoRepository.class);
        when(repo.find(10L)).thenReturn(jogoDisponivel);
        when(repo.find(11L)).thenReturn(jogoIndisponivel);

        Module testModule = new AbstractModule() {
            @Override
            public void configure() {
                bind(JogoRepository.class)
                        .toInstance(repo);
            }
        };

        GuiceApplicationBuilder builder = new GuiceApplicationLoader()
                .builder(new ApplicationLoader.Context(Environment.simple()))
                .overrides(testModule);
        Guice.createInjector(builder.applicationModule()).injectMembers(this);
    }

    @After
    public void teardown() {
        Helpers.stop(application);
    }


    @Test
    public void testCoverDisponiveis() {
        running(application, () -> {
            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/api/capa/10");
            Result result = route(application, request);
            assertThat(result.status(), equalTo(TEMPORARY_REDIRECT));
            assertThat(result.redirectLocation(), containsString("/cover+disponiveis/"));
        });
    }

    @Test
    public void testCoverAlugados() {
        running(application, () -> {
            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/api/capa/11");
            Result result = route(application, request);
            assertThat(result.status(), equalTo(TEMPORARY_REDIRECT));
            assertThat(result.redirectLocation(), containsString("/cover+alugados/"));
        });
    }

    @Test
    public void testCoverBarraDisponivel(){
        running(application, () -> {
            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/api/capa/10?tipo=barra");
            Result result = route(application, request);
            assertThat(result.status(), equalTo(TEMPORARY_REDIRECT));
            assertThat(result.redirectLocation(), containsString("/cover+disponiveis/bar.png"));
        });
    }

    @Test
    public void testCoverBarraAlugado() {
        running(application, () -> {
            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/api/capa/11?tipo=barra");
            Result result = route(application, request);
            assertThat(result.status(), equalTo(TEMPORARY_REDIRECT));
            assertThat(result.redirectLocation(), containsString("/cover+alugados/bar.png"));
        });
    }
}
