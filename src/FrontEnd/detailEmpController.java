package FrontEnd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import metier.Implementation;
import metier.Intervention;
import metier.Machine;

public class detailEmpController implements Initializable{

    @FXML
    private TextField chercher;

    @FXML
    private ImageView image;
    @FXML
    private TableColumn<Intervention, Date> date;

    @FXML
    private TableView<Intervention> interventions;

    @FXML
    private TableColumn<Intervention, Machine> machine;

    @FXML
    private TableColumn<Intervention, String> nom;

    @FXML
    private Label nomprenom;

    @FXML
    private Label numlocation;

    @FXML
    private Label role;

    @FXML
    private TableColumn<Intervention, String> statut;

    @FXML
    private Label statutEmp;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	     ObservableList<Intervention> inters= FXCollections.observableArrayList();
	     Implementation imp = new Implementation();
		nomprenom.setText(empController.emp.getNom()+" "+empController.emp.getPrenom());
		role.setText(empController.emp.getRole());
		statutEmp.setText(empController.emp.getStatut());
		numlocation.setText(empController.emp.getNumlocation());
		Image imge = new Image(imp.getImage(empController.emp));
		image.setImage(imge);
		
		image.setImage(imge);
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		machine.setCellValueFactory(new PropertyValueFactory<>("machine"));

inters.setAll(imp.getInterventionOfEmploye(empController.emp));
interventions.setItems(inters);

empController.emp=null;
//chercher
chercher.textProperty().addListener((observable, oldValue, newValue) -> {
    interventions.getItems().clear();
    inters.addAll(imp.getInterventionOfEmployeByNom(empController.emp,chercher.getText()));
    interventions.setItems(inters);
    });
	}

}
