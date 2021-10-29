import htmlParser.HtmlParse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller implements Initializable {

    @FXML
    private TextField urlTextField;

    @FXML
    private Button urlFetch;
    
    @FXML
    private MenuItem parseButton;
    
    @FXML
    private TextArea parseTextField;
    
    @FXML
    private TextArea docTextArea;
   
    @FXML
    private WebView webPanel;
    
    @FXML
    private Document doc;
    
    @FXML
    private CheckMenuItem menuWebView;
    
    @FXML
    private CheckMenuItem menuHTMLView;

    @FXML
    private SplitPane MainSplitView;
    
    @FXML
    private BorderPane webViewBorderPane;
    
    //@FXML
    //private BorderPane htmlview 
    
    @FXML
    void handleFetchPane(ActionEvent event) {
    	
    }
    
    @FXML
    void toggleWebView(ActionEvent event) {
    	
                if (((CheckMenuItem) event.getSource()).isSelected()) {
                	MainSplitView.getItems().add(webViewBorderPane);
                	try {
						displayWeb(docTextArea, webPanel);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
                else {
                	MainSplitView.getItems().remove(webViewBorderPane);
                //webViewBorderPane.remove(docTextArea);
                }
  }

  
    
    @FXML
    void toggleHTMLView(ActionEvent event) {
        if (((CheckMenuItem) event.getSource()).isSelected()) {
        	MainSplitView.getItems().add(docTextArea);
        }
        else
        	MainSplitView.getItems().remove(docTextArea);
    }
    
    
    	
   	 void displayWeb(TextArea docTextArea, WebView webPanel) throws IOException {
   	    	String htmlbody = docTextArea.getText();
   	    	File newHtmlFile = new File("src/pageHTML.html");
   	        BufferedWriter writer;
   			try {
   				writer = new BufferedWriter(new FileWriter(newHtmlFile));
   		        writer.write(htmlbody);
   		        
   		        writer.close();
   		        
   			} catch (IOException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}

   	        WebEngine engine = webPanel.getEngine();

   	        URL url = new File("src/pageHTML.html").toURI().toURL();
   	        engine.load(url.toExternalForm());
   	        
   	    }
    
   
    
    @FXML
    void handleParseHtml(ActionEvent event){
    	String parsedString = "";
    	HtmlParse prox = new HtmlParse();
    	Document doc = Jsoup.parse(docTextArea.getText());
    	parsedString += prox.parseHTML(doc);
    	parseTextField.setText(parsedString);
    }
    
    @FXML
    void handleFetchPage(ActionEvent event) {
        try {
            doc = Jsoup.connect(urlTextField.getText()).get();
            docTextArea.setText(doc.body().html());
            	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
    
}