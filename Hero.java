/**
 * 
 */
package info;
import java.awt.Component;

import java.io.*;
import java.lang.*;
import java.util.*;

import javax.swing.Icon;
import javax.swing.JOptionPane;


/**
 * @author Louis & Justin & Philemon
 * date 18/10/17
 * @version 0.1
 *
 */
public class Hero {
	
	public static final int forceElfle = 4;
	public static final int enduranceElfle = 4;
	public static final int vitesseAttaqueElfe = 1;
	public static final int forceNain = 3;
	public static final int enduranceNain = 6;
	public static final int vitesseAttaqueNain = 1;
	public static final int forceOrque = 1;
	public static final int enduranceOrque = 8;
	public static final int vitesseAttaqueOrque = 1;
	public static final int forceHumain = 5;
	public static final int enduranceHumain = 3;
	public static final int vitesseAttaqueHumain = 1;
	public static final int tailleInventaireArmeBase = 2;
	public static final int degatFourchette = 1;
	public static final int degatEpee = 3;
	public static final int degatHachette = 2;
	public static final int degatArc = 3;
	public static final int degatMasse = 3;
	public static final String ptVie = " points de vie !";
	
	private static Icon icon;
	
	private String classe;
	private int vie;
	private int force;
	private int endurance;
	private int niveau;
	private String armeDroite;
	private int vitesseAttaque;
	private ArrayList<String> inventaireArme = new ArrayList<String>();
	public int tailleInventaireArme;
	

	/**
	 * constructeur.
	 */
	public Hero() {
		this.classe = "euh... j'ai pas choisi de classe moi !!";
		this.force = 2;
		this.endurance = enduranceHumain;
		this.vie = 2 * this.endurance;
		this.armeDroite = "fourchette";
		this.vitesseAttaque = 1;
		this.niveau = 1;
		this.inventaireArme.add("fourchette");
		this.tailleInventaireArme = 1;
	}
	/**
	 * constructeur 2.
	 * @param selectHero est le hero selectionne par le player
	 */
	public Hero(String selectHero) {
		switch(selectHero) {
			case "Elfe" : 
				System.out.println("je suis un Elfle !!");
				this.classe = "Elfe";
				this.niveau = 1;
				this.endurance = enduranceElfle;
				this.vie = 2 * this.endurance;
				this.force = forceElfle;
				this.armeDroite = "arc";
				this.vitesseAttaque = vitesseAttaqueElfe;
				this.inventaireArme.add("arc");
				this.inventaireArme.add("epee");
				System.out.println(this.inventaireArme);
				this.tailleInventaireArme = tailleInventaireArmeBase;
				break;
			case "Nain" : 
				System.out.println("je suis un Nain !!");
				this.classe = "Nain";
				this.niveau = 1;
				this.endurance = enduranceNain;
				this.vie = 2 * this.endurance;
				this.force = forceNain;
				this.armeDroite = "hachette";
				this.vitesseAttaque = vitesseAttaqueNain;
				this.tailleInventaireArme = tailleInventaireArmeBase;
				break;
			case "Orque" : 
				System.out.println("je suis un Orque !!");
				this.classe = "Orque";
				this.niveau = 1;
				this.endurance = enduranceOrque;
				this.vie = 2 * this.endurance;
				this.force = forceOrque;
				this.armeDroite = "masse";
				this.vitesseAttaque = vitesseAttaqueOrque;
				this.tailleInventaireArme = tailleInventaireArmeBase;
				break;
			case "Humain" : 
				System.out.println("je suis un Humain !!");
				this.classe = "Humain";
				this.niveau = 1;
				this.endurance = enduranceHumain;
				this.vie = 2 * this.endurance;
				this.force = forceHumain;
				this.armeDroite = "epee";
				this.vitesseAttaque = vitesseAttaqueHumain;
				this.tailleInventaireArme = tailleInventaireArmeBase;
				break;
			default : 
				System.out.println("je suis un ... heu... j'ai pas choisi !!");
				this.niveau = 1;
				this.force = forceHumain;
				this.endurance = enduranceHumain;
				this.vie = 2 * this.endurance;
				this.armeDroite = "defaut";
				this.vitesseAttaque = 1;
				this.tailleInventaireArme = tailleInventaireArmeBase;
				break;
		}
	}

