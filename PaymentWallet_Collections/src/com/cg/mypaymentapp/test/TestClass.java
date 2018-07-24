package com.cg.mypaymentapp.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class TestClass {

	WalletService service;

	@Before
	public void initData() {

		Map<String, Customer> data = new HashMap<String, Customer>();

		Customer cust1 = new Customer("Amit", "9900112212", new Wallet(new BigDecimal(9000)));
		Customer cust2 = new Customer("Ajay", "9963242422", new Wallet(new BigDecimal(6000)));
		Customer cust3 = new Customer("Yogini", "9922950519", new Wallet(new BigDecimal(7000)));

		data.put("9900112212", cust1);
		data.put("9963242422", cust2);
		data.put("9922950519", cust3);
		service = new WalletServiceImpl(data);

	}

	@Test(expected = NullPointerException.class)
	public void checkcreateAccount() {

		if (service.createAccount(null, null, null) == null)
			;
		throw new NullPointerException();
	}

	@Test
	public void testCreateAccountPassed() {
		Customer customer = service.createAccount("Lalit", "9999999999", new BigDecimal(0));
		assertNotNull(customer);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccountFailed() {
		Customer customer = service.createAccount(null, null, null);

	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccountFailed1() {
		Customer customer = service.createAccount("Lalit", "9909000097", null);

	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccountFailed2() {
		Customer customer = service.createAccount("Lalit", null, new BigDecimal(0));
	}

	@Test(expected = NullPointerException.class)
	public void testCreateAccountFailed3() {
		Customer customer = service.createAccount(null, "9999999999", new BigDecimal(0));

	}

	@Test(expected = InvalidInputException.class)
	public void testCreateAccountFailed4() {
		Customer customer = service.createAccount("lalit", "999999999", new BigDecimal(0));

	}

	@Test(expected = InsufficientBalanceException.class)
	public void testWithdraw() {

		service.withdrawAmount("9922950519", new BigDecimal(8000));

	}

	@Test
	public void testWithdraw1() {

		service.withdrawAmount("9922950519", new BigDecimal(200));
	}

	@Test
	public void testShowBalance() {

		service.showBalance("9922950519");
	}

	@Test(expected = InvalidInputException.class)
	public void testDeposit() {
		service.depositAmount("9929577597", new BigDecimal(21000));

	}

	@Test
	public void testDeposit1() {
		Customer customer = service.depositAmount("9900112212", new BigDecimal(1000));
		assertEquals(new BigDecimal(10000), customer.getWallet().getBalance());

	}

	@Test
	public void testWithdraw2() {
		Customer customer = service.withdrawAmount("9900112212", new BigDecimal(1000));
		assertEquals(new BigDecimal(8000), customer.getWallet().getBalance());

	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer("9929574436", "9768766349", new BigDecimal(1000));
	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer1() {
		service.fundTransfer("9922950519", "9768766349", new BigDecimal(1000));
	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer2() {
		service.fundTransfer("9922950519", "9922950519", new BigDecimal(1000));
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testFundTransfer3() {
		service.fundTransfer("9922950519", "9900112212", new BigDecimal(10000));
	}

	@Test
	public void testFundTransfer4() {
		service.fundTransfer("9922950519", "9900112212", new BigDecimal(1000));
	}

	@Test(expected = NullPointerException.class)
	public void testFundTransfer5() {
		service.fundTransfer(null, "9900112212", new BigDecimal(1000));
	}

	@Test(expected = InvalidInputException.class)
	public void testFundTransfer6() {
		service.fundTransfer("9900112212", null, new BigDecimal(1000));
	}

	@Test(expected = NullPointerException.class)
	public void testFundTransfer7() {
		service.fundTransfer("9900112212", "9922950519", null);
	}

}