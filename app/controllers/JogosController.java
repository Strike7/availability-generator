package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Jogo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.JogoRepository;

import javax.inject.Inject;
import java.util.List;

public class JogosController extends Controller{

    JogoRepository jogoRepository;

    @Inject
    public JogosController(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }

    @Transactional(readOnly = true)
    public Result list() {

        List<Jogo> jogos = jogoRepository.list();
        return ok(resultJson(Json.toJson(jogos))).as(Http.MimeTypes.JSON);
    }
    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result create() {

        JsonNode jsonJogo = request().body().asJson();
        if(jsonJogo == null || !jsonJogo.has("jogos") || jsonJogo.findPath("jogos").isArray()) {
            return badRequest();
        }
        Jogo jogo = Json.fromJson(jsonJogo.findPath("jogos"), Jogo.class);
        jogoRepository.save(jogo);
        return created();
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result update(Long id) {

        JsonNode jsonJogo = request().body().asJson();
        if(jsonJogo == null || !jsonJogo.has("jogos") || jsonJogo.findPath("jogos").isArray()) {
            return badRequest();
        }
        Jogo jogo = Json.fromJson(jsonJogo.findPath("jogos"), Jogo.class);
        jogoRepository.update(id, jogo);
        return noContent();
    }

    JsonNode resultJson(JsonNode result) {

        return Json.newObject()
                .set("jogos", result);
    }

}
