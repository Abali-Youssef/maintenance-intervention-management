package metier;

import java.io.File;

public class Employe {
private int id;
private String nom;
private String prenom;
private String role;
private String numlocation;
private String statut;
private String password;
private File image;


public Employe() {
	super();
}
public Employe(String nom, String prenom, String role, String numlocation, String statut, String password) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.role = role;
	this.numlocation = numlocation;
	this.statut = statut;
	this.password = password;
}
public Employe(int id, String nom, String prenom, String role, String numlocation, String statut, String password) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.role = role;
	this.numlocation = numlocation;
	this.statut = statut;
	this.password = password;
}
public Employe(int id, String nom, String prenom, String role, String numlocation,  String password) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.role = role;
	this.numlocation = numlocation;

	this.password = password;
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
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getNumlocation() {
	return numlocation;
}
public void setNumlocation(String numlocation) {
	this.numlocation = numlocation;
}
public String getStatut() {
	return statut;
}
public void setStatut(String statut) {
	this.statut = statut;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public File getImage() {
	return image;
}
public void setImage(File image) {
	this.image = image;
}
@Override
public String toString() {
	return  nom +"  "+ prenom+"\n"  ;
}

}
