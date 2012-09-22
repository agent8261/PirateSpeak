package piratespeak.server;

import java.util.TreeSet;

import piratespeak.shared.PLexiconOfy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;


public class LexiconStoreOfy
{
	private String config;
	LexiconStoreOfy(String _config)
	{
		config = _config;
		
	}
	
	public String getConfig(){  return config; }
	
	public void addLexicon(String engText, String pirateText)
	{
		PLexiconOfy lexi= new PLexiconOfy(engText, pirateText);
		Objectify ofy = ObjectifyService.begin();
		ofy.put(lexi);		
	}
	
	public void delLexicon(String engText)
	{
		Objectify ofy = ObjectifyService.begin();
		PLexiconOfy lexi = ofy.get(new Key<PLexiconOfy>(PLexiconOfy.class, engText));
		ofy.delete(lexi);
	}
	
	public PLexiconOfy getLexicon(String engText)
	{
		Objectify ofy = ObjectifyService.begin();
		PLexiconOfy lexi = ofy.get(PLexiconOfy.class, engText);
		return lexi;
	}
	
	public TreeSet<PLexiconOfy> getAllLexicon()
	{
		return null;
	}
}
