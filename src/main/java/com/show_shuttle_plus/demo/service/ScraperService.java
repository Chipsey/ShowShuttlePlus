package com.show_shuttle_plus.demo.service;

import com.show_shuttle_plus.demo.dto.MovieDetails;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    public List<MovieDetails> scrapeDetails(String link) throws IOException {
        List<MovieDetails> movies = new ArrayList<>();

        //Fetch the html page content
        Document doc = Jsoup.connect(link).get();

        //Select data
        Elements elements = doc.select("div.detName a.detLink");

        System.out.println(elements);
        for(Element movie : elements) {
            String title = movie.text();
            String magnet = movie.attr("href");
            movies.add(new MovieDetails(title, magnet));
            System.out.println(movie);
        }

        return movies;
    }
}
