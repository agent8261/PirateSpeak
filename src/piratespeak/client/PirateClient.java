package piratespeak.client;

import piratespeak.shared.PirateLexicon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class PirateClient implements EntryPoint
{
	private static final int TEXTBOXLENGTH = 20;
	private static final int TEXTBOXVISIBLELENGTH = 22;
	private VerticalPanel mainPanel = new VerticalPanel();	
	private FlexTable msgFlexTable = new FlexTable();
	private HorizontalPanel addMsgPanel = new HorizontalPanel();
	private TextBox engTextBox = new TextBox();
	private TextBox pirateTextBox = new TextBox();
	private Button addButton = new Button("Add");
	private Button getAllButton = new Button("Get All");
	private ArrayList<String> allEnglish = new ArrayList<String>();	
	private TranslateServiceAsync translateSvc = GWT.create(TranslateService.class);
	private final HorizontalPanel TextBoxtPanel = new HorizontalPanel();
	private final Label enterEngLabel = new Label("Enter English");
	private final Label enterPirateLabel = new Label("Enter Pirate");
	
	
	//===========================================================================
	// Entry Point
	public void onModuleLoad() 
	{	
		// Associate the Main Panel with HTML Host page
		RootPanel rootPanel = RootPanel.get("pirateMainPanel");
		rootPanel.add(mainPanel);
		
		// Modify Labels
		enterEngLabel.setWidth("170px");
		enterPirateLabel.setWidth("257px");		
		// Assemble Text Box Panel
		TextBoxtPanel.add(enterEngLabel);		
		TextBoxtPanel.add(enterPirateLabel);
				
		// Modify Text Box length
		engTextBox.setMaxLength(TEXTBOXLENGTH);
		engTextBox.setVisibleLength(TEXTBOXVISIBLELENGTH);
		pirateTextBox.setMaxLength(TEXTBOXLENGTH);
		pirateTextBox.setVisibleLength(TEXTBOXVISIBLELENGTH);		
				
		// Assemble Add Panel
		addMsgPanel.add(engTextBox);
		addMsgPanel.add(pirateTextBox);
		addMsgPanel.add(addButton);
		addMsgPanel.add(getAllButton);
				
		// Modify Flex Table
		msgFlexTable.setSize("372px", "24px");
		msgFlexTable.setText(0,0, "English");
		msgFlexTable.setText(0,1, "Pirate");
		msgFlexTable.setText(0,2, "Remove");
		
		// Assemble Main Panel
		mainPanel.add(TextBoxtPanel);
		mainPanel.add(addMsgPanel);
		
		// Move cursor focus to the input box
		engTextBox.setFocus(true);
		rootPanel.add(msgFlexTable);
		
		// Listen for mouse events on the Send button.
		addButton.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event)
			{	addLexicon();	}
		});
		
		getAllButton.addClickHandler(new ClickHandler() 
		{
			public void onClick(ClickEvent event)
			{	recvAll();	}
		});		
	}
	
	//===========================================================================
	// Add Call Back
	public class AddCallback implements AsyncCallback<Void>
	{
		String englishText;
		String pirateText;
		
		AddCallback(String _engl, String _pirate)
		{ englishText = _engl; pirateText = _pirate; }
		
		@Override
		public void onFailure(Throwable caught)
		{ }

		@Override
		public void onSuccess(Void result)
		{ updateTable(englishText, pirateText);  }
	}
	
	//===========================================================================
	// Add Lexicon
	public void addLexicon()
	{		
		// Grab text from text box and trim whitespace.
		final String msgText = engTextBox.getText().toLowerCase().trim();
		final String pirateText = pirateTextBox.getText().toLowerCase().trim();
		pirateTextBox.setFocus(true);
		
		// Check that message is valid
    if(!msgText.matches("^[a-z\\']{1,80}$")) 
    {
      Window.alert("'" + msgText + "' is not a valid symbol. Use only letters");
      engTextBox.selectAll();
      return;
    }
    if(msgText.equals(""))
    {
      Window.alert("Enter something in english");
      engTextBox.selectAll();
      return;
    }
    if(!pirateText.matches("^[a-z\\']{1,80}$")) 
    {
      Window.alert("'" + msgText + "' is not a valid symbol. Use only letters or '");
      engTextBox.selectAll();
      return;
    }
    if(msgText.equals(""))
    {
      Window.alert("Enter something in pirate");
      engTextBox.selectAll();
      return;
    }    
    engTextBox.setText("");
    pirateTextBox.setText("");
    // Initialize the service proxy.
    if(translateSvc == null)
    { translateSvc = GWT.create(TranslateService.class); }
        
    // Set up the callback object.
    AsyncCallback<Void> callback = new AddCallback(msgText, pirateText);
  		
    // Make the call to translation service.
  	translateSvc.addLexicon(msgText, pirateText, callback);
	}
	
	//===========================================================================
	// Receive All's Call back
	public class RecvAllcb implements AsyncCallback<TreeSet<PirateLexicon>>
	{
		@Override
		public void onFailure(Throwable caught){ }

		@Override
		public void onSuccess(TreeSet<PirateLexicon> result)
		{ updateTable(result); }		
	}
	
	//===========================================================================
	// Receive All	
	public void recvAll()
	{
		if(translateSvc == null)
		{  translateSvc = GWT.create(TranslateService.class);  }
    
		// Set up the callback object.
    AsyncCallback<TreeSet<PirateLexicon>> callback = new RecvAllcb();
    // Make the call to translation service.
  	translateSvc.getAll(callback);  				
	}
	
	//===========================================================================
	//	Remove Text handler	
	public class RemoveText implements ClickHandler
	{
		final int index;
		final String englishText;
		RemoveText(int _index, String _englishText)
		{  index = _index; englishText = _englishText;  }
		
		public void onClick(ClickEvent event)
		{  			
			if(translateSvc == null)
			{  translateSvc = GWT.create(TranslateService.class);  }
			AsyncCallback<Void> callback = new AsyncCallback<Void>()
					{
						@Override
						public void onFailure(Throwable caught) { }
						@Override
						public void onSuccess(Void result){}	
					};
			translateSvc.delLexicon(englishText, callback);
			allEnglish.remove(index); msgFlexTable.removeRow(index + 1);
		}
	}
	
	//===========================================================================
	//	Update the Table
	public void updateTable(String englishText, String pirateText)
	{
		if(allEnglish.contains(englishText))
		{  return; }
    int row = msgFlexTable.getRowCount();
    allEnglish.add(englishText);
    msgFlexTable.setText(row,  0,  englishText);
    msgFlexTable.setText(row,  1,  pirateText);
    
    // Add remove button
    Button removeButton = new Button("x");
    removeButton.addClickHandler
    	(new RemoveText(allEnglish.indexOf(englishText), englishText));
    msgFlexTable.setWidget(row, 3, removeButton);
	}
	
	//===========================================================================
	//	Update the Table from a tree
	public void updateTable(TreeSet<PirateLexicon> tree)
	{
		Iterator<PirateLexicon> iter = tree.iterator();
		while(iter.hasNext())
		{
			PirateLexicon lexi = iter.next();
			updateTable(lexi.engText, lexi.pirateText);	    
		}
	}
}
