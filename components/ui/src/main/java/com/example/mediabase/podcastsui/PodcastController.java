package com.example.mediabase.podcastsui;

import com.example.mediabase.podcastsui.PodcastClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class PodcastController {
    private PodcastClient podcastClient;

    public PodcastController(PodcastClient podcastClient) {
        this.podcastClient = podcastClient;
    }

    @GetMapping("/podcasts")
    public String allPodcasts(Map<String, Object> model) {
        model.put("podcasts", podcastClient.findAll() );
        return "podcasts";
    }

}