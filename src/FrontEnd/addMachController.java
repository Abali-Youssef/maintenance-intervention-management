package FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Implementation;
import metier.Machine;
import metier.Materiel;

public class addMachController {

    @FXML
    private Button ajouter;

    @FXML
    private TextField nom;

    @FXML
    void ajouter(ActionEvent event) {
    	Machine m = new Machine(nom.getText().toString(),"disponible");
    	Implementation imp = new Implementation();
    	imp.addMach(m);
    	machController.machs.setAll(imp.getAllMachines());
    	Stage st =(Stage) nom.getScene().getWindow();
    	st.close();
    }

}
