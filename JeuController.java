/**
 * 
 */
package info;

import java.util.Scanner;

/**
 * @author louis & philemon & justin
 *
 */
public class JeuController {

	Jeu jeu;
	JeuVue jVue;
	/**
	 * constructeur.
	 */
	public JeuController(Jeu jeu) {
		this.jeu = jeu;
	}

	/**
	 * appel la methode adequate de jeu pour afficher les differents menus.
	 * @param num numero de l etape qui determine le menu
	 * @param joueur joueur qui doit jouer
	 */
	public void menu(int num, int joueur) {
		switch(num){
		case 0 :
			//jVue.affiche("Voulez-vous vraiment quitter ? y or n");
			jeu.printMenuText(2, joueur);
			//System.exit(0);
			break;
		case 1 :
			jeu.printMenuText(2, joueur);
			break;
		case 2 : 
			System.exit(0);
			//jControl.methode(i);
			break;
		default : 
			jVue.affiche("Operation incorrecte");
			//printHelp();
		}
	}
	
	/**
	 * appel de l affichage du choix des personnage.
	 * @param i etape du jeu.
	 * @param joueur = joueur qui doit jouer
	 */
	public void printTextMenu(int i, int joueur){
		if (i == 1) {
			jeu.printMenuText(1, joueur);
			
		}
		else if (i == 2) {
			jeu.printMenuText(2, joueur);
		}
	}
	/**
	 * lance le choix du personnage de jeu.
	 * @param i choix du joueur
	 */
	public void choixPersonnage(int i) {
		switch(i){
			case 1 :
				jeu.choixPerso(1);
				jVue.affiche("Personnage choisi : Elfe !\n");
				break;
			case 2 : 
				jeu.choixPerso(2);
				jVue.affiche("Personnage choisi : Nain !\n");
				break;
			case 3 : 
				jeu.choixPerso(3);
				jVue.affiche("Personnage choisi : Orque !\n");
				break;
			case 4 : 
				jeu.choixPerso(4);
				jVue.affiche("Personnage choisi : Humain !\n");
				break;
			default :
				jeu.choixPerso(4);
				jVue.affiche("Personnage par defaut choisi : Humain !\n");
		}
	}

	/**
	 * cree les differents donjons.
	 */
	public void creationDonjons() {
		jeu.creationDonjons();
		int num = jeu.getDonjonNum();
		if (num < 5) {
			jVue.affiche("---------- Cr�ation du donjon " + jeu.getDonjonNum() + " ... ----------");
			jVue.affiche("---------- Cr�ation des 3 vagues d'ennemis ... ----------\n");
			histoire(num);
		}
		if (num == 5) {
			jVue.affiche("---------- Cr�ation du donjon 5 ... ----------");
			jVue.affiche("---------- Cr�ation des 2 vagues d'ennemis ... ----------");
			jVue.affiche("---------- Cr�ation du boss ... ----------\n");
		}
	}
	/**
	 * gere le combat.
	 * @param vagueNum numero de vague de l ennemi.
	 * @param choixMob ennemi cible
	 * @param joueurNum numero du joueur qui joue.
	 */
	public void combat(int vagueNum,int choixMob, int joueurNum) {
		String s = jeu.combat(vagueNum, choixMob, joueurNum);
		if (s.compareTo("mort") == 0) {
			int xp = jeu.xpRecu(vagueNum, choixMob);
			jVue.affiche("Votre coup a tu� votre ennemi. Vous gagnez " + xp +" xp.\n");
			/* pour debug
			 * Integer no = new Integer(jeu.getJoueur().get(0).getXp());
			Integer niv = new Integer(jeu.getJoueur().get(0).getNiveau());
			jVue.affiche("xp : " + no.toString());
			jVue.affiche("niveau : " + niv.toString());*/
			if (jeu.checkVagueClean(vagueNum) && (jeu.getDonj().getBoss() == null)) {
				jVue.affiche("Pi�ce clean. Vous avez ann�anti les forces ennemies qui entravaient votre chemin.");
				jVue.affiche("Vous passez donc dans la pi�ce suivante !\n");
			}
		}
		else {
			Hero h = jeu.getJoueur().get(joueurNum-1);
			Ennemi e;
			if(jeu.getDonj().getBoss() == null || vagueNum != 3) {
				e = jeu.getDonj().getPopVague(vagueNum)[choixMob-1];
			}else {
				e = jeu.getDonj().getBoss();
			}
			int degatPrimaire =  h.getForce() + h.getNiveau();
			int degat = (h.getArmeDroite().getDegat() + degatPrimaire) * h.getVitesseAttaque();
			jVue.affiche("Votre ennemi n'a pas succomb� � votre attaque mais vous lui avez inflig� " + degat +" points de degats !\n Il lui reste : "+e.getVie()+" PV \n");
		}
	}
	/**
	 * affiche la vague d ennemi present.
	 * @param vagueNum numero de la vague actuelle.
	 */
	public void afficheVague(int vagueNum) {
		jVue.affiche("Quel ennemi allez vous attaquer ?");
		jeu.afficheVague(vagueNum);
	}
	/**
	 * affiche le boss du dernier donjon.
	 */
	public void afficheChoixBoss() {
		jVue.affiche("Attaquez ce boss !");
		jeu.afficheChoixBoss();
	}
	/**
	 * affiche un petit texte d intro pour chaque donjon.
	 * @param num numero du donjon.
	 */
	public void histoire(int num) {
		if (num == 1) {
			jVue.affiche("Vous arrivez face � votre premier donjon. Serez-vous assez fort que pour vaincre ceux qui l'habite ?");
			jVue.affiche("Vous poussez la porte d'entr�e et arrivez dans une premi�re salle.\n");
		}
		if (num == 2) {
			jVue.affiche("Vous avez vaincu votre premier donjon. Mais ne vous reposez pas, le suivant vous attend !");
			jVue.affiche("A peine pass� la porte, des ennemis vous rep�rent. BASTON !\n");
		}
		if (num == 3) {jVue.affiche("toto1");}	// TODO 
		if (num == 4) {jVue.affiche("toto2");}	// TODO 
		if (num == 5) {jVue.affiche("toto3");}	// TODO 
	}
	
