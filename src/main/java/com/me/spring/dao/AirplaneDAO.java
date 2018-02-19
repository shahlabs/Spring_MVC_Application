package com.me.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Airplane;
import com.me.spring.pojo.FlightDetails;



public class AirplaneDAO extends DAO {

	public Airplane create(String airlineName, String owner) throws AdException{
		try {
            begin();
            Airplane airplane = new Airplane(airlineName, owner);
            getSession().save(airplane);
            commit();
            return airplane;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not create the airplane", e);
        }
		finally{
			close();
		}
		
	}
	
	
	public void updateAirplane(Airplane airplane) throws AdException {
        try {
            begin();
            getSession().update(airplane);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not update the airplane", e);
        }
        finally{
			close();
		}
    }
	
	public Airplane searchAirplaneByID(long airplane_id) throws AdException {
        try {
        	
            begin();
            Query q = getSession().createQuery("from Airplane where airplane_id = :airplane_id");
            q.setLong("airplane_id", airplane_id);
            Airplane airplane = (Airplane) q.uniqueResult();
            commit();
            return airplane;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the airplane details whose id is: " + airplane_id + " " + e.getMessage());
        }
        finally{
			close();
		}
    }
	
	public void deleteAirplane(long airplane_id) throws AdException{
		
		try{
			
//			Query q = getSession().createQuery("delete Airplane where airplane_id=:airplane_id");
//			q.setLong("airplane_id", airplane_id);
//			int result = q.executeUpdate();
			
			Airplane airplane = searchAirplaneByID(airplane_id);
			begin();
			getSession().delete(airplane);
			commit();
			
			
		}
		catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete the airplane", e);
            
        }
		finally{
			close();
		}
		
		
	}
	
	
}
