package cleancode;

import cleancode.domain.XDate;
import cleancode.infrastructure.EmailSender;
import cleancode.infrastructure.FileSystemEmployeeRepository;
import cleancode.usecase.SendGreetings;

import java.io.*;
import java.text.ParseException;

import javax.mail.*;
import javax.mail.internet.*;

public class Main {

	public static void main(String[] args) throws AddressException, IOException, ParseException, MessagingException {
		SendGreetings service = new SendGreetings(
				new FileSystemEmployeeRepository("employee_data.txt"),
				new EmailSender("localhost", 25));
		service.sendGreetings(new XDate());
	}

}