	/**
	 * gere le tour d un ennemi.
	 * @param vague numero de la vague de l ennemi qui joue.
	 */
	public void tourMob(int vague) {
		if (!jeu.checkVagueClean(vague)) {
			String etatJoueur = jeu.combatMob(vague);
			if (etatJoueur.compareTo("mort") == 0) {
				jVue.affiche("Vous n'avez pas �t� assez r�sistant, joueur " + jeu.getJoueurMort() + " ! Vous etes mort dans d'attroces souffrances... \n");
				jeu.mortDUnJoueur();
			}
		}
	}
	/* plus necessaire, meme code que checkvagueclean()
	 * public boolean encoreMob(int vague) {
		for (Ennemi e : jeu.getDonj().getPopVague(vague)) {
			if (e.getEtat().compareTo("vivant") == 0) {
				return true;
			}
		}
		return false;
	}*/
	/**
	 * gere le tour du boss.
	 */
	public void tourBoss() {
		if (jeu.getDonj().getBoss().getEtat().compareTo("vivant") == 0) {
			String etatJoueur = jeu.combatBoss();
			if (etatJoueur.compareTo("mort") == 0) {
				jVue.affiche("Vous n'avez pas �t� assez r�sistant, joueur " + jeu.getJoueurMort() + " ! Vous etes mort dans d'attroces souffrances... \n");
				jeu.mortDUnJoueur();
			}
		}
	}
	/**
	 * affiche la feuille de personnage du joueur choix.
	 * @param choix joueur demande
	 */
	public void afficheFeuillePersonnages(int choix) {
		Hero h = jeu.getJoueur().get(choix-1);
		System.out.println("Feuille de personnage du joueur " + choix + " : ");
		System.out.println("Classe : " + h.getClasse());
		System.out.println("Niveau : " + h.getNiveau());
		System.out.println("Force : " + h.getForce());
		System.out.println("Vie : " + h.getVie());
		System.out.println("Endurance : " + h.getEndurance());
		System.out.println("xp : " + h.getXp());
		System.out.println("Arme : " + h.getArmeDroite().getNom());
		System.out.println("Etat : " + h.getEtat());
		System.out.println("---------- Retour au combat ----------\n");
	}
	
	public void gestionLoot() {
		jVue.affiche("Gestion des Loot : ");
		jeu.gestionLoot();
		jVue.affiche("Gestion des Loot terminee \n");
	}
	
	public void addView(JeuVue jVue) {
		this.jVue = jVue;
		
	}
}
