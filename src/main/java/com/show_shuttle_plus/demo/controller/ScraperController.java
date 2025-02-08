package com.show_shuttle_plus.demo.controller;

import com.show_shuttle_plus.demo.dto.MovieDetails;
import com.show_shuttle_plus.demo.service.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class ScraperController {

    private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDetails>> getTopMovies(
            @RequestParam String title,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "0") int season,
            @RequestParam(defaultValue = "0") int episode) throws IOException {

        // Build the title with season and episode
        StringBuilder searchQuery = new StringBuilder(title);

        if (season != 0) {
            searchQuery.append(String.format(" S%02d", season));
        }
        if (episode != 0) {
            searchQuery.append(String.format("E%02d", episode));
        }

        // Replace spaces with %20 for URL encoding
        String formattedTitle = searchQuery.toString().replace(" ", "%20");

        // Construct the final URL
        String url = String.format("https://thepiratebay0.org/search/%s/%d/99/0", formattedTitle, page);

        return ResponseEntity.ok(scraperService.scrapeDetails(url));
    }

}
