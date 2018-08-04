package com.pccw.representation;

import com.pccw.domain.User;
import lombok.Data;

/**
 * Create by ryan.zhu on 04/08/2018
 **/

@Data
public class UserRep {

    private String email;

    private String name;

    private String username;

    private String password;

    public UserRep() {}

    public UserRep(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

//    public UserRep(Integer cashValue, String cashDesc, Integer cashQuantity) {
//        this.cashValue = cashValue;
//        this.cashDesc = cashDesc;
//        this.cashQuantity = cashQuantity;
//    }

}
