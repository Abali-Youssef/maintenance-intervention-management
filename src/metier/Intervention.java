package metier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Intervention {
private int id;
private String nom;
private String statut;
private Date date;
private Machine machine;
private List<Employe> employes;
private List<Materiel> materiels;

public Intervention() {
	super();
	employes = new ArrayList<>();
	materiels = new ArrayList<>();
}
public Intervention(int id, String nom, String statut, Date date, Machine machine) {
	
	super();
	this.id = id;
	this.nom = nom;
	this.statut = statut;
	this.date = date;
	this.machine = machine;
	employes = new ArrayList<>();
	materiels = new ArrayList<>();
}
public Intervention(int id, String nom, String statut, Date date, Machine machine, List<Employe> employes,
		List<Materiel> materiels) {
	super();
	this.id = id;
	this.nom = nom;
	this.statut = statut;
	this.date = date;
	this.machine = machine;
	this.employes = employes;
	this.materiels = materiels;
}
public Intervention(String nom, String statut, Date date, Machine machine) {
	super();
	this.nom = nom;
	this.statut = statut;
	this.date = date;
	this.machine = machine;
	employes = new ArrayList<>();
	materiels = new ArrayList<>();
}
public List<Employe> getEmployes() {
	return employes;
}
public void setEmployes(List<Employe> employes) {
	this.employes = employes;
}
public Intervention(String nom, String statut, Date date, Machine machine, List<Employe> employes,
		List<Materiel> materiels) {
	super();
	this.nom = nom;
	this.statut = statut;
	this.date = date;
	this.machine = machine;
	this.employes = employes;
	this.materiels = materiels;
}
public List<Materiel> getMateriels() {
	return materiels;
}
public void setMateriels(List<Materiel> materiels) {
	this.materiels = materiels;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getStatut() {
	return statut;
}
public void setStatut(String statut) {
	this.statut = statut;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public Machine getMachine() {
	return machine;
}
public void setMachine(Machine machine) {
	this.machine = machine;
}
@Override
public String toString() {
	return "Intervention [id=" + id + ", nom=" + nom + ", statut=" + statut + ", date=" + date + ", machine=" + machine
			+ ", employes=" + employes + ", materiels=" + materiels + "]";
}


}
