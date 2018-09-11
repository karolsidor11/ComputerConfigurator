package connectionProvider;

import daoimpl.ComputerComponentJPA;
import daoimpl.CustomerJPA;
import model.ComputerComponent;
import model.Customer;

import java.util.List;

public class Test {
    public static void main(String[] args) {


//        CustomerJPA customerJPA = new CustomerJPA();
//        List<Customer> customers = customerJPA.allCustomer();
//
//
//        for (Customer a: customers) {
//            System.out.println( a.getName());
//            System.out.println(a.getId());
//        }
//
//        customerJPA.removeById(1);
//         Customer cc= new Customer();

        ComputerComponentJPA  computerComponentJPA = new ComputerComponentJPA();

        ComputerComponent byId = computerComponentJPA.getById(21);

     //computerComponentJPA.removeComputerComponent(byId);


//        customerJPA.mergeCustomer();


    }
}
