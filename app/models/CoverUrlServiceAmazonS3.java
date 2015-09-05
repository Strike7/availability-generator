package models;

/**
 * Created by jesse on 08/08/15.
 */
public class CoverUrlServiceAmazonS3 extends CoverUrlService {

    private final String urlBase = "http://s3-sa-east-1.amazonaws.com/strike7-image/";


    public String urlFrom(Jogo jogo){
        if (jogo.disponibilidade){
            return urlBase + "cover/" + jogo.capa;
        }else {
            return urlBase + "cover+alugados/" + jogo.capa;
        }
    }
}
