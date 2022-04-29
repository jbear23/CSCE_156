package unl.cse;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

public class Birthday {
	public static void main(String args[]) {

		String name = "Josh";

		int month = 2;
		int date = 23;
		int year = 2000;

		DateTime bday = new DateTime(year, month, date, 0, 0);
		DateTime today = new DateTime();

		Period age = new Period(bday, today);

		int years = age.getYears();
		int months = age.getMonths();
		int days = age.getWeeks() * 7 + age.getDays();

		DateTime next_bday = new DateTime(year + years + 1, month, date, 0, 0);
		Interval daysToNextBdayI = new Interval(today, next_bday);
		double daysRemaining = daysToNextBdayI.toDurationMillis() / (1000 * 60 * 60 * 24) + 1;

		// TODO: write code to output results here
		System.out.println("Greetings, " + name + ". Today you are " + years + " years, " + months + " months, and " + days + " days old.");
		if(bday == today) {
			System.out.println("Happy Birthday!");
		} else {
			System.out.println("Your friends have " + daysRemaining + " shopping days until your next birthday.");
		}
	}
}
