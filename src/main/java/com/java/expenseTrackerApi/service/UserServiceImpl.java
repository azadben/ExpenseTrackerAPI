package com.java.expenseTrackerApi.service;

import com.java.expenseTrackerApi.entity.User;
import com.java.expenseTrackerApi.entity.UserModel;
import com.java.expenseTrackerApi.exceptions.ItemAlreadyExistsException;
import com.java.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.java.expenseTrackerApi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

   public UserServiceImpl(UserRepository userRepository)
   {
       this.userRepository=userRepository;
   }

    @Override
    public User createUser(UserModel userModel) {
       if(userRepository.existsByEmail(userModel.getEmail()))
       {
           throw new ItemAlreadyExistsException("User is already registered with email: "+userModel.getEmail());
       }
       User newUser = new User();
        BeanUtils.copyProperties(userModel,newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long Id) {
        return userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("No user with this Id: "+Id));
    }

    @Override
    public User updateUser(UserModel userModel, Long Id) {
        User existingUser = readUser(Id);
        existingUser.setName(userModel.getName()!=null ? userModel.getName() : existingUser.getName());
        existingUser.setAge(userModel.getAge() != null ? userModel.getAge() : existingUser.getAge());
        existingUser.setEmail(userModel.getEmail()!=null ? userModel.getEmail() : existingUser.getEmail());
        existingUser.setPassword(userModel.getPassword()!=null ? userModel.getPassword() : existingUser.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long Id) {
        userRepository.deleteById(Id);
    }


}
