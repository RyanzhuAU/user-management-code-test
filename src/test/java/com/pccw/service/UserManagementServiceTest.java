package com.pccw.service;

import antlr.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.Application;
import com.pccw.H2JpaConfig;
import com.pccw.domain.User;
import com.pccw.repository.UserRepository;
import com.pccw.representation.UserRep;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
 * Created by ryan.zhu on 14/05/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
public class UserManagementServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserManagementService userManagementService;

    private long testUserId;

    @Before
    public void setup() {
        userManagementService = new UserManagementServiceImpl(userRepository);

        userRepository.deleteAll();

        User user = new User("1@1.com", "test001", "test001", "1111");
        userRepository.save(user);


        user = new User("2@2.com", "test002", "test002", "2222");
        user = userRepository.save(user);
        testUserId = user.getUserId();

//        List<User> userList = this.userManagementService.getUsers();
//        assertThat(userList.size(), is(2));

    }

    @Test
    public void getUsersTest() {
        List<User> userList = this.userManagementService.getUsers();

        assertThat(userList.size(), is(2));

        userList.stream().forEach(user -> {
            if (user.getUsername().equals("test001")) {
                assertThat(user.getEmail(), is("1@1.com"));
                assertThat(user.getName(), is("test001"));
                assertThat(user.getPassword(), is("1111"));
            } else {
                assertThat(user.getEmail(), is("2@2.com"));
                assertThat(user.getName(), is("test002"));
                assertThat(user.getUsername(), is("test002"));
                assertThat(user.getPassword(), is("2222"));
            }
        });
    }

    @Test
    public void addUserTest() {
        try {
            UserRep newUser = new UserRep("3@3.com", "test003", "test003", "3333");
            String json = objectMapper.writeValueAsString(newUser);
            User user = this.userManagementService.addUser(json);

            assertThat((int)user.getUserId(), is((int)testUserId + 1));
            assertThat(user.getEmail(), is("3@3.com"));
            assertThat(user.getName(), is("test003"));
            assertThat(user.getUsername(), is("test003"));
            assertThat(user.getPassword(), is("3333"));


        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getUserTest() {
        User user = this.userManagementService.getUser(testUserId);
        assertThat(user.getEmail(), is("2@2.com"));
        assertThat(user.getName(), is("test002"));
        assertThat(user.getUsername(), is("test002"));
        assertThat(user.getPassword(), is("2222"));
    }

    @Test
    public void deleteUserTest() {
        User user = this.userManagementService.getUser(testUserId);
        assertThat(user, notNullValue());

        this.userManagementService.deleteUser(testUserId);

        user = this.userManagementService.getUser(testUserId);
        assertThat(user, equalTo(null));
    }

    @Test
    public void updateUserTest() {
        try {
            UserRep updatedUser = new UserRep("3@3.com", "test003", "test003", "3333");
            String json = objectMapper.writeValueAsString(updatedUser);
            User user = this.userManagementService.updateUser(testUserId, json);

            assertThat(user.getUserId(), is(testUserId));
            assertThat(user.getEmail(), is("3@3.com"));
            assertThat(user.getName(), is("test003"));
            assertThat(user.getUsername(), is("test003"));
            assertThat(user.getPassword(), is("3333"));

        } catch (Exception e) {
            fail();
        }

    }

}
