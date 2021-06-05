package test;

import java.text.ParseException;

import util.general.DateTimeUtil;

public class Testing {
	public static void main(String[] args) throws ParseException {
//		DateTimeUtil.calculateParikngFee("Valet Parking", "05/06/2021", "03:30", "AM", "05/06/2021", "04:40", "AM");
//		System.out.println(DateTimeUtil.dateCompare("05/06/2021", "05:40", "AM", "05/06/2021", "04:40", "AM"));
//		double a =2.7;
//		int b = (int)(a / 24);
//		double c = a % 24;
//		System.out.println(c);
//		double a = 2.7;
//		System.out.println(a - 1);
		System.out.println(DateTimeUtil.calculateParkingFeeFromParkingType("Valet Parking", 36.72));
	}

}
