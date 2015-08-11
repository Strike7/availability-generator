package models;

/**
 * Created by jesse on 08/08/15.
 */
public class Jogo {

    private final String titulo;
    private final String cover;
    private boolean disponiblidade = false;
    private boolean disponibilidade;


    public Jogo(String titulo, String cover, boolean disponiblidade){
        this.titulo = titulo;
        this.cover = cover;
        this.disponiblidade = disponiblidade;

    }

    public String getTitulo() {
        return titulo;
    }

    public String getCover() {
        return cover;
    }

    public boolean isDisponiblidade() {
        return disponiblidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
