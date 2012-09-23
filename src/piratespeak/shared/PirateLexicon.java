package piratespeak.shared;


import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class PirateLexicon implements 
		Serializable, Comparator<PirateLexicon>, Comparable<PirateLexicon>
{
	@Id public String engText;
	public String pirateText;
	
	public PirateLexicon(String _engText, String _pirateText)
	{ engText = _engText; pirateText = _pirateText; }
	
	public PirateLexicon(){}

	@Override
	public int compare(PirateLexicon o1, PirateLexicon o2)
	{	return o1.engText.compareTo(o2.engText);  }

	@Override
	public int compareTo(PirateLexicon o)
	{	return engText.compareTo(o.engText);  }
}


//import java.io.Serializable;
//import java.util.Comparator;
//
//import javax.jdo.annotations.PersistenceCapable;
//import javax.jdo.annotations.PrimaryKey;
//
//@SuppressWarnings("serial")
//@PersistenceCapable
//public class PirateLexicon implements 
//		Serializable, Comparator<PirateLexicon>, Comparable<PirateLexicon>
//{
//	@PrimaryKey
//	public String engText;
//	public String pirateText;
//	
//	public PirateLexicon(String _engText, String _pirateText)
//	{ engText = _engText; pirateText = _pirateText; }
//	
//	public PirateLexicon(){}
//
//	@Override
//	public int compare(PirateLexicon o1, PirateLexicon o2)
//	{	return o1.engText.compareTo(o2.engText);  }
//
//	@Override
//	public int compareTo(PirateLexicon o)
//	{	return engText.compareTo(o.engText);  }
//}
