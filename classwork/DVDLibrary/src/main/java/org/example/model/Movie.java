package org.example.model;

import lombok.Data;
import org.example.util.MPAARating;

import java.util.Date;


@Data
public class Movie {
    private String title;
    private Date releaseDate;
    private MPAARating rating;
    private String director;
    private String studio;
    private String userNote;
}
