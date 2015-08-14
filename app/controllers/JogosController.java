package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Jogo;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repository.JogoRepository;

import javax.inject.Inject;
import java.util.List;

public class JogosController extends Controller{

    @Inject
    JogoRepository jogoRepository;

    public Result list() {
        List<Jogo> jogos = jogoRepository.list();

        return ok(resultJson(Json.toJson(jogos))).as(Http.MimeTypes.JSON);
    }

    JsonNode resultJson(JsonNode result) {
        return Json.newObject()
                .set("jogo", result);
    }

}
