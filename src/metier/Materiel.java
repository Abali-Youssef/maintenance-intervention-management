package metier;

public class Materiel {
private int id;
private String intitule;
private String statut;

public Materiel() {
	super();
}
public Materiel(int id, String intitule, String statut) {
	super();
	this.id = id;
	this.intitule = intitule;
	this.statut = statut;
}
public Materiel(String intitule, String statut) {
	super();
	this.intitule = intitule;
	this.statut = statut;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getIntitule() {
	return intitule;
}
public void setIntitule(String intitule) {
	this.intitule = intitule;
}
public String getStatut() {
	return statut;
}
public void setStatut(String statut) {
	this.statut = statut;
}
@Override
public String toString() {
	return intitule ;
}


}
