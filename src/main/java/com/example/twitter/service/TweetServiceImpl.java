package com.example.twitter.service ;

import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exceptions.TwitterException;
import com.example.twitter.repository.TweetRepository;
import com.example.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Tweet> findAllTweetsByFollowing(int id) {
        return tweetRepository.findAllTweetsByFollowing(id);
    }

    @Override
    public List<Tweet> findAllTweets() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet findTweetById(int id) {
        Optional<Tweet> optionalTweet = tweetRepository.findById(id);
        if(optionalTweet.isPresent()){
            return optionalTweet.get();
        }
        throw new TwitterException("id is not found ... ", HttpStatus.NOT_FOUND);
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        if(tweet.getText() == null){
            throw new TwitterException("Tweet texti boş olamaz ... ",HttpStatus.BAD_REQUEST);
        }
        User user = tweet.getUser();
        user.addTweet(tweet);
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet deleteTweet(int id,User user) {


        Tweet tweet = findTweetById(id);
        if(user.getId()==tweet.getUser().getId()){
        tweetRepository.delete(tweet);
        return tweet;
        }
        else{
            throw new TwitterException("You cant delete the tweet you didnt send",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Tweet> findTweetByUserId(int id) {
        List<Tweet> tweetList = tweetRepository.findTweetByUserId(id);
        if(!tweetList.isEmpty()){
            return tweetList;

        }
        throw new TwitterException("id si verilen kullanıcı tweeti yoktur. " , HttpStatus.NOT_FOUND);
    }



}