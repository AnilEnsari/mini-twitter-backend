package com.example.twitter.dto;


public record UserResponse(Integer id,String firstName, String lastName, String email , String password , String phone
        , String userName, String birthday, String registerDate , String address, String profilePicture) {
}
