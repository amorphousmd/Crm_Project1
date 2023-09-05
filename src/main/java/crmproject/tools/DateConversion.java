package crmproject.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConversion {
	public static String convertDate(String inputDate) {
        try {
            // Define the input date format
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM/yyyy");

            // Parse the input date
            Date date = inputFormat.parse(inputDate);

            // Define the desired output date format
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Format the date into the desired output format
            String outputDate = outputFormat.format(date);

            return outputDate;
        } catch (ParseException e) {
            // Handle parsing exceptions here
            e.printStackTrace();
            return null;
        }
	}
}
