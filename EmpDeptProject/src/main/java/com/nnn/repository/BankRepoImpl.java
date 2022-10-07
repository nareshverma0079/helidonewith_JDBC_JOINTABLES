package com.nnn.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import com.nnn.model.BankDetails;
import com.nnn.request.EmpRequest;

public class BankRepoImpl implements BankRepo{
	
	private static final String GET_BANK_DETAILS = "select e.empname, d.department , b.acc_no from emp e "
													+ "left join dept d on d.dept_id = e.dept_id "
														+ "left join bank_details b on b.emp_id = e.emp_id where b.bank_id=?";
	
	@Inject
	@Named("employeeDataSource")
	DataSource ds;

	@Override
	public EmpRequest getbankdetails(int id) {
		EmpRequest request = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_BANK_DETAILS);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String empname = rs.getString(1);
				String department = rs.getString(2);
				long accNo= rs.getLong(3);
				request = new EmpRequest(empname, department, accNo);
			} 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return request;
	}

}
