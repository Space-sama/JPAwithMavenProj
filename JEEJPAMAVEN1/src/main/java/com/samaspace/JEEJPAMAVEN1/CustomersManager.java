package com.samaspace.JEEJPAMAVEN1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.google.protobuf.TextFormat.InvalidEscapeSequenceException;

public class CustomersManager {
	
	public static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("JEEJPAMAVEN1");
	
	public static void addCustomer(String fname, String lname) {
		
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	EntityTransaction et = null;
    	
    	try {
    		
    		et = em.getTransaction();
    		et.begin();
    		
    		Customers C = new Customers();
    		//C.setCust_id(id);
    		C.setFName(fname);
    		C.setLName(lname);
    		
    		em.persist(C);
    		et.commit();
			
		} catch (Exception e) {
			
			if(et != null) {
				et.rollback();
			}
			
			e.printStackTrace();
			
		}
    	finally {
			
    		em.close();
		}
    	
    }
    
    public static void getOneCustomer(int id) {
    		
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	String query = "SELECT c FROM Customers c WHERE c.id = :custID";
    	
    	TypedQuery<Customers> tq = em.createQuery(query, Customers.class);
    	tq.setParameter("custID", id);
    	Customers C = null;
    	
    	try {
    		
    		C = tq.getSingleResult();
    		System.out.println("********** CUSTRMOER *************");
    		System.out.println(C.getFName() + " " + C.getLName());
    		
		} catch (NoResultException ex) {
			
			ex.printStackTrace();
		}
    	
    	finally {
    		
			em.close();
		}
    	
    	
    }
    
    public static void getCustomerByName(String n) {
		
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	String query = "SELECT c FROM Customers c WHERE c.FName LIKE :fname";
    	
    	TypedQuery<Customers> tq = em.createQuery(query, Customers.class);
    	tq.setParameter("fname", "%"+n+"%");
    	List<Customers> C = null;
    	
    	try {
    		
    		C = tq.getResultList();
    		System.out.println("********** CUSTRMOERS *************");
    		C.forEach(clt->System.out.println("Here is the customer --> " + " " + clt.getFName() + " " + clt.getLName()));
    		
    	
    		
    	
    	
    		
		} catch (NoResultException ex) {
			
			ex.printStackTrace();
		}
    	
    	finally {
    		
			em.close();
		}
    	
    	
    }
    
    public static void getAllCustomer() {
		
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	String query = "SELECT c FROM Customers c WHERE c.id IS NOT NULL";
    	
    	TypedQuery<Customers> tq = em.createQuery(query, Customers.class);
    	List<Customers> custs;
    	
    	try {
    		custs = tq.getResultList();
    		custs.forEach(cust->System.out.println(cust.getFName() + " " + cust.getLName()));
			
		} catch (Exception e) {
			System.out.println("Not generated !");
			e.printStackTrace();
			
		}
    	finally {
			em.close();
		}
  
    }
    
    
    public static void updateCustomer(int id, String fname, String lname) {
		
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	EntityTransaction et = null;
    	Customers Cust = null;
    	
    	try {
    		
    		et = em.getTransaction();
    		et.begin();
    		
    		Cust = em.find(Customers.class, id);
    		Cust.setFName(fname);
    		Cust.setLName(lname);
    		
    		em.persist(Cust);
    		et.commit();
			
		} catch (Exception e) {
			
			if(et != null) {
				et.rollback();
			}
			
			e.printStackTrace();
			
		}
    	finally {
			
    		em.close();
		}
    	
    }
    
    public static void deleteCustomer(int id) {
    	
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	EntityTransaction et = null;
    	Customers cust = null;
    	
    	try {
    		
    		et = em.getTransaction();
    		et.begin();
    		
    		cust = em.find(Customers.class, id);
    		
    		//if(cust.equals(true)) {
    			
    			em.remove(cust);
        		et.commit();
    		//}
    		
    		//else {
    			//System.out.println("Already deleted !");
    		//}
    		
    		
			
		} catch (Exception e) {
			if(et != null) {
				
				et.rollback();
			}
			
			e.printStackTrace();
		}
    	
    	finally {
			em.close();
		}
    	
    	
    }
    
    
    public static void closeEntity() {
    	ENTITY_MANAGER_FACTORY.close();
    }

}
