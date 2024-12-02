package cleancode;

import cleancode.infrastructure.FileSystemEmployeeRepository;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
		BirthdayService service = new BirthdayService(
				new FileSystemEmployeeRepository("employee_data.txt"),
				new EmailService("localhost", 25));
		service.sendGreetings(new XDate());
	}

}
