package com.Apointment.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Apointment.Model.*;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**   
	 * @see HttpServlet#HttpServlet()
	 */
	UserDAOimp udi = null;
	AdminDAOimp adi =null;
	public LoginController() {
		udi = new UserDAOimp();
		adi = new AdminDAOimp();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String action=request.getParameter("action");
	        System.out.println(action+"   :main action");
	        
	        
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		if(mobileNumber!=null || password!=null) {
			mobileNumber = mobileNumber.trim();
			password = password.trim();
		}
		/*
		 * request.setAttribute("mobile", mobileNumber);
		 * response.sendRedirect("/PatientController");
		 */
		if (mobileNumber == "" || password == "") {
			//here we set a messseg  for showing on jsp page
			
			request.setAttribute("loginError", "Please Fill requred details !!!");
			if(action.equals("userLogin")) {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}else if(action.equals("adminLogin")) {
				RequestDispatcher rd = request.getRequestDispatcher("admin/login.jsp");
				rd.forward(request, response);
			}
			
			
		} else {
			System.out.println(action+"   :main action else");
			if(action.equals("userLogin")) {
					userLogin(request,response,mobileNumber,password);
			}else if(action.equals("adminLogin")) {
					adminLogin(request,response,mobileNumber,password);
			}else if(action.equals("logout")) {
				
				System.out.println(action+"   :main action logout");
					session.invalidate();
					response.sendRedirect("index-2.jsp");
					  
					  
			}
					 
		}
		
	}
	protected void userLogin(HttpServletRequest request, HttpServletResponse response,String mobileNumber,String password) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (udi.verifyPassword(password)) {
			
			if (udi.verifyUser(mobileNumber, password)) {

				session.setAttribute("mobileNumber",mobileNumber );
				if (udi.identifyUser(mobileNumber)) {
			
					session.setAttribute("type","doctor");
					
					RequestDispatcher rd = request.getRequestDispatcher("doctor-dashboard.jsp");
					rd.forward(request, response);
				} else {

					session.setAttribute("type","patient");
					
					RequestDispatcher rd = request.getRequestDispatcher("patient-dashboard.jsp");
					rd.forward(request, response);

				}
			} else {
				System.out.println("User Not Registred!!");
				request.setAttribute("loginError","User Not Registred!!" );
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		} else {
			request.setAttribute("loginError", "Invalid Password !!!");

			System.out.println("Invalid Password !!!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
		
	}
	
protected void adminLogin(HttpServletRequest request, HttpServletResponse response,String mobileNumber,String password) throws ServletException, IOException {
	
	HttpSession session = request.getSession();

			System.out.println("adminLogin");
			if (adi.verifyPassword(password)) {
			
					if (adi.verifyUser(mobileNumber, password)) {

					session.setAttribute("mobileNumber",mobileNumber );
					
					session.setAttribute("type","admin");
					System.out.println("adminLoginsuccesfully");
					
					RequestDispatcher rd = request.getRequestDispatcher("admin/index.jsp");
					rd.forward(request, response);
				
					} else {
						System.out.println("Admin Not Registred!!");
						request.setAttribute("loginError","Admin Not Registred!!" );
						RequestDispatcher rd = request.getRequestDispatcher("admin/login.jsp");
						rd.forward(request, response);
					}
			} else {
					request.setAttribute("loginError", "Invalid Password !!!");

						System.out.println("Invalid Password !!!");
						RequestDispatcher rd = request.getRequestDispatcher("admin/login.jsp");
						rd.forward(request, response);
		}

		
	}
	
	

}
