package repository.impl;

import models.Jogo;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import repository.JogoRepository;

import javax.inject.Singleton;
import java.util.Arrays;

/**
 * Created by jesse on 11/08/15.
 */
@Singleton
public class JogoRepositoryImp implements JogoRepository{

    @Override
    public Long save(Jogo jogo) {
        JPA.em().persist(jogo);
        return jogo.id;
    }

    @Override
    public Jogo find(Long id) {
        System.out.println("id  " + id + " \n" + JPA.em().createQuery("select e from Jogo e").getResultList().toString());
        return JPA.em().find(Jogo.class, id);
    }
}
