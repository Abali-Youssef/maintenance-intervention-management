package metier;

import java.sql.Date;

/*import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;*/
import java.sql.Date;
import javafx.stage.Stage;

public class test  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employe e1 = new Employe(2,"ahmed","eeee", "admin", "22223", "eer", "ped");
		Employe e2 = new Employe("ali","eeee", "admin", "22223", "eer", "ped");
		Machine m1 = new Machine(1,"hp","oui");
		Machine m2 = new Machine("pop","oui");
		Materiel mt1 = new Materiel(1,"hp","oui");
		Materiel mt2 = new Materiel("pop","oui");
		Intervention in =new Intervention(1,"bpp", "desable", Date.valueOf("1997-03-10"), m1);
		Implementation imp = new Implementation();
		/*imp.addEmp(e1);
		imp.addMach(m1);
		imp.addMateriel(mt1);
		imp.editEmp(e1);
		imp.editMach(m1);
		imp.editMateriel(mt1);
		imp.deleteEmp(e1);
		imp.deleteMach(m1);
		imp.deleteMateriel(mt1);
		for(Employe e:imp.getAllEmployees())System.out.println(e);
		for(Machine e:imp.getAllMachines())System.out.println(e);
		for(Materiel e:imp.getAllMateriels())System.out.println(e);
		System.out.println(imp.getEmp("ahmed"));
		System.out.println(imp.getEmpById(3));
		System.out.println(imp.getMach("hp"));
		System.out.println(imp.getMachById(3));
		System.out.println(imp.getMateriel("hp"));
		System.out.println(imp.getMaterielById(3));
		imp.addIntervention(in);
		for( Intervention i : imp.getAllIntervention())
		System.out.println(i);*/
		for(Intervention iv :imp.getInterventionOfEmploye(e1)) {
			System.out.println(iv);
		}
	}

	

}
