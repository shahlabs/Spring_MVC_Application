package com.me.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.spring.dao.LoginDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.FlightDetails;
import com.me.spring.pojo.Users;

@Controller
@RequestMapping(value="/")
public class LoginController {

	@Autowired
	@Qualifier("login")
	LoginDAO login;
	
	@RequestMapping(value="/login.htm", method=RequestMethod.GET)
	public String initializeForm(){
		
		return "login";
	}
	
	
	@RequestMapping(value="/login.htm", method=RequestMethod.POST)
	public String validateUser(HttpServletRequest request ) throws Exception
	{
		
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean flag=false;
		boolean check = false;
		try{
			
		flag = login.validateAdmin(username, password);
		check = login.validateUser(username, password);
		
		}
		catch(AdException e){
		
			System.out.println("Exception: " + e.getMessage());
			
		}
		
		if(flag)
		{
			System.out.println("Login successful");
			session.setAttribute("username", username);
			return "adminHome";
			
		}
		
		else if(check){
			
			System.out.println("Login successful");
			session.setAttribute("username", username);
			return "viewCart";
			
		}
		else{
			return "error";
		}
	}
	
	@RequestMapping(value="/logout.htm", method=RequestMethod.GET)
	public String logout(@ModelAttribute("flightDetails") FlightDetails flightDetails,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		return "index";
		
	}
	
	@RequestMapping(value="/signup.htm", method=RequestMethod.GET)
	public String SignUp(@ModelAttribute("users")Users users){
		
		return "signup";
		
	}
	
	@RequestMapping(value="/signup.htm", method=RequestMethod.POST)
	public String SignUpUser(@ModelAttribute("users")Users users, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
		
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("ajaxCheck"))
		{
			PrintWriter out = response.getWriter();
			
			if(login.userExists(request.getParameter("username"))){
	              
	              JSONObject obj = new JSONObject();
	               obj.put("message","Username already exists");
	               out.println(obj);
	               
	           }
	          else{
	              out.println("Username is available");
	          }
			return null;
		}
		
		
		else if(action.equalsIgnoreCase("signup")){
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		username = username.replaceAll("[^\\dA-Za-z ]", "");
		String password = request.getParameter("password");
		String role= "customer";
		
		try
		{
			login.addUser(username,password,role);
		}
		catch(AdException e){
			
			System.out.println("Exception: " + e.getMessage());
			
		}
		
		System.out.println("New user added successfully");
		session.setAttribute("username", username);
		
		
	}
	
		return "viewCart";
	}
}
