package FrontEnd;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Implementation;
import metier.Materiel;

public class editMatController implements Initializable{

    @FXML
    private TextField intitule;

    @FXML
    private Button modifier;

    @FXML
    void modifier(ActionEvent event) {
    	Materiel m = new Materiel(matController.m.getId(),intitule.getText().toString(),matController.m.getStatut());
    	Implementation imp = new Implementation();
    	imp.editMateriel(m);
    	matController.mats.setAll(imp.getAllMateriels());
    	matController.m=null;
    	Stage st =(Stage) intitule.getScene().getWindow();
    	st.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		intitule.setText(matController.m.getIntitule());
	}

}
