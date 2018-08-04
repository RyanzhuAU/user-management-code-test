package com.pccw.repository;

import com.pccw.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ryan.zhu on 04/08/2018.
 */

public interface UserRepository extends CrudRepository<User, String> {

    User findById(long cashTypeId);

    void deleteByUserId(long userId);

    User save(User user);

}
