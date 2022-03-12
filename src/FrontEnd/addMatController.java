package FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Implementation;
import metier.Materiel;

public class addMatController {

    @FXML
    private Button ajouter;

    @FXML
    private TextField intitule;

    @FXML
    void ajouter(ActionEvent event) {
Materiel m = new Materiel(intitule.getText().toString(),"disponible");
Implementation imp = new Implementation();
imp.addMateriel(m);
matController.mats.setAll(imp.getAllMateriels());
Stage st =(Stage) intitule.getScene().getWindow();
st.close();
    }

}
