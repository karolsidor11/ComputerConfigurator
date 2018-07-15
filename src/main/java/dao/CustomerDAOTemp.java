package dao;

import model.Customer;


import java.util.List;
import java.util.Map;

public interface CustomerDAOTemp {

    Map<Integer, Customer> getAllCustomer();

    List<Customer> getAllCustomerList();

    Customer getCustomer(Integer id);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
