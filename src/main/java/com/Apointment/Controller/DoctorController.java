package com.Apointment.Controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
       udi= new UserDAOimp();
    	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String degree[]= request.getParameterValues("degree");
		System.out.println(degree[0]);
		System.out.println(degree[1]);
//		System.out.println(degree[1]);
		
//		String name=request.getParameter("name");
		
		/*
		 * System.out.println(degree[1]); System.out.println(degree[2]);
		 *//*
			 * doPost(request, response);
			 * response.getWriter().append("Served at: ").append(request.getContextPath());
			 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String mobileNumber = request.getParameter("mobileNumber");
		String password=request.getParameter("password");
	
		fname=fname.trim();
		lname=lname.trim();
		mobileNumber=mobileNumber.trim();
		password=password.trim();
		
		if( fname==""|| lname=="" || mobileNumber=="" ||password=="" ) {
			request.setAttribute("loginError", "Please Enter details !!!!!!");
			RequestDispatcher rd = request.getRequestDispatcher("doctor-register.jsp");
			rd.forward(request, response);
		}else if(isValid(mobileNumber)) {

			UserData ud = new UserData();
			ud.setFname(fname);
			ud.setLname(lname);
			ud.setMobileNumber(mobileNumber);
			ud.setPassword(password);
		    ud.setType(1);
		
		    udi.addUserData(ud);
		    
		    request.setAttribute("mobile", mobileNumber);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		
		}else{
			
			request.setAttribute("loginError", "Please Enter Valid mobile number !!!!!!");
			RequestDispatcher rd = request.getRequestDispatcher("doctor-register.jsp");
			rd.forward(request, response);
		}
		// TODO Auto-generated method stub
		
	}
	 public static boolean isValid(String s)
	    {
	 
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
