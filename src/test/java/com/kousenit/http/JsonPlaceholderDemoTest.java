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

    @Test
    void testTrulyAsyncBehavior() throws Exception {
        // Start multiple async requests
        var future1 = demo.getPostTrulyAsync(1);
        var future2 = demo.getPostTrulyAsync(2);
        var future3 = demo.getPostTrulyAsync(3);
        
        // All requests are now in flight concurrently
        System.out.println("All requests sent, waiting for responses...");
        
        // Wait for all to complete
        BlogPost post1 = future1.get(5, java.util.concurrent.TimeUnit.SECONDS);
        BlogPost post2 = future2.get(5, java.util.concurrent.TimeUnit.SECONDS);
        BlogPost post3 = future3.get(5, java.util.concurrent.TimeUnit.SECONDS);
        
        // Verify we got all three
        System.out.printf("Got posts with IDs: %d, %d, %d%n", 
            post1.id(), post2.id(), post3.id());
    }
}