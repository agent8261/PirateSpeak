package piratespeak.client;

import java.util.TreeSet;

import piratespeak.shared.PirateLexicon;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TranslateServiceAsync
{
	void getTranslation(String orginal, AsyncCallback<String> callback);

	void getAll(AsyncCallback<TreeSet<PirateLexicon>> callback);

	void addLexicon(String englishText, String pirateText,
			AsyncCallback<Void> callback);

	void delLexicon(String englishText, AsyncCallback<Void> callback);
}
