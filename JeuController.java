/**
 * 
 */
package info;

import java.sql.SQLException;
import java.util.ArrayList;
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
			jeu.printMenuText(2, joueur);
			break;
		case 1 :
			jeu.printMenuText(2, joueur);
			break;
		case 2 : 
			jeu.chargerTbJoueur();
			jeu.chargerTbJeuModele();
			break;
		default : 
			jVue.affiche("Operation incorrecte");
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void creationDonjons(){
		jeu.creationDonjons();
		int num = jeu.getDonjonNum();
		if (num < 5) {
			jVue.affiche("---------- Creation du donjon " + jeu.getDonjonNum() + " ... ----------");
			jVue.affiche("---------- Creation des 3 vagues d'ennemis ... ----------\n");
			histoire(num);
		}
		if (num == 5) {
			jVue.affiche("---------- Cretion du donjon 5 ... ----------");
			jVue.affiche("---------- Creation des 2 vagues d'ennemis ... ----------");
			jVue.affiche("---------- Creation du boss ... ----------\n");
		}
	}
	/**
	 * gere le combat.
	 * @param vagueNum numero de vague de l ennemi.
	 * @param choixMob ennemi cible
	 * @param joueurNum numero du joueur qui joue.
	 * @return 1 si l ennemi est mort, 0 sinon.
	 */
	public int combat(int vagueNum,int choixMob, int joueurNum) {
		String s = jeu.combat(vagueNum, choixMob, joueurNum);
		if (s.compareTo("mort") == 0) {
			int xp = jeu.xpRecu(vagueNum, choixMob);
			jVue.affiche("Votre coup a tu� votre ennemi. Vous gagnez " + xp +" xp.\n");
			if (jeu.checkVagueClean(vagueNum) && (jeu.getDonj().getBoss() == null)) {
				jVue.affiche("Piece clean. Vous avez ann�anti les forces ennemies qui entravaient votre chemin.");
				jVue.affiche("Vous passez donc dans la piece suivante !\n");
			}
			return 1;
		}
		else {
			Hero h = jeu.getJoueur().get(joueurNum-1);
			Ennemi e =  new Ennemi();
			if(jeu.getDonj().getBoss() == null || vagueNum != 3) {
				e = jeu.getDonj().getPopVague(vagueNum)[choixMob-1];
			}
			if (jeu.getDonj().getBoss() != null && vagueNum == 0){
				e = jeu.getDonj().getBoss();
			}
			int degatPrimaire =  h.getForce() + h.getNiveau();
			int degat = (h.getArmeDroite().getDegat() + degatPrimaire) * h.getVitesseAttaque();
			jVue.affiche("Votre ennemi n'a pas succombe � votre attaque mais vous lui avez inflige " + degat +" points de degats !\n Il lui reste : "+ e.getVie()+" PV \n");
			return 0;
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
			jVue.affiche("Vous arrivez face a votre premier donjon. Serez-vous assez fort que pour vaincre ceux qui l'habite ?");
			jVue.affiche("Vous poussez la porte d'entree et arrivez dans une premi�re salle.\n");
		}
		if (num == 2) {
			jVue.affiche("Vous avez vaincu votre premier donjon. Mais ne vous reposez pas, le suivant vous attend !");
			jVue.affiche("A peine passe la porte du nouveau donjon, des ennemis vous rep�rent. BASTON !\n");
			
		}
		if (num == 3) {
			jVue.affiche("Vous avez vaincu votre deuxieme donjon. Mais ne vous reposez pas, le suivant vous attend !");
			jVue.affiche("A peine passe la porte du nouveau donjon, des ennemis vous rep�rent. BASTON !\n");
		}
		if (num == 4) {
			jVue.affiche("Vous avez vaincu votre troisieme donjon. Mais ne vous reposez pas, le suivant vous attend !");
			jVue.affiche("A peine passe la porte du nouveau donjon, des ennemis vous rep�rent. BASTON !\n");
		} 
		if (num == 5) {
			jVue.affiche("Vous avez vaincu votre quatrieme donjon. Serez-vous assez fort que pour vaincre le boss ultime de ce donjon ?");
		} 
	}
	
	/**
	 * gere le tour d un ennemi.
	 * @param vague numero de la vague de l ennemi qui joue.
	 */
	public void tourMob(int vague) {
		if (!jeu.checkVagueClean(vague)) {
			String etatJoueur = jeu.combatMob(vague);
			if (etatJoueur.compareTo("mort") == 0) {
				jVue.affiche("Vous n'avez pas ete assez resistant, joueur " + jeu.getJoueurMort() + " ! Vous etes mort dans d'attroces souffrances... \n");
				jeu.mortDUnJoueur();
			}
		}
	}
	/**
	 * gere le tour du boss.
	 */
	public void tourBoss() {
		if (jeu.getDonj().getBoss().getEtat().compareTo("vivant") == 0) {
			String etatJoueur = jeu.combatBoss();
			if (etatJoueur.compareTo("mort") == 0) {
				jVue.affiche("Vous n'avez pas �t� assez resistant, joueur " + jeu.getJoueurMort() + " ! Vous etes mort dans d'attroces souffrances... \n");
				jeu.mortDUnJoueur();
			}
		}
	}
	/**
	 * affiche la feuille de personnage du joueur choix.
	 * @param choix joueur demande
	 */
	public void afficheFeuillePersonnages(int choix) {
		jeu.afficheFeuillePerso(choix);
	}
	
	/**
	 * affiche le joueur qui doit jouer en console.
	 * @param i numero du joueur
	 */
	public void afficheTour(int i) {
		jVue.affiche("-- JOUEUR " + i + " : A vous d'attaquer --");
	}
	
	/**
	 * fait la gestion des loots apres une vague.
	 */
	public void gestionLoot(int vueNum) {
		jVue.affiche("Gestion des Loot : ");
		jeu.gestionLoot(vueNum);
		jVue.affiche("Gestion des Loot terminee \n");
	}
	/**
	 * incremente le numero du donjon.
	 */
	public void incrDonjonNum() {
		jeu.incDonjonNum();
	}
	/**
	 * ajoute une vue.
	 * @param jVue vue
	 */
	public void addView(JeuVue jVue) {
		this.jVue = jVue;
		
	}
}
