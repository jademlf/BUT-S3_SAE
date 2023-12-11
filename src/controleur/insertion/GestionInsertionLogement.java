package controleur.insertion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vue.Fenetre_Accueil;
import vue.insertion.Fenetre_InsertionColocataire;
import vue.insertion.Fenetre_InsertionCompteur;
import vue.insertion.Fenetre_InsertionLocation;
import vue.insertion.Fenetre_InsertionLogement;

public class GestionInsertionLogement implements ActionListener {

	private Fenetre_InsertionLogement fil;

	public GestionInsertionLogement(Fenetre_InsertionLogement fil) {
		this.fil = fil;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		Fenetre_Accueil fenetre_Principale = (Fenetre_Accueil) this.fil.getTopLevelAncestor(); // fenetre dans laquelle on ouvre des internal frame
		switch (btn.getText()) {
		case "Ajouter une quotité":
			
			break;
			
		case "Ajouter un compteur":
	
			break;
		
		case "Ajouter":
			// Ajouter le code pour gérer l'ajout de la location
			break;
		case "Annuler":
			this.fil.dispose();
			break;
		}
	}
}