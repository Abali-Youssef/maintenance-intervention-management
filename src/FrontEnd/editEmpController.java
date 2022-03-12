package FrontEnd;

import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import metier.Employe;
import metier.Implementation;

public class editEmpController implements Initializable{

    @FXML
    private Button modifier;

    @FXML
    private TextField nom;

    @FXML
    private TextField numlocation;

    @FXML
    private TextField prenom;
    
    @FXML
    private ImageView image;

    @FXML
    private Button choisir;

    @FXML
    private ComboBox<String> roles;

    @FXML
    void modifier(ActionEvent event) {
    	Employe e= new Employe(empController.emp.getId(),nom.getText().toString(),prenom.getText().toString(),roles.getSelectionModel().getSelectedItem(),numlocation.getText().toString(),empController.emp.getStatut(),numlocation.getText().toString());
    	Implementation imp = new Implementation();
    	e.setImage(file);
    	imp.editEmp(e);
    	try {
			empController.emps.setAll(imp.getAllEmployees());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	empController.emp=null;
    	Stage st =(Stage) prenom.getScene().getWindow();
    	st.close();

    }
    File file;
    @FXML
    void choisir(ActionEvent event) throws FileNotFoundException {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("selectionner une image");
    	file = fileChooser.showOpenDialog(choisir.getScene().getWindow());
    	
    	image.setImage(new Image(new FileInputStream(file.getPath())));
    }
	Implementation imp = new Implementation();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Image imge = new Image(imp.getImage(empController.emp ));
		image.setImage(imge);
		
		roles.getItems().addAll("admin","user");
		//empController.emp
		nom.setText(empController.emp.getNom());
		prenom.setText(empController.emp.getPrenom());
		numlocation.setText(empController.emp.getNumlocation());
		roles.setValue(empController.emp.getRole());
	}

}
