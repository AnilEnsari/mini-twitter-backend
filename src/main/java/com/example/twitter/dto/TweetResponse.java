package com.example.twitter.dto;


import java.time.LocalDate;
import java.util.Date;

public record TweetResponse(Integer tweetId,String text , Integer likes , Integer retweet , Integer commentsTotal ,
                            String tweetDate, UserTweetResponse userTweetResponse){

}