	private static Icon getIcon() {
		return icon;
	}
	private static void setIcon(Icon icon) {
		Hero.icon = icon;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public int getVie() {
		return vie;
	}
	public void setVie(int vie) {
		this.vie = vie;
	}
	public int getForce() {
		return force;
	}
	public void setForce(int force) {
		this.force = force;
	}
	public int getEndurance() {
		return endurance;
	}
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	public String getArmeDroite() {
		return armeDroite;
	}
	public void setArmeDroite(String armeDroite) {
		this.armeDroite = armeDroite;
	}
	public String getClasse() {
		return this.classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public int getVitesseAttaque() {
		return vitesseAttaque;
	}
	public void setVitesseAttaque(int vitesseAttaque) {
		this.vitesseAttaque = vitesseAttaque;
	}
	public ArrayList<String> getInventaireArme() {
		return inventaireArme;
	}
	public void setInventaireArme(ArrayList<String> inventaireArme) {
		for (int i = 0; i < inventaireArme.size(); i++) {
			this.inventaireArme.add(inventaireArme.get(i));
		}
	}
	public int getTailleInventaireArme() {
		return tailleInventaireArme;
	}
	public void setTailleInventaireArme(int tailleInventaireArme) {
		this.tailleInventaireArme = tailleInventaireArme;
	}
	/**
	 * 
	 * @return une phrase immersive comprennant les degats applique.
	 */
	public String attaque() {
		int degatPrimaire =  this.force + this.niveau;
		int degat = 0;
		int vitesseDAttaque = this.vitesseAttaque;
		String s = null;
		switch(this.armeDroite) {
		case "fourchette" : 
			degat = (degatFourchette + degatPrimaire) * vitesseDAttaque;
			s = "TCHIC ! Tu perds " + degat + ptVie;
			break;
		case "epee" : 
			degat = (degatEpee + degatPrimaire) * vitesseDAttaque;
			s = "DING ! Tu perds " + degat + ptVie;
			break;
		case "arc" : 
			degat = (degatArc + degatPrimaire) * vitesseDAttaque;
			s = "FUUIT ! Tu perds " + degat + ptVie;
			break;
		case "hachette" : 
			degat = (degatHachette + degatPrimaire) * vitesseDAttaque;
			s = "TCHING ! Tu perds " + degat + ptVie;
			break;
		case "masse" : 
			degat = (degatMasse + degatPrimaire) * vitesseDAttaque;
			s = "BOUM ! Tu perds " + degat + ptVie;
			break;
		default : 
			degat = degatPrimaire * vitesseDAttaque;
			s = "Tu perds " + degat + ptVie;
			break;
		}
		return s;
	}
	
	public void ramasser(ArrayList<String> drop) {
		Object[] possibilities = {drop.get(0), drop.get(1)};
		Component frame = null;
		String s = (String) JOptionPane.showInputDialog(
		                    frame,
		                    "Choose your loot :\n",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    "oui");
		if ((s != null) && (s.length() > 0)) {
		    //System.out.println(s);
		    System.out.println("loot choisi : " + s);
		    this.setInventaireArme(drop);
		    System.out.println(this.getInventaireArme());
		    return;
		}
	}
	
	/**
	 * @param args .
	 */
	public static void main(String[] args) {
		Object[] possibilities = {"Elfe", "Nain", "Orque", "Humain"};
		Component frame = null;
		String s = (String) JOptionPane.showInputDialog(
		                    frame,
		                    "Choose your hero :\n",
		                    "Customized Dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    icon,
		                    possibilities,
		                    "oui");

		//If a string is choose, say what hero player choose.
		if ((s != null) && (s.length() > 0)) {
		    //System.out.println(s);
		    Hero h = new Hero(s);
		    System.out.println(h.attaque());
		    ArrayList<String> drop = new ArrayList<String>();
		    drop.add("epee");
		    drop.add("gourde");
		    h.ramasser(drop);
		    return;
		}

		//If you're here, the return value was null/empty.
		System.out.println("Come on, choose your Hero !");

	}

}
