package org.example.dao;

import org.example.model.Movie;

import java.util.List;

public interface IMovieDAO {
    void save(Movie savedMovie);
    List<Movie> getAll();
    List<Movie> findByTitle(String title);
    void remove(Movie movieToRemove);

    void edit(Movie oldMovie, Movie updatedMovie);

}
