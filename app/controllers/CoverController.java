package controllers;

import models.CoverUrlService;
import models.CoverUrlServiceAmazonS3;
import models.Jogo;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import repository.JogoRepository;
import repository.impl.JogoRepositoryImp;

import javax.inject.Inject;

public class CoverController extends Controller {

    JogoRepository jogoRepository;

    CoverUrlService coverUrlService;

    @Inject
    public CoverController(JogoRepository jogoRepository, CoverUrlService coverUrlService) {
        this.jogoRepository = jogoRepository;
        this.coverUrlService = coverUrlService;
    }

    @Transactional
    public Result cover(Long id) {
        Jogo jogo = jogoRepository.find(id);
        return temporaryRedirect(coverUrlService.urlFrom(jogo));
    }
}
