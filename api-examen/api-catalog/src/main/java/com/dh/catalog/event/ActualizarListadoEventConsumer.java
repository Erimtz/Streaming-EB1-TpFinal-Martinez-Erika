package com.dh.catalog.event;


import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.catalog.Catalog;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.CatalogRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActualizarListadoEventConsumer {

    private final CatalogRepository catalogRepository;


    public ActualizarListadoEventConsumer(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ADD_NEW_SERIE)
    public void listenSerie (ActualizarListadoEventConsumer.DataSerie message){
        System.out.print("NOMBRE DE CATEGORIA SERIE " + message.name);
        Serie serie = new Serie(message.id, message.name, message.genre);
        Catalog catalog = new Catalog();
        List<Catalog> catalogList = catalogRepository.findAllByGenre(message.genre);

        if(catalogList == null || catalogList.isEmpty()){
            catalog.setGenre(message.genre);
        }else{
            catalog = catalogList.get(0);
        }

        catalog.getSeries().add(serie);

        catalogRepository.save(catalog);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ADD_NEW_MOVIE)
    public void listenMovie (ActualizarListadoEventConsumer.DataMovie message){
        System.out.print("NOMBRE DE CATEGORIA MOVIE " + message.name);
        Movie movie = new Movie(message.id.toString(), message.name, message.genre);

        Catalog catalog = new Catalog();
        List<Catalog> catalogList = catalogRepository.findAllByGenre(message.genre);

        if(catalogList == null || catalogList.isEmpty()){
            catalog.setGenre(message.genre);
        }else{
            catalog = catalogList.get(0);
        }

        catalog.getMovies().add(movie);

        catalogRepository.save(catalog);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DataSerie {
        private String id;
        private String name;
        private String genre;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DataMovie {
        private Integer id;
        private String name;
        private String genre;
    }

}
