package com.dh.catalog.model.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

        @Id
        private String id;
        private String name;
        private String genre;
}

