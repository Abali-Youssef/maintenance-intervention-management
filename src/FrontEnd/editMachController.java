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
import metier.Machine;
import metier.Materiel;

public class editMachController implements Initializable{

    @FXML
    private Button modifier;

    @FXML
    private TextField nom;

    @FXML
    void modifier(ActionEvent event) {
    	Machine m = new Machine(machController.m.getId(),nom.getText().toString(),machController.m.getStatut());
    	Implementation imp = new Implementation();
    	imp.editMach(m);
    	machController.machs.setAll(imp.getAllMachines());
    	machController.m=null;
    	Stage st =(Stage) nom.getScene().getWindow();
    	st.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nom.setText(machController.m.getNom());
	}

}
