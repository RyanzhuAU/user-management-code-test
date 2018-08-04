package com.pccw.domain;

import com.pccw.representation.UserRep;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by ryan.zhu on 04/08/2018.
 */

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long userId;

    private String email;

    private String name;

    private String username;

    private String password;

    public User() {

    }

    public User(UserRep userRep) {
        this.email = userRep.getEmail();
        this.name = userRep.getName();
        this.username = userRep.getUsername();
        this.password = userRep.getPassword();
    }

//    public User(String cashDesc, Integer cashValue) {
//        this.cashValue = cashValue;
//        this.cashDesc = cashDesc;
//    }

}
