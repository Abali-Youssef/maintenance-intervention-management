package FrontEnd;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Employe;
import metier.Implementation;
import metier.Intervention;
import metier.Materiel;

public class detailMachController implements Initializable{
	Implementation imp = new Implementation();

    @FXML
    private TextField chercher;
    @FXML
    private DatePicker debut;
    @FXML
    private DatePicker fin;
    @FXML
    private BarChart<String,Number>  intchart;
    @FXML
    private Button afficher;
    @FXML
    private TableColumn<Intervention, Date> date;
    @FXML
    private TableColumn<Intervention, Materiel> materiels;
    @FXML
    private TableColumn<Intervention,Employe > employes;

    @FXML
    private TableView<Intervention> interventions;

    @FXML
    private TableColumn<Intervention, String> nom;

    @FXML
    private TextField nomMach;

    @FXML
    private TableColumn<Intervention, String> statut;

    @FXML
    private TextField statutMach;

    @FXML
    void afficher(ActionEvent event) {
    	if (debut.getValue()!=null && fin.getValue()!=null) {
	    	intchart.getData().clear();
intchart.getData().addAll(imp.getSerieOfPeriod(Date.valueOf(debut.getValue()),Date.valueOf(fin.getValue())));

}else {
	Alert alrt = new Alert(Alert.AlertType.ERROR);
	    alrt.setTitle("Attention");
	    alrt.setContentText("Veuillez choisir une période à afficher");
	    alrt.showAndWait();
}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Intervention> inters= FXCollections.observableArrayList();
		nomMach.setText(machController.m.getNom());
		statutMach.setText(machController.m.getStatut());
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		employes.setCellValueFactory(new PropertyValueFactory<>("employes"));
		materiels.setCellValueFactory(new PropertyValueFactory<>("materiels"));
		inters.setAll(imp.getInterventionOfMachine(machController.m));
	    interventions.setItems(inters);
	    intchart.getData().addAll(imp.getSerieOfMachine(machController.m));
	    machController.m=null;
	  //chercher
	    chercher.textProperty().addListener((observable, oldValue, newValue) -> {
	        interventions.getItems().clear();
	        inters.addAll(imp.getInterventionOfMachineByNom(machController.m,chercher.getText()));
	        interventions.setItems(inters);
	        });
	}

}
