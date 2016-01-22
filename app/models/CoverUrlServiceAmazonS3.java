package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by jesse on 08/08/15.
 */
public class CoverUrlServiceAmazonS3 extends CoverUrlService {

    private final String urlBase = "http://s3-sa-east-1.amazonaws.com/strike7-image/";

    private final String barCapa = "bar.png";

    public String urlFrom(Jogo jogo, String tipo){
        switch(tipo) {
            case "barra":
                return urlFromBarra(jogo);
            case "data":
                return urlFromData(jogo);
            default:
                return urlFromCapa(jogo);
        }
    }

    private String urlFromString(boolean disp, String capa) {
        if(disp)
                return urlBase + "cover+disponiveis/" + capa;
          else
                return urlBase + "cover+alugados/" + capa;

    }

    private String urlFromBarra(Jogo jogo) {
        return urlFromString(jogo.disponibilidade, barCapa);
    }

    private String urlFromCapa(Jogo jogo) {
        return urlFromString(jogo.disponibilidade, jogo.capa + ".png");
    }

    private String urlFromData(Jogo jogo) {
        DateFormat df = new SimpleDateFormat("ddMM");
        if(jogo.disponibilidade)
            return urlFromBarra(jogo);
        else
            return urlFromString(jogo.disponibilidade, "data/" + df.format(jogo.data_reserva) + ".png");


    }
}
