package com.me.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.spring.dao.PassengerDAO;
import com.me.spring.dao.TicketDAO;
import com.me.spring.pojo.Passenger;
import com.me.spring.pojo.Ticket;

@Controller
@RequestMapping(value="/*passenger*.htm")
public class PassengerController {

	@Autowired
	@Qualifier("passengerValidator")
	PassengerValidator validator;
	
	@Autowired
	@Qualifier("pdao")
	PassengerDAO pdao;
	
	@Autowired
	@Qualifier("tdao")
	TicketDAO tdao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	} 
	
	@RequestMapping(value = "/passenger1.htm", method = RequestMethod.GET)
	public String checkUserInSession(HttpServletRequest request, @ModelAttribute("passenger") Passenger passenger,BindingResult result ) {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);

		if (username == null) {
			return "message";
		}

		else {
			return "passenger";
		}

	}

	
	@RequestMapping(value="/passenger.htm", method=RequestMethod.GET)
	public String initialize(@ModelAttribute("passenger")Passenger passenger,HttpServletRequest request,BindingResult result)
	{
		HttpSession session = request.getSession();
		int noOfTravellers=1;
		session.setAttribute("noOfTravellers",noOfTravellers);
		return "passenger";
	}
	
	
	@RequestMapping(value="/passenger.htm", method=RequestMethod.POST)
	public String doSubmit(@ModelAttribute("passenger")Passenger passenger,BindingResult result, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		validator.validate(passenger, result);
    	if(result.hasErrors()){
    		return "passenger"; 
    	}
    	
		try{
	
			String firstName=passenger.getFirstName(); 
			firstName = firstName.replaceAll("[^A-Za-z]+$", "");
			String lastName=passenger.getLastName(); 
			lastName = lastName.replaceAll("[^A-Za-z]+$", "");
			String gender=passenger.getGender(); 
			String email=passenger.getEmail(); 
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			         + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			String dob=passenger.getDob(); 
			String phonenum=passenger.getPhonenum();
			phonenum = phonenum.replaceAll("[^0-9]", "-");
			String address=passenger.getAddress();
			
			Passenger pas = pdao.createPassenger(firstName,lastName,gender,email,dob,phonenum,address);
			long passenger_id = pas.getId(); 
			session.setAttribute("passenger_id", passenger_id);
			
		}
		catch(Exception e)
		{
			System.out.println("Could not create Passenger"+e.getMessage());
		}
		
		//Payment payment = new Payment();
		
		return "payment";
	}
	
	@RequestMapping(value="/viewpassengers.htm", method=RequestMethod.GET)
	public ModelAndView viewPassenger(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		List<Ticket> ticketList= new ArrayList<Ticket>();
		try{
			ticketList = tdao.TicketList();
			System.out.println("----->"+ticketList.size());
		   }
		catch(Exception e)
		{
			System.out.println("Could not list passengers"+e.getMessage());
		}
		ModelAndView mv = new ModelAndView("passengerList", "ticketList", ticketList);
        return mv;
	}
	
	
}
