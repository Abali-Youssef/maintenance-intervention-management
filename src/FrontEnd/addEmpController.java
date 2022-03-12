package FrontEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import metier.Employe;
import metier.Implementation;

public class addEmpController implements Initializable{
Employe e=new Employe();
File file;
    @FXML
    private Button ajouter;
    @FXML
    private Button choisir;

    @FXML
    private Label image;
    @FXML
    private TextField nom;

    @FXML
    private TextField numlocation;

    @FXML
    private TextField prenom;

    @FXML
    private ComboBox<String> roles;

    @FXML
    void ajouter(ActionEvent event) throws FileNotFoundException {
 e= new Employe(nom.getText().toString(),prenom.getText().toString(),roles.getSelectionModel().getSelectedItem(),numlocation.getText().toString(),"disponible",numlocation.getText().toString());
Implementation imp = new Implementation();
e.setImage(file);
imp.addEmp(e);
empController.emps.setAll(imp.getAllEmployees());
Stage st =(Stage) prenom.getScene().getWindow();
st.close();
    }
    @FXML
    void choisir(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("selectionner une image");
    	
    	
    	 file = fileChooser.showOpenDialog(ajouter.getScene().getWindow());
    	
    	image.setText(file.getName());
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roles.getItems().addAll("admin","user");
		
	}

}
