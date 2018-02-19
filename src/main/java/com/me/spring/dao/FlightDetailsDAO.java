package com.me.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Airplane;
import com.me.spring.pojo.Cities;
import com.me.spring.pojo.FlightDetails;



public class FlightDetailsDAO extends DAO{

	public FlightDetails createFlight(long airplane_id, String from, String dest, String deptTime,
			String arrivalTime, String travelClass, int totalSeats, int amount, String deptDate, String arrDate,
			String flight_name, int availableSeats) throws AdException
	{
		try{
		begin();	
		FlightDetails fd = new FlightDetails(airplane_id, from, dest, deptTime,
				arrivalTime, travelClass, totalSeats, amount, deptDate, arrDate, 
				flight_name,availableSeats);
		getSession().save(fd);
		commit();
		//fd.setAvailableSeats(totalSeats);
		System.out.println("Added flight and available seats are"+fd.getAvailableSeats());
		return fd;
		}
		catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create flight", e);
            throw new AdException("Exception while creating new flight: " + e.getMessage());
        }    finally{
			close();
		}    
	}
	
	public List listFlights() throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from FlightDetails");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the flights", e);
        }finally{
			close();
		}
    }
	
	public List listCities(String cityname) throws AdException
	{
		try{
			begin();
			//Query q = getSession().createSQLQuery("select * from cities where cityname like '%"+cityname+"%'");
			//q.setString("cityname", "'%"+cityname+"%'");
			Criteria city = getSession().createCriteria(Cities.class);
			city.add(Restrictions.ilike("cityname", cityname,MatchMode.ANYWHERE));
			List list = city.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
            rollback();
            throw new AdException(e.getMessage());
        }
		finally{
			close();
		}
	}
	
	public FlightDetails searchFlightByID(long flight_id) throws AdException {
        try {
        	
            begin();
            Query q = getSession().createQuery("from FlightDetails where flight_id = :flight_id");
            q.setLong("flight_id", flight_id);
            FlightDetails fd = (FlightDetails) q.uniqueResult();
            System.out.println("DAO available seats"+fd.getAvailableSeats());
            System.out.println("others**********"+fd.getFlight_name()+fd.getFlight_id());
            commit();
            return fd;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the flight details whose id is: " + flight_id + " " + e.getMessage());
        }
        finally{
			close();
		}
        
    }
	
	public void deleteFlight(FlightDetails flight)
            throws AdException {
        try {
            begin();
            getSession().delete(flight);
            commit();
            getSession().flush();
            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete flight", e);
        }finally{
			close();
		}
    }
	
	
	public void updateFlight(FlightDetails flight) throws AdException
	{
		try {
            begin();
            getSession().update(flight);
            commit();
            getSession().flush();
            getSession().clear();
            
        } catch (HibernateException e) {
            rollback();
            throw new AdException(e.getMessage());
        }finally{
			close();
		}
	}
	
	public void updateAvailableSeats(FlightDetails flight, int oldTotal, int newTotal) throws AdException
	{
		try {
            begin();
            
            	int oldAvail = flight.getAvailableSeats();
				System.out.println("Old Seats available are"+flight.getAvailableSeats());
				flight.setAvailableSeats(newTotal-(oldTotal-oldAvail));
				System.out.println("Available seats are "+flight.getAvailableSeats());
				
			getSession().update(flight);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not update flight", e);
        }finally{
			close();
		}
	}
}
