package FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class userController {

    @FXML
    private Button ordre;

    @FXML
    private Button persoinfo;

    @FXML
    void ordre(ActionEvent event) {
    	Pane pane=null;
    	try {
    	pane = FXMLLoader.load(getClass().getResource("ordre.fxml"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
App.root.setCenter(pane);
    }

    @FXML
    void persoInfo(ActionEvent event) {
Pane pane=null;
    	try {
    	pane = FXMLLoader.load(getClass().getResource("persoInfo.fxml"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
App.root.setCenter(pane);
    }
    }
