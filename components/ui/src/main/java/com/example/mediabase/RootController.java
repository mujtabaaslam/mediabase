package com.example.mediabase;

import com.example.mediabase.moviesui.MovieClient;
import com.example.mediabase.moviesui.MoviesInitialList;
import com.example.mediabase.podcastsui.PodcastClient;
import com.example.mediabase.podcastsui.PodcastInitialList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RootController {
    private MoviesInitialList moviesInitialList;
    private MovieClient movieClient;
    private PodcastClient podcastClient;
    private PodcastInitialList podcastInitialList;

    public RootController(MoviesInitialList moviesInitialList, MovieClient movieClient, PodcastClient podcastClient, PodcastInitialList podcastInitialList) {
        this.moviesInitialList = moviesInitialList;
        this.movieClient = movieClient;
        this.podcastClient = podcastClient;
        this.podcastInitialList = podcastInitialList;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model) {
        moviesInitialList.asList().forEach(movieClient::create);

        model.put("movies", movieClient.getAll());

        podcastInitialList.asList().forEach(podcastClient::save);

        model.put("podcasts", podcastClient.findAll());

        return "setup";
    }

}
