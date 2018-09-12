package connectionProvider;

import daoimpl.ComputerComponentJPA;
import daoimpl.CustomerJPA;
import model.Adres;
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



        CustomerJPA customerJPA = new CustomerJPA();

        Customer customer = new Customer();
        Adres adres = new Adres();


        customer.setName("Karol");
        customer.setLastname("Sidor");
        customer.setAdres(adres);
        adres.setLocality("Lublin");
        adres.setZipCode("sdsfs");
        adres.setStreetNumber(22);
        adres.setStreet("dfsdfs");

        customerJPA.addCustomer(customer);



    }
}
