package com.dh.apiserie.service;

import com.dh.apiserie.event.AddNewSerieEventProducer;
import com.dh.apiserie.exception.MessageCode;
import com.dh.apiserie.exception.SerieException;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;
    private final AddNewSerieEventProducer addNewSerieEventProducer;

    public SerieService(SerieRepository repository, AddNewSerieEventProducer addNewSerieEventProducer) {
        this.repository = repository;
        this.addNewSerieEventProducer = addNewSerieEventProducer;
    }

    public List<Serie> getSeriesByGenre(String genre) throws SerieException {
        try {
            return repository.findAllByGenre(genre);
        }catch (Exception e){
            throw new SerieException(MessageCode.SERIE_NOT_FOUND);
        }

    }

    public Serie createSerie(Serie serie) throws SerieException {
        try {
            addNewSerieEventProducer.publishAddNewSerieEvent(new AddNewSerieEventProducer.Data(serie.getId(), serie.getName(), serie.getGenre()));
            return repository.save(serie);
        }catch (Exception e){
            throw new SerieException(MessageCode.SERIE_EXISTS);
        }
    }

}
