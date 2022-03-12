package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import metier.Implementation;

public class LayoutController implements Initializable{
    @FXML
    private Button employe;

    @FXML
    private Button intervention;
    @FXML
    private BorderPane root;
    @FXML
    private Button machine;

    @FXML
    private Button materiel;
    @FXML
    private Label acteur;

    @FXML
    private Button persoInfo;

   
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
    
    @FXML
    void showEmploye() {
    	Pane pane=null;
    	try {
    	pane = FXMLLoader.load(getClass().getResource("employe.fxml"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
App.root.setCenter(pane);
    }

    @FXML
    void showIntervention() {
    	Pane pane=null;
    	try {
    	pane = FXMLLoader.load(getClass().getResource("intervention.fxml"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
App.root.setCenter(pane);
    }
    

    @FXML
    void showMachine() {
    	Pane pane=null;
    	try {
    	pane = FXMLLoader.load(getClass().getResource("machine.fxml"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
App.root.setCenter(pane);
    }
    

    @FXML
    void showMateriel() {
    	Pane pane=null;
    	try {
    	pane = FXMLLoader.load(getClass().getResource("materiel.fxml"));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
App.root.setCenter(pane);
    
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 Implementation imp = new Implementation();
	
	
	}
}
