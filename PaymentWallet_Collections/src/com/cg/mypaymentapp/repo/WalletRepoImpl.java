package com.cg.mypaymentapp.repo;

import java.util.Map;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo {

	private Map<String, Customer> data;

	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public boolean save(Customer customer) {
		data.put(customer.getMobileNo(), customer);
		return true;
	}

	public Customer findOne(String mobileNo) {
		Customer customer = data.get(mobileNo);
		if (customer == null)
			throw new InvalidInputException("No account found associated with this mobile number");
		return customer;
	}
}
