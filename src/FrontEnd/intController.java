package FrontEnd;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import metier.*;

public class intController implements Initializable {
	public static Intervention intv = new Intervention();
	@FXML
	private Button ajouter;

	@FXML
	private TextField chercher;
	@FXML
	private Button imprimer;

	@FXML
	private Button afficher;
	@FXML
	private DatePicker debut;

	@FXML
	private DatePicker fin;

	@FXML
	private TableColumn<Intervention, Date> date;

	@FXML
	private BarChart<String, Number> intchart;
	static XYChart.Series serie = new XYChart.Series();

	public XYChart.Series getSerie() {
		return serie;
	}

	public static void setSerie(XYChart.Series serie) {
		serie = serie;
	}

	@FXML
	private TableView<Intervention> interventions;

	@FXML
	private TableColumn<Intervention, Machine> machine;

	@FXML
	private Button modifier;

	@FXML
	private TableColumn<Intervention, String> nb_intervenant;

	@FXML
	private TableColumn<Intervention, String> nb_materiel;

	@FXML
	private TableColumn<Intervention, String> employe;

	@FXML
	private TableColumn<Intervention, String> statut;
	@FXML
	private TableColumn<Intervention, String> nom;

	@FXML
	private Button supprimer;

	@FXML
	void ajouter(ActionEvent event) {
		try {
			Stage primaryStage = new Stage();
			Pane root = FXMLLoader.load(getClass().getResource("addIntervention.fxml"));

			Scene scene = new Scene(root, 780, 560);

			primaryStage.setScene(scene);
			// primaryStage.initOwner(ajoute.getParent().getScene().getWindow());
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void afficher(ActionEvent event) {
		if (debut.getValue() != null && fin.getValue() != null) {
			intchart.getData().clear();
			intchart.getData().addAll(imp.getSerieOfPeriod(Date.valueOf(debut.getValue()), Date.valueOf(fin.getValue())));

		} else {
			Alert alrt = new Alert(Alert.AlertType.ERROR);
			alrt.setTitle("Attention");
			alrt.setContentText("Veuillez choisir une période à afficher");
			alrt.showAndWait();
		}
	}

	@FXML
	void modifier(ActionEvent event) {
		intv = interventions.getSelectionModel().getSelectedItem();
		if (intv != null) {
			try {
				Stage primaryStage = new Stage();
				Pane root = FXMLLoader.load(getClass().getResource("editIntervention.fxml"));

				Scene scene = new Scene(root, 780, 560);

				primaryStage.setScene(scene);
				primaryStage.initOwner(ajouter.getParent().getScene().getWindow());
				primaryStage.initModality(Modality.APPLICATION_MODAL);
				primaryStage.showAndWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alrt = new Alert(Alert.AlertType.ERROR);
			alrt.setTitle("Attention");
			alrt.setContentText("Veuillez sélectionner l'élément à modifier");
			alrt.showAndWait();
		}

	}

	@FXML
	void supprimer(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING, "voulez-vous vraiment supprimer cet intervention", ButtonType.YES,
				ButtonType.NO, ButtonType.CANCEL);
		Intervention in = interventions.getSelectionModel().getSelectedItem();
		if (in != null) {
			alert.showAndWait();
		} else {
			Alert alrt = new Alert(Alert.AlertType.ERROR);
			alrt.setTitle("Attention");
			alrt.setContentText("Veuillez sélectionner l'élément à supprimer");
			alrt.showAndWait();
		}

		if (alert.getResult() == ButtonType.YES) {

			imp.deleteIntervention(interventions.getSelectionModel().getSelectedItem());

			inters.setAll(imp.getAllIntervention());

		}
	}

	@FXML
	void imprimer(ActionEvent event) {

		Document doc = new Document();
		long millis = System.currentTimeMillis();
		Date dt = new Date(millis);
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		FileChooser filechooser = new FileChooser();
		File file;
		try {

			filechooser.getExtensionFilters().addAll(new ExtensionFilter("pdf", ".pdf"));
			filechooser.setInitialDirectory(new File("C:\\Users\\HP\\Desktop"));
			file = filechooser.showSaveDialog(ajouter.getScene().getWindow());
			filechooser.setInitialFileName("extrait-intervention-du-" + sdf.format(dt));
			PdfWriter.getInstance(doc, new FileOutputStream(file.getPath()));
			doc.open();
			// doc.add(new Paragraph(" "));

			Image img = Image.getInstance("D:\\_ENSET\\_ENSET__S3\\JAVA\\header.JPG");
			img.scaleAbsoluteHeight(120);
			img.scaleAbsoluteWidth(600);
			img.setAlignment(Image.ALIGN_CENTER);

			doc.add(img);
			Paragraph titre = new Paragraph("Extrait des intervention des maintenance");
			titre.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(titre);

			Paragraph dat = new Paragraph("Le " + sdf.format(dt));
			dat.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(dat);
			doc.add(new Paragraph("   "));
			doc.add(new Paragraph("   "));
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(80);
			PdfPCell nom = new PdfPCell(new Phrase("nom"));
			nom.setHorizontalAlignment(Element.ALIGN_CENTER);
			nom.setBorderColor(BaseColor.BLUE);
			table.addCell(nom);

			PdfPCell date = new PdfPCell(new Phrase("Date"));
			date.setHorizontalAlignment(Element.ALIGN_CENTER);
			nom.setBorderColor(BaseColor.BLUE);

			table.addCell(date);
			PdfPCell statut = new PdfPCell(new Phrase("Statut"));
			statut.setHorizontalAlignment(Element.ALIGN_CENTER);
			nom.setBorderColor(BaseColor.BLUE);
			table.addCell(statut);
			PdfPCell machine = new PdfPCell(new Phrase("Machine"));
			machine.setHorizontalAlignment(Element.ALIGN_CENTER);
			nom.setBorderColor(BaseColor.BLUE);
			table.addCell(machine);
			PdfPCell employe = new PdfPCell(new Phrase("Employe"));
			employe.setHorizontalAlignment(Element.ALIGN_CENTER);
			nom.setBorderColor(BaseColor.BLUE);
			table.addCell(employe);
			PdfPCell materiel = new PdfPCell(new Phrase("Materiel"));
			materiel.setHorizontalAlignment(Element.ALIGN_CENTER);
			nom.setBorderColor(BaseColor.BLUE);
			table.addCell(materiel);
			for (Intervention itv : inters) {
				PdfPCell rowNom = new PdfPCell(new Phrase(itv.getNom()));
				rowNom.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rowNom);
				PdfPCell rowDate = new PdfPCell(new Phrase(itv.getDate().toString()));
				rowDate.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rowDate);
				PdfPCell rowStatut = new PdfPCell(new Phrase(itv.getStatut()));
				rowStatut.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rowStatut);
				PdfPCell rowMachine = new PdfPCell(new Phrase(itv.getMachine().toString()));
				rowMachine.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rowMachine);
				PdfPCell rowEmployes = new PdfPCell(new Phrase(itv.getEmployes().toString()));
				rowEmployes.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rowEmployes);
				PdfPCell rowMateriel = new PdfPCell(new Phrase(itv.getMateriels().toString()));
				rowMateriel.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(rowMateriel);
			}

			doc.add(table);

			doc.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static ObservableList<Intervention> inters = FXCollections.observableArrayList();
	Implementation imp = new Implementation();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
		nb_materiel.setCellValueFactory(new PropertyValueFactory<>("materiels"));
		nb_intervenant.setCellValueFactory(new PropertyValueFactory<>("employes"));
		machine.setCellValueFactory(new PropertyValueFactory<>("machine"));
		inters.setAll(imp.getAllIntervention());
		interventions.setItems(inters);

		intchart.getData().add(imp.getSerieInterventions());
//chercher
		chercher.textProperty().addListener((observable, oldValue, newValue) -> {
			interventions.getItems().clear();
			inters.addAll(imp.getInterventionByNom(chercher.getText()));
			interventions.setItems(inters);
		});
	}

}
