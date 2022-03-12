package FrontEnd;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Implementation;
import metier.Intervention;
import metier.Machine;

public class detailMatController implements Initializable{

    @FXML
    private TextField chercher;

    @FXML
    private TableColumn<Intervention, Date> date;

    @FXML
    private TableView<Intervention> interventions;

    @FXML
    private TableColumn<Intervention, Machine> machine;

    @FXML
    private TableColumn<Intervention, String> nom;

    @FXML
    private TextField nomMat;

    @FXML
    private TableColumn<Intervention, String> statut;

    @FXML
    private TextField statutMat;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	     ObservableList<Intervention> inters= FXCollections.observableArrayList();
	     Implementation imp = new Implementation();
		nomMat.setText(matController.m.getIntitule());
		statutMat.setText(matController.m.getStatut());
		
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		machine.setCellValueFactory(new PropertyValueFactory<>("machine"));

inters.setAll(imp.getInterventionOfMateriel(matController.m));
interventions.setItems(inters);
matController.m=null;
//chercher
chercher.textProperty().addListener((observable, oldValue, newValue) -> {
  interventions.getItems().clear();
  inters.addAll(imp.getInterventionOfMaterielByNom(matController.m,chercher.getText()));
  interventions.setItems(inters);
  });
	}

}
