package de.ladouce.customerapi.repository;

import de.ladouce.customerapi.entity.Post;

public interface PostRepository {
    public String getAllPosts();
    public String getAllPostsByLatLon(String lat, String lon);
    public Post createPost(Post post);
}
