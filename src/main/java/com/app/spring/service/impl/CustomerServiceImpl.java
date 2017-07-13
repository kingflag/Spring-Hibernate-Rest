package com.app.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.spring.dao.CustomerDAO;
import com.app.spring.model.Customer;
import com.app.spring.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerDAO customerDAO;

  public void setCustomerDAO(CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  @Override
  @Transactional
  public void addCustomer(Customer c) {
    this.customerDAO.addCustomer(c);
  }

  @Override
  @Transactional
  public void updateCustomer(Customer c) {
    this.customerDAO.updateCustomer(c);
  }

  @Override
  @Transactional
  public List<Customer> listCustomers() {
    return this.customerDAO.listCustomers();
  }

  @Override
  @Transactional
  public Customer getCustomerById(int id) {
    return this.customerDAO.getCustomerById(id);
  }

  @Override
  @Transactional
  public void removeCustomer(int id) {
    this.customerDAO.removeCustomer(id);
  }

  /*
   * rest
   */
  @Override
  @Transactional
  public Customer hbaddCustomer(Customer c) {
    return this.customerDAO.hbaddCustomer(c);
  }

  @Override
  @Transactional
  public Customer hbupdateCustomer(Customer c) {
    return this.customerDAO.hbupdateCustomer(c);
  }

  @Override
  @Transactional
  public List<Customer> hblistCustomers() {
    return this.customerDAO.hblistCustomers();
  }

  @Override
  @Transactional
  public Customer hbgetCustomerById(int id) {
    return this.customerDAO.hbgetCustomerById(id);
  }

  @Override
  @Transactional
  public void hbremoveCustomer(int id) {
    this.customerDAO.hbremoveCustomer(id);
  }
}
