package controllers;

import models.CoverUrlService;
import models.Jogo;
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
        boolean showBar = (tipo.equals("barra"))? true: false;
        Jogo jogo = jogoRepository.find(id);
        return temporaryRedirect(coverUrlService.urlFrom(jogo, showBar));
    }
}
