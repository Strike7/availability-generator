package models;

/**
 * Created by jesse on 08/08/15.
 */
public class CoverUrlServiceAmazonS3 extends CoverUrlService {

    private final String urlBase = "http://s3-sa-east-1.amazonaws.com/strike7-image/";

    private final String barCapa = "bar.png";

    public String urlFrom(Jogo jogo, boolean showBar){
        String capa = (showBar)? barCapa: jogo.capa;
        if (jogo.disponibilidade){
            return urlBase + "cover+disponiveis/" + capa;
        }else {
            return urlBase + "cover+alugados/" + capa;
        }
    }
}
