package com.Apointment.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Apointment.Utill.DBConnection;

public class AdminDAOimp {
	

	static Connection con=null;
	public AdminDAOimp() {
		con= DBConnection.openConnection();
	}
	
	public boolean verifyUser(String mobileNumber, String password) {
		//this method for verify user . user is register or not
		System.out.println(mobileNumber+"  "+password);
		String dql = "select * from admindata where mobileNumber=? AND password=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(dql);
			ps.setString(1, mobileNumber);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return true;
			}
			return false;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return false;
	}
	public boolean verifyPassword( String password) {
		//this method for verify admin ,admin is registerd or not
//		System.out.println(mobileNumber+"  "+password);
		String dql = "select * from admindata where  password=?";
		try {
			
			PreparedStatement ps = con.prepareStatement(dql);
			
			ps.setString(1, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				return true;
			}
			return false;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return false;
	}
	

}
