package com.dh.catalog.client;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="api-serie",url = "http://localhost:8085")
public interface SerieServiceClient {

	@GetMapping("/api/v1/series/{genre}")
	List<SerieDto> getSerieByGenre(@PathVariable (value = "genre") String genre);

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	 class SerieDto{

		private String id;

		private String name;

		private String genre;

	}

}
