package controllers;

import models.CoverUrlService;
import models.Jogo;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.JogoRepository;

import javax.inject.Inject;

public class CoverController extends Controller {

    JogoRepository jogoRepository;

    CoverUrlService coverUrlService;

    @Inject
    public CoverController(JogoRepository jogoRepository, CoverUrlService coverUrlService) {
        this.jogoRepository = jogoRepository;
        this.coverUrlService = coverUrlService;
    }

    @Transactional(readOnly = true)
    public Result cover(Long id, String tipo) {

        Jogo jogo = jogoRepository.find(id);
        Logger.debug(jogo.toString());
        return temporaryRedirect(coverUrlService.urlFrom(jogo, tipo));
    }
}
