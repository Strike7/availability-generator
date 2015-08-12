package repository;

import com.google.inject.ImplementedBy;
import models.Jogo;
import repository.impl.JogoRepositoryImp;

import javax.inject.Singleton;

@ImplementedBy(JogoRepositoryImp.class)
public interface JogoRepository  {

    public Long save(Jogo jogo);

    public Jogo find(Long id);
}
