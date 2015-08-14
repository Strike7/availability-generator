package repository.impl;

import models.Jogo;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import repository.JogoRepository;

import javax.inject.Singleton;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jesse on 11/08/15.
 */
@Singleton
public class JogoRepositoryImpl implements JogoRepository{

    @Override
    public Long save(Jogo jogo) {
        JPA.em().persist(jogo);
        return jogo.id;
    }

    @Override
    public Jogo find(Long id) {
        return JPA.em().find(Jogo.class, id);
    }

    @Override
    public List<Jogo> list() {
        CriteriaQuery<Jogo> queryBuilder = JPA.em().getCriteriaBuilder().createQuery(Jogo.class);
        return JPA.em().createQuery(
                queryBuilder.select(queryBuilder.from(Jogo.class))).getResultList();
    }

    @Override
    public void update(Long id, Jogo jogo) {
        jogo = jogo.copy(id);
        JPA.em().merge(jogo);
    }

    @Override
    public void update(Jogo jogo) {
        update(jogo.id , jogo);
    }
}
