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

public class Controller implements Initializable {

    @FXML
    private TextField urlTextField;

    @FXML
    private Button urlFetch;

    @FXML
    private TextArea docTextArea;

    @FXML
    void handleFetchPane(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
    private Document doc;

    @FXML
    void handleFetchPage(ActionEvent event) {
        try {
            doc = Jsoup.connect(urlTextField.getText()).get();
            docTextArea.setText(doc.body().html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}