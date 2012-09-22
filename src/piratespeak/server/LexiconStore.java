package piratespeak.server;

import java.util.Iterator;
import java.util.TreeSet;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;


import piratespeak.shared.PirateLexicon;

// Demo using of persistence using JDO API
public class LexiconStore
{
	private final PersistenceManagerFactory pmfactory;
	private final PersistenceManager pmanger;
	
	public LexiconStore(String config)
	{	
		this.pmfactory = JDOHelper.getPersistenceManagerFactory(config);
		this.pmanger = pmfactory.getPersistenceManager();
	}
	
	public void addLexicon(String engText, String pirateText)
	{
		PirateLexicon lexicon= new PirateLexicon(engText, pirateText);
		Transaction tx = pmanger.currentTransaction();
		tx.begin();
		pmanger.makePersistent(lexicon);
		tx.commit();		
	}
	
	public void delLexicon(String engText)
	{		
		PirateLexicon lexicon = pmanger.getObjectById(PirateLexicon.class, engText);
		Transaction tx = pmanger.currentTransaction();
		tx.begin();
		pmanger.deletePersistent(lexicon);
		tx.commit();
	}
	
	public PirateLexicon getLexicon(String engText)
	{	return pmanger.getObjectById(PirateLexicon.class, engText); }
	
	public TreeSet<PirateLexicon> getAllLexicon()
	{
		Extent<PirateLexicon> lexiExtent = pmanger.getExtent(PirateLexicon.class, false);
		TreeSet<PirateLexicon> ptree = new TreeSet<PirateLexicon>();
		
		Iterator<PirateLexicon> iter = lexiExtent.iterator();
		for(int i=0; iter.hasNext() && (i < 50); ++i)
		{	ptree.add(iter.next());  }
		return ptree;
	}

}
