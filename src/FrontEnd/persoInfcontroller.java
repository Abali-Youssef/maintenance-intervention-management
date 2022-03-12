package FrontEnd;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.Implementation;

public class persoInfcontroller implements Initializable{
	Implementation imp = new Implementation();
	public static String passwrd;
    @FXML
    private ImageView image;

    @FXML
    private Button importer;

    @FXML
    private Button modifier;

    @FXML
    private PasswordField motdepasse;

    @FXML
    private CheckBox afficher;
    @FXML
    private TextField nom;

    @FXML
    private TextField numlocation;
    @FXML
    private TextField shownpasswrd;

    @FXML
    private TextField prenom;

    @FXML
    private Button sauvegarder;

    @FXML
    void afficher(ActionEvent event) {
if(afficher.isSelected()) {
	motdepasse.setVisible(false);
	shownpasswrd.setVisible(true);
}else {
	motdepasse.setVisible(true);
	shownpasswrd.setVisible(false);
}
    }
    
    @FXML
    void importer(ActionEvent event) throws Exception {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("selectionner une image");
    	File file = fileChooser.showOpenDialog(importer.getScene().getWindow());
    	App.connectedEmploye.setImage(file);
    	image.setImage(new Image(new FileInputStream(file.getPath())));
    }

    @FXML
    void modifier(ActionEvent event) {
    	try {
    		Stage primaryStage= new Stage();
    		Pane root=  FXMLLoader.load(getClass().getResource("editPassword.fxml")); 
            Scene scene=new Scene(root,313,290);
          
            primaryStage.setScene(scene);
            //primaryStage.initOwner(ajoute.getParent().getScene().getWindow());
            primaryStage.initModality(Modality.APPLICATION_MODAL); 
            primaryStage.showAndWait();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void sauvegarder(ActionEvent event) {
    	Implementation imp = new Implementation();
App.connectedEmploye.setNom(nom.getText());
App.connectedEmploye.setPrenom(prenom.getText());
App.connectedEmploye.setPassword(motdepasse.getText());
App.connectedEmploye.setNumlocation(numlocation.getText());
imp.editEmp(App.connectedEmploye);

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Image imge = new Image(imp.getImage(App.connectedEmploye));
		image.setImage(imge);
		
		
		nom.setText(App.connectedEmploye.getNom());
		prenom.setText(App.connectedEmploye.getPrenom());
		numlocation.setText(App.connectedEmploye.getNumlocation());
		passwrd=App.connectedEmploye.getPassword();
		motdepasse.setText(passwrd);
		shownpasswrd.setText(passwrd);
		shownpasswrd.setVisible(false);
		motdepasse.textProperty().addListener((observable, oldValue, newValue) -> {
		    shownpasswrd.setText(motdepasse.getText());
		    });
	}

}
