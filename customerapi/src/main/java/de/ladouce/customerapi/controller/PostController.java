package de.ladouce.customerapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ladouce.customerapi.entity.Customer;
import de.ladouce.customerapi.entity.Post;
import de.ladouce.customerapi.exception.CustNotFoundException;
import de.ladouce.customerapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public JsonNode getAllPosts() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(postRepository.getAllPosts());
        return jsonNode;
    }

    @GetMapping("{lat} - {lon}")
    public JsonNode getWeatherByLatLon(@PathVariable String lat, @PathVariable String lon) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(postRepository.getAllPostsByLatLon(lat, lon));
        return jsonNode;
    }

}
