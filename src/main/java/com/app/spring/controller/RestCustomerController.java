package com.app.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.spring.common.JsonUtil;
import com.app.spring.model.Customer;
import com.app.spring.service.CustomerService;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONValue;

@RestController
public class RestCustomerController {

  private Logger logger = Logger.getLogger(RestCustomerController.class);

  private CustomerService customerService;

  @Autowired(required = true)
  @Qualifier(value = "customerService")
  public void setPersonService(CustomerService cs) {
    this.customerService = cs;
  }

  /*
   * 以下使用rest的接口方式
   */

  @RequestMapping(value = "/hbcustomers", method = RequestMethod.GET)
  public String hblistCustomers() {
    Map<String, String> map = new HashMap<String, String>();
    List<Customer> customers = this.customerService.hblistCustomers();
    List<String> cus = new ArrayList<String>();
    for (Customer c : customers) {
      logger.info("Customer List::" + c);
      try {
        cus.add(JsonUtil.objectToJsonStr(c));
      } catch (MapperException e) {
        logger.info("err");
      }
    }
    map.put("result", cus.toString());
    return map.toString();
  }

  @RequestMapping(value = "/hbcustomer/add", method = RequestMethod.POST)
  @ResponseBody
  public String hbaddCustomer(@RequestBody Customer c) {
    try {
      logger.info(c.toString());
      Customer customer = new Customer();
      Map<String, String> result = new HashMap<String, String>();
      if (c.getId() == 0) {
        // new person, add it
        customer = this.customerService.hbaddCustomer(c);
        result.put("insert", JsonUtil.objectToJsonStr(customer));
      } else {
        // existing person, call update
        customer = this.customerService.hbupdateCustomer(c);
        result.put("update", JsonUtil.objectToJsonStr(customer));
      }
      return result.toString();
    } catch (Exception e) {
      logger.info("出现异常");
      return null;
    }

  }

  @RequestMapping("/hbcustomer/remove/{id}")
  public String hbremoveCustomer(@PathVariable("id") int id) {

    this.customerService.hbremoveCustomer(id);
    return "redirect:/hbcustomers";
  }

  @RequestMapping("/hbcustomer/edit/{id}")
  public String hbeditCustomer(@PathVariable("id") int id, Model model) {
    model.addAttribute("customer", this.customerService.hbgetCustomerById(id));
    model.addAttribute("listCustomers", this.customerService.hblistCustomers());
    return "customer";
  }

}
