package dao;

import model.Customer;

import java.util.List;

//TODO tak samo jak w ComputerSetDao do poprawy nazwy metod + usuń niepotrzebny import
public interface CustomerDAO {

    Customer getById(Integer id);

    List<Customer> allCustomer();

    void addCustomer(Customer customer);

    void mergeCustomer(Customer customer);

    void removeCustomer(Customer customer);

    void removeById(Integer id);
}
