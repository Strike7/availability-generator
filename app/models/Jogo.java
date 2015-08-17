package models;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "JOGOS")
public class Jogo implements Cloneable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    public Long id;
    public final String titulo;
    public final String capa;
    public Boolean disponibilidade;

    @Temporal(TemporalType.TIMESTAMP)
    public final Date created_on = new Date();

    public Jogo() {
        this("", "", false);
    }

    public Jogo(String titulo, String capa, boolean disponiblidade){
        this.titulo = titulo;
        this.capa = capa;
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
                ", cover='" + capa + '\'' +
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
                Objects.equal(capa, jogo.capa) &&
                Objects.equal(disponibilidade, jogo.disponibilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, titulo);
    }

    public Jogo copy(Long id) {
        try {
            Jogo copy = clone();
            copy.id = id;
            return copy;
        }catch (CloneNotSupportedException e){
            return null;
        }
    }
}
