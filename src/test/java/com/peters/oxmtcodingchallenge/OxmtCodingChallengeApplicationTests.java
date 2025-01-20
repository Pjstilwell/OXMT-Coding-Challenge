package com.peters.oxmtcodingchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.peters.oxmtcodingchallenge.exceptions.BadAgeException;
import com.peters.oxmtcodingchallenge.exceptions.BadTimeUnitException;

@SpringBootTest
class OxmtCodingChallengeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void InvalidTimeUnitHandledCorrectly() {
		assertThrows(BadTimeUnitException.class, () -> new Machine(1, "4 yards"));
		assertThrows(BadTimeUnitException.class, () -> new Machine(1, "1 dey"));
	}

	@Test
	void InvalidAgeHandledCorrectly() {
		assertThrows(BadAgeException.class, () -> new Machine(1, "4yards"));
		assertThrows(BadAgeException.class, () -> new Machine(1, "4"));
		assertThrows(BadAgeException.class, () -> new Machine(1, "3  "));
	}

	@Test
	void ageInDaysComputedCorrectlyDays() {
		Machine machine1 = new Machine(1, "10 days");
		assertEquals(10, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyWeeks() {
		Machine machine1 = new Machine(1, "12 weeks");
		assertEquals(84, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyMonths() {
		Machine machine1 = new Machine(1, "5 months");
		assertEquals(152.5, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyYears() {
		Machine machine1 = new Machine(1, "4 years");
		assertEquals(1461, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyDaysSingular() {
		Machine machine1 = new Machine(1, "10 day");
		assertEquals(10, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyWeeksSingular() {
		Machine machine1 = new Machine(1, "12 week");
		assertEquals(84, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyMonthsSingular() {
		Machine machine1 = new Machine(1, "5 month");
		assertEquals(152.5, machine1.getAgeInDays());
	}

	@Test
	void ageInDaysComputedCorrectlyYearsSingular() {
		Machine machine1 = new Machine(1, "4 year");
		assertEquals(1461, machine1.getAgeInDays());
	}

	@Test
	void zScoreComputesCorrectly() {
		OutlierComputer computer = new OutlierComputer();

		double[] data = { 1, 3, 5, 4, 12, 6, 14, 98, 0.5, 0.01, 13 };
		DescriptiveStatistics stat = new DescriptiveStatistics(data);

		assertEquals(-0.2913950537596499, computer.computeZScore(data[5], stat));
	}

}
