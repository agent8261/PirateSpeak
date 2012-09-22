package piratespeak.shared;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;


@SuppressWarnings("serial")
@Entity
public class PLexiconOfy implements 
		Serializable, Comparator<PLexiconOfy>, Comparable<PLexiconOfy>
{
	@Id public String engText;
	public String pirateText;
	Long id;
		
	public PLexiconOfy(String _engText, String _pirateText)
	{ engText = _engText; pirateText = _pirateText; }
	
	public PLexiconOfy(){}

	@Override
	public int compare(PLexiconOfy o1, PLexiconOfy o2)
	{	return o1.engText.compareTo(o2.engText);  }

	@Override
	public int compareTo(PLexiconOfy o)
	{	return engText.compareTo(o.engText);  }
}
