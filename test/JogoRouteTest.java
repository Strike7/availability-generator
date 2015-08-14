import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Module;
import models.Jogo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.db.jpa.JPA;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.routing.RouterBuilderHelper;
import play.test.Helpers;
import play.test.WithApplication;
import repository.JogoRepository;

import javax.inject.Inject;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.test.Helpers.*;
import static org.junit.Assert.*;

public class JogoRouteTest {

    final Jogo jogoDisponivel = new Jogo("a", "b", true);
    final Jogo jogoIndisponivel = new Jogo("c", "d", false);
    @Inject
    Application app;

    @Before
    public void setup() {
        Jogo jogoDisponivel = new Jogo("a", "b", true);
        Jogo jogoIndisponivel = new Jogo("c", "d", false);

        JogoRepository repo = mock(JogoRepository.class);
        when(repo.find(10L)).thenReturn(jogoDisponivel);
        when(repo.find(11L)).thenReturn(jogoIndisponivel);
        when(repo.list()).thenReturn(Arrays.asList(jogoDisponivel, jogoIndisponivel));

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
        Helpers.stop(app);
    }

    @Test
    public void listJogosContentTypeTest() {

        running(app, () -> {

            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/jogos");
            Result response = route(request);
            assertThat(response.status(), equalTo(OK));
            assertThat(response.contentType(), equalTo(Http.MimeTypes.JSON));
        });
    }

    @Test
    public void listJogosBodyTest() {

        final JsonNode expected = Json.newObject()
                .set("jogos", Json.newArray()
                        .add(Json.toJson(jogoDisponivel))
                        .add(Json.toJson(jogoIndisponivel)));
        running(app, () ->{

            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(GET)
                    .uri("/jogos");
            Result response = route(request);
            assertThat(Json.parse(contentAsString(response)), equalTo(expected));
        });
    }

    @Test
    public void updateJogosBadRequestTest() {

        running(app, () ->{

            Http.RequestBuilder request = new Http.RequestBuilder()
                    .method(PUT)
                    .uri("/jogos/10")
                    .bodyJson(
                            Json.newObject());
            Result response = route(request);
            assertThat(response.status(), equalTo(BAD_REQUEST));
        });
    }

    @Test
    public void updateJogosSuccessTest() {

        running(app, () -> {

                Http.RequestBuilder request = new Http.RequestBuilder()
                        .method(PUT)
                        .uri("/jogos/10")
                        .bodyJson(
                                Json.newObject().
                                        set("jogos", Json.toJson(jogoDisponivel)));
                Result response = route(request);
                assertThat(response.status(), equalTo(NO_CONTENT));
        });
    }


}
