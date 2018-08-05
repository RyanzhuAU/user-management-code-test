package com.pccw.controller;

import com.pccw.MessageConstants;
import com.pccw.domain.User;
import com.pccw.representation.UserRep;
import com.pccw.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ryan.zhu on 04/08/2018.
 */

@RestController
@RequestMapping("/users")
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * GET /users - returns the user list
     *
     * @param page - Optional
     * @param size - Optional
     *
     * @return JSON - list of user object and status 200, or status 400
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getUsers(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        try {
            List<User> userList = this.userManagementService.getUsers();

            return new ResponseEntity(userList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(MessageConstants.GET_USER_LIST_ERROR_MESSAGE,
                                        HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * PUT /users - create the new user
     *
     * @param json
     * @return JSON - User object and status 200, or status 400
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity setChars(@RequestBody String json) {
        try {
            User user = this.userManagementService.addUser(json);

            return new ResponseEntity(new UserRep(user), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(MessageConstants.ADD_USER_ERROR_MESSAGE,
                    HttpStatus.BAD_REQUEST);        }
    }

    /**
     * GET /users/{id} - returns the selected user
     *
     * @param userId
     * @return JSON - user and status 200, or status 400
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("id") String userId) {
        try {
            User user = this.userManagementService.getUser(Long.valueOf(userId));

            return new ResponseEntity(new UserRep(user), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(MessageConstants.GET_USER_ERROR_MESSAGE,
                    HttpStatus.BAD_REQUEST);         }
    }

    /**
     * POST /users/{id} - returns the update user
     *
     * @param userId
     * @param json - updated user data
     * @return JSON - updated user and status 200, or status 400
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity updateUser(@PathVariable("id") String userId, @RequestBody String json) {
        try {
            User user = this.userManagementService.updateUser(Long.valueOf(userId), json);

            return new ResponseEntity(new UserRep(user), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(MessageConstants.UPDATE_USER_ERROR_MESSAGE,
                    HttpStatus.BAD_REQUEST);         }
    }

    /**
     * DELETE /users/{id} - delete user
     *
     * @param userId
     * @return JSON - user and status 200, or status 400
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") String userId) {
        try {
            this.userManagementService.deleteUser(Long.valueOf(userId));

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(MessageConstants.DELETE_USER_ERROR_MESSAGE,
                    HttpStatus.BAD_REQUEST);         }
    }
}
