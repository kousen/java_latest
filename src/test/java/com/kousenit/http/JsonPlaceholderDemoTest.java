package com.kousenit.http;

import org.junit.jupiter.api.Test;

import static com.kousenit.http.JsonPlaceholderDemo.*;

class JsonPlaceholderDemoTest {

    private final JsonPlaceholderDemo demo = new JsonPlaceholderDemo();

    @Test
    void getPost() {
        BlogPost post1 = demo.getPost(1);
        System.out.println("Id: " + post1.id());
        System.out.println("User ID: " + post1.userId());
        System.out.println("Title: " + post1.title());
        System.out.println("Body: " + post1.body());
    }

    @Test
    void addPost() {
        BlogPost post = new BlogPost(1, 0, "foo", "bar");
        BlogPost newPost = demo.addPost(post);
        System.out.println("Id: " + newPost.id());
        System.out.println("User ID: " + newPost.userId());
        System.out.println("Title: " + newPost.title());
        System.out.println("Body: " + newPost.body());
    }

    @Test
    void getPostAsync() {
        BlogPost post2 = demo.getPostAsync(2);
        System.out.println("Id: " + post2.id());
        System.out.println("User ID: " + post2.userId());
        System.out.println("Title: " + post2.title());
        System.out.println("Body: " + post2.body());
    }
}