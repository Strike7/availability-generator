package models;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jogo implements Cloneable {

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

    @Override
    public Jogo clone() throws CloneNotSupportedException {
        return (Jogo) super.clone();
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", cover='" + cover + '\'' +
                ", disponibilidade=" + disponibilidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogo jogo = (Jogo) o;
        return Objects.equal(id, jogo.id) &&
                Objects.equal(titulo, jogo.titulo) &&
                Objects.equal(cover, jogo.cover) &&
                Objects.equal(disponibilidade, jogo.disponibilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, titulo);
    }
}
