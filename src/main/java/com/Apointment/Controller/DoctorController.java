package com.Apointment.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Apointment.Entity.DoctorSettingData;
import com.Apointment.Entity.UserData;
import com.Apointment.Model.*;

/**
 * Servlet implementation class DoctorController
 */
@WebServlet("/DoctorController")
public class DoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	UserDAOimp udi = null;

	public DoctorController() {
		udi = new UserDAOimp();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String action=request.getParameter("action");
				switch(action)
				{
				    case "register":  doctorRegistration(request,response);break;
				
				    case "profileSettingInsData": doctorProfileSettingInsData(request,response);break;
				
				}
		// TODO Auto-generated method stub

	}

	protected void doctorRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");

		fname = fname.trim();
		lname = lname.trim();
		mobileNumber = mobileNumber.trim();
		password = password.trim();

		if (fname == "" || lname == "" || mobileNumber == "" || password == "") {
			request.setAttribute("loginError", "Please Enter details !!!!!!");
			RequestDispatcher rd = request.getRequestDispatcher("doctor-register.jsp");
			rd.forward(request, response);
		} else if (isValid(mobileNumber)) {

			UserData ud = new UserData();
			ud.setFname(fname);
			ud.setLname(lname);
			ud.setMobileNumber(mobileNumber);
			ud.setPassword(password);
			ud.setType(1);

			udi.addUserData(ud);
			request.setAttribute("signup", "SignUp Successfully!!!!");
			request.setAttribute("mobile", mobileNumber);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);

		} else {

			request.setAttribute("loginError", "Please Enter Valid mobile number !!!!!!");
			RequestDispatcher rd = request.getRequestDispatcher("doctor-register.jsp");
			rd.forward(request, response);
		}

	}

	protected void doctorProfileSettingInsData(HttpServletRequest request, HttpServletResponse response) {
		
		DoctorSettingData dpsd=new DoctorSettingData();
		
		
		String firstName=request.getParameter("firstName");
		firstName=firstName.trim();
		String lastName=request.getParameter("lastName");
		lastName=lastName.trim();
		String mobileNumber=request.getParameter("mobileNumber");
		mobileNumber=mobileNumber.trim();
		String gender=request.getParameter("gender");
		gender=gender.trim();
		String dateOfBirth=request.getParameter("dateOfBirth");
		dateOfBirth=dateOfBirth.trim();
		String biography=request.getParameter("biography");
		biography=biography.trim();
		String clinicName=request.getParameter("clinicName");
		clinicName=clinicName.trim();
		String clinicAddress=request.getParameter("clinicAddress");
		clinicAddress=clinicAddress.trim();
		String addressLine1=request.getParameter("addressLine1");
		addressLine1=addressLine1.trim();
		String addressLine2=request.getParameter("addressLine2");
		addressLine2=addressLine2.trim();
		String city=request.getParameter("city");
		city=city.trim();
		String state=request.getParameter("state");
		state=state.trim();
		String country=request.getParameter("country");
		country=country.trim();
		String postalCode=request.getParameter("postalCode");
		postalCode=postalCode.trim();
		String pricing=request.getParameter("pricing");
		pricing=pricing.trim();
		String services=request.getParameter("services");
		services=services.trim();
		String specialist=request.getParameter("specialist");
		specialist=specialist.trim();
		String [] degree=request.getParameterValues("degree");
		for(int i=0;i<degree.length;i++)
		{
			degree[i]=degree[i].trim();
			
		}
		String [] college=request.getParameterValues("college");
		for(int i=0;i<college.length;i++)
		{
			college[i]=college[i].trim();
			
		}
		String []yearCompletetion=request.getParameterValues("yearCompletetion");
		for(int i=0;i<yearCompletetion.length;i++)
		{
			yearCompletetion[i]=yearCompletetion[i].trim();
			
		}
		String []hospitalName=request.getParameterValues("hospitalName");
		for(int i=0;i<hospitalName.length;i++)
		{
			hospitalName[i]=hospitalName[i].trim();
			
		}
		String []from=request.getParameterValues("from");
		for(int i=0;i<from.length;i++)
		{
			from[i]=from[i].trim();
			
		}
		String []to=request.getParameterValues("to");
		for(int i=0;i<to.length;i++)
		{
			to[i]=to[i].trim();
			
		}
		String []designation=request.getParameterValues("Designation");
		for(int i=0;i<designation.length;i++)
		{
			designation[i]=designation[i].trim();
			
		}
		String []award=request.getParameterValues("award");
		for(int i=0;i<award.length;i++)
		{
			award[i]=award[i].trim();
			
		}
		String []awardYear=request.getParameterValues("awardYear");
		for(int i=0;i<awardYear.length;i++)
		{
			awardYear[i]=awardYear[i].trim();
			
		}
		String []memberships=request.getParameterValues("memberships");
		for(int i=0;i<memberships.length;i++)
		{
			memberships[i]=memberships[i].trim();
			
		}
		String []registration=request.getParameterValues("registration");
		for(int i=0;i<registration.length;i++)
		{
			registration[i]=registration[i].trim();
			
		}
		String []registrationYear=request.getParameterValues("registrationYear");
		for(int i=0;i<registrationYear.length;i++)
		{
			registrationYear[i]=registrationYear[i].trim();
			
		}
		
		// all the variables are set data into DoctorSettingData in getter data
		dpsd.setFirstName(firstName);
		dpsd.setLastName(lastName);
		dpsd.setMobileNumber(mobileNumber);
		dpsd.setGender(gender);
		dpsd.setDateOfBirth(dateOfBirth);
		dpsd.setBiography(biography);
		dpsd.setClinicName(clinicName);
		dpsd.setClinicAddress(clinicAddress);
		dpsd.setAddressLine1(addressLine1);
		dpsd.setAddressLine2(addressLine2);
		dpsd.setCity(city);
		dpsd.setState(state);
		dpsd.setCountry(country);
		dpsd.setPostalCode(postalCode);
		dpsd.setPricing(pricing);
		dpsd.setServices(services);
		dpsd.setSpecialist(specialist);
		dpsd.setDegree(degree);
		dpsd.setCollege(college);
		dpsd.setYearCompletetion(yearCompletetion);
		dpsd.setHospitalName(hospitalName);
		dpsd.setFrom(from);
		dpsd.setTo(to);
		dpsd.setDesignation(designation);
		dpsd.setAward(award);
		dpsd.setAwardYear(awardYear);
		dpsd.setMemberships(memberships);
		dpsd.setRegistration(registration);
		dpsd.setRegistrationYear(registrationYear);
		
		
		

	}

	public static boolean isValid(String s) {

		// The given argument to compile() method
		// is regular expression. With the help of
		// regular expression we can validate mobile
		// number.
		// The number should be of 10 digits.

		// Creating a Pattern class object
		Pattern p = Pattern.compile("^\\d{10}$");

		// Pattern class contains matcher() method
		// to find matching between given number
		// and regular expression for which
		// object of Matcher class is created
		java.util.regex.Matcher m = p.matcher(s);

		// Returning boolean value
		return (m.matches());
	}

}
