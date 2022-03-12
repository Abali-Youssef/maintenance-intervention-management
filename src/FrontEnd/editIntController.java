package FrontEnd;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.*;

public class editIntController implements Initializable {
	Implementation imp = new Implementation();
	List<String> status = new ArrayList<>();
	List<Employe> emp = new ArrayList<>();
	List<Materiel> mat = new ArrayList<>();
    @FXML
    private DatePicker date;
    @FXML
    private Button supprimerEmp;

    @FXML
    private Button supprimerMat;
    @FXML
    private ComboBox<Employe> employe;

    @FXML
    private ListView<Employe> employes;

    @FXML
    private ComboBox<Machine> machine;

    @FXML
    private ComboBox<Materiel> materiel;

    @FXML
    private ListView<Materiel> materiels;

    @FXML
    private Button modifier;

    @FXML
    private TextField nom;

    @FXML
    private ComboBox<String> statut;


    @FXML
    void supprimerEmp(ActionEvent event) {
    	Employe e= new Employe();
    	e=employes.getSelectionModel().getSelectedItem();
    	if(e!=null) {
    		imp.deleteFromIntervenir(intController.intv, e);
    	employes.getItems().remove(employes.getSelectionModel().getSelectedItem());
    	emp.remove(e);
    	intController.inters.setAll(imp.getAllIntervention());
    	}
    	
    }

    @FXML
    void supprimerMat(ActionEvent event) {
Materiel mt= new Materiel();
mt=materiels.getSelectionModel().getSelectedItem();
if(mt!=null) {
imp.deleteFromIntMat(intController.intv, mt);
materiels.getItems().remove(materiels.getSelectionModel().getSelectedItem());
mat.remove(mt);
intController.inters.setAll(imp.getAllIntervention());}
    }
    
    @FXML
    void modifier(ActionEvent event) {
    	Intervention intervention = new Intervention(intController.intv.getId(),nom.getText().toString(),statut.getSelectionModel().getSelectedItem().toString(),Date.valueOf(date.getValue()),machine.getSelectionModel().getSelectedItem(),emp,mat);
    	imp.editIntervention(intervention);
    	intController.inters.setAll(imp.getAllIntervention());
    	//intController.setIntchart(imp.getSerieInterventions());
    	intController.intv=null;
    	Stage st =(Stage) nom.getScene().getWindow();
    	st.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		status.add("en cours ");
	status.add("terminé ");
	
	statut.getItems().setAll(status);
	statut.setValue(intController.intv.getStatut());
	nom.setText(intController.intv.getNom());
	date.setValue(LOCAL_DATE(intController.intv.getDate().toString()));
	employes.getItems().setAll(intController.intv.getEmployes());
	emp.addAll(intController.intv.getEmployes());
	materiels.getItems().setAll(intController.intv.getMateriels());
	mat.addAll(intController.intv.getMateriels());
	try {
		employe.getItems().setAll(imp.getAllEmployees());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	materiel.getItems().setAll(imp.getAllMateriels());
	machine.getItems().setAll(imp.getAllMachines());
	machine.setValue(intController.intv.getMachine());
	employe.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
		  if(employe.getSelectionModel().getSelectedItem() != null) { 
			  employes.getItems().add(employe.getSelectionModel().getSelectedItem());}
		  emp.add(employe.getSelectionModel().getSelectedItem());
		  
		}); 

	materiel.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
		  mat.add(materiel.getSelectionModel().getSelectedItem());
		  materiels.getItems().add(materiel.getSelectionModel().getSelectedItem());
	}); 
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}

}
