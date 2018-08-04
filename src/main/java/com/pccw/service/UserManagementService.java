package com.pccw.service;

import com.pccw.domain.User;
import com.pccw.representation.UserRep;

import java.util.List;

/**
 * Created by ryan.zhu on 04/08/2018.
 */

public interface UserManagementService {

    List<User> getUsers();

    User addUser(String json) throws Exception;

    User getUser(long userId);

    void deleteUser(long userId);

    User updateUser(long userId, String json) throws Exception;


}
