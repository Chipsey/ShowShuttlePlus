package com.show_shuttle_plus.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {

    private String title;
    private String description;
    private String cover;
    private String imdbRating;
    private List<String> genre;
}
