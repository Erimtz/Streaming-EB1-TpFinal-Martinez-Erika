package com.dh.catalog.service;


import com.dh.catalog.exception.CatalogException;
import com.dh.catalog.exception.MessageCode;
import com.dh.catalog.model.catalog.Catalog;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.CatalogRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Autowired
    @Lazy
    private CatalogRepository self;

    public Catalog findCatalog (String genre) {
        List<Catalog> lista =  catalogRepository.findAllByGenre(genre);
        Catalog catalog =  lista.get(0);
        return catalog;
    }

    @Retry(name = "retryCreateMovie")
    @CircuitBreaker(name = "movieCreate", fallbackMethod = "findMovieFallback")
    private List<Catalog> findMovie (String genre){

        return catalogRepository.findAllByGenre(genre);
    }

    public void findMovieFallback (Movie entity, Throwable t) throws Exception {
        try {
            throw new CatalogException(MessageCode.CATALOG_NOT_FOUND);
        } catch (Exception e) {
            throw new CatalogException(MessageCode.CATALOG_MOVIE_NOT_FOUND);
        }

    }
    @Retry(name = "retryCreateSerie")
    @CircuitBreaker(name = "serieCreate", fallbackMethod = "findSerieFallback")
    private List<Catalog> findSerie (String genre){

        return catalogRepository.findAllByGenre(genre);
    }

    public void findSerieFallback (Serie entity, Throwable t) throws Exception {
        try {
            throw new CatalogException(MessageCode.CATALOG_NOT_FOUND);
        } catch (Exception e) {
            throw new CatalogException(MessageCode.CATALOG_SERIE_NOT_FOUND);
        }
    }

}

