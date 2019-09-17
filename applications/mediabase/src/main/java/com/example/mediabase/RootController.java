package com.example.mediabase;

import com.example.mediabase.movies.MoviesBean;
import com.example.mediabase.podcasts.Podcast;
import com.example.mediabase.podcasts.PodcastInitialList;
import com.example.mediabase.podcasts.PodcastRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.mediabase.movies.MoviesInitialList;

import java.util.Map;

@Controller
public class RootController {
    private MoviesInitialList moviesInitialList;
    private MoviesBean moviesBean;
    private PodcastRepository podcastRepository;
    private PodcastInitialList podcastInitialList;

    public RootController(MoviesInitialList moviesInitialList, MoviesBean moviesBean, PodcastRepository podcastRepository, PodcastInitialList podcastInitialList) {
        this.moviesInitialList = moviesInitialList;
        this.moviesBean = moviesBean;
        this.podcastRepository = podcastRepository;
        this.podcastInitialList = podcastInitialList;
    }

    @GetMapping("/")
    public String rootPath() {
        return "index";
    }

    @GetMapping("/setup")
    public String setupDatabase(Map<String, Object> model) {
        moviesInitialList.asList().forEach(moviesBean::addMovie);

        model.put("movies", moviesBean.getMovies());

        podcastInitialList.asList().forEach(podcastRepository::save);

        model.put("podcasts", podcastRepository.findAll());

        return "setup";
    }

}
