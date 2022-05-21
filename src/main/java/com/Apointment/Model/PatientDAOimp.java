package com.Apointment.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;


import com.Apointment.Entity.PatientSettingData;
import com.Apointment.Utill.DBConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class PatientDAOimp {
	
	static Connection con=null;
	
	public PatientDAOimp()
	{
		System.out.println("vccvvx");
		con=(Connection) DBConnection.openConnection();
		System.out.println(con);
	}
	 
	
	public PatientSettingData patientProfileGetData(String mobileNumber) {
		
		String DQL ="select * from patientProfileSetting where mobile=? ";
		
		PatientSettingData psd = new PatientSettingData();
		try {
		
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(DQL);
			ps.setString(1, mobileNumber);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				psd.setFirstName(rs.getString("firstName"));
				psd.setLastName(rs.getString("lastName"));
				psd.setBloodGroup(rs.getString("bloodGroup"));
				psd.setDateOfBirth(rs.getString("dateOfBirth"));
				psd.setEmailId(rs.getString("emailId"));
				psd.setMobile(rs.getString("mobile"));
				psd.setAddress(rs.getString("address"));
				psd.setCity(rs.getString("city"));
				psd.setState(rs.getString("state"));
				psd.setZipCode(rs.getString("zipCode"));
				psd.setCountry(rs.getString("country"));
				
			}else {
			
				System.out.println("data is empty");
				
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	
		return psd;
	}
	

	//method for inserting details of patient
	public void patientProfileInsData(PatientSettingData psd) { 
	  String DML="UPDATE patientProfileSetting SET firstName=?,lastName=?,bloodGroup=?,dateOfBirth=?,emailId=?,address=?,city=?,state=?,zipCode=?,country=? ,image=? WHERE mobile=?"; 
	  FileInputStream fis=null;
	  try {
	  
	  PreparedStatement ps=(PreparedStatement) con.prepareStatement(DML);
	  
	  ps.setString(1,psd.getFirstName()); 
	  ps.setString(2,psd.getLastName());
	  ps.setString(3,psd.getBloodGroup());
	  ps.setString(4,psd.getDateOfBirth());
	  ps.setString(5,psd.getEmailId()); 
	  ps.setString(6,psd.getAddress());
	  ps.setString(7,psd.getCity());
	  ps.setString(8,psd.getState()); 
	  ps.setString(9,psd.getZipCode());
	  ps.setString(10,psd.getCountry());
	  
//	  System.out.println(psd.getImage()); 
	  
//	  File image=new File(psd.getPhoto());
//	  fis=new FileInputStream(image);
	  ps.setString(11 , psd.getImage());
	  
	  ps.setString(12,psd.getMobile());
	  
	  ps.executeUpdate();
	  
	  }catch(Exception e) {
	  
	  e.printStackTrace();
	  
	  
	  }
	  
	   
	  
	 }
	  
	  
	 	
	
	

}