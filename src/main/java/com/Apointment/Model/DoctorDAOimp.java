package com.Apointment.Model;

import com.Apointment.Entity.DoctorSettingData;
import com.Apointment.Entity.PatientSettingData;
import com.Apointment.Utill.DBConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DoctorDAOimp {
	static Connection con=null;
	public DoctorDAOimp()
	{
		con=(Connection) DBConnection.openConnection();
	}

	
	
	public void doctorProfileInsData(DoctorSettingData dsd)
	{
		try {
			
		PreparedStatement ps= (PreparedStatement) con.prepareStatement(" d");
		
		ps.setString(1, dsd.getFirstName());
		ps.setString(2, dsd.getLastName());
		ps.setString(3, dsd.getMobileNumber());
		ps.setString(4, dsd.getGender());
		ps.setString(5, dsd.getDateOfBirth());
		ps.setString(6, dsd.getBiography());
		ps.setString(7, dsd.getClinicName());
		ps.setString(8, dsd.getClinicAddress());
		ps.setString(9, dsd.getAddressLine1());
		ps.setString(10, dsd.getAddressLine2());
		ps.setString(11, dsd.getCity());
		ps.setString(12, dsd.getState());
		ps.setString(13, dsd.getCountry());
		ps.setString(14, dsd.getPostalCode());
		ps.setString(15, dsd.getPricing());
		
		
		
		}catch(Exception e )
		{
			e.printStackTrace();
		}
	}
}
