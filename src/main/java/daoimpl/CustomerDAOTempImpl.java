package daoimpl;


import dao.CustomerDAOTemp;
import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOTempImpl implements CustomerDAOTemp {

    Map<Integer, Customer> customers = new HashMap<Integer, Customer>();
    List<Customer> customersList = new ArrayList<Customer>();


    public CustomerDAOTempImpl() {

        Customer customer = new Customer(1, "Jan", "Kowalski", "Lublin");
        Customer customer1 = new Customer(2, "Janina", "Kowalska", "Lublin");
        Customer customer2 = new Customer(3, "Piotr", "Kowalska", "Lublin");
        customers.put(customer.getId(), customer);
        customers.put(customer1.getId(), customer1);
        customers.put(customer2.getId(), customer2);

        customersList.add(customer);
        customersList.add(customer1);
        customersList.add(customer2);
    }

    public Map<Integer, Customer> getAllCustomer() {
        return customers;
    }

    public List<Customer> getAllCustomerList() {
        return customersList;
    }


    public Customer getCustomer(Integer id) {
        return customers.get(id);
    }

    public void updateCustomer(Customer customer) {

        Customer customerOld = customers.get(customer.getId());
        customerOld.setId(customer.getId());
        customerOld.setName(customer.getName());
        customerOld.setLastname(customer.getLastname());
        customerOld.setAdres(customer.getAdres());
        customers.put(customerOld.getId(), customerOld);

    }

    public void deleteCustomer(Integer id) {
        //customers.remove(id);
        customersList.remove(id);
    }

}
