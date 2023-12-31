package com.example.twitter.entity;


import com.example.twitter.exceptions.TwitterException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "twitter")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 3,message = "Fırst name can not be less than 3 chars")
    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email")
    private String email;


    @Column(name = "password")
    private String password;


    @Column(name = "phone")
    private String phone;


    @Column(name = "username")
    private String userName;


    @Column(name = "birthday")
    private LocalDate birthday;


    @Column(name = "register_date")
    private LocalDate registerDate;


    @Column(name = "address")
    private String address;


    @Column(name = "profile_picture")
    private String profilePicture;


    @Column(name = "profile_wallpaper")
    private String profileWallpaper;


    @Column(name = "liked_tweets_id")
    private List<Integer> likedTweetIdList;


    @Column(name = "retweets_tweets_id")
    private List<Integer> retweetsTweetsIdList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    List<Tweet> tweetList;




    public void removeRetweetsTweetsIdList(int id){
        if(retweetsTweetsIdList == null){
            throw new TwitterException("You didn't retweet this tweet already.", HttpStatus.BAD_REQUEST);
        }
        if(retweetsTweetsIdList.contains(id)){
            retweetsTweetsIdList = retweetsTweetsIdList.stream().filter(eachId->eachId !=id).collect(Collectors.toList());
            return;
        }
        throw new TwitterException("You didn't retweet this tweet already.", HttpStatus.BAD_REQUEST);
    }

    public void addRetweetsTweetsIdList(int id) {
        if (retweetsTweetsIdList == null) {
            retweetsTweetsIdList = new ArrayList<>();
        }
        if(retweetsTweetsIdList.contains(id)){
            throw new TwitterException("You already retweet this tweet already ...",HttpStatus.BAD_REQUEST);
        }
        retweetsTweetsIdList.add(id);
    }


    public void addTweet(Tweet tweet) {
        if (tweetList == null) {
            tweetList = new ArrayList<>();
        }
        tweetList.add(tweet);
    }

    public void removeLikedTweet(int id){
        if(likedTweetIdList == null){
            throw new TwitterException("You didn't like this tweet already.", HttpStatus.BAD_REQUEST);
        }
        if(likedTweetIdList.contains(id)){
            likedTweetIdList = likedTweetIdList.stream().filter(eachId->eachId !=id).collect(Collectors.toList());
            return;
        }
        throw new TwitterException("You didn't like this tweet already.", HttpStatus.BAD_REQUEST);
    }

    public void addLikedTweet(int id) {
        if (likedTweetIdList == null) {
            likedTweetIdList = new ArrayList<>();
        }
        if(likedTweetIdList.contains(id)){
            throw new TwitterException("You already liked this tweet already ...",HttpStatus.BAD_REQUEST);
        }
        likedTweetIdList.add(id);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}