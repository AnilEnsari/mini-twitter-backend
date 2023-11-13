package com.example.twitter.controller;

import com.example.twitter.dto.TweetResponse;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.TweetService;
import com.example.twitter.service.UserService;
import com.example.twitter.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;
    private UserService userService;

    @Autowired
    public TweetController(TweetService tweetService,UserService userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @GetMapping("/homepage/{id}")
    public List<TweetResponse> findAllTweetsByFollowing(@PathVariable int id){
        return Converter.tweetResponseListConverter(tweetService.findAllTweetsByFollowing(id));
    }

    @GetMapping("/profile/{userId}")
    public List<TweetResponse> findAllTweetsByUserId(@PathVariable int userId){
        return Converter.tweetResponseListConverter(tweetService.findTweetByUserId(userId));
    }


    @GetMapping("/{id}")
    public TweetResponse findTweetById(@PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.findTweetById(id));
    }

    @PostMapping("/")
    public TweetResponse saveTweet(@RequestBody Tweet tweet){
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @PutMapping("/{id}")
    public TweetResponse updateTweet(@RequestBody Tweet tweet,@PathVariable int id){
        User user = userService.findByUserId(tweet.getUser().getId());
        tweet.setId(id);
        tweet.setUser(user);
        return Converter.tweetResponseConverter(tweetService.saveTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public TweetResponse deleteTweet(@PathVariable int id){
        return Converter.tweetResponseConverter(tweetService.deleteTweet(id));
    }



}