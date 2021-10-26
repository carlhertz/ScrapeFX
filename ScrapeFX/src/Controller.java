import web.webDisplay;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Controller implements Initializable {

    @FXML
    private TextField urlTextField;

    @FXML
    private Button urlFetch;
    @FXML
    private Button parseButton;
    
    @FXML
    private TextArea parseTextField;
    @FXML
    private TextArea docTextArea;
   
    @FXML
    private WebView webPanel;

    private Document doc;
    
    @FXML
    void handleFetchPane(ActionEvent event) {
    	
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

   	        URL urlHello = new File("src/pageHTML.html").toURI().toURL();
   	        engine.load(urlHello.toExternalForm());
   	        
   	    }
    
   
    
    @FXML
    void handleParseHtml(ActionEvent event) {
        //try {
         /*   ;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    @FXML
    void handleFetchPage(ActionEvent event) {
        try {
            doc = Jsoup.connect(urlTextField.getText()).get();
            docTextArea.setText(doc.body().html());
            displayWeb(docTextArea, webPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    
    
}