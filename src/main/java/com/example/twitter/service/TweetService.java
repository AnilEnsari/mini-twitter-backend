package com.example.twitter.service;

import com.example.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> findAllTweetsByFollowing(int id);
    List<Tweet> findAllTweets();

    Tweet findTweetById(int id);

    Tweet saveTweet(Tweet tweet);

    Tweet deleteTweet(int id);

    List<Tweet> findTweetByUserId(int id);
}