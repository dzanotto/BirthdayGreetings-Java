package cleancode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public List<Employee> findAll(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        in.readLine(); // skip header
        String str = "";

        List<Employee> employees = new ArrayList<>();
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
            employees.add(employee);
        }

        return employees;
    }
}
