package com.pccw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.Application;
import com.pccw.H2JpaConfig;
import com.pccw.repository.UserRepository;
import com.pccw.service.UserManagementService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by ryan.zhu on 14/5/2018.
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
    private CashSupplyRepository cashSupplyRepository;

    @Autowired
    private TransactionLogDetailRepository transactionLogDetailRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserManagementService userManagementService;

    @Before
    public void setup() {
//        userManagementService = new UserManagementServiceImpl(this.cashSupplyRepository, this.userRepository);
//
//        transactionLogDetailRepository.deleteAllInBatch();
//        cashSupplyRepository.deleteAllInBatch();
//        userRepository.deleteAllInBatch();
//
//        User cashType = new User("$100", 100);
//        userRepository.save(cashType);
//        CashSupply cashSupply = new CashSupply(cashType, 2);
//        cashSupplyRepository.save(cashSupply);
//
//        cashType = new User("$50", 50);
//        userRepository.save(cashType);
//        cashSupply = new CashSupply(cashType, 2);
//        cashSupplyRepository.save(cashSupply);
//
//        cashType = new User("$20", 20);
//        userRepository.save(cashType);
//        cashSupply = new CashSupply(cashType, 3);
//        cashSupplyRepository.save(cashSupply);
//
//        cashType = new User("$10", 10);
//        userRepository.save(cashType);
//        cashSupply = new CashSupply(cashType, 4);
//        cashSupplyRepository.save(cashSupply);
    }

//    @Test
//    public void dispenseCashControllerTest() throws Exception {
//        // test the scenario - withdraw with one cash supply.
//        MvcResult result = this.mockMvc.perform(get("/cashMachine/dispenseCash/100"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//
//        List<UserRep> cashSupplyRepList = objectMapper.readValue(content, new TypeReference<List<UserRep>>() {
//        });
//
//        assertThat(cashSupplyRepList.size(), is(1));
//
//        UserRep cashSupplyRep = cashSupplyRepList.get(0);
//        assertThat(cashSupplyRep.getCashDesc(), is("$100"));
//        assertThat(cashSupplyRep.getCashQuantity(), is(1));
//        assertThat(cashSupplyRep.getCashValue(), is(100));
//
//        // test the scenario - need to withdraw with different note/coin
//        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/90"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        content = result.getResponse().getContentAsString();
//
//        cashSupplyRepList = objectMapper.readValue(content, new TypeReference<List<UserRep>>() {
//        });
//
//        assertThat(cashSupplyRepList.size(), is(2));
//
//        cashSupplyRepList.forEach(cashRep -> {
//            if (cashRep.getCashValue() == 50) {
//                assertThat(cashRep.getCashDesc(), is("$50"));
//                assertThat(cashRep.getCashQuantity(), is(1));
//                assertThat(cashRep.getCashValue(), is(50));
//            } else if (cashRep.getCashValue() == 20) {
//                assertThat(cashRep.getCashDesc(), is("$20"));
//                assertThat(cashRep.getCashQuantity(), is(2));
//                assertThat(cashRep.getCashValue(), is(20));
//            }
//        });
//
//        // test the scenario - the cash stock cannot meet the required cash amount
//        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/55"))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//
//        content = result.getResponse().getContentAsString();
//        assertThat(content, is("Sorry, this ATM cannot supply the amount required $55 with current stock. " +
//                "The closest amount that can be supplied is $50. Please try again later."));
//
//        // test the scenario - cannot withdraw with existing cash supply
//        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/400"))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//
//        content = result.getResponse().getContentAsString();
//        assertThat(content, is("Sorry, this ATM cannot supply the amount required $400 with current stock. " +
//                "The closest amount that can be supplied is $210. Please try again later."));
//
//        // test the over daily withdraw limitation scenario
//        int withdrawLimitation = userManagementService.getAccountCashWithdrawLimitation();
//        result = this.mockMvc.perform(get("/cashMachine/dispenseCash/" + (withdrawLimitation + 1)))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//
//        content = result.getResponse().getContentAsString();
//        assertThat(content, is("Sorry, the amount $" + (withdrawLimitation + 1) + " is over your withdraw limitation. The amount you can withdraw is $" + withdrawLimitation + " today."));
//
//    }
//
//    @Test
//    public void getCashStockTest() throws Exception {
//        // test the check cash stock endpoint
//        MvcResult result = this.mockMvc.perform(get("/cashMachine/getCashStock"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//        List<UserRep> cashSupplyRepList = objectMapper.readValue(content, new TypeReference<List<UserRep>>() {});
//
//        List<CashSupply> cashSupplyListFromDb = cashSupplyRepository.findAll();
//        Map<Integer, Integer> cashSupplyMap = new HashMap<>();
//        cashSupplyListFromDb.forEach(cashSupply -> {
//            cashSupplyMap.put(cashSupply.getCashType().getCashValue(), cashSupply.getCashQuantity());
//        });
//
//        cashSupplyRepList.forEach(cashSupply -> {
//            assertThat(cashSupply.getCashQuantity(), is(cashSupplyMap.get(cashSupply.getCashValue())));
//        });
//    }
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
