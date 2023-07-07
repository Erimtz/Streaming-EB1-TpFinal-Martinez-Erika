package com.dh.catalog;

import com.dh.catalog.model.catalog.Catalog;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.CatalogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients

public class ApiCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCatalogApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadDataMovie(CatalogRepository repository) {
        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }
            Movie movieA = new Movie("1", "Pelicula 1","Terror");
            Movie movieB = new Movie("2", "Pelicula 2", "Comedia");
            Movie movieC = new Movie("3", "Pelicula 3", "Ficcion");


            Catalog catalogTerror = new Catalog();
            Catalog catalogComedia = new Catalog();
            Catalog catalogFiccion = new Catalog();

            catalogTerror.setGenre("Terror");
            catalogTerror.setMovies(Arrays.asList(movieA));

            catalogTerror.setGenre("Comedia");
            catalogTerror.setMovies(Arrays.asList(movieB));

            catalogTerror.setGenre("Ficcion");
            catalogTerror.setMovies(Arrays.asList(movieC));

            repository.save(catalogTerror);
            repository.save(catalogComedia);
            repository.save(catalogFiccion);

        };
    }

    @Bean
    public CommandLineRunner loadDataSerie(CatalogRepository repository) {
        String baseUrl = "www.netflix.com/series";

        return (args) -> {
            if (!repository.findAll().isEmpty()) {
                return;
            }


            Serie serieA = new Serie( "6","Serie A", "Terror");
            Serie serieB = new Serie( "7","Serie B", "Comedia");

            Catalog catalogTerror = repository.findAllByGenre("Terror").get(0);
            Catalog catalogComedia = repository.findAllByGenre("Comedia").get(0);


            catalogTerror.setSeries(Arrays.asList(serieA));
            catalogComedia.setSeries(Arrays.asList(serieB));

            repository.save(catalogTerror);
            Catalog catalogComedia1 = repository.save(catalogComedia);
            System.out.println(catalogComedia1.getGenre());

        };
    }

}
