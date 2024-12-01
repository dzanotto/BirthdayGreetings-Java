package cleancode;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

	@Test
	public void testBirthday() throws Exception {
		Employee employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");
		assertThat(employee.isBirthday(new XDate("2008/01/30"))).isFalse();
		assertThat(employee.isBirthday(new XDate("2008/01/31"))).isTrue();
	}

	@Test
	public void equality() throws Exception {
		Employee base = new Employee("First", "Last", "1999/09/01", "first@last.com");
		Employee same = new Employee("First", "Last", "1999/09/01", "first@last.com");
		Employee different = new Employee("First", "Last", "1999/09/01", "boom@boom.com");

		assertThat(base).isNotNull();
		assertThat(base).isNotEqualTo("");
		assertThat(base).isEqualTo(same);
		assertThat(base).isNotEqualTo(different);
	}
}
