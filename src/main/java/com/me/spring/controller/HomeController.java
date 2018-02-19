package com.me.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.spring.dao.FlightDetailsDAO;
import com.me.spring.pojo.Cities;
import com.me.spring.pojo.FlightDetails;



@Controller
@RequestMapping(value="/")
public class HomeController {
	
	@Autowired
	@Qualifier("fdao")
	FlightDetailsDAO fdao;
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("flightDetails")FlightDetails flightDetails) {

		flightDetails.setTravelClass("Economy");
		return "index";
	}
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("flightDetails")FlightDetails flightDetails) {

		flightDetails.setTravelClass("Economy");
		return "index";
	}
	
	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.GET)
	public String adminHomePage() {

		
		return "adminHome";
	}
	
	@RequestMapping(value="/fromCitieslist.htm", method=RequestMethod.POST)
	public void ajaxForCities(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try{
		
		String fromCities = request.getParameter("fromCities");
		PrintWriter out = response.getWriter();
		
		List list = fdao.listCities(fromCities);
		
		
		  List<Cities> cities= (ArrayList<Cities>)list;
		  
          JSONArray jsonArray = new JSONArray();
          for (int i=0; i < cities.size(); i++) {
              JSONObject obj = new JSONObject();
              obj.put("cityname", cities.get(i).getCityname());
              System.out.println("***********"+cities.get(i).getCityname());
              jsonArray.put(obj);
          }
  
          JSONObject Obj = new JSONObject();
          Obj.put("list", jsonArray);
          out.println(Obj);
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in listing cities"+e.getMessage());
		}
	}
}
