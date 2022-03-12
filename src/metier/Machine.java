package metier;

public class Machine {
private int id;
private String nom;
private String statut;

public Machine() {
	super();
}
public Machine(int id, String nom, String statut) {
	super();
	this.id = id;
	this.nom = nom;
	this.statut = statut;
}
public Machine(String nom, String statut) {
	super();
	this.nom = nom;
	this.statut = statut;
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
@Override
public String toString() {
	return nom ;
}

}
