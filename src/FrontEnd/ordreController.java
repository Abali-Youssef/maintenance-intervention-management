package FrontEnd;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Implementation;
import metier.Intervention;
import metier.Machine;

public class ordreController implements Initializable{



    @FXML
    private TableColumn<Intervention, Date> date;

    @FXML
    private RadioButton encours;

    @FXML
    private RadioButton finis;

    @FXML
    private TableView<Intervention> interventions;

    @FXML
    private TableColumn<Intervention, Machine> machine;

    @FXML
    private TableColumn<Intervention, String> nom;

    @FXML
    private TableColumn<Intervention, String> statut;

    @FXML
    private RadioButton tous;

    @FXML
    void enCours(ActionEvent event) {
    	tous.setSelected(false);
    	finis.setSelected(false);
    	inters.setAll(imp.getInterventionOfEmployeEnCours(App.connectedEmploye));
		interventions.setItems(inters);
    }

    @FXML
    void finis(ActionEvent event) {
    	tous.setSelected(false);
    	encours.setSelected(false);
    	inters.setAll(imp.getInterventionOfEmployeFinis(App.connectedEmploye));
		interventions.setItems(inters);
    }

    @FXML
    void tous(ActionEvent event) {
    	finis.setSelected(false);
    	encours.setSelected(false);
    	inters.setAll(imp.getInterventionOfEmploye(App.connectedEmploye));
		interventions.setItems(inters);
    }
  ObservableList<Intervention> inters= FXCollections.observableArrayList();
  Implementation imp = new Implementation();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		     
		     nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
				date.setCellValueFactory(new PropertyValueFactory<>("date"));
				statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
				machine.setCellValueFactory(new PropertyValueFactory<>("machine"));

		inters.setAll(imp.getInterventionOfEmploye(App.connectedEmploye));
		interventions.setItems(inters);
		
		
		
	}

}
