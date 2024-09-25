package com.example.simplechatprogramfinal.DBController.user;

import com.example.simplechatprogramfinal.Entity.Users;

public interface UserRepository {

    /**
     *
     * @param email Is the users email to find alle the users information
     * @return The correct user information from the database with the given email
     */
    Users getUserByEmail(String email);

}
