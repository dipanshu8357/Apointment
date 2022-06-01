package com.Apointment.Model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

import com.Apointment.Entity.AdminPatientData;
import com.Apointment.Utill.DBConnection;

public class AdminDAOimp {
	

	static Connection con=null;
	public AdminDAOimp() {
		con= DBConnection.openConnection();
	}
	
	public ArrayList<AdminPatientData> PatientData() {
		ArrayList<AdminPatientData> patientList = new ArrayList<>();
		String dql="Select * From patientprofilesetting";
		try {
			PreparedStatement ps = con.prepareStatement(dql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				AdminPatientData apd = new AdminPatientData();
				
				apd.setFirstName(rs.getString("firstName"));
				apd.setLastName(rs.getString("lastName"));
				//System.out.println(rs.getString("lastName")+"  "+rs.getString("firstName"));
				
				  apd.setAge(rs.getInt("Age")); apd.setAddress(rs.getString("address"));
				  apd.setMobile(rs.getString("mobile"));
				  
				  
				  //==================================
				  
				  Blob blob = rs.getBlob("photo");
				  
				  InputStream inputstream = null; if(blob!=null) { 
					  inputstream = blob.getBinaryStream(); // read the input stream...
				  
				  } ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); byte[]
				  buffer = new byte[4096]; int bytesRead=-1;
				  
				  while((bytesRead = inputstream.read(buffer)) != -1) {
				  outputStream.write(buffer,0,bytesRead);
				  
				  } byte[] imageBytes = outputStream.toByteArray(); String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				  
				  inputstream.close(); outputStream.close(); 
				  //-------here set the binary data of image
				  apd.setBase64Image(base64Image);
				  //====================================
					
				
				patientList.add(apd);
				
//				apd.setLastVisit(rs.getString("lastVisit"));
//				apd.setPaidAmout(rs.getString("paidAmount"));
				
			}
			
		} catch (Exception e) {
			
			System.out.println(e);
			// TODO: handle exception
		}
		
		
		return patientList;
	
	
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
