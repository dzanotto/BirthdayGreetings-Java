package cleancode.usecase;

import cleancode.domain.Employee;
import cleancode.domain.EmployeeRepository;
import cleancode.domain.MessageSender;
import cleancode.domain.XDate;

import java.io.IOException;
import java.util.List;

public class SendGreetings {

    private EmployeeRepository employeeRepository;
    private MessageSender messageSender;

    public SendGreetings(EmployeeRepository employeeRepository, MessageSender messageSender) {
        this.employeeRepository = employeeRepository;
        this.messageSender = messageSender;
    }

    public void sendGreetings(XDate xDate) throws IOException {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            if (employee.isBirthday(xDate)) {
                String recipient = employee.getEmail();
                String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
                String subject = "Happy Birthday!";
                messageSender.sendMessage("sender@here.com", subject, body, recipient);
            }
        }
    }
}
