package menusystem2;

import java.util.ArrayList;

public class Buffer
{
	public int maxBufferSize = 10;
	public ArrayList<Double> buffer = new ArrayList<Double>();
	
	public Buffer()
	{
		
	}
	
	public Buffer(int size)
	{
		maxBufferSize = size;
	}
	
	public void setBufferSize(int size)
	{
		maxBufferSize = size;
	}
	
	public void addVal(double val)
	{
		//here get the current number, and add it to the end of the arraylist
		if(buffer.size() == maxBufferSize)
			buffer.remove(0);
		buffer.add(new Double(val));
		
//		System.out.println(toString());
	}
	
	public double getAverage()
	{
		return getAverage(buffer);
	}
	
	public double getAverage(ArrayList<Double> list)
	{
		int sum = 0;
		for(int i = 0; i < list.size(); i++)
			sum += list.get(i).doubleValue();
		return 1.0 * sum / list.size();
	}
	
	public double getAverageChange()
	{
		double averageChange = 0;
		for(int i = 0; i < buffer.size()-1; i++)
			averageChange += buffer.get(i+1) - buffer.get(i);
		averageChange /= buffer.size();
		return averageChange;
	}
	
	public double getValInNSteps(int n)
	{
		if(buffer.size() > 0)
			return buffer.get(buffer.size()-1) + (double)(n) * getAverageChange();
		return -1;
	}
	
	public double getNStepsToVal(double val)
	{
		if(buffer.size() > 0)
			return 1.0 * (val - buffer.get(buffer.size()-1)) / getAverageChange();
		return -1;
	}
	
	public void clear()
	{
		buffer.clear();
	}
	
	public String toString()
	{
		return NumGen.round(getAverageChange(),1)+" "+buffer;
	}
}
