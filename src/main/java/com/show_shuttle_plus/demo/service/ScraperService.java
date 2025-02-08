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

        // Select all rows in the torrent table
        Elements rows = doc.select("tr");

        for (Element row : rows) {
            try {
                MovieDetails movieDetails = new MovieDetails();
                // Movie Name
                String title = row.select("div.detName a.detLink").text();
                // Magnet Link
                String magnetLink = row.select("td a[title='Download this torrent using magnet']").attr("href");

                // File Size
                String fileSize = row.select("font.detDesc").text(); // Extracts entire line
                fileSize = fileSize.replaceAll(".*Size ", "").replaceAll(", ULed.*", ""); // Extracts only size

                movieDetails.setTitle(title);
                movieDetails.setMagnet(magnetLink);
                movieDetails.setSize(fileSize);

                // Add to movie list
                movies.add(movieDetails);
            } catch (Exception e) {
                System.err.println("Skipping row due to error: " + e.getMessage());
            }
        }

        return movies;
    }
}
