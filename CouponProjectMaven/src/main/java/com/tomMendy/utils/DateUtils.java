package com.tomMendy.utils;

import java.time.LocalDate;

public class DateUtils {

	public static java.sql.Date GetCurrentDate() {
		LocalDate startDate = LocalDate.now();
		java.sql.Date date = java.sql.Date.valueOf(startDate);
		return date;
	}

	public static java.sql.Date GetEndDate() {
		LocalDate endDate = LocalDate.now();
		endDate = endDate.plusMonths(1);
		java.sql.Date date = java.sql.Date.valueOf(endDate);
		System.out.println("this is local date" + date);

		return date;

	}

	public static String getDBUrl() {
		// TODO Auto-generated method stub
		return "jdbc:derby://localhost:3301/JDBD;create=true";
	}

}
