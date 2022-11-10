package de.ladouce.customerapi.repository;

import de.ladouce.customerapi.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostRepositoryImpl implements PostRepository{

    @Value("${external.api.url}")
    private String postApiBaseUrl;

    @Value("${api.key}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAllPosts(){
       return restTemplate.getForObject(postApiBaseUrl+"/posts",String.class);
        //System.out.println(result);


    }

    @Override
    public String getAllPostsByLatLon(String lat, String lon){
        return restTemplate.getForObject(postApiBaseUrl+"/weather?lat="+lat+"&lon="+lon+"&appid="+apikey,String.class);
    }

    @Override
    public Post createPost(Post post){
        Post model = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Post> httpEntity = new HttpEntity<>(post, headers);
        ResponseEntity<Post> newPostEntity = restTemplate.postForEntity(postApiBaseUrl+"/posts",httpEntity,Post.class);

        if(newPostEntity.getStatusCode() == HttpStatus.CREATED)
            model = newPostEntity.getBody();
        return model;

    }

}
