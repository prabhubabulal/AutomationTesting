package testAutomation.testing;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeStamp {

	public static String getFormattedDate(String format)
	{
		Format dateformatter = new SimpleDateFormat(format);
		Date today = Calendar.getInstance().getTime();
		String formattedDate = dateformatter.format(today);

		return formattedDate;
	}

	public static String getPastDate(String format, int days)
	{
		String pastDate = "";
		Format dateFormat = new SimpleDateFormat(format);
		Date myDate = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DATE, -days);
		pastDate = dateFormat.format(cal.getTime());
		return pastDate;
	}

	public static String getFutureDate(String format, int days)
	{
		String futureDate = "";
		Format dateFormat = new SimpleDateFormat(format);
		Date myDate = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(Calendar.DATE, +days);
		futureDate = dateFormat.format(cal.getTime());
		return futureDate;
	}

	public static String getFormattedTime()
	{
		Date date = Calendar.getInstance().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		long timeInMillis = calendar.getTimeInMillis();
		String formattedTime= Long.toString(timeInMillis);

		double randomNumber = Math.random();
		String converedString = Double.toString(randomNumber);
		converedString = converedString.substring(5, 9);
		formattedTime = converedString.concat(formattedTime);

		return formattedTime;
	}

	public static String getTimeStamp()
	{
		Format formatter = new SimpleDateFormat("HH:mm:ss");
		Date today = Calendar.getInstance().getTime();        
		String formattedDate = formatter.format(today);

		return formattedDate;
	}

	public static String getTimeDifference(long startTime)
	{
		String timeDifference = "";
		long endTime = System.currentTimeMillis();
		long differenceTime = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime);
		String timedifferenceinsecond = Long.toString(differenceTime % 60);
		String timedifferenceinminutes = Long.toString(differenceTime/60);
		timeDifference = timedifferenceinminutes+":"+timedifferenceinsecond +" minutes";   
		return timeDifference;
	}
}