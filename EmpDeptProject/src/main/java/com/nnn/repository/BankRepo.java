package com.nnn.repository;

import com.nnn.model.BankDetails;
import com.nnn.request.EmpRequest;

public interface BankRepo {

	EmpRequest getbankdetails(int id);
}
