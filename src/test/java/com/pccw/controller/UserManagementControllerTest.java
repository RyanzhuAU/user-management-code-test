package com.pccw.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.Application;
import com.pccw.H2JpaConfig;
import com.pccw.domain.User;
import com.pccw.repository.UserRepository;
import com.pccw.representation.UserRep;
import com.pccw.service.UserManagementService;
import com.pccw.service.UserManagementServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ryan.zhu on 04/08/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
@AutoConfigureMockMvc
public class UserManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserManagementService userManagementService;

    private long testUserId;

    @Before
    public void setup() {
        userManagementService = new UserManagementServiceImpl(this.userRepository);

        userRepository.deleteAll();

        User user = new User("1@1.com", "test001", "test001", "1111");
        userRepository.save(user);


        user = new User("2@2.com", "test002", "test002", "2222");
        user = userRepository.save(user);
        testUserId = user.getUserId();
    }

    @Test
    public void getUsersTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        List<UserRep> userRepList = objectMapper.readValue(content, new TypeReference<List<UserRep>>() {
        });

        assertThat(userRepList.size(), is(2));

        userRepList.stream().forEach(user -> {
            if (user.getUsername().equals("test001")) {
                Assert.assertThat(user.getEmail(), is("1@1.com"));
                Assert.assertThat(user.getName(), is("test001"));
                Assert.assertThat(user.getPassword(), is("1111"));
            } else {
                Assert.assertThat(user.getEmail(), is("2@2.com"));
                Assert.assertThat(user.getName(), is("test002"));
                Assert.assertThat(user.getUsername(), is("test002"));
                Assert.assertThat(user.getPassword(), is("2222"));
            }
        });
    }


    //
    //    }
    //
    //        assertThat(content, is("Sorry, the amount $" + (withdrawLimitation + 1) + " is over your withdraw limitation. The amount you can withdraw is $" + withdrawLimitation + " today."));
    //        content = result.getResponse().getContentAsString();
    //
    //                .andReturn();
    //                .andExpect(status().isBadRequest())
    //        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/" + (withdrawLimitation + 1)))
    //        int withdrawLimitation = userManagementService.getAccountCashWithdrawLimitation();
    //        // test the over daily withdraw limitation scenario
    //
    //                "The closest amount that can be supplied is $210. Please try again later."));
    //        assertThat(content, is("Sorry, this ATM cannot supply the amount required $400 with current stock. " +
    //        content = result.getResponse().getContentAsString();
    //
    //                .andReturn();
    //                .andExpect(status().isBadRequest())
    //        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/400"))
    //        // test the scenario - cannot withdraw with existing cash supply
    //
    //                "The closest amount that can be supplied is $50. Please try again later."));
    //        assertThat(content, is("Sorry, this ATM cannot supply the amount required $55 with current stock. " +
    //        content = result.getResponse().getContentAsString();
    //
    //                .andReturn();
    //                .andExpect(status().isBadRequest())
    //        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/55"))
    //        // test the scenario - the cash stock cannot meet the required cash amount
    //
    //        });
    //            }
    //                assertThat(cashRep.getCashValue(), is(20));
    //                assertThat(cashRep.getCashQuantity(), is(2));
    //                assertThat(cashRep.getCashDesc(), is("$20"));
    //            } else if (cashRep.getCashValue() == 20) {
    //                assertThat(cashRep.getCashValue(), is(50));
    //                assertThat(cashRep.getCashQuantity(), is(1));
    //                assertThat(cashRep.getCashDesc(), is("$50"));
    //            if (cashRep.getCashValue() == 50) {
    //        cashSupplyRepList.forEach(cashRep -> {
    //
    //        assertThat(cashSupplyRepList.size(), is(2));
    //
    //        });
    //        cashSupplyRepList = objectMapper.readValue(content, new TypeReference<List<UserRep>>() {
    //
    //        content = result.getResponse().getContentAsString();
    //
    //                .andReturn();
    //                .andExpect(status().isOk())
    //        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/90"))
    //        // test the scenario - need to withdraw with different note/coin
    //
    //        assertThat(cashSupplyRep.getCashValue(), is(100));
    //        assertThat(cashSupplyRep.getCashQuantity(), is(1));
    //        assertThat(cashSupplyRep.getCashDesc(), is("$100"));
    //        UserRep cashSupplyRep = cashSupplyRepList.get(0);
    //
    //        assertThat(cashSupplyRepList.size(), is(1));
    //
    //        });
    //        List<UserRep> cashSupplyRepList = objectMapper.readValue(content, new TypeReference<List<UserRep>>() {
    //
    //        String content = result.getResponse().getContentAsString();
    //
    //                .andReturn();
    //                .andExpect(status().isOk())
    //        MvcResult result = this.mockMvc.perform(get("/cashMachine/dispenseCash/100"))
    //        // test the scenario - withdraw with one cash supply.
    //    public void dispenseCashControllerTest() throws Exception {
//    @Test
//
//    @Test
//    public void initializeEndpointTest() throws Exception {
//        // test the initialization endpoint
//        this.mockMvc.perform(post("/cashMachine/initialize"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        List<CashSupply> cashSupplyList = userManagementService.getCurrentCashSupplies();
//
//        cashSupplyList.forEach(cashSupply -> {
//            if (cashSupply.getCashType().getCashValue() == 100) {
//                assertThat(cashSupply.getCashQuantity(), is(10));
//            } else if (cashSupply.getCashType().getCashValue() == 50) {
//                assertThat(cashSupply.getCashQuantity(), is(20));
//            } else if (cashSupply.getCashType().getCashValue() == 20) {
//                assertThat(cashSupply.getCashQuantity(), is(30));
//            } else if (cashSupply.getCashType().getCashValue() == 10) {
//                assertThat(cashSupply.getCashQuantity(), is(40));
//            } else if (cashSupply.getCashType().getCashValue() == 5) {
//                assertThat(cashSupply.getCashQuantity(), is(50));
//            } else if (cashSupply.getCashType().getCashValue() == 10) {
//                assertThat(cashSupply.getCashQuantity(), is(60));
//            } else if (cashSupply.getCashType().getCashValue() == 10) {
//                assertThat(cashSupply.getCashQuantity(), is(70));
//            }
//        });
//    }
}
