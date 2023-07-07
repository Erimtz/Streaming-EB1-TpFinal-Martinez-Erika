package com.dh.catalog.client;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-movie",url = "http://localhost:8085")
public interface MovieServiceClient {

	@GetMapping("/api/v1/movies/{genre}")
	List<MovieDto> getMovieByGenre(@PathVariable (value = "genre") String genre);


	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	 class MovieDto{

		private String id;

		private String name;

		private String genre;

	}

}
