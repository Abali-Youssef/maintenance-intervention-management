package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.Machine;
import metier.Materiel;
import metier.Implementation;

public class machController implements Initializable {
	public static Machine m = new Machine();
	Implementation imp = new Implementation();
    @FXML
    private Button ajouter;

    @FXML
    private TextField chercher;

    @FXML
    private TableView<Machine> machines;

    @FXML
    private Button modifier;
    
    @FXML
    private Button detail;

    @FXML
    private TableColumn<Machine, String> nom;

    @FXML
    private TableColumn<Machine, String> statut;

    @FXML
    private Button supprimer;

    @FXML
    void ajouter(ActionEvent event) {
    	try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("addMachine.fxml")); 
            
            Scene scene=new Scene(root,370,220);
          
            primaryStage.setScene(scene);
            //primaryStage.initOwner(ajoute.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void modifier(ActionEvent event) {
    	m=machines.getSelectionModel().getSelectedItem();
    	if(m!=null) {
    		try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("editMachine.fxml")); 
            
            Scene scene=new Scene(root,370,220);
          
            primaryStage.setScene(scene);
            primaryStage.initOwner(ajouter.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}else {
       		Alert alrt = new Alert(Alert.AlertType.ERROR);
     	    alrt.setTitle("Attention");
     	    alrt.setContentText("Veuillez sélectionner l'élément à modifier");
     	    alrt.showAndWait();
       	 }
    	
    }

    @FXML
    void supprimer(ActionEvent event) {
    	Alert alert = new Alert(AlertType.WARNING,  "voulez-vous vraiment supprimer cet intervention", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    	 Machine m = machines.getSelectionModel().getSelectedItem();
    	 if(m!=null){
    		  alert.showAndWait();
    	 }else {
    	   		Alert alrt = new Alert(Alert.AlertType.ERROR);
    	 	    alrt.setTitle("Attention");
    	 	    alrt.setContentText("Veuillez sélectionner l'élément à supprimer");
    	 	    alrt.showAndWait();
    	   	 }
    	

    	if (alert.getResult() == ButtonType.YES) {
    		
        	imp.deleteMach(machines.getSelectionModel().getSelectedItem());

        	machs.setAll(imp.getAllMachines());
     
    	}
    }

    @FXML
    void detail(ActionEvent event) {
    	m=machines.getSelectionModel().getSelectedItem();
    	if(m!=null) {
    		try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("detailMachine.fxml")); 
            
            Scene scene=new Scene(root,730,600);
          
            primaryStage.setScene(scene);
            primaryStage.initOwner(ajouter.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}else {
       		Alert alrt = new Alert(Alert.AlertType.ERROR);
     	    alrt.setTitle("Attention");
     	    alrt.setContentText("Veuillez sélectionner l'élément à afficher");
     	    alrt.showAndWait();
       	 }
    }
    public static ObservableList<Machine> machs= FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		
machs.setAll(imp.getAllMachines());
machines.setItems(machs);		
	
	chercher.textProperty().addListener((observable, oldValue, newValue) -> {
	    machines.getItems().clear();
	    machs.addAll(imp.getMach(chercher.getText()));
	    machines.setItems(machs);
	    });		
	}

}
