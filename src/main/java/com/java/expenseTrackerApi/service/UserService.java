package com.java.expenseTrackerApi.service;

import com.java.expenseTrackerApi.entity.User;
import com.java.expenseTrackerApi.entity.UserModel;

import java.util.List;

public interface UserService {

    User createUser(UserModel userModel);

    User readUser(Long Id);

    User updateUser(UserModel user, Long Id);

    void deleteUser(Long Id);
}
