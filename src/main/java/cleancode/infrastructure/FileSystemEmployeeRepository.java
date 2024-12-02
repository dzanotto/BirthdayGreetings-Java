package cleancode.infrastructure;

import cleancode.domain.Employee;
import cleancode.domain.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSystemEmployeeRepository implements EmployeeRepository {

    String fileName;

    public FileSystemEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> findAll() throws IOException {
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
