package FrontEnd;

import java.io.FileNotFoundException;
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
import metier.*;

public class empController implements Initializable{
	public static Employe emp = new Employe();
	Implementation imp = new Implementation();
	@FXML
    private Button ajouter;

    @FXML
    private TextField chercher;

    @FXML
    private TableView<Employe> employes;

    @FXML
    private Button modifier;

    @FXML
    private TableColumn<Employe, String> nom;

    @FXML
    private TableColumn<Employe, String> numlocation;

    @FXML
    private TableColumn<Employe, String> prenom;

    @FXML
    private TableColumn<Employe, String> statut;

    @FXML
    private Button supprimer;

    @FXML
    private Button detail;
    
    @FXML
    void ajouter(ActionEvent event) {
    	try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("addEmploye.fxml")); 
            
            Scene scene=new Scene(root,355,505);
          
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
    	emp=employes.getSelectionModel().getSelectedItem();
    	
    	
    	if(emp!= null){
    		try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("editEmploye.fxml")); 
            
            Scene scene=new Scene(root,355,505);
          
            primaryStage.setScene(scene);
            //primaryStage.initOwner(ajoute.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	}
    		else {
    	   		Alert alrt = new Alert(Alert.AlertType.ERROR);
    	 	    alrt.setTitle("Attention");
    	 	    alrt.setContentText("Veuillez sélectionner l'élément à modifier");
    	 	    alrt.showAndWait();
    	   	 
    	}
      	
    }
    @FXML
    void supprimer(ActionEvent event) throws FileNotFoundException {
    	Alert alert = new Alert(AlertType.WARNING,  "voulez-vous vraiment supprimer cet employé", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
      	 Employe in = employes.getSelectionModel().getSelectedItem();
      	 if(in!=null){
      		  alert.showAndWait();
      	 }else {
        		Alert alrt = new Alert(Alert.AlertType.ERROR);
         	    alrt.setTitle("Attention");
         	    alrt.setContentText("Veuillez sélectionner l'élément à supprimer");
         	    alrt.showAndWait();
           	 }
      	

      	if (alert.getResult() == ButtonType.YES) {
      		
          	imp.deleteEmp(employes.getSelectionModel().getSelectedItem());

          	emps.setAll(imp.getAllEmployees());
       
      	}
    }
    
    @FXML
    void detail(ActionEvent event) {
    	emp=employes.getSelectionModel().getSelectedItem();
    	if(emp!= null){
    		try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("detailEmploye.fxml")); 
            
            Scene scene=new Scene(root,666,500);
          
            primaryStage.setScene(scene);
            //primaryStage.initOwner(ajoute.getParent().getScene().getWindow());
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

    
    public static ObservableList<Employe> emps= FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		numlocation.setCellValueFactory(new PropertyValueFactory<>("numlocation"));
		
try {
	
	emps.setAll(imp.getAllEmployees());
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
employes.setItems(emps);		
	
	chercher.textProperty().addListener((observable, oldValue, newValue) -> {
	    employes.getItems().clear();
	    emps.addAll(imp.getEmp(chercher.getText()));
	    employes.setItems(emps);
	    });
}}
