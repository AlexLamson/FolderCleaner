package menuSystem;
import java.math.BigInteger;	//http://docs.oracle.com/javase/6/docs/api/java/math/BigInteger.html

public class Prob17
{
	public static BigInteger oneHundred = new BigInteger("100");
	public static BigInteger oneThousand = new BigInteger("1000");
	public static BigInteger tenThousand = new BigInteger("10000");
	public static BigInteger oneMillion = new BigInteger("1000000");
	
	public static void main(String[] args)
	{
		// note: longs stop at 1 quintillion
		
		printNums(new BigInteger("1"), new BigInteger("100"));
		
		// printNums(new BigInteger("999990"), new BigInteger("1000010"));
	}
	
	public static void printNums(long start, long end)
	{
		printNums(new BigInteger(""+start), new BigInteger(""+end));
	}
	
	public static void printNums(BigInteger start, BigInteger end)
	{
		BigInteger currentNum = new BigInteger(start.toString());
		while(currentNum.compareTo(end) <= 0)			//while it hasn't reached the end
		{
			System.out.println( numToString(currentNum) );	//print the number
			currentNum = currentNum.add(BigInteger.ONE);	//increment the number
		}
	}
	
	public static String numToString(long num)
	{
		return numToString(new BigInteger(""+num));
	}
	
	public static String numToString(BigInteger bigInt)
	{
		String output = "";
		
		// if(num < 0)
		if(bigInt.compareTo(BigInteger.ZERO) == -1)
		{
			output += "negative ";
			// num *= -1;
			bigInt = bigInt.multiply(new BigInteger("-1"));
		}
		
		// if(num == 0)
		if(bigInt.equals(BigInteger.ZERO))
		{
			output = "zero";
		}
		// if(num < 100)
		else if(bigInt.compareTo(oneHundred) == -1)
		{
			output += easyTens(bigInt.longValue());
		}
		// else if(num % 100 == 0 && num < 1000)
		else if(bigInt.remainder(oneHundred).equals(BigInteger.ZERO) && bigInt.compareTo(oneThousand) == -1)
		{
			output += ones(bigInt.longValue()/100);
			output += " hundred";
		}
		// else if(num < 1000)
		else if(bigInt.compareTo(oneThousand) == -1)
		{
			output += ones(bigInt.longValue()/100);
			output += " hundred ";
			
			output += easyTens(bigInt.longValue() % 100);
		}
		// else if(num % 1000 == 0 && num < 10000)
		else if(bigInt.remainder(oneThousand).equals(BigInteger.ZERO) && bigInt.compareTo(tenThousand) == -1)
		{
			output += ones(bigInt.longValue()/1000);
			output += " thousand";
		}
		// else if(num < 1000000)
		else if(bigInt.compareTo(oneMillion) == -1)
		{
			output += hundreds(bigInt.longValue()/1000);
			output += " ";
			output += "thousand";
			
			// if(num >= 1100 && !(num % 1000 < 100))
			if(bigInt.compareTo(new BigInteger("1100")) >= 0 && bigInt.remainder(oneThousand).compareTo(oneHundred) == -1)
			{
				output += " "+ones(bigInt.longValue() % 1000 / 100);
				output += " hundred";
			}
			
			output += " "+easyTens(bigInt.longValue() % 100);
		}
		// else if(num == 1000000)
		else if(bigInt.equals(oneMillion))
		{
			output = "one million";
		}
		
		return output.trim();
		// return removeSpaces(output);
	}
	
	public static String ones(long num)
	{
		int intNum = (int)num;
		switch(intNum)
		{
		case 0: return "";
		case 1: return "one";
		case 2: return "two";
		case 3: return "three";
		case 4: return "four";
		case 5: return "five";
		case 6: return "six";
		case 7: return "seven";
		case 8: return "eight";
		case 9: return "nine";
		}
		return "";
	}
	
	public static String teens(long num)
	{
		int intNum = (int)num;
		switch(intNum)
		{
		case 10: return "ten";
		case 11: return "eleven";
		case 12: return "twelve";
		case 13: return "thirteen";
		case 14: return "fourteen";
		case 15: return "fifteen";
		case 16: return "sixteen";
		case 17: return "seventeen";
		case 18: return "eighteen";
		case 19: return "nineteen";
		}
		return "";
	}
	
	public static String tens(long num)
	{
		int intNum = (int)num;
		switch(intNum)
		{
		case 20: return "twenty";
		case 30: return "thirty";
		case 40: return "forty";
		case 50: return "fifty";
		case 60: return "sixty";
		case 70: return "seventy";
		case 80: return "eighty";
		case 90: return "ninety";
		}
		return "";
	}
	
	public static String easyTens(long num)
	{
		String output = "";
		if(num < 10)
		{
			output += ones(num);
		}
		else if(num < 20)
		{
			output += teens(num);
		}
		else if(num < 100)
		{
			output += tens(num/10*10);
			output += " ";
			output += ones(num - num/10*10);
		}
		return output;
	}
	
	public static String hundreds(long num)
	{
		String output = "";
		
		if(num < 100 || num >= 1000)
			return easyTens(num);
		
		output += ones(num/100);
		output += " hundred ";
		output += easyTens(num % 100);
		output = output.trim();
		return output;
	}
	
	//note: change 'numbeOfZeros' to 'orderOfMagnitude'
	public static String thousands(long numbeOfZeros)		// note: change this to an int
	{
		int place = (int)(numbeOfZeros/3)-1;
		switch(place)
		{
		case 0: return "thousand";
		case 1: return "million";
		case 2: return "billion";
		case 3: return "trillion";
		case 4: return "quadrillion";
		case 5: return "quintillion";
		case 6: return "sextillion";
		case 7: return "septillion";
		case 8: return "octillion";
		case 9: return "nonillion";
		case 10: return "decillion";
		}
		return "";
	}
	
	// remove all instance of "  " and replace it with " "
	public static String removeSpaces(String numberName)
	{
		String save = numberName+"";
		
		//keep removing double spaces until no changes have been made
		while(true)
		{
			save = numberName+"";
			numberName = numberName.replace("  "," ");
			
			if(save.equals(numberName))
				break;
			save = numberName+"";
		}
		
		numberName.trim();
		return numberName;
	}
	
	public static String capFirstLetter(String str)
	{
		return str.substring(0,1).toUpperCase()+str.substring(1,str.length());
	}
	
	public static String numToString(long num, boolean junk)
	{
		return capFirstLetter(numToString(num));
	}
}
