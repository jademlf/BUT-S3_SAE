package vue.insertion;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controleur.GestionTableEntrepriseFenetreInsertionAssurance;
import controleur.insertion.GestionInsertionAssurance;

public class Fenetre_InsertionAssurance extends JInternalFrame {
	private JTextField textField_numPolice;
	private JTextField textField_montant;
	private GestionInsertionAssurance gestionClic;
	private JButton btn_ajouter_entreprise;
	private JButton btn_charger_entreprise;
	private JScrollPane scrollPane_table_entreprise;

	// Table pour afficher les données d'entreprise
	private JTable table_entreprise;

	private GestionTableEntrepriseFenetreInsertionAssurance gtefia;

	public Fenetre_InsertionAssurance() {
		// Initialisation du gestionnaire d'actions
		this.gestionClic = new GestionInsertionAssurance(this);
		this.gtefia = new GestionTableEntrepriseFenetreInsertionAssurance(this);

		// Configuration de la fenêtre interne
		this.setBounds(100, 100, 762, 541);
		this.getContentPane().setLayout(null);

		// Panneau principal
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 755, 511);
		this.getContentPane().add(panel);
		panel.setLayout(null);

		// Champs de saisie
		this.textField_numPolice = new JTextField();
		this.textField_numPolice.setBounds(135, 177, 163, 40);
		this.textField_numPolice.setColumns(10);
		this.textField_numPolice.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204)), "Numéro de police",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		panel.add(this.textField_numPolice);

		// Libellé "Assurance"
		JLabel lbl_InsertionAssurance = new JLabel("Assurance");
		lbl_InsertionAssurance.setBounds(302, 39, 163, 20);
		lbl_InsertionAssurance.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_InsertionAssurance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lbl_InsertionAssurance);

		// Champs de saisie
		this.textField_montant = new JTextField();
		this.textField_montant.setColumns(10);
		this.textField_montant.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204)), "Montant",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		this.textField_montant.setBounds(135, 246, 163, 40);
		panel.add(this.textField_montant);

		// Bouton "Ajouter"
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAjouter.setBackground(new Color(0, 102, 204));
		btnAjouter.setBounds(276, 431, 94, 31);
		btnAjouter.addActionListener(this.gestionClic);
		panel.add(btnAjouter);

		// Bouton "Annuler"
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setForeground(Color.WHITE);
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnnuler.setBackground(new Color(0, 102, 204));
		btnAnnuler.setBounds(428, 431, 94, 31);
		btnAnnuler.addActionListener(this.gestionClic);
		panel.add(btnAnnuler);

		// Séparateur
		JSeparator separator_Assurance = new JSeparator();
		separator_Assurance.setForeground(new Color(0, 102, 204));
		separator_Assurance.setBounds(302, 72, 190, 2);
		panel.add(separator_Assurance);

		// Séparateur vertical
		JSeparator separator_vertical = new JSeparator();
		separator_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_vertical.setForeground(new Color(0, 102, 204));
		separator_vertical.setBounds(385, 100, 15, 292);
		panel.add(separator_vertical);

		// Partie ENTREPRISE
		this.btn_ajouter_entreprise = new JButton("Insérer");
		this.btn_ajouter_entreprise.setForeground(Color.WHITE);
		this.btn_ajouter_entreprise.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.btn_ajouter_entreprise.setBackground(new Color(0, 102, 204));
		this.btn_ajouter_entreprise.setBounds(518, 328, 94, 30);
		this.btn_ajouter_entreprise.addActionListener(gestionClic);
		panel.add(this.btn_ajouter_entreprise);

		this.btn_charger_entreprise = new JButton("Charger");
		this.btn_charger_entreprise.setForeground(Color.WHITE);
		this.btn_charger_entreprise.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.btn_charger_entreprise.setBackground(new Color(0, 102, 204));
		this.btn_charger_entreprise.setBounds(412, 328, 94, 30);
		this.btn_charger_entreprise.addActionListener(gestionClic);
		panel.add(this.btn_charger_entreprise);

		this.scrollPane_table_entreprise = new JScrollPane();
		this.scrollPane_table_entreprise.setBorder(new LineBorder(new Color(0, 102, 204), 1, true));
		this.scrollPane_table_entreprise.setBounds(443, 196, 247, 97);
		panel.add(this.scrollPane_table_entreprise);

		// Table pour afficher les données d'entreprise
		this.table_entreprise = new JTable();
		this.table_entreprise.setSelectionBackground(new Color(0, 102, 204));
		this.table_entreprise
				.setModel(new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "SIRET", "Nom" }));
		this.table_entreprise.setBounds(499, 80, 135, 16);
		scrollPane_table_entreprise.setViewportView(this.table_entreprise);
		this.table_entreprise.getSelectionModel().addListSelectionListener(this.gtefia);

		JLabel lbl_Entreprise = new JLabel("Entreprise");
		lbl_Entreprise.setForeground(Color.BLACK);
		lbl_Entreprise.setBackground(new Color(0, 102, 204));
		lbl_Entreprise.setBounds(529, 143, 132, 31);
		panel.add(lbl_Entreprise);

		JButton btn_modifier_entreprise = new JButton("Modifier");
		btn_modifier_entreprise.setForeground(Color.WHITE);
		btn_modifier_entreprise.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_modifier_entreprise.setBackground(new Color(0, 102, 204));
		btn_modifier_entreprise.setBounds(624, 329, 94, 30);
		panel.add(btn_modifier_entreprise);
	}

	// Getters pour les champs de saisie
	public JTextField getTextField_numPolice() {
		return this.textField_numPolice;
	}

	public JTextField getTextField_montant() {
		return this.textField_montant;
	}

	public JButton getBtn_ajouter_entreprise() {
		return btn_ajouter_entreprise;
	}

	public JButton getBtn_charger_entreprise() {
		return btn_charger_entreprise;
	}

	public JScrollPane getScrollPane_table_entreprise() {
		return scrollPane_table_entreprise;
	}

	public JTable getTable_entreprise() {
		return table_entreprise;
	}
}
