package main;


import model.entity.Employee;
import repository.impl.EmployeeRepository;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        EmployeeRepository fatjon = new EmployeeRepository();
//        for (Employee employee : fatjon.findAll()){
//            System.out.println(employee.getId()+ " " +employee.getFirstName());
//        }
        fatjon.exists(1056);


        fatjon.save(new Employee(1056,"Vogli","Fatjon"));
        fatjon.save(new Employee(111111,"Vogli","Fatjon"));


    }

}
