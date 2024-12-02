package cleancode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

    public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort) throws IOException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employees = employeeRepository.findAll(fileName);

        for (Employee employee : employees) {
            if (employee.isBirthday(xDate)) {
                String recipient = employee.getEmail();
                String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
                String subject = "Happy Birthday!";
                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
            }
        }
    }

    private void sendMessage(String smtpHost, int smtpPort, String sender, String subject, String body, String recipient) {
        try {
            // Create a mail session
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", "" + smtpPort);
            Session session = Session.getInstance(props, null);

            // Construct the message
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(subject);
            msg.setText(body);

            // Send the message
            Transport.send(msg);
        } catch (Exception e) {
            throw new RuntimeException("Unable to send email", e);
        }
    }
}
