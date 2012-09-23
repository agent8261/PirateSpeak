package piratespeak.server;


 import java.util.TreeSet;

 import piratespeak.shared.PirateLexicon;

 import com.google.appengine.api.datastore.QueryResultIterator;
 import com.googlecode.objectify.ObjectifyService;
 import com.googlecode.objectify.Query;
 import com.googlecode.objectify.util.DAOBase;

 public class LexiconStore extends DAOBase
 {
 static
 {
 ObjectifyService.register(PirateLexicon.class);
 }

 LexiconStore(String config)
 {
 }

 public void addLexicon(String engText, String pirateText)
 {
 PirateLexicon lexi = new PirateLexicon(engText, pirateText);
 ofy().put(lexi);
 }

 public void delLexicon(String engText)
 {
 ofy().delete(PirateLexicon.class, engText);
 }

 public PirateLexicon getLexicon(String engText)
 {
 PirateLexicon lexi = ofy().get(PirateLexicon.class, engText);
 return lexi;
 }

 public TreeSet<PirateLexicon> getAllLexicon()
 {
 Query<PirateLexicon> query = ofy().query(PirateLexicon.class);
 TreeSet<PirateLexicon> ptree = new TreeSet<PirateLexicon>();

 QueryResultIterator<PirateLexicon> iter = query.iterator();
 while (iter.hasNext())
 {
 ptree.add(iter.next());
 }

 return ptree;
 }

 }
 

//import java.util.Iterator;
//import java.util.TreeSet;
//
//import javax.jdo.Extent;
//import javax.jdo.JDOHelper;
//import javax.jdo.PersistenceManager;
//import javax.jdo.PersistenceManagerFactory;
//import javax.jdo.Transaction;
//
//import piratespeak.shared.PirateLexicon;
//
//public class LexiconStore
//{
//	private final PersistenceManagerFactory pmfactory;
//	private final PersistenceManager pmanger;
//
//	LexiconStore(String config)
//	{
//		this.pmfactory = JDOHelper.getPersistenceManagerFactory(config);
//		this.pmanger = pmfactory.getPersistenceManager();
//	}
//
//	public void addLexicon(String engText, String pirateText)
//	{
//		PirateLexicon lexicon = new PirateLexicon(engText, pirateText);
//		Transaction tx = pmanger.currentTransaction();
//		tx.begin();
//		pmanger.makePersistent(lexicon);
//		tx.commit();
//	}
//
//	public void delLexicon(String engText)
//	{
//		PirateLexicon lexicon = pmanger.getObjectById(PirateLexicon.class, engText);
//		Transaction tx = pmanger.currentTransaction();
//		tx.begin();
//		pmanger.deletePersistent(lexicon);
//		tx.commit();
//	}
//
//	public PirateLexicon getLexicon(String engText)
//	{
//		return pmanger.getObjectById(PirateLexicon.class, engText);
//	}
//
//	public TreeSet<PirateLexicon> getAllLexicon()
//	{
//		Extent<PirateLexicon> lexiExtent = pmanger.getExtent(PirateLexicon.class,
//				false);
//		TreeSet<PirateLexicon> ptree = new TreeSet<PirateLexicon>();
//
//		Iterator<PirateLexicon> iter = lexiExtent.iterator();
//		for (int i = 0; iter.hasNext() && (i < 50); ++i)
//		{
//			ptree.add(iter.next());
//		}
//		return ptree;
//	}
//
//}
