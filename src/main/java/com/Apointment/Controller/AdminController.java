package com.Apointment.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Apointment.Entity.AdminPatientData;
import com.Apointment.Model.AdminDAOimp;

/**
 * Servlet implementation class AdminController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	AdminDAOimp adi = null;
    public AdminController() {
    	adi= new AdminDAOimp();
       
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String action= request.getParameter("action");
		System.out.println(action);
		
		switch(action) {
		
		case "showPatinetData": showPatientData(request,response);
								
		break;
		
		
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	
	
	protected void showPatientData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<AdminPatientData> patientList =adi.PatientData();
		request.setAttribute("patientList", patientList);
		/*
		 * for(AdminPatientData i:patientList) {
		 * 
		 * System.out.println("->"+i.getAge()); }
		 */
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/patient-list.jsp");
		rd.forward(request, response);
	    	
	}
		

}
