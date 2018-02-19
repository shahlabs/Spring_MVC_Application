package com.me.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.spring.exception.AdException;

public class ListFlightsDAO extends DAO {

	public List listFlights(String fromPlace, String dest, String deptDate)throws AdException{
	
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from FlightDetails where fromPlace = :fromPlace and dest=:dest and deptDate=:deptDate");
			q.setString("fromPlace", fromPlace);
			q.setString("dest", dest);
			q.setString("deptDate", deptDate);
			
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new AdException("Could not find any flights", e);
		}finally{
			close();
		}
		
		
	}
	
	public List listAllFlights()throws AdException{
		
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from FlightDetails");
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new AdException("Could not find any flights", e);
		}
		finally{
			close();
		}
		
	}
	
	
}
