package repository;

import com.google.inject.ImplementedBy;
import models.Jogo;
import repository.impl.JogoRepositoryImpl;

import java.util.List;

public interface JogoRepository  {

    public Long save(Jogo jogo);

    public Jogo find(Long id);

    public List<Jogo> list();

    public void update(Jogo jogo);

    public void update(Long id, Jogo jogo);
}
