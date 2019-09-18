package com.example.mediabase.podcastsui;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;

import java.util.List;

public class PodcastClient {

    private static ParameterizedTypeReference<List<PodcastUI>> podcastListType = new ParameterizedTypeReference<List<PodcastUI>>() {
    };
    private RestOperations restOperations;
    private String podcastsURL;


    public PodcastClient(String podcastsURL, RestOperations restOperations) {
        this.restOperations = restOperations;
        this.podcastsURL = podcastsURL;
    }

    public void save(PodcastUI podcast) {
        restOperations.postForEntity(podcastsURL, podcast, PodcastUI.class);
    }


    public Iterable<PodcastUI> findAll() {
        return restOperations.exchange(podcastsURL, HttpMethod.GET, null, podcastListType).getBody();
    }




}
