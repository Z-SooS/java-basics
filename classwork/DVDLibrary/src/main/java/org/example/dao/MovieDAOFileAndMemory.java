package org.example.dao;

import org.example.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDAOFileAndMemory implements IMovieDAO {

    public MovieDAOFileAndMemory() {
        movies = readSavedFile();
    }

    private List<Movie> movies;

    private List<Movie> readSavedFile() {
        // TODO: 2023. 08. 22. Read actual file
        return new ArrayList<>();
    }

    private void addLineToFile() {
        // TODO: 2023. 08. 22.
    }

    private void overwriteFile() {
        // TODO: 2023. 08. 22.
    }

    @Override
    public void save(Movie savedMovie) {
        movies.add(savedMovie);
    }

    @Override
    public List<Movie> getAll() {
        return movies;
    }

    @Override
    public List<Movie> findByTitle(String searchTitle) {
        return movies.stream().filter(m -> m.getTitle().contains(searchTitle)).toList();
    }

    @Override
    public void remove(Movie movieToRemove) {
        movies.remove(movieToRemove);
    }

    @Override
    public void edit(Movie oldMovie, Movie updatedMovie) {
        movies.remove(oldMovie);
        movies.add(updatedMovie);
    }
}
