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
import metier.Employe;
import metier.Implementation;
import metier.Materiel;

public class matController implements Initializable{
	public static Materiel m = new Materiel();
	Implementation imp = new Implementation();
    @FXML
    private Button ajouter;

    @FXML
    private TextField chercher;

    @FXML
    private TableColumn<Materiel, String> intitule;

    @FXML
    private TableView<Materiel> materiels;

    @FXML
    private Button modifier;

    @FXML
    private TableColumn<Materiel, String> statut;

    @FXML
    private Button supprimer;

    @FXML
    void ajouter(ActionEvent event) {
    	try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("addMateriel.fxml")); 
            
            Scene scene=new Scene(root,370,220);
          
            primaryStage.setScene(scene);
            primaryStage.initOwner(ajouter.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void modifier(ActionEvent event) {
    	m=materiels.getSelectionModel().getSelectedItem();
    	if(m!=null) {
    		try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("editMateriel.fxml")); 
            
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
     	 Materiel mt = materiels.getSelectionModel().getSelectedItem();
     	 if(mt!=null){
     		  alert.showAndWait();
     	 }else {
        		Alert alrt = new Alert(Alert.AlertType.INFORMATION);
         	    alrt.setTitle("Attention");
         	    alrt.setContentText("Veuillez sélectionner l'élément à supprimer");
         	    alrt.showAndWait();
           	 }
     	

     	if (alert.getResult() == ButtonType.YES) {
     		
         	imp.deleteMateriel(materiels.getSelectionModel().getSelectedItem());

         	mats.setAll(imp.getAllMateriels());
      
     	}
    }
    
    @FXML
    void detail(ActionEvent event) {
    	m=materiels.getSelectionModel().getSelectedItem();
    	if(m!=null) {
    		try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("detailMateriel.fxml")); 
            
            Scene scene=new Scene(root,670,400);
          
            primaryStage.setScene(scene);
            primaryStage.initOwner(ajouter.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}else {
       		Alert alrt = new Alert(Alert.AlertType.WARNING);
     	    alrt.setTitle("Attention");
     	    alrt.setContentText("Veuillez sélectionner l'élément à afficher");
     	    alrt.showAndWait();
       	 }
    	
    }
    public static ObservableList<Materiel> mats= FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		intitule.setCellValueFactory(new PropertyValueFactory<>("intitule"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		
mats.setAll(imp.getAllMateriels());
materiels.setItems(mats);		
	
	chercher.textProperty().addListener((observable, oldValue, newValue) -> {
	    materiels.getItems().clear();
	    mats.addAll(imp.getMateriel(chercher.getText()));
	    materiels.setItems(mats);
	    });
		
	}

}
