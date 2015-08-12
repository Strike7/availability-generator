package modules;

import com.google.inject.AbstractModule;
import models.CoverUrlService;
import models.CoverUrlServiceAmazonS3;
import repository.JogoRepository;
import repository.impl.JogoRepositoryImp;

public class RepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JogoRepository.class)
                .to(JogoRepositoryImp.class);
        bind(CoverUrlService.class)
                .to(CoverUrlServiceAmazonS3.class);
    }
}
