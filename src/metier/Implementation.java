package metier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
public class Implementation {
	Connection connexion;
	 public Implementation (){
		connexion=SingletonConnexion.getConnection();
	}
	 
	 public Employe seConnecter(String num,String password) throws FileNotFoundException {
		 Employe e=new Employe();
		 InputStream is ;
		 File img = new File("temp.png");
		 OutputStream fos =new FileOutputStream(img);
		 int v=0;
		 try {
			 	
		        PreparedStatement pstm=connexion.prepareStatement("select * from employe where  numlocation=? and password=? ");
		        pstm.setString(1,num);
		        pstm.setString(2,password);
		        pstm.executeQuery();
		        ResultSet rs= pstm.getResultSet();
		        while (rs.next()){
		            
		            e.setId(rs.getInt(1));
		            e.setNom(rs.getString(2));
		            e.setPrenom(rs.getString(3));
		            e.setRole(rs.getString(4));
		            e.setNumlocation(rs.getString(5));
		            e.setStatut(rs.getString(6));
		            e.setPassword(rs.getString(7));
		            
		            is=getImage(e);
		            while((v=is.read())!=-1) {
		            	fos.write(v);
		            }
		            e.setImage(img);
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		 return e;
	 }
	 public InputStream getImage(Employe e) {
		 InputStream input=null;
		 try {
		        PreparedStatement pstm=connexion.prepareStatement("select image from employe where  id=? ");
		        pstm.setInt(1,e.getId());
		        pstm.executeQuery();
		        ResultSet rs= pstm.getResultSet();
		        while (rs.next()){
		        	
		        	input = rs.getBinaryStream(1);
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		 return input;
	 }
	 
public void addEmp(Employe e) {
	InputStream is = null;
    try {
        PreparedStatement pstm=connexion.prepareStatement("insert into employe(nom,prenom,role,numlocation,statut,password,image) values(?,?,?,?,?,?,?)");
        pstm.setString(1,e.getNom());
        pstm.setString(2,e.getPrenom());
        pstm.setString(3,e.getRole());
        pstm.setString(4,e.getNumlocation());
        pstm.setString(5,"disponible");
        pstm.setString(6,e.getPassword());
        if(e.getImage()==null) {
        	e.setImage(new File("C:\\Users\\HP\\Pictures\\trial\\ano.png"));
        }
        is = new FileInputStream(e.getImage());
        pstm.setBinaryStream(7, is,
				(int) (e.getImage().length()));
        

        pstm.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
public void editEmp(Employe e) {
	InputStream is = null;
	
	try {
	PreparedStatement pstm = connexion.prepareStatement("update employe set nom=?,prenom=?,role=?,numlocation=?,statut=?,password=?,image=? where id = ?");
	pstm.setString(1, e.getNom());
	pstm.setString(2, e.getPrenom());
	pstm.setString(3, e.getRole());
	pstm.setString(4, e.getNumlocation());
	pstm.setString(5, e.getStatut());
	pstm.setString(6, e.getPassword());
	pstm.setInt(8, e.getId());
	is = new FileInputStream(e.getImage());
    pstm.setBinaryStream(7, is,(int)(e.getImage().length()));
	pstm.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
	}
}
public void deleteEmp(Employe e) {
	try {
	PreparedStatement pstm = connexion.prepareStatement("delete from employe where id=?");
	pstm.setInt(1, e.getId());
	
	pstm.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
	}
}
public List<Employe> getEmp(String motif) {
	List<Employe> employe=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from employe where  nom like ? ");
        pstm.setString(1,"%"+motif+"%");
        //pstm.setString(2,"%"+motif+"%");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            Employe e=new Employe();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString(3));
            e.setRole(rs.getString(4));
            e.setNumlocation(rs.getString(5));
            e.setStatut(rs.getString(6));
            e.setPassword(rs.getString(7));
            employe.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employe;
}
public Employe getEmpById(int id) {
	Employe e=new Employe();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from employe where  id=? ");
        pstm.setInt(1,id);
        //pstm.setString(2,"%"+motif+"%");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString(3));
            e.setRole(rs.getString(4));
            e.setNumlocation(rs.getString(5));
            e.setStatut(rs.getString(6));
            e.setPassword(rs.getString(7));
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return e;
}
public List<Employe>  getAllEmployees() throws FileNotFoundException {
	List<Employe> employe=new ArrayList<>();
	 InputStream is ;
	
	 int v=0;
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from employe ");
  
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){ 
        	File img = new File("temp.png");
	 OutputStream fos =new FileOutputStream(img);
            Employe e=new Employe();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setPrenom(rs.getString(3));
            e.setRole(rs.getString(4));
            e.setNumlocation(rs.getString(5));
            e.setStatut(rs.getString(6));
            e.setPassword(rs.getString(7));
            is=getImage(e);
            if(e.getImage()!=null) {
            while((v=is.read())!=-1  ) {
            	fos.write(v);
            }	
            }
            
            e.setImage(img);
            employe.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employe;
}








public void addMateriel(Materiel m) {

    try {
        PreparedStatement pstm=connexion.prepareStatement("insert into materiel(intitule,statut) values(?,?)");
        pstm.setString(1,m.getIntitule());
       
        pstm.setString(2,"disponible");
       pstm.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
public void editMateriel(Materiel m) {
	try {
	PreparedStatement pstm = connexion.prepareStatement("update materiel set intitule=?,statut=? where id = ?");
	pstm.setString(1, m.getIntitule());
	pstm.setString(2, m.getStatut());
	pstm.setInt(3, m.getId());
	
	pstm.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
	}
}
public void deleteMateriel(Materiel m) {
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from materiel where id=?");
		pstm.setInt(1, m.getId());
		
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
}
public List<Materiel> getMateriel(String motif) {
	List<Materiel> materiel=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from materiel where  intitule like ? ");
        pstm.setString(1,"%"+motif+"%");
        //pstm.setString(2,"%"+motif+"%");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            Materiel e=new Materiel();
            e.setId(rs.getInt(1));
            e.setIntitule(rs.getString(2));
          
            e.setStatut(rs.getString(3));
            materiel.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return materiel;
}
public Materiel getMaterielById(int motif) {
	Materiel e=new Materiel();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from materiel where  id=? ");
        pstm.setInt(1,motif);
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
           
            e.setId(rs.getInt(1));
            e.setIntitule(rs.getString(2));
          
            e.setStatut(rs.getString(3));
        
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return e;
}
public List<Materiel> getAllMateriels() {
	List<Materiel> materiel=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from materiel  ");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            Materiel e=new Materiel();
            e.setId(rs.getInt(1));
            e.setIntitule(rs.getString(2));
          
            e.setStatut(rs.getString(3));
            materiel.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return materiel;
}
















public void addMach(Machine m) {

    try {
        PreparedStatement pstm=connexion.prepareStatement("insert into machine(nom,statut) values(?,?)");
        pstm.setString(1,m.getNom());
       
        pstm.setString(2,"disponible");
       pstm.executeUpdate();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
public void editMach(Machine m) {
	try {
	PreparedStatement pstm = connexion.prepareStatement("update machine set nom=?,statut=? where id = ?");
	pstm.setString(1, m.getNom());
	pstm.setString(2, m.getStatut());
	pstm.setInt(3, m.getId());
	
	pstm.executeUpdate();
	}catch(Exception ex) {
		ex.printStackTrace();
	}
}
public void deleteMach(Machine m) {
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from machine where id=?");
		pstm.setInt(1, m.getId());
		
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
}
public List<Machine> getMach(String motif) {
	List<Machine> machine=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from machine where  nom like ? ");
        pstm.setString(1,"%"+motif+"%");
        //pstm.setString(2,"%"+motif+"%");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            Machine e=new Machine();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
          
            e.setStatut(rs.getString(3));
            machine.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return machine;
}
public Machine getMachById(int motif) {
	Machine e=new Machine();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from machine where  id=? ");
        pstm.setInt(1,motif);
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
          
            e.setStatut(rs.getString(3));
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return e;
}
public List<Machine> getAllMachines() {
	List<Machine> machine=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from machine  ");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
            Machine e=new Machine();
            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
          
            e.setStatut(rs.getString(3));
            machine.add(e);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return machine;
}

















public void addIntervention(Intervention inter) {
int id=0;
    try {
    	
        PreparedStatement pstm=connexion.prepareStatement("insert into intervention(nom,date,statut,machine) values(?,?,?,?)");
        pstm.setString(1,inter.getNom());
        pstm.setDate(2,inter.getDate());
        pstm.setString(3,inter.getStatut());
        pstm.setInt(4,inter.getMachine().getId());
        PreparedStatement pstmMach=connexion.prepareStatement("update machine set statut = ? where id=?");
        if(!(inter.getStatut()=="en cours")) {
        	 pstmMach.setString(1,"en panne");
        pstmMach.setInt(2,inter.getMachine().getId());
        }else {
        	 pstmMach.setString(1,"disponible");
             pstmMach.setInt(2,inter.getMachine().getId());
        }
        pstmMach.executeUpdate();
        pstm.executeUpdate();
        PreparedStatement pstmid=connexion.prepareStatement("select id from intervention where nom=? and date=? and machine=? ");
        pstmid.setString(1,inter.getNom());
        pstmid.setDate(2,inter.getDate());
        pstmid.setInt(3,inter.getMachine().getId());
        pstmid.executeQuery();
        ResultSet r = pstmid.getResultSet();
        while(r.next()) {
        	id=r.getInt(1);
        }
       for(Employe e : inter.getEmployes()) {
    	   PreparedStatement pstm2=connexion.prepareStatement("insert into intervenir(id_employe,id_intervention) values(?,?)");
           pstm2.setInt(1,e.getId());
           pstm2.setInt(2,id);
           pstm2.executeUpdate();
           PreparedStatement pstmEmp=connexion.prepareStatement("update employe set statut =? where id =?");
           if(!(inter.getStatut()=="en cours")) {
         	  pstmEmp.setString(1,"en service");
           pstmEmp.setInt(2,e.getId());
           }else {
         	  pstmEmp.setString(1,"disponible");
               pstmEmp.setInt(2,e.getId());
           }
           
           pstmEmp.executeUpdate();
       }
       for(Materiel m : inter.getMateriels()) {
    	   PreparedStatement pstm2=connexion.prepareStatement("insert into int_materiel(id_intervention,id_materiel) values(?,?)");
           pstm2.setInt(1,id);
           pstm2.setInt(2,m.getId());
           pstm2.executeUpdate();
           PreparedStatement pstmMat=connexion.prepareStatement("update Materiel set statut =? where id =?");
           if(!(inter.getStatut()=="en cours")) {
         	  pstmMat.setString(1,"en service");
           pstmMat.setInt(2,m.getId());
           }else {
         	  pstmMat.setString(1,"disponible");
               pstmMat.setInt(2,m.getId());
           }
           pstmMat.executeUpdate();
       }
 
       
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}



public void editIntervention(Intervention inter) {
	try {
        PreparedStatement pstm=connexion.prepareStatement("update intervention set nom=?,date=?,statut=?,machine=? where id=?");
        pstm.setString(1,inter.getNom());
        pstm.setDate(2,inter.getDate());
        pstm.setString(3,inter.getStatut());
        pstm.setInt(4,inter.getMachine().getId());
        PreparedStatement pstmMach=connexion.prepareStatement("update machine set statut = ? where id=?");
        if(!(inter.getStatut()=="en cours")) {
        	System.out.print(inter.getStatut());
        	 pstmMach.setString(1,"en panne");
        pstmMach.setInt(2,inter.getMachine().getId());
        pstmMach.executeUpdate();
        }else {
        	 pstmMach.setString(1,"disponible");
             pstmMach.setInt(2,inter.getMachine().getId());
             pstmMach.executeUpdate();
        }
       
        
        pstm.setInt(5, inter.getId());
        pstm.executeUpdate();
       for(Employe e : inter.getEmployes()) {
    	   PreparedStatement test=connexion.prepareStatement("select * from intervenir where id_employe=? and id_intervention=? ");
           test.setInt(1,e.getId());
           test.setInt(2,inter.getId());
           test.executeQuery();
          if(!test.getResultSet().next()) {
        	 PreparedStatement pstm2=connexion.prepareStatement("insert into intervenir(id_employe,id_intervention) values(?,?)");
              pstm2.setInt(1,e.getId());
              pstm2.setInt(2,inter.getId());
              pstm2.executeUpdate();}
              PreparedStatement pstmEmp=connexion.prepareStatement("update employe set statut =? where id =?");
              if(inter.getStatut().equals("en cours")) {
            	  pstmEmp.setString(1,"en service");
              pstmEmp.setInt(2,e.getId());
              }else {
            	  pstmEmp.setString(1,"disponible");
                  pstmEmp.setInt(2,e.getId());
              }
              
              pstmEmp.executeUpdate();
            
          
     }
       for(Materiel m : inter.getMateriels()) {
    	   PreparedStatement test=connexion.prepareStatement("select * from int_materiel where id_materiel=? and id_intervention=?");
           test.setInt(1,m.getId());
           test.setInt(2,inter.getId());
           test.executeQuery();
          
           if(!test.getResultSet().next()) {
        	   PreparedStatement pstm2=connexion.prepareStatement("insert into int_materiel(id_intervention,id_materiel) values(?,?)");
               pstm2.setInt(1,inter.getId());
               pstm2.setInt(2,m.getId());
               pstm2.executeUpdate();
           }
               PreparedStatement pstmMat=connexion.prepareStatement("update Materiel set statut =? where id =?");
               if(inter.getStatut().equals("en cours")) {
             	  pstmMat.setString(1,"en service");
               pstmMat.setInt(2,m.getId());
               }else {
             	  pstmMat.setString(1,"disponible");
                   pstmMat.setInt(2,m.getId());
               }
               pstmMat.executeUpdate();
           
    }
 
      
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

public void deleteIntervention(Intervention inter) {
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from intervention where id=?");
		pstm.setInt(1, inter.getId());
		
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from intervenir where id_intervention=?");
		pstm.setInt(1, inter.getId());
		
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from int_materiel where id_intervention=?");
		pstm.setInt(1, inter.getId());
		
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
}
public void deleteFromIntervenir(Intervention inter,Employe e) {
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from intervenir where id_employe=? and id_intervention=?");
		pstm.setInt(2, inter.getId());
		pstm.setInt(1, e.getId());
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
}
public void deleteFromIntMat(Intervention inter,Materiel e) {
	try {
		PreparedStatement pstm = connexion.prepareStatement("delete from int_materiel where id_Materiel=? and id_intervention=?");
		pstm.setInt(2, inter.getId());
		pstm.setInt(1, e.getId());
		pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
}

public List<Employe> getEmployesOfIntervention(int id) {
	List<Employe> employes=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervenir where  id_intervention=? ");
        pstm.setInt(1,id);
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	
          employes.add(getEmpById(rs.getInt(1))) ;
          }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employes;
}
public List<Materiel> getMaterielsOfIntervention(int id) {
	List<Materiel> materiel=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from int_materiel where  id_intervention=? ");
        pstm.setInt(1,id);
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	
        	materiel.add(getMaterielById(rs.getInt(2))) ;
          }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return materiel;
}
public List<Intervention> getInterventionByNom(String motif) {
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where  nom like ? ");
        pstm.setString(1,"%"+motif+"%");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            try {
            	 PreparedStatement pstm2=connexion.prepareStatement("select * from machine where  id=? ");
                 pstm2.setInt(1,rs.getInt(5));
                 pstm2.executeQuery();
                 ResultSet r= pstm2.getResultSet();
                 while(r.next()) {
                	 Machine m = new Machine(r.getInt(1),r.getString(2),r.getString(3));
                	 
                	 itv.setMachine(m);
                 }
            }catch(Exception e) {
            	e.printStackTrace();
            }
            itv.setEmployes(getEmployesOfIntervention(rs.getInt(1)));
            itv.setMateriels(getMaterielsOfIntervention(rs.getInt(1)));
            intervention.add(itv);
            
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return intervention;
}



public List<Intervention> getInterventionById(int motif) {
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where  id=? ");
        pstm.setInt(1,motif);
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            try {
            	 PreparedStatement pstm2=connexion.prepareStatement("select * from machine where  id=? ");
                 pstm2.setInt(1,rs.getInt(5));
                 pstm2.executeQuery();
                 ResultSet r= pstm2.getResultSet();
                 while(r.next()) {
                	 Machine m = new Machine(r.getInt(1),r.getString(2),r.getString(3));
                	 
                	 itv.setMachine(m);
                 }
            }catch(Exception e) {
            	e.printStackTrace();
            }
            itv.setEmployes(getEmployesOfIntervention(rs.getInt(1)));
            itv.setMateriels(getMaterielsOfIntervention(rs.getInt(1)));
            intervention.add(itv);
            
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return intervention;
}




public List<Intervention> getAllIntervention() {
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention  ");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            try {
            	 PreparedStatement pstm2=connexion.prepareStatement("select * from machine where  id=? ");
                 pstm2.setInt(1,rs.getInt(5));
                 pstm2.executeQuery();
                 ResultSet r= pstm2.getResultSet();
                 while(r.next()) {
                	 Machine m = new Machine(r.getInt(1),r.getString(2),r.getString(3));
                	 
                	 itv.setMachine(m);
                 }
            }catch(Exception e) {
            	e.printStackTrace();
            }
            itv.setEmployes(getEmployesOfIntervention(rs.getInt(1)));
            itv.setMateriels(getMaterielsOfIntervention(rs.getInt(1)));
            intervention.add(itv);
            
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return intervention;
}
public List<Intervention> getInterventionOfEmployeFinis(Employe e){
	List<Intervention>intervention=new ArrayList<>();
    try {
    	  PreparedStatement pstm=connexion.prepareStatement("select * from intervention where statut=? and  id in ( select id_intervention from intervenir where id_employe=?) ");
          pstm.setInt(2,e.getId());
          pstm.setString(1,"terminé");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            intervention.add(itv);
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}

public List<Intervention> getInterventionOfEmployeEnCours(Employe e){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where statut=? and  id in ( select id_intervention from intervenir where id_employe=?) ");
        pstm.setInt(2,e.getId());
        pstm.setString(1,"en cours");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            intervention.add(itv);
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}

public List<Intervention> getInterventionOfEmploye(Employe e){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where  id in ( select id_intervention from intervenir where id_employe=?) ");
        pstm.setInt(1,e.getId());
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            intervention.add(itv);
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}

public List<Intervention> getInterventionOfEmployeByNom(Employe e,String motif){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where nom like ? and  id in ( select id_intervention from intervenir where id_employe=?) ");
        pstm.setString(1,"%"+motif+"%");
        pstm.setInt(2,e.getId());
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            intervention.add(itv);
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}

public List<Intervention> getInterventionOfMachine(Machine e){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where  machine= ? ");
        pstm.setInt(1,e.getId());
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            itv.setEmployes(getEmployesOfIntervention(rs.getInt(1)));
            itv.setMateriels(getMaterielsOfIntervention(rs.getInt(1)));
            intervention.add(itv);
            
        }
        
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}


public List<Intervention> getInterventionOfMachineByNom(Machine e,String nom){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where  machine= ? and nom like ?");
        pstm.setInt(1,e.getId());
        pstm.setString(2,"%"+nom+"%");
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            itv.setEmployes(getEmployesOfIntervention(rs.getInt(1)));
            itv.setMateriels(getMaterielsOfIntervention(rs.getInt(1)));
            intervention.add(itv);
            
        }
        
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}

public List<Intervention> getInterventionOfMateriel(Materiel e){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where  id in ( select id_intervention from int_materiel where id_materiel=?) ");
        pstm.setInt(1,e.getId());
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            intervention.add(itv);
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}
public List<Intervention> getInterventionOfMaterielByNom(Materiel e,String nom){
	List<Intervention>intervention=new ArrayList<>();
    try {
        PreparedStatement pstm=connexion.prepareStatement("select * from intervention where nom like ? and  id in ( select id_intervention from int_materiel where id_materiel=?) ");
        pstm.setString(1,"%"+nom+"%");
        pstm.setInt(2,e.getId());
        pstm.executeQuery();
        ResultSet rs= pstm.getResultSet();
        while (rs.next()){
        	Intervention itv=new Intervention();
        	itv.setId(rs.getInt(1));
            itv.setNom(rs.getString(2));
            itv.setDate(rs.getDate(3));
            itv.setStatut(rs.getString(4));
            itv.setMachine( getMachById(rs.getInt(5)));
            intervention.add(itv);
            
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return intervention;
}

public XYChart.Series getSerieInterventions() {
	 final CategoryAxis xAxis = new CategoryAxis();
     final NumberAxis yAxis = new NumberAxis();
  
     XYChart.Series series= new XYChart.Series();
     series.setName("April");
     try {
    	 PreparedStatement pstm=connexion.prepareStatement("select count(*),date_format(date, '%m-%Y') from intervention group by date_format(date,'%m-%Y') order by date_format(date,'%Y-%m')");
     pstm.executeQuery();
     ResultSet rs= pstm.getResultSet();
     while(rs.next()) {
    	series.getData().add(new XYChart.Data(rs.getString(2).toString(),rs.getInt(1) )); 
     }
     }catch(Exception ex) {
    	 ex.printStackTrace();
     }
     
     return series;
}


public XYChart.Series getSerieOfPeriod(Date depart ,Date fin) {
	 final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
   
    XYChart.Series serie= new XYChart.Series();
    serie.setName("April");
    try {
   	 PreparedStatement pstm=connexion.prepareStatement("select count(*),date_format(date, '%m-%Y') from intervention where date between ? and ? group by date_format(date,'%m-%Y') order by date_format(date,'%Y-%m')");
    pstm.setDate(1, depart);
    pstm.setDate(2, fin);
   	 pstm.executeQuery();
    ResultSet rs= pstm.getResultSet();
    while(rs.next()) {
   	serie.getData().add(new XYChart.Data(rs.getString(2).toString(),rs.getInt(1) )); 
    }
    
    }catch(Exception ex) {
   	 ex.printStackTrace();
    }
    
    return serie;
}



public XYChart.Series getSerieOfMachine(Machine machine) {
	 final CategoryAxis xAxis = new CategoryAxis();
   final NumberAxis yAxis = new NumberAxis(1, 15, 1);
  yAxis.setTickMarkVisible(false);
   XYChart.Series serie= new XYChart.Series();
   serie.setName("April");
   try {
  	 PreparedStatement pstm=connexion.prepareStatement("select count(*),date_format(date, '%m-%Y') from intervention where machine=? group by date_format(date,'%m-%Y') order by date_format(date,'%Y-%m')");
   pstm.setInt(1, machine.getId());
  	 pstm.executeQuery();
   ResultSet rs= pstm.getResultSet();
   while(rs.next()) {
  	serie.getData().add(new XYChart.Data(rs.getString(2).toString(),rs.getInt(1) )); 
   }
   
   }catch(Exception ex) {
  	 ex.printStackTrace();
   }
   
   return serie;
}




public XYChart.Series getSerieOfMachineInPeriod(Machine machine,Date debut,Date fin) {
	 final CategoryAxis xxis = new CategoryAxis();
	   final NumberAxis yAxis = new NumberAxis(1, 15, 1);
  XYChart.Series serie= new XYChart.Series();
  serie.setName("April");
  try {
 	 PreparedStatement pstm=connexion.prepareStatement("select count(*),date_format(date,'%m-%Y') from intervention where machine=? and date between ? and ? group by date_format(date,'%m-%Y') order by date_format(date,'%Y-%m')");
  pstm.setInt(1, machine.getId());
  pstm.setDate(2, debut);
  pstm.setDate(3, fin);
 	 pstm.executeQuery();
  ResultSet rs= pstm.getResultSet();
  while(rs.next()) {
 	serie.getData().add(new XYChart.Data(rs.getString(2).toString(),rs.getInt(1) )); 
  }
  
  }catch(Exception ex) {
 	 ex.printStackTrace();
  }
  
  return serie;
}


}
