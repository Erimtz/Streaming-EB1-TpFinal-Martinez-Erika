package com.dh.catalog.model.catalog;


import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "Catalog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {

    @Id
    private String id;
    private String genre;
    private List<Movie> movies = new ArrayList<>();
    private List<Serie> series = new ArrayList<>();

}