package com.dh.movie.service;


import com.dh.movie.event.AddNewMovieEventProducer;
import com.dh.movie.exception.MessageCode;
import com.dh.movie.exception.MovieException;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {


    private final MovieRepository movieRepository;

    private final AddNewMovieEventProducer addNewMovieEventProducer;

    public MovieService(MovieRepository movieRepository, AddNewMovieEventProducer addNewMovieEventProducer) {
        this.movieRepository = movieRepository;
        this.addNewMovieEventProducer = addNewMovieEventProducer;
    }

    public List<Movie> findByGenre(String genre) throws MovieException {
        try {
            return movieRepository.findByGenre(genre);
        }catch (Exception e){
            throw new MovieException(MessageCode.MOVIE_NOT_FOUND);
        }
    }

    public Movie save(Movie movie) throws MovieException {
        try {
            Movie movieSaved = movieRepository.save(movie);

            addNewMovieEventProducer.publishAddNewMovieEvent(new AddNewMovieEventProducer.Data(movieSaved.getId().intValue(), movieSaved.getName(), movieSaved.getGenre()));

            return movieSaved;
        } catch (Exception e){
            throw new MovieException(MessageCode.MOVIE_EXISTS);
        }
    }
}
