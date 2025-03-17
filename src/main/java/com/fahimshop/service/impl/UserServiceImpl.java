package com.fahimshop.service.impl;

import com.fahimshop.model.UserD;
import com.fahimshop.repository.UserRepository;
import com.fahimshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
  public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


      @Override
     public UserD save(UserD user) {
          UserD save = userRepository.save(user);
          return save;
      }
  }
