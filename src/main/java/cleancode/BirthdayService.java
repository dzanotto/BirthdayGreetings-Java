package cleancode;

import cleancode.domain.EmployeeRepository;

import java.io.IOException;
import java.util.List;

public class BirthdayService {

    private EmployeeRepository employeeRepository;
    private EmailService emailService;

    public BirthdayService(EmployeeRepository repository, EmailService emailService1) {
        employeeRepository = repository;
        emailService = emailService1;
    }

    public void sendGreetings(XDate xDate) throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            if (employee.isBirthday(xDate)) {
                String recipient = employee.getEmail();
                String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
                String subject = "Happy Birthday!";
                emailService.sendMessage("sender@here.com", subject, body, recipient);
            }
        }
    }
}
