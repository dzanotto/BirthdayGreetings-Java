package cleancode.domain;

import java.io.IOException;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll() throws IOException;
}
