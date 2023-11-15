package com.example.twitter.dto;


import java.util.List;

public record TweetResponse(Integer tweetId, String text  , List<Integer> commentsTweetIdList ,
                            String tweetDate, List<Integer> likedUserIdList,List<Integer> retweetsUserIdList , UserTweetResponse userTweetResponse, Integer commentedTo){

}