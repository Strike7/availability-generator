package repository;

import com.google.inject.ImplementedBy;
import models.Jogo;
import repository.impl.JogoRepositoryImpl;

import java.util.List;

@ImplementedBy(JogoRepositoryImpl.class)
public interface JogoRepository  {

    public Long save(Jogo jogo);

    public Jogo find(Long id);

    public List<Jogo> list();
}
