package com.pccw.service;

import com.pccw.Application;
import com.pccw.H2JpaConfig;
import com.pccw.repository.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ryan.zhu on 14/05/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, H2JpaConfig.class})
public class UserManagementServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserManagementService userManagementService;

    @Before
    public void setup() {
//        userManagementService = new UserManagementServiceImpl(cashSupplyRepository, userRepository);
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
//        cashSupply = new CashSupply(cashType, 3);
//        cashSupplyRepository.save(cashSupply);
//
//        cashType = new User("$20", 20);
//        userRepository.save(cashType);
//        cashSupply = new CashSupply(cashType, 4);
//        cashSupplyRepository.save(cashSupply);
//
//        cashType = new User("$10", 10);
//        userRepository.save(cashType);
//        cashSupply = new CashSupply(cashType, 5);
//        cashSupplyRepository.save(cashSupply);
    }

//    @Test
//    public void dispenseCashTest() throws Exception {
//        // test the scenario - can withdraw with one note/coin
//        Map<User, WithdrawCashSupplyRep> result = userManagementService.dispenseCash(200);
//
//        result.forEach((cashType, cashSupply) -> {
//            assertThat(cashSupply.getCashValue(), is(cashType.getCashValue()));
//
//            if (StringUtils.equals(cashType.getCashDesc(), "100")) {
//                assertThat(cashSupply.getCashQuantity(), is(1));
//
//                CashSupply newCashSupply = cashSupplyRepository.findByCashTypeEquals(cashType);
//                assertThat(newCashSupply.getCashQuantity(), is(1));
//            } else if (StringUtils.equals(cashType.getCashDesc(), "50")) {
//                assertThat(cashSupply.getCashQuantity(), is(2));
//
//                CashSupply newCashSupply = cashSupplyRepository.findByCashTypeEquals(cashType);
//                assertThat(newCashSupply.getCashQuantity(), is(1));
//            }
//        });
//
//        // test the scenario - need to withdraw with different note/coin
//        result = userManagementService.dispenseCash(90);
//
//        result.forEach((cashType, cashSupply) -> {
//            assertThat(cashSupply.getCashValue(), is(cashType.getCashValue()));
//
//            if (StringUtils.equals(cashType.getCashDesc(), "20")) {
//                assertThat(cashSupply.getCashQuantity(), is(3));
//
//                CashSupply newCashSupply = cashSupplyRepository.findByCashTypeEquals(cashType);
//                assertThat(newCashSupply.getCashQuantity(), is(1));
//            } else if (StringUtils.equals(cashType.getCashDesc(), "10")) {
//                assertThat(cashSupply.getCashQuantity(), is(3));
//
//                CashSupply newCashSupply = cashSupplyRepository.findByCashTypeEquals(cashType);
//                assertThat(newCashSupply.getCashQuantity(), is(2));
//            }
//        });
//
//        // test the scenario - the cash stock cannot meet the required cash amount
//        try {
//            userManagementService.dispenseCash(200);
//        } catch (CashSupplyException e) {
//            int amountRequired = e.getAmountRequired();
//            int amountSupplied = e.getAmountSupplied();
//
//            assertThat(amountRequired, is(200));
//            assertThat(amountSupplied, is(190));
//            assertThat(e.getMessage(), is("Sorry, this ATM cannot supply the amount required $" + amountRequired + " with current stock. " +
//                    "The closest amount that can be supplied is $" + amountSupplied + ". Please try again later."));
//        }
//
//        // test the scenario - cannot withdraw with existing cash supply
//        try {
//            userManagementService.dispenseCash(25);
//        } catch (CashSupplyException e) {
//            int amountRequired = e.getAmountRequired();
//            int amountSupplied = e.getAmountSupplied();
//
//            assertThat(amountRequired, is(25));
//            assertThat(amountSupplied, is(20));
//            assertThat(e.getMessage(), is("Sorry, this ATM cannot supply the amount required $" + amountRequired + " with current stock. " +
//                    "The closest amount that can be supplied is $" + amountSupplied + ". Please try again later."));
//        }
//
//        // test the scenario - over the withdraw daily limitation
//        int limitation = userManagementService.getAccountCashWithdrawLimitation();
//        try {
//            userManagementService.dispenseCash(limitation + 1);
//        } catch (CashSupplyException e) {
//            int amountRequired = e.getAmountRequired();
//            int amountSupplied = e.getAmountSupplied();
//
//            assertThat(amountRequired, is(limitation + 1));
//            assertThat(amountSupplied, is(limitation));
//            assertThat(e.getMessage(), is("Sorry, the amount $" + amountRequired + " is over your withdraw limitation. The amount you can withdraw is $" + amountSupplied + " today."));
//        }
//    }
//
//    @Test
//    public void getCurrentCashSuppliesTest() {
//        List<CashSupply> currentCashSupplyList = userManagementService.getCurrentCashSupplies();
//
//        List<CashSupply> cashSupplyListFromDb = cashSupplyRepository.findAll();
//        Map<Integer, Integer> cashSupplyMap = new HashMap<>();
//        cashSupplyListFromDb.forEach(cashSupply -> {
//            cashSupplyMap.put(cashSupply.getCashType().getCashValue(), cashSupply.getCashQuantity());
//        });
//
//        currentCashSupplyList.forEach(cashSupply -> {
//            assertThat(cashSupply.getCashQuantity(), is(cashSupplyMap.get(cashSupply.getCashType().getCashValue())));
//        });
//    }
//
//    @Test
//    public void initializationTest() {
//        userManagementService.initializeCashMachine();
//
//        List<CashSupply> currentCashSupplyList = userManagementService.getCurrentCashSupplies();
//        currentCashSupplyList.forEach(cashSupply -> {
//            if (cashSupply.getCashType().getCashValue() == 100) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(10));
//            } else if (cashSupply.getCashType().getCashValue() == 50) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(20));
//            } else if (cashSupply.getCashType().getCashValue() == 20) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(30));
//            } else if (cashSupply.getCashType().getCashValue() == 10) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(40));
//            } else if (cashSupply.getCashType().getCashValue() == 5) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(50));
//            } else if (cashSupply.getCashType().getCashValue() == 10) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(60));
//            } else if (cashSupply.getCashType().getCashValue() == 10) {
//                MatcherAssert.assertThat(cashSupply.getCashQuantity(), is(70));
//            }
//        });
//    }
}
