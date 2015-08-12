package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jesse on 08/08/15.
 */
@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;
    public final String titulo;
    public final String cover;
    public Boolean disponibilidade;


    public Jogo(String titulo, String cover, boolean disponiblidade){
        this.titulo = titulo;
        this.cover = cover;
        this.disponibilidade = disponiblidade;
    }


}
