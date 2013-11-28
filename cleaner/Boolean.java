package cleaner;

//Boolean - to be used when creating an arraylist of booleans
public class Boolean
{
	public boolean bool = false;
	
	public Boolean()
	{
		bool = false;
	}
	
	public Boolean(boolean bool)
	{
		this.bool = bool;
	}
	
	public boolean isTrue()
	{
		return bool;
	}
	
	public void makeTrue()
	{
		bool = true;
	}
	
	public void makeFalse()
	{
		bool = false;
	}
}
