package util.general;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.lang.Math;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
	public static double calculateParikngFee(String parkingType, String startDate, String startTime, String startingAMPM, String endDate, String endTime, String endingAMPM) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		
//		System.out.println(startDate + " " + startTime + " " + startingAMPM);
//		System.out.println(endDate + " " + endTime + " " + endingAMPM);
		
		LocalDateTime startDateTime = LocalDateTime.parse(startDate + " " + startTime + " " + startingAMPM, format);
		LocalDateTime endDateTime = LocalDateTime.parse(endDate + " " + endTime + " " + endingAMPM, format);
		
		long diff = Duration.between(startDateTime, endDateTime).toMillis();

		double diffHours = diff / (60.0 * 60.0 * 1000.0);
		return calculateParkingFeeFromParkingType(parkingType, diffHours);
	}
	
	public static int dateCompare(String startDate, String startTime, String startingAMPM, String endDate, String endTime, String endingAMPM) throws ParseException {
//		Date startingDate = new SimpleDateFormat("MM/DD/YY hh:mm a").parse(startDate + " " + startTime + " " + startingAMPM);
//		Date endingDate = new SimpleDateFormat("MM/DD/YY hh:mm a").parse(startDate + " " + startTime + " " + startingAMPM);
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		LocalDateTime startDateTime = LocalDateTime.parse(startDate + " " + startTime + " " + startingAMPM, format);
		LocalDateTime endDateTime = LocalDateTime.parse(endDate + " " + endTime + " " + endingAMPM, format);
		
		return startDateTime.compareTo(endDateTime);
	}
	
	public static double calculateParkingFeeFromParkingType(String parkingType, double hours) {
		double parkingFee = 0;
		
		switch(parkingType) {
			case "Valet Parking":
				int dayCountValetParking = (int)(hours / 24);
				double dayFeeValetParking = dayCountValetParking * 18.0;
				double hourCountValetParking = hours % 24;
				double hourFeeValetParking;
				if(hourCountValetParking <= 5.0)
					hourFeeValetParking = 12.0;
				else
					hourFeeValetParking = 18.0;
				parkingFee = dayFeeValetParking + hourFeeValetParking;
				break;
				
			case "Short-Term Parking":
				int dayCountShortTermParking = (int)(hours / 24);
				double dayFeeShortTermParking = dayCountShortTermParking * 24.0;
				double hourCountShortTermParking = hours % 24;
				double hourFeeShortTermParking;
				double tempSTParkingFull;
				if(hourCountShortTermParking <= 1.0)
					hourFeeShortTermParking = 2.0;
				else {
					tempSTParkingFull = Math.ceil((hourCountShortTermParking - 1) / 0.5);
					hourFeeShortTermParking = 2.0 + 1.0 * tempSTParkingFull;
				}	
				parkingFee = dayFeeShortTermParking + hourFeeShortTermParking;
				break;
				
			case "Economy Parking":
				int weekCountEconomyParking = (int)(hours / (24 * 7));
				double weekFeeEconomyParking = weekCountEconomyParking * 54.0;
				int dayCountEconomyParking = (int)((hours % (24 * 7)) / 24);
				double dayFeeEconomyParking = dayCountEconomyParking * 9.0;
				double hourCountEconomyParking = (hours % (24 * 7)) % 24;
				double hourFeeEconomyParking;
				if(hourCountEconomyParking <= 4)
					hourFeeEconomyParking = Math.ceil(hourCountEconomyParking) * 2.0;
				else
					hourFeeEconomyParking = 9.0;
				parkingFee = weekFeeEconomyParking + dayFeeEconomyParking + hourFeeEconomyParking;
				break;
				
			case "Long-Term Garage Parking":
				int weekCountLTGParking = (int)(hours / (24 * 7));
				double weekFeeLTGParking = weekCountLTGParking * 72.0;
				int dayCountLTGParking = (int)((hours % (24 * 7)) / 24);
				double dayFeeLTGParking = dayCountLTGParking * 12.0;
				double hourCountLTGParking = (hours % (24 * 7)) % 24;
				double hourFeeLTGParking;
				if(hourCountLTGParking <= 5)
					hourFeeLTGParking = Math.ceil(hourCountLTGParking) * 2.0;
				else
					hourFeeLTGParking = 12.0;
				parkingFee = weekFeeLTGParking + dayFeeLTGParking + hourFeeLTGParking;
				break;
				
			case "Long-Term Surface Parking":	
				int weekCountLTSParking = (int)(hours / (24 * 7));
				double weekFeeLTSParking = weekCountLTSParking * 60.0;
				int dayCountLTSParking = (int)((hours % (24 * 7)) / 24);
				double dayFeeLTSParking = dayCountLTSParking * 10.0;
				double hourCountLTSParking = (hours % (24 * 7)) % 24;
				double hourFeeLTSParking;
				if(hourCountLTSParking <= 4)
					hourFeeLTSParking = Math.ceil(hourCountLTSParking) * 2.0;
				else
					hourFeeLTSParking = 10.0;
				parkingFee = weekFeeLTSParking + dayFeeLTSParking + hourFeeLTSParking;
				break;
		}
		return parkingFee;
	}

}
