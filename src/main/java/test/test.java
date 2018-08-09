package test;

import frame.MainFrame;

import javax.swing.*;
import java.awt.*;


public class test {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame mainFrame = new MainFrame();
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });




//        CustomerDAOTempImpl customerDAOTemp = new CustomerDAOTempImpl();
//        Map<Integer, Customer> allCustomer = customerDAOTemp.getAllCustomer();
//
//
//      //  for (Map.Entry<Integer, Customer> c : allCustomer.entrySet()) {
//          //  System.out.println(c.toString());
//        //}
//
//
//        Customer customer = customerDAOTemp.getCustomer(1);
//        System.out.println("Znaleziono klienta: " + customer.toString());
//
//        customer.setName("Piotr");
//        customerDAOTemp.updateCustomer(customer);
//        customerDAOTemp.getCustomer(1);
//
//
//        for( Map.Entry<Integer, Customer>  cd: allCustomer.entrySet()){
//            System.out.println("Karol"+ cd);
//        }
//
//
//        System.out.println(customer.toString());


    }
}
