package com.me.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.spring.dao.ListFlightsDAO;
import com.me.spring.exception.AdException;
import com.me.spring.pojo.Airplane;
import com.me.spring.pojo.FlightDetails;

@Controller
@RequestMapping(value="/listflights.htm")
public class ListFlightsController {

	@Autowired
	@Qualifier("ldao")
	ListFlightsDAO ldao;
	
	
	@RequestMapping(value = "/listflights.htm", method = RequestMethod.POST)
	public String initializeForm(@ModelAttribute("flightDetails") FlightDetails flightDetails, HttpServletRequest request) throws AdException
	{
		HttpSession session = request.getSession();
		String from = request.getParameter("from");
		from = from.replaceAll("[^A-Za-z]+$", "");
		String dest = request.getParameter("dest");
		dest = dest.replaceAll("[^A-Za-z]+$", "");
		String deptDate = request.getParameter("deptDate");
		String retDate = request.getParameter("arrDate");

		System.out.println("From place"+from+"Dest"+dest+"Dept date"+deptDate);
		
		try{
		
		List<String> flightlist = ldao.listFlights(from, dest, deptDate);
		
		int length = flightlist.size();
		System.out.println("Length is "+length);
		
		
		session.setAttribute("flightlist", flightlist);
	
		
		}
		catch(AdException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		return "flightList";
	}
	
}
