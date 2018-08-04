package com.pccw.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.domain.User;
import com.pccw.repository.UserRepository;
import com.pccw.representation.UserRep;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ryan.zhu on 04/08/2018.
 */

@Service
public class UserManagementServiceImpl implements UserManagementService {
    @Autowired
    private UserRepository userRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public UserManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<User> getUsers() {
        List<User> userList = Lists.newArrayList(this.userRepository.findAll());

        return userList;
    }

    public User addUser(String json) throws Exception{
        UserRep userRep = this.mapper.readValue(json, UserRep.class);
        User user = new User(userRep);

        user = this.userRepository.save(user);

        return user;
    }

    public User getUser(long userId) {
        User user = this.userRepository.findById(userId);

        return user;
    }

    public void deleteUser(long userId) {
        this.userRepository.deleteByUserId(userId);
    }

    public User updateUser(long userId, String json) throws Exception {
        UserRep userRep = this.mapper.readValue(json, UserRep.class);

        User user = new User(userRep);
        user = this.userRepository.save(user);

        return user;
    }

}
