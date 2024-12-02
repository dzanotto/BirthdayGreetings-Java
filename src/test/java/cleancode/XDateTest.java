package cleancode;

import cleancode.domain.XDate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class XDateTest {
	@Test
	public void getters() throws Exception {
		XDate date = new XDate("1789/01/24");
		assertThat(1).isEqualTo(date.getMonth());
		assertThat(24).isEqualTo(date.getDay());
	}

	@Test
	public void isSameDate() throws Exception {
		XDate date = new XDate("1789/01/24");
		XDate sameDay = new XDate("2001/01/24");
		XDate notSameDay = new XDate("1789/01/25");
		XDate notSameMonth = new XDate("1789/02/25");

		assertThat(date.isSameDay(sameDay)).isTrue();
		assertThat(date.isSameDay(notSameDay)).isFalse();
		assertThat(date.isSameDay(notSameMonth)).isFalse();
	}

	@Test
	public void equality() throws Exception {
		XDate base = new XDate("2000/01/02");
		XDate same = new XDate("2000/01/02");
		XDate different = new XDate("2000/01/04");

		assertThat(base).isNotNull();
		assertThat(base).isNotEqualTo("");
		assertThat(base).isEqualTo(base);
		assertThat(base).isEqualTo(same);
		assertThat(base).isNotEqualTo(different);
	}

}
