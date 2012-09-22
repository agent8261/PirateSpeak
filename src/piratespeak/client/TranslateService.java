package piratespeak.client;

import java.util.TreeSet;

import piratespeak.shared.PirateLexicon;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("translator")
public interface TranslateService extends RemoteService
{
	String getTranslation(String orginal);
	
	TreeSet<PirateLexicon> getAll();
	
	void addLexicon(String englishText, String pirateText);
	
	void delLexicon(String englishText);
}
