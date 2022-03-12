package FrontEnd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import metier.Employe;
import metier.Implementation;

public class App extends Application {
	public static Employe connectedEmploye=null;
	public static BorderPane root;
    @FXML
    private Button connecter;

    @FXML
    private TextField numlocation;

    @FXML
    private TextField password;

    @FXML
	void connecter(ActionEvent event) throws Exception {
		Stage primaryStage = new Stage();
		Implementation imp = new Implementation();
		connectedEmploye = imp.seConnecter(numlocation.getText(), password.getText());
		if (connectedEmploye != null) {
			Stage st = (Stage) password.getScene().getWindow();
			st.close();

			if (connectedEmploye.getRole().equals("admin")) {
				root = FXMLLoader.load(getClass().getResource("layout.fxml"));
				Scene scene = new Scene(root, 1000, 650);
				primaryStage.setScene(scene);
				Pane pane = null;
				try {
					pane = FXMLLoader.load(getClass().getResource("intervention.fxml"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				App.root.setCenter(pane);

				primaryStage.show();

			}

			else {
				root = FXMLLoader.load(getClass().getResource("dashUser.fxml"));
				Scene scene = new Scene(root, 950, 523);
				primaryStage.setScene(scene);
		    	Pane pane=null;
		    	try {
		    	pane = FXMLLoader.load(getClass().getResource("ordre.fxml"));
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
		App.root.setCenter(pane);
				primaryStage.show();
			}
		}

		else {
			System.out.print("wrong infos");
		}

	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane rt= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene=new Scene(rt,630,421);
        primaryStage.setScene(scene);
        primaryStage.show();		
	}

}
