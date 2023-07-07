package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.catalog.Catalog;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;

	private final SerieServiceClient serieServiceClient;

	private final CatalogService catalogService;

	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogService catalogService) {
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
		this.catalogService = catalogService;
	}

	@GetMapping({"/{genre}"})
	ResponseEntity<Catalog> getGenreMovie(@PathVariable String genre) {
		return ResponseEntity.ok(this.catalogService.findCatalog(genre));
	}

	@GetMapping("/api/v1/series/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDto>> getSerieByGenre(@PathVariable String genre) {
		return ResponseEntity.ok(this.serieServiceClient.getSerieByGenre(genre));
	}

}
