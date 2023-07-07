package com.dh.apiserie.controller;

import com.dh.apiserie.event.AddNewSerieEventProducer;
import com.dh.apiserie.exception.SerieException;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private SerieService serieService;

    private AddNewSerieEventProducer addNewSerieEventProducer;


    public SerieController(SerieService serieService, AddNewSerieEventProducer addNewSerieEventProducer) {
        this.serieService = serieService;
        this.addNewSerieEventProducer = addNewSerieEventProducer;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre) throws SerieException {
        return ResponseEntity.ok(serieService.getSeriesByGenre(genre));
    }

    @PostMapping("/save")
    void createNewSerie(@RequestBody Serie serie) throws SerieException {
        serieService.createSerie(serie);
    }

}
