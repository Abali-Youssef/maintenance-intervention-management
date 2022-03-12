package FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class editPasswordController {

    @FXML
    private TextField confpassword;

    @FXML
    private Button modifier;

    @FXML
    private TextField newpassword;

    @FXML
    private TextField password;

    @FXML
    void modifier(ActionEvent event) {
if(confpassword.getText().equals(newpassword.getText()) && password.getText().equals(persoInfcontroller.passwrd)) {
	App.connectedEmploye.setPassword(newpassword.getText());
	
Stage stg=(Stage)modifier.getScene().getWindow();
stg.close();

}
    }

}
