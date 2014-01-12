package menusystem2;

public class StringTime
{
	//convert seconds to an english string
	public static String secToString(double seconds)
	{
//		//Example:
//		String str = Loader.getTimeFromSeconds(1*365*24*60*60 + 2*30*24*60*60 + 3*24*60*60 + 4*60*60 + 5*60 + 6.7);
//		System.out.println(str);	//"1 year 2 months 3 days 4 hours 5 minutes 6.7 seconds "
		
		if(seconds > 68*365*24*60*60)
			return "Unknown time";
		
		int year = 0, month = 0, day = 0, hour = 0, min = 0;
		double sec = 0;
		sec = seconds;
		
		min = (int)sec / 60;
		sec = sec % 60;
		sec = NumGen.round(sec,1);
		
		hour = min / 60;
		min = min % 60;
		
		day = hour / 24;
		hour = hour % 24;
		
		year = day / 365;
		month = day / 30;
		
		day = day % 365;
		day = day % 30;
		
		month = month % 12;
		
		String str = "";
		if(year > 0)
			str += year+" years ";
		if(year == 1)
			str = str.substring(0,str.length()-2)+" ";
		if(month > 0)
			str += month+" months ";
		if(month == 1)
			str = str.substring(0,str.length()-2)+" ";
		if(day > 0)
			str += day+" days ";
		if(day == 1)
			str = str.substring(0,str.length()-2)+" ";
		if(hour > 0)
			str += hour+" hours ";
		if(hour == 1)
			str = str.substring(0,str.length()-2)+" ";
		if(min > 0)
			str += min+" minutes ";
		if(min == 1)
			str = str.substring(0,str.length()-2)+" ";
		if(sec > 0)
			str += sec+" seconds ";
		if(sec == 1)
			str = str.substring(0,str.length()-2)+" ";
		
		if(year == 0 && month == 0 && day == 0 && hour == 0 && min == 0 && sec == 0)
			str = "0.0 seconds ";
		
//		System.out.println("months: "+month+" days: "+day+" hours: "+hour+" minutes: "+min+" seconds: "+sec);
		
		return str.trim();
	}
}
