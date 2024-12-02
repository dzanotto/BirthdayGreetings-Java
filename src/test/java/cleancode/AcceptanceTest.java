package cleancode;

import cleancode.domain.XDate;
import cleancode.infrastructure.EmailSender;
import cleancode.infrastructure.FileSystemEmployeeRepository;
import cleancode.usecase.SendGreetings;
import com.dumbster.smtp.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;


public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private SendGreetings sendGreetings;
	private SimpleSmtpServer mailServer;

	@BeforeEach
	public void setUp() {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		sendGreetings = new SendGreetings(
				new FileSystemEmployeeRepository("employee_data.txt"),
				new EmailSender("localhost", NONSTANDARD_PORT));
	}

	@AfterEach
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}

	@Test
	public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

		sendGreetings.sendGreetings(new XDate("2008/10/08"));

		assertThat(mailServer.getReceivedEmailSize()).isEqualTo(1);
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertThat(message.getBody()).isEqualTo("Happy Birthday, dear John!");
		assertThat(message.getHeaderValue("Subject")).isEqualTo("Happy Birthday!");
		String[] recipients = message.getHeaderValues("To");
		assertThat(recipients.length).isEqualTo(1);
		assertThat(recipients[0]).isEqualTo("john.doe@foobar.com");
	}

	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		sendGreetings.sendGreetings(new XDate("2008/01/01"));

		assertThat(mailServer.getReceivedEmailSize()).isEqualTo(0);
	}
}
