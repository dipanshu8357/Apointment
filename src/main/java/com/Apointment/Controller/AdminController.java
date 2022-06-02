package com.Apointment.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Apointment.Entity.AdminPatientDataUpdate;
import com.Apointment.Entity.AdminPatientShowData;
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
		
		case "showPatinetData": showPatientData(request,response);break;
		
		case "editPatinetProfileShow": editPatientProfileShow(request,response);break;
					
		case "editPatinetProfileUpdate":editPatinetProfileUpdate(request,response);break;
		default:
		
		
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	
	
	protected void showPatientData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<AdminPatientShowData> patientList =adi.allPatientsData();
		request.setAttribute("patientList", patientList);
		
		RequestDispatcher rd = request.getRequestDispatcher("admin/patient-list.jsp");
		rd.forward(request, response);
	    	
	}
	
	
	  protected void editPatientProfileShow(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	  
		 
    //ArrayList<AdminPatientData> patients =adi.PatientData();
		  String mobile = request.getParameter("mobile");
		  AdminPatientDataUpdate patients=adi.patientData(mobile);
	  
		  request.setAttribute("patients", patients);
	  
	  RequestDispatcher rd = request.getRequestDispatcher("admin/editPatientProfile.jsp"); 
	  rd.forward(request,response);
	  
	 }
	  private void editPatinetProfileUpdate(HttpServletRequest request, HttpServletResponse response) {
			
			
		  String mobile = request.getParameter("mobile");
		  
		  
		  
		  
//		  AdminPatientDataUpdate patients=adi.patientData(mobile);
			
			
	  }
		

}
