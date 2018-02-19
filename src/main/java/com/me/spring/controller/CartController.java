package com.me.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.spring.dao.FlightDetailsDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.FlightDetails;

@Controller
@RequestMapping(value="/*Cart.htm")
public class CartController {

	@Autowired
	@Qualifier("fdao")
	FlightDetailsDAO fdao;
	
	@RequestMapping(value="/addToCart.htm", method=RequestMethod.GET)
	public String intialize(HttpServletRequest request, HttpServletResponse response) throws AdException, IOException, JSONException{
		
		try{
		HttpSession session = request.getSession();
		System.out.println("flight id" + request.getParameter("fid"));
		Long flightid = Long.parseLong(request.getParameter("fid"));
		System.out.println("Flight ID is"+flightid);
		List<FlightDetails> cart;
 
		FlightDetails fd = fdao.searchFlightByID(flightid);
		
		System.out.println("Cart Controller:"+fd.getAvailableSeats());
		
		int noOfSeats = fd.getAvailableSeats();
		PrintWriter out = response.getWriter();
		if(noOfSeats>0)
		{
			
			out.println("Seats are available");
			if (session.getAttribute("cart") != null) {
	             cart = (ArrayList<FlightDetails>) session.getAttribute("cart");
	         } else {
	             cart = new ArrayList<FlightDetails>();
	         }
			
			 cart.add(fd);
			 session.setAttribute("cart", cart);
			 session.setAttribute("flightdetail", fd);
			 
			 float total = 0;
	         for (FlightDetails f : cart) {
	             total = total + f.getAmount();
	         }
	         
	         session.setAttribute("total", total);
	         
	         return "viewCart";
	         
			}
		
		else
		{
			
			return "notAvailable";
			
		}	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value="/removeFromCart.htm", method=RequestMethod.GET)
	public String removeItems(HttpServletRequest request) throws AdException{
		
		HttpSession session = request.getSession();
		try{
			
			List<FlightDetails> cart =(ArrayList<FlightDetails>) session.getAttribute("cart");
			String id = request.getParameter("id");
			long flight_id = Long.parseLong(id); 
			
			
			for(FlightDetails fd:cart){
				if(fd.getFlight_id()==flight_id){
					cart.remove(fd);
					break;
					
				}
			}
			
			session.setAttribute("cart", cart);
			
			 float total = 0;
	         for (FlightDetails f : cart) {
	             total = total + f.getAmount();
	         }
	         
	         session.setAttribute("total", total);
		}
		
		catch(Exception e)
		{
			System.out.println("Could not remove flight object"+ e);
		}
		
		
		return "viewCart";
	}
	
	@RequestMapping(value="/viewCart.htm", method=RequestMethod.GET)
	public String viewCart(HttpServletRequest request) {
		return "viewCart";
		
	}
		
	}

