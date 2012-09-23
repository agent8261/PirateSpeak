package piratespeak.server;

import java.util.TreeSet;

import piratespeak.client.TranslateService;
import piratespeak.shared.PirateLexicon;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class TranslateServiceImpl extends RemoteServiceServlet implements
		TranslateService
{
	private final LexiconStore lexicon = new LexiconStore("transactions-optional");

	@Override
	public String getTranslation(String orginal)
	{
		return orginal;
	}

	@Override
	public void addLexicon(String englishText, String pirateText)
	{
		lexicon.addLexicon(englishText, pirateText);
	}

	@Override
	public TreeSet<PirateLexicon> getAll()
	{
		return lexicon.getAllLexicon();
	}

	public void delLexicon(String englishText)
	{
		lexicon.delLexicon(englishText);
	}

}
