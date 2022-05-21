package com.Apointment.Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	public LoginController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");

		// System.out.println(mobileNumber+"-"+password);

		mobileNumber = mobileNumber.trim();
		password = password.trim();
		
		/*
		 * request.setAttribute("mobile", mobileNumber);
		 * response.sendRedirect("/PatientController");
		 */
		if (mobileNumber == "" || password == "") {
			//here we set a messseg  for showing on jsp page
			
			request.setAttribute("loginError", "Please Fill requred details !!!");

			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			
			rd.forward(request, response);
			
		} else {

			if (udi.verifyPassword(password)) {
				if (udi.verifyUser(mobileNumber, password)) {

					if (udi.identifyUser(mobileNumber)) {

						
						RequestDispatcher rd = request.getRequestDispatcher("doctor-dashboard.jsp");
						rd.forward(request, response);
					} else {

						request.setAttribute("mobileNumber",mobileNumber );
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
		
	}

}
