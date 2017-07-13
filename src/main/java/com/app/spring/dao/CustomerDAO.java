package com.app.spring.dao;

import java.util.List;

import com.app.spring.model.Customer;

/**
 * 
 * @author malalanayake
 *
 */
public interface CustomerDAO {

  public void addCustomer(Customer p);

  public void updateCustomer(Customer p);

  public List<Customer> listCustomers();

  public Customer getCustomerById(int id);

  public void removeCustomer(int id);

  /*
   * hb
   */
  public Customer hbaddCustomer(Customer p);

  public Customer hbupdateCustomer(Customer p);

  public List<Customer> hblistCustomers();

  public Customer hbgetCustomerById(int id);

  public void hbremoveCustomer(int id);
}
