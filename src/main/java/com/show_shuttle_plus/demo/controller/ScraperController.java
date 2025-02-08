package com.show_shuttle_plus.demo.controller;

import com.show_shuttle_plus.demo.dto.MovieDetails;
import com.show_shuttle_plus.demo.service.ScraperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/scrape")
public class ScraperController {

    private final ScraperService scraperService;

    public ScraperController(ScraperService scraperService) {
        this.scraperService = scraperService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDetails>> getTopMovies() throws IOException {
        return ResponseEntity.ok(scraperService.scrapeDetails("https://thepiratebay0.org/search/modern%20family/1/99/0"));
    }
}
