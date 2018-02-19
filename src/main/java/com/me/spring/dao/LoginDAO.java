package com.me.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.me.spring.exception.AdException;
import com.me.spring.pojo.Users;

public class LoginDAO extends DAO {

	public boolean validateAdmin(String username,String password) throws AdException
	{
		try{
			
			
			SQLQuery q = getSession().createSQLQuery("select * from users where username=:username and password=:password and role='ADMIN'");
			q.setString("username", username);
			q.setString("password",password);
		    Object obj = q.uniqueResult();
		    if(obj==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new AdException("Could not find any user", e);
		}finally{
			close();
		}
	
		
		
		return true;
		
	}
	
	
	public boolean validateUser(String username,String password) throws AdException
	{
		try{
			
			
			SQLQuery q = getSession().createSQLQuery("select * from users where username=:username and password=:password and role='customer'");
			q.setString("username", username);
			q.setString("password",password);
		    Object obj = q.uniqueResult();
		    if(obj==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new AdException("Could not find any user", e);
		}finally{
			close();
		}
	
		
		
		return true;
		
	}
	
	
	
	
	public void addUser(String username, String password, String role) throws AdException{
		
		try{
			begin();
			Users u = new Users(username, password, role);
			getSession().save(u);
			commit();
		}
		catch(HibernateException e){
			rollback();
            throw new AdException("Could not add user", e);
		}finally{
			close();
		}
	
		
		
	}
	
	public boolean userExists(String username)
	{
		try{
			begin();
			Query q = getSession().createQuery("From Users where username=:username");
			q.setString("username", username);
			List list = q.list();
			commit();
			
			if(list.size()==0)
			{
				return false;
			}
			
			
			
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		finally{
			close();
		}
		return true;
	}
}
