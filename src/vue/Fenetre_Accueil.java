package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controleur.GestionAccueil;
import controleur.GestionBienLogement;
import controleur.GestionLocations;
import controleur.GestionTableAssurance;
import controleur.GestionTableCharges;
import controleur.GestionTableLogement;
import controleur.GestionTableTravaux;
import modele.dao.DaoBien;
import modele.dao.DaoLocataire;

public class Fenetre_Accueil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredPane_Accueil;
	private JLayeredPane layeredPane_MesLocations;
	private JLayeredPane layeredPane_MesBiens;
	private JLayeredPane layeredPane_MesTravaux;
	private JLayeredPane layeredPane_MesFactures;
	private JLayeredPane layeredPane_MesAssurances;
	private JLayeredPane layeredPane_RegularisationDesCharges;
	private JLayeredPane layeredPane_MesDocuments;
	private JLayeredPane layeredPane_MesArchives;

	private JTable tableMesBiens;
	private JTable tableMesBiens_Logements;
	private JTable table_MesLocations;
	private JTable table_MesTravaux;
	private JTable table_MesFactures;
	private JTable table_MesAssurances;
	private JTable table_MesDocuments;

	private JTextField textField_loyer;
	private JTextField textField_provisionCharges;
	private JTextField textField_caution;
	private JTextField textField_dateEmission;
	private JTextField textField_datePaiement;
	private JTextField textField_paye;
	private JTextField textField_restantDu;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JTable tableRegularisation;

	private JComboBox<String> comboBox_MesAssurances;
	private JComboBox<String> comboBox_MesFactures;
	private JComboBox<String> comboBox_Regularisation;
	private JComboBox<String> comboBox_MesDocuments;

	private GestionAccueil gestionAccueil;

	// Getteur specifique utilisé dans le code pour faire une action de chargement
	// de la table
	public GestionAccueil getGestionAccueil() {
		return this.gestionAccueil;
	}

	private GestionBienLogement gestionBienLogement;
	private GestionTableLogement gestionTableLogement;
	private GestionTableCharges gestionTableCharges;
	private GestionLocations gestionLocations;

	private DaoBien daoBien;
	private DaoLocataire daoLocataire;
	private GestionTableTravaux gestionTableTravaux;
	private GestionTableAssurance gestionTableAssurance;

	/**
	 * Create the frame.
	 */
	public Fenetre_Accueil() {
		this.gestionTableLogement = new GestionTableLogement(this);
		this.gestionTableCharges = new GestionTableCharges(this);
		this.gestionBienLogement = new GestionBienLogement(this);
		this.gestionLocations = new GestionLocations(this);
		this.gestionAccueil = new GestionAccueil(this);
		this.gestionTableAssurance = new GestionTableAssurance(this);
		this.gestionTableTravaux = new GestionTableTravaux(this);

		this.daoBien = new DaoBien();
		this.daoLocataire = new DaoLocataire();

		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 970, 646);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel_Menu = new JPanel();
		panel_Menu.setBackground(Color.LIGHT_GRAY);
		this.contentPane.add(panel_Menu, BorderLayout.WEST);
		panel_Menu.setLayout(new BorderLayout(0, 0));

		JPanel panel_Menu_Boutons = new JPanel();
		panel_Menu_Boutons.setBackground(Color.LIGHT_GRAY);
		panel_Menu.add(panel_Menu_Boutons, BorderLayout.CENTER);
		panel_Menu_Boutons.setLayout(new GridLayout(8, 1, 0, 0));

		////// Bande accueil//////////////////////////////////////////////////
		JPanel bandeAccueil = new JPanel();
		bandeAccueil.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		bandeAccueil.setBackground(Color.LIGHT_GRAY);
		this.contentPane.add(bandeAccueil, BorderLayout.NORTH);
		bandeAccueil.setLayout(new GridLayout(0, 8, 0, 0));

		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);

		logo.setIcon(new ImageIcon(Fenetre_Accueil.class.getResource("/icon/logo_appli.png")));
		bandeAccueil.add(logo);
		JPanel panelDuBtnAccueil = new JPanel();
		panelDuBtnAccueil.setBackground(new Color(192, 192, 192));
		bandeAccueil.add(panelDuBtnAccueil);
		panelDuBtnAccueil.setLayout(null);

		JButton btnAccueil = new JButton("");
		btnAccueil.setBorder(null);
		btnAccueil.addActionListener(this.gestionAccueil);
		btnAccueil.setBackground(new Color(192, 192, 192));
		btnAccueil.setIcon(new ImageIcon(Fenetre_Accueil.class.getResource("/icon/home_att-removebg-preview.png")));
		btnAccueil.setBounds(0, 53, 42, 31);
		btnAccueil.setName("btnAccueil");
		panelDuBtnAccueil.add(btnAccueil);

		///////////////////////////////////////////////////////////////////
		// MENU DE BOUTONS SUR LE CÔTE
		// ////////////////////////////////////////////////////////////////
		JButton btnMesBiens = new JButton("Mes Biens");
		btnMesBiens.addActionListener(this.gestionAccueil);
		btnMesBiens.setBackground(new Color(0, 102, 204));
		btnMesBiens.setForeground(new Color(255, 255, 255));
		btnMesBiens.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesBiens.setName("btnMesBiens");
		panel_Menu_Boutons.add(btnMesBiens);

		JButton btnMesLocations = new JButton("Mes Locations");
		btnMesLocations.setForeground(new Color(255, 255, 255));
		btnMesLocations.addActionListener(this.gestionAccueil);
		btnMesLocations.setBackground(new Color(0, 102, 204));
		btnMesLocations.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesLocations.setName("btnMesLocations");
		panel_Menu_Boutons.add(btnMesLocations);

		JButton btnMesTravaux = new JButton("Mes Travaux");
		btnMesTravaux.setForeground(new Color(255, 255, 255));
		btnMesTravaux.addActionListener(this.gestionAccueil);
		btnMesTravaux.setBackground(new Color(0, 102, 204));
		btnMesTravaux.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesTravaux.setName("btnMesTravaux");
		panel_Menu_Boutons.add(btnMesTravaux);

		JButton btnMesChargesLocatives = new JButton("Mes Factures");
		btnMesChargesLocatives.setForeground(new Color(255, 255, 255));
		btnMesChargesLocatives.addActionListener(this.gestionAccueil);
		btnMesChargesLocatives.setBackground(new Color(0, 102, 204));
		btnMesChargesLocatives.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesChargesLocatives.setName("btnMesChargesLocatives");
		panel_Menu_Boutons.add(btnMesChargesLocatives);

		JButton btnMesAssurances = new JButton("Mes Assurances");
		btnMesAssurances.setForeground(new Color(255, 255, 255));
		btnMesAssurances.addActionListener(this.gestionAccueil);
		btnMesAssurances.setBackground(new Color(0, 102, 204));
		btnMesAssurances.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesAssurances.setName("btnMesAssurances");
		panel_Menu_Boutons.add(btnMesAssurances);

		JButton btnRegularisationDesCharges = new JButton("Régularisation des Charges");
		btnRegularisationDesCharges.setForeground(new Color(255, 255, 255));
		btnRegularisationDesCharges.addActionListener(this.gestionAccueil);
		btnRegularisationDesCharges.setBackground(new Color(0, 102, 204));
		btnRegularisationDesCharges.setFont(new Font("Dialog", Font.BOLD, 12));
		btnRegularisationDesCharges.setName("btnRegularisationDesCharges");
		panel_Menu_Boutons.add(btnRegularisationDesCharges);

		JButton btnMesDocuments = new JButton("Mes Documents");
		btnMesDocuments.setForeground(new Color(255, 255, 255));
		btnMesDocuments.addActionListener(this.gestionAccueil);
		btnMesDocuments.setBackground(new Color(0, 102, 204));
		btnMesDocuments.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesDocuments.setName("btnMesDocuments");
		panel_Menu_Boutons.add(btnMesDocuments);

		JButton btnMesArchives = new JButton("Mes Archives");
		btnMesArchives.setForeground(new Color(255, 255, 255));
		btnMesArchives.addActionListener(this.gestionAccueil);
		btnMesArchives.setBackground(new Color(0, 102, 204));
		btnMesArchives.setFont(new Font("Dialog", Font.BOLD, 12));
		btnMesArchives.setName("btnMesArchives");
		panel_Menu_Boutons.add(btnMesArchives);

		///////////////////////////////////////////////////////////////////
		// LAYERED ACCUEIL
		// ////////////////////////////////////////////////////////////////

		this.layeredPane_Accueil = new JLayeredPane();
		this.layeredPane_Accueil.setBackground(new Color(255, 255, 255));
		this.contentPane.add(this.layeredPane_Accueil, BorderLayout.CENTER);
		this.layeredPane_Accueil.setLayout(new BorderLayout(0, 0));

		JPanel panel_accueil = new JPanel();
		this.layeredPane_Accueil.add(panel_accueil, BorderLayout.CENTER);
		panel_accueil.setLayout(new GridLayout(2, 2, 0, 0));

		this.panel_1 = new JPanel();
		panel_accueil.add(this.panel_1);

		this.panel_2 = new JPanel();
		panel_accueil.add(this.panel_2);

		this.panel_4 = new JPanel();
		panel_accueil.add(this.panel_4);

		this.panel_3 = new JPanel();
		panel_accueil.add(this.panel_3);

		JButton btnCSV = new JButton("Importer un csv");
		btnCSV.setForeground(Color.WHITE);
		btnCSV.setBackground(new Color(0, 102, 204));
		btnCSV.setBounds(111, 449, 94, 31);
		btnCSV.addActionListener(this.gestionAccueil);
		btnCSV.setName("importCSV");
		this.panel_4.add(btnCSV);

		///////////////////////////////////////////////////////////////////
		// LAYERED MES BIENS
		///////////////////////////////////////////////////////////////////
		this.layeredPane_MesBiens = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesBiens, BorderLayout.CENTER);
		this.layeredPane_MesBiens.setLayout(new BorderLayout(0, 0));

		JPanel panelMesBiens = new JPanel();
		panelMesBiens.setBackground(Color.WHITE);
		this.layeredPane_MesBiens.add(panelMesBiens);
		panelMesBiens.setLayout(null);

		// Tableaux et scrollPane
		JScrollPane scrollPaneMesBiens = new JScrollPane();
		scrollPaneMesBiens.setToolTipText("40, 53, 668, 130");
		scrollPaneMesBiens.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPaneMesBiens.setBackground(Color.LIGHT_GRAY);
		scrollPaneMesBiens.setBounds(40, 101, 475, 147);
		panelMesBiens.add(scrollPaneMesBiens);

		this.tableMesBiens = new JTable();
		this.tableMesBiens.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Nom du bien", "Adresse", "Nb de logements", "Type" }));
		this.tableMesBiens.setBounds(40, 53, 668, 130);
		scrollPaneMesBiens.setViewportView(this.tableMesBiens);
		this.tableMesBiens.getSelectionModel().addListSelectionListener(this.gestionBienLogement);

		JScrollPane scrollPaneMesBiens_Logements = new JScrollPane();
		scrollPaneMesBiens_Logements.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPaneMesBiens_Logements.setBackground(Color.LIGHT_GRAY);
		scrollPaneMesBiens_Logements.setBounds(40, 314, 475, 113);
		panelMesBiens.add(scrollPaneMesBiens_Logements);

		this.tableMesBiens_Logements = new JTable();
		this.tableMesBiens_Logements.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Nom", "Surface", "Nb pi\u00E8ces", "Etage", "Aquisition", "Occup\u00E9", "Type" }));
		this.tableMesBiens_Logements.setBounds(40, 266, 438, 106);
		scrollPaneMesBiens_Logements.setViewportView(this.tableMesBiens_Logements);
		// Pour action de ligne sur table logement
		this.tableMesBiens_Logements.getSelectionModel().addListSelectionListener(this.gestionTableLogement);

		// Labels
		JLabel lblMesBiens = new JLabel("Mes Biens");
		lblMesBiens.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMesBiens.setHorizontalAlignment(SwingConstants.CENTER);
		lblMesBiens.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMesBiens.setBounds(244, 22, 216, 43);
		panelMesBiens.add(lblMesBiens);

		JLabel lblImmeubles = new JLabel("Bien");
		lblImmeubles.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImmeubles.setBounds(40, 71, 109, 31);
		panelMesBiens.add(lblImmeubles);

		JLabel lblLogements = new JLabel("Mes Logements");
		lblLogements.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogements.setBounds(40, 287, 94, 26);
		panelMesBiens.add(lblLogements);

		// Séparateurs
		JSeparator separatorMesBiens = new JSeparator();
		separatorMesBiens.setForeground(new Color(0, 102, 204));
		separatorMesBiens.setBounds(258, 63, 190, 2);
		panelMesBiens.add(separatorMesBiens);

		JSeparator separator_mesBiens_1 = new JSeparator();
		separator_mesBiens_1.setForeground(new Color(0, 102, 204));
		separator_mesBiens_1.setBounds(40, 274, 690, 2);
		panelMesBiens.add(separator_mesBiens_1);

		// Boutons généraux
		JButton btnMesBiens_Charger = new JButton("Charger");
		btnMesBiens_Charger.setForeground(Color.WHITE);
		btnMesBiens_Charger.setBackground(new Color(0, 102, 204));
		btnMesBiens_Charger.setBounds(111, 449, 94, 31);
		btnMesBiens_Charger.addActionListener(this.gestionAccueil);
		btnMesBiens_Charger.setName("btnMesBiens_Charger");
		panelMesBiens.add(btnMesBiens_Charger);

		JButton btnMesBiens_Modifier = new JButton("Modifier");
		btnMesBiens_Modifier.setForeground(Color.WHITE);
		btnMesBiens_Modifier.setBackground(new Color(0, 102, 204));
		btnMesBiens_Modifier.setBounds(287, 449, 99, 31);
		btnMesBiens_Modifier.addActionListener(this.gestionAccueil);
		btnMesBiens_Modifier.setName("btnMesBiens_Modifier");
		panelMesBiens.add(btnMesBiens_Modifier);

		JButton btnMesBiens_Supprimer = new JButton("Supprimer");
		btnMesBiens_Supprimer.setForeground(Color.WHITE);
		btnMesBiens_Supprimer.setBackground(new Color(0, 102, 204));
		btnMesBiens_Supprimer.setBounds(457, 449, 109, 31);
		btnMesBiens_Supprimer.addActionListener(this.gestionAccueil);
		btnMesBiens_Supprimer.setName("btnMesBiens_Supprimer");
		panelMesBiens.add(btnMesBiens_Supprimer);

		// Boutons BIENS
		JButton btnMesBiens_AjouterBien = new JButton("Ajouter un bien");
		btnMesBiens_AjouterBien.setBounds(551, 124, 161, 23);
		btnMesBiens_AjouterBien.addActionListener(this.gestionAccueil);
		btnMesBiens_AjouterBien.setName("btnMesBiens_AjouterBien");
		panelMesBiens.add(btnMesBiens_AjouterBien);

		JButton btnMesBiens_AjouterPaiements = new JButton("Ajouter des factures");
		btnMesBiens_AjouterPaiements.addActionListener(this.gestionAccueil);
		btnMesBiens_AjouterPaiements.setBounds(551, 158, 161, 23);
		btnMesBiens_AjouterPaiements.setName("btnMesBiens_AjouterPaiements");
		panelMesBiens.add(btnMesBiens_AjouterPaiements);

		JButton btnMesBiens_AfficherCompteurs_Bien = new JButton("Afficher les compteurs");
		btnMesBiens_AfficherCompteurs_Bien.setBounds(551, 192, 161, 23);
		btnMesBiens_AfficherCompteurs_Bien.addActionListener(this.gestionAccueil);
		btnMesBiens_AfficherCompteurs_Bien.setName("btnMesBiens_AfficherCompteurs_Bien");
		panelMesBiens.add(btnMesBiens_AfficherCompteurs_Bien);

		// Boutons LOGEMENTS
		JButton btnMesBiens_AjouterLogement = new JButton("Ajouter un logement");
		btnMesBiens_AjouterLogement.setBounds(551, 300, 161, 23);
		btnMesBiens_AjouterLogement.addActionListener(this.gestionAccueil);
		btnMesBiens_AjouterLogement.setName("btnMesBiens_AjouterLogement");
		panelMesBiens.add(btnMesBiens_AjouterLogement);

		JButton btnMesBiens_AjouterDiagnostic_Logements = new JButton("Ajouter un diagnostic");
		btnMesBiens_AjouterDiagnostic_Logements.setBounds(551, 334, 161, 23);
		btnMesBiens_AjouterDiagnostic_Logements.addActionListener(this.gestionAccueil);
		btnMesBiens_AjouterDiagnostic_Logements.setName("btnMesBiens_AjouterDiagnostic_Logements");
		panelMesBiens.add(btnMesBiens_AjouterDiagnostic_Logements);

		JButton btnMesBiens_AjouterPaiements_Logements = new JButton("Ajouter des factures");
		btnMesBiens_AjouterPaiements_Logements.setBounds(551, 368, 161, 23);
		btnMesBiens_AjouterPaiements_Logements.addActionListener(this.gestionAccueil);
		btnMesBiens_AjouterPaiements_Logements.setName("btnMesBiens_AjouterPaiements_Logements");
		panelMesBiens.add(btnMesBiens_AjouterPaiements_Logements);

		JButton btnMesBiens_AfficherCompteurs_Logement = new JButton("Afficher les compteurs");
		btnMesBiens_AfficherCompteurs_Logement.setBounds(551, 402, 161, 23);
		btnMesBiens_AfficherCompteurs_Logement.addActionListener(this.gestionAccueil);
		btnMesBiens_AfficherCompteurs_Logement.setName("btnMesBiens_AfficherCompteurs_Logement");
		panelMesBiens.add(btnMesBiens_AfficherCompteurs_Logement);

		////////////////////////////////////////////////////////////////////////////
		// LAYERED MES
		// LOCATIONS////////////////////////////////////////////////////////////////
		this.layeredPane_MesLocations = new JLayeredPane();
		this.layeredPane_MesLocations.setBackground(Color.WHITE);
		this.contentPane.add(this.layeredPane_MesLocations, BorderLayout.CENTER);
		this.layeredPane_MesLocations.setLayout(new BorderLayout(0, 0));

		JPanel panel_MesLocations = new JPanel();
		panel_MesLocations.setBackground(Color.WHITE);
		this.layeredPane_MesLocations.add(panel_MesLocations, BorderLayout.CENTER);
		panel_MesLocations.setLayout(null);

		// Tableaux et scrollPane
		JScrollPane scrollPane_MesLocations = new JScrollPane();
		scrollPane_MesLocations.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPane_MesLocations.setBounds(30, 95, 447, 323);
		panel_MesLocations.add(scrollPane_MesLocations);

		this.table_MesLocations = new JTable();
		this.table_MesLocations.setSelectionBackground(new Color(0, 102, 204));
		this.table_MesLocations.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Locataire", "Bien", "Type", "Date d\u00E9but", "Derni\u00E8re r\u00E9gularisation" }));
		this.table_MesLocations.getColumnModel().getColumn(0).setPreferredWidth(60);
		this.table_MesLocations.getColumnModel().getColumn(1).setPreferredWidth(58);
		this.table_MesLocations.getColumnModel().getColumn(2).setPreferredWidth(70);
		this.table_MesLocations.setBounds(40, 53, 668, 130);
		scrollPane_MesLocations.setViewportView(this.table_MesLocations);
		this.table_MesLocations.getSelectionModel().addListSelectionListener(this.gestionLocations);

		// Labels
		JLabel lbl_MesLocations = new JLabel("Mes Locations");
		lbl_MesLocations.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MesLocations.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MesLocations.setBounds(244, 22, 216, 43);
		panel_MesLocations.add(lbl_MesLocations);

		// Séparateurs
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 102, 204));
		separator.setBounds(258, 63, 190, 2);
		panel_MesLocations.add(separator);

		// Boutons généraux
		JButton btn_MesLocations_Charger = new JButton("Charger");
		btn_MesLocations_Charger.setForeground(Color.WHITE);
		btn_MesLocations_Charger.setBackground(new Color(0, 102, 204));
		btn_MesLocations_Charger.setBounds(185, 449, 94, 31);
		btn_MesLocations_Charger.addActionListener(this.gestionAccueil);
		btn_MesLocations_Charger.setName("btn_MesLocations_Charger");
		panel_MesLocations.add(btn_MesLocations_Charger);

		JButton btn_MesLocations_Modifier = new JButton("Modifier");
		btn_MesLocations_Modifier.setForeground(Color.WHITE);
		btn_MesLocations_Modifier.setBackground(new Color(0, 102, 204));
		btn_MesLocations_Modifier.setBounds(438, 449, 99, 31);
		btn_MesLocations_Modifier.addActionListener(this.gestionAccueil);
		btn_MesLocations_Modifier.setName("btn_MesLocations_Modifier");
		panel_MesLocations.add(btn_MesLocations_Modifier);

		JButton btn_MesLocations_Inserer = new JButton("Insérer");
		btn_MesLocations_Inserer.setForeground(Color.WHITE);
		btn_MesLocations_Inserer.setBackground(new Color(0, 102, 204));
		btn_MesLocations_Inserer.setBounds(312, 449, 94, 31);
		btn_MesLocations_Inserer.addActionListener(this.gestionAccueil);
		btn_MesLocations_Inserer.setName("btn_MesLocations_Inserer");
		panel_MesLocations.add(btn_MesLocations_Inserer);

		JButton btn_MesLocations_Supprimer = new JButton("Supprimer");
		btn_MesLocations_Supprimer.setForeground(Color.WHITE);
		btn_MesLocations_Supprimer.setBackground(new Color(0, 102, 204));
		btn_MesLocations_Supprimer.setBounds(568, 449, 94, 31);
		btn_MesLocations_Supprimer.addActionListener(this.gestionAccueil);
		btn_MesLocations_Supprimer.setName("btn_MesLocations_Supprimer");
		panel_MesLocations.add(btn_MesLocations_Supprimer);

		// Bouton propre à la page
		JButton btn_mesLocations_InfoLocataire = new JButton("Mon locataire");
		btn_mesLocations_InfoLocataire.setBounds(510, 407, 152, 23);
		btn_mesLocations_InfoLocataire.addActionListener(this.gestionAccueil);
		btn_mesLocations_InfoLocataire.setName("btn_mesLocations_InfoLocataire");
		panel_MesLocations.add(btn_mesLocations_InfoLocataire);

		JButton btn_mesLocations_AjouterFacture = new JButton("Ajouter facture pour loyer");
		btn_mesLocations_AjouterFacture.setName("btn_mesLocations_AjouterFacture");
		btn_mesLocations_AjouterFacture.addActionListener(this.gestionAccueil);
		btn_mesLocations_AjouterFacture.setBounds(510, 373, 200, 23);
		panel_MesLocations.add(btn_mesLocations_AjouterFacture);

		// Champs de saisie
		this.textField_loyer = new JTextField();
		this.textField_loyer.setBackground(new Color(255, 255, 255));
		this.textField_loyer.setEditable(false);
		this.textField_loyer.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true), "Loyer",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_loyer.setBounds(510, 72, 152, 40);
		panel_MesLocations.add(this.textField_loyer);
		this.textField_loyer.setColumns(10);

		this.textField_provisionCharges = new JTextField();
		this.textField_provisionCharges.setEditable(false);
		this.textField_provisionCharges.setColumns(10);
		this.textField_provisionCharges.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true),
				"Provision sur charges", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_provisionCharges.setBackground(Color.WHITE);
		this.textField_provisionCharges.setBounds(510, 114, 152, 40);
		panel_MesLocations.add(this.textField_provisionCharges);

		this.textField_caution = new JTextField();
		this.textField_caution.setEditable(false);
		this.textField_caution.setColumns(10);
		this.textField_caution.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true), "Caution",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_caution.setBackground(Color.WHITE);
		this.textField_caution.setBounds(510, 322, 152, 40);
		panel_MesLocations.add(this.textField_caution);

		this.textField_dateEmission = new JTextField();
		this.textField_dateEmission.setEditable(false);
		this.textField_dateEmission.setColumns(10);
		this.textField_dateEmission.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true),
				"Date \u00E9mission", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_dateEmission.setBackground(Color.WHITE);
		this.textField_dateEmission.setBounds(510, 238, 152, 40);
		panel_MesLocations.add(this.textField_dateEmission);

		this.textField_datePaiement = new JTextField();
		this.textField_datePaiement.setEditable(false);
		this.textField_datePaiement.setColumns(10);
		this.textField_datePaiement.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true),
				"Date paiement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_datePaiement.setBackground(Color.WHITE);
		this.textField_datePaiement.setBounds(510, 280, 152, 40);
		panel_MesLocations.add(this.textField_datePaiement);

		this.textField_paye = new JTextField();
		this.textField_paye.setEditable(false);
		this.textField_paye.setColumns(10);
		this.textField_paye.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true),
				"Montant pay\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_paye.setBackground(Color.WHITE);
		this.textField_paye.setBounds(510, 156, 152, 40);
		panel_MesLocations.add(this.textField_paye);

		this.textField_restantDu = new JTextField();
		this.textField_restantDu.setEditable(false);
		this.textField_restantDu.setColumns(10);
		this.textField_restantDu.setBorder(new TitledBorder(new LineBorder(new Color(0, 102, 204), 1, true),
				"Restant d\u00FB", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 102, 204)));
		this.textField_restantDu.setBackground(Color.WHITE);
		this.textField_restantDu.setBounds(510, 196, 152, 40);
		panel_MesLocations.add(this.textField_restantDu);

		JButton btn_MesLocations_Archiver = new JButton("Archiver");
		btn_MesLocations_Archiver.setName("btn_MesLocations_Archiver");
		btn_MesLocations_Archiver.setForeground(Color.WHITE);
		btn_MesLocations_Archiver.setBackground(new Color(0, 102, 204));
		btn_MesLocations_Archiver.addActionListener(this.gestionAccueil);
		btn_MesLocations_Archiver.setBounds(50, 449, 94, 31);
		panel_MesLocations.add(btn_MesLocations_Archiver);

		//////////////////////////////////////////////////////////////////////////
		// LAYERED MES
		// TRAVAUX////////////////////////////////////////////////////////////////
		this.layeredPane_MesTravaux = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesTravaux, BorderLayout.CENTER);
		this.layeredPane_MesTravaux.setLayout(new BorderLayout(0, 0));

		JPanel panel_MesTravaux = new JPanel();
		panel_MesTravaux.setBackground(Color.WHITE);
		this.layeredPane_MesTravaux.add(panel_MesTravaux);
		panel_MesTravaux.setLayout(null);

		// Tableaux et scrollPane
		JScrollPane scrollPane_MesTravaux = new JScrollPane();
		scrollPane_MesTravaux.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPane_MesTravaux.setBounds(51, 128, 641, 289);
		panel_MesTravaux.add(scrollPane_MesTravaux);

		this.table_MesTravaux = new JTable();
		this.table_MesTravaux.setSelectionBackground(new Color(0, 102, 204));
		this.table_MesTravaux
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
						new String[] { "Numéro", "Bien/Logement", "D\u00E9signation", "Date \u00E9mission", "Montant",
								"Pay\u00E9", "Prestataire", "Adresse" }));
		this.table_MesTravaux.setBounds(40, 53, 668, 130);
		this.table_MesTravaux.getSelectionModel().addListSelectionListener(this.gestionTableTravaux);
		scrollPane_MesTravaux.setViewportView(this.table_MesTravaux);

		// labels
		JLabel lbl_MesTravaux = new JLabel("Mes Travaux");
		lbl_MesTravaux.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MesTravaux.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MesTravaux.setBounds(244, 22, 216, 43);
		panel_MesTravaux.add(lbl_MesTravaux);

		// Séparateurs
		JSeparator separator_MesTravaux = new JSeparator();
		separator_MesTravaux.setForeground(new Color(0, 102, 204));
		separator_MesTravaux.setBounds(258, 63, 190, 2);
		panel_MesTravaux.add(separator_MesTravaux);

		// Boutons généraux
		JButton btn_Travaux_Modifier = new JButton("Modifier");
		btn_Travaux_Modifier.setForeground(Color.WHITE);
		btn_Travaux_Modifier.setBackground(new Color(0, 102, 204));
		btn_Travaux_Modifier.setBounds(216, 449, 99, 31);
		btn_Travaux_Modifier.addActionListener(this.gestionAccueil);
		btn_Travaux_Modifier.setName("btn_Travaux_Modifier");
		panel_MesTravaux.add(btn_Travaux_Modifier);

		JButton btn_Travaux_Supprimer = new JButton("Supprimer");
		btn_Travaux_Supprimer.setForeground(Color.WHITE);
		btn_Travaux_Supprimer.setBackground(new Color(0, 102, 204));
		btn_Travaux_Supprimer.setBounds(425, 449, 105, 31);
		btn_Travaux_Supprimer.addActionListener(this.gestionAccueil);
		btn_Travaux_Supprimer.setName("btn_Travaux_Supprimer");
		panel_MesTravaux.add(btn_Travaux_Supprimer);

		// Boutons propres à la page
		JToggleButton tglbtn_Travaux_immeubles = new JToggleButton("Travaux pour mes immeubles");
		tglbtn_Travaux_immeubles.setBounds(112, 76, 216, 23);
		tglbtn_Travaux_immeubles.addActionListener(this.gestionAccueil);
		tglbtn_Travaux_immeubles.setName("tglbtn_Travaux_immeubles");
		panel_MesTravaux.add(tglbtn_Travaux_immeubles);

		JToggleButton tglbtn_Travaux_logements = new JToggleButton("Travaux pour mes logements");
		tglbtn_Travaux_logements.setBounds(384, 76, 208, 23);
		tglbtn_Travaux_logements.addActionListener(this.gestionAccueil);
		tglbtn_Travaux_logements.setName("tglbtn_Travaux_logements");
		panel_MesTravaux.add(tglbtn_Travaux_logements);

		///////////////////////////////////////////////////////////////////
		// LAYERED MES FACTURES
		// ////////////////////////////////////////////////////////////////
		this.layeredPane_MesFactures = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesFactures, BorderLayout.CENTER);
		this.layeredPane_MesFactures.setLayout(new BorderLayout(0, 0));

		JPanel panel_factures = new JPanel();
		panel_factures.setBackground(Color.WHITE);
		this.layeredPane_MesFactures.add(panel_factures);
		panel_factures.setLayout(null);

		// Tableaux et scrollPane
		JScrollPane scrollPane_MesFactures = new JScrollPane();
		scrollPane_MesFactures.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPane_MesFactures.setBounds(55, 124, 643, 288);
		panel_factures.add(scrollPane_MesFactures);

		this.table_MesFactures = new JTable();
		this.table_MesFactures.setSelectionBackground(new Color(0, 102, 204));
		this.table_MesFactures.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Logement", "Numero", "Designation", "Date d'emission", "Date de paiement", "Imputable",
						"Montant", "Montant payé", "Restant dû" }));
		this.table_MesFactures.setBounds(40, 53, 668, 130);
		scrollPane_MesFactures.setViewportView(this.table_MesFactures);
		this.table_MesFactures.getSelectionModel().addListSelectionListener(this.gestionTableCharges);

		// Labels
		JLabel lbl_MesFactures = new JLabel("Mes factures");
		lbl_MesFactures.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MesFactures.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MesFactures.setBounds(244, 22, 216, 43);
		panel_factures.add(lbl_MesFactures);

		// Séparateurs
		JSeparator separator_mesFactures = new JSeparator();
		separator_mesFactures.setForeground(new Color(0, 102, 204));
		separator_mesFactures.setBounds(258, 63, 190, 2);
		panel_factures.add(separator_mesFactures);

		// Boutons généraux
		JButton btn_MesFactures_Modifier = new JButton("Modifier");
		btn_MesFactures_Modifier.setForeground(Color.WHITE);
		btn_MesFactures_Modifier.setBackground(new Color(0, 102, 204));
		btn_MesFactures_Modifier.setBounds(417, 449, 99, 31);
		btn_MesFactures_Modifier.addActionListener(this.gestionAccueil);
		btn_MesFactures_Modifier.setName("btn_MesFactures_Modifier");
		panel_factures.add(btn_MesFactures_Modifier);

		JButton btn_MesFactures_Supprimer = new JButton("Supprimer");
		btn_MesFactures_Supprimer.setForeground(Color.WHITE);
		btn_MesFactures_Supprimer.setBackground(new Color(0, 102, 204));
		btn_MesFactures_Supprimer.setBounds(561, 449, 106, 31);
		btn_MesFactures_Supprimer.addActionListener(this.gestionAccueil);
		btn_MesFactures_Supprimer.setName("btn_MesFactures_Supprimer");
		panel_factures.add(btn_MesFactures_Supprimer);

		// Boutons propres à la page
		JToggleButton tglbtn_FactureCharge_biens = new JToggleButton("Charger pour mes logements");
		tglbtn_FactureCharge_biens.setBounds(55, 453, 208, 23);
		tglbtn_FactureCharge_biens.addActionListener(this.gestionAccueil);
		tglbtn_FactureCharge_biens.setName("tglbtn_FactureCharge_biens");
		panel_factures.add(tglbtn_FactureCharge_biens);

		JButton btn_MesFactures_Archiver = new JButton("Archiver");
		btn_MesFactures_Archiver.setName("btn_MesFactures_Archiver");
		btn_MesFactures_Archiver.setForeground(Color.WHITE);
		btn_MesFactures_Archiver.setBackground(new Color(0, 102, 204));
		btn_MesFactures_Archiver.setBounds(292, 449, 99, 31);
		btn_MesFactures_Archiver.addActionListener(this.gestionAccueil);
		panel_factures.add(btn_MesFactures_Archiver);

		// ComboBox
		this.comboBox_MesFactures = new JComboBox<String>();
		this.comboBox_MesFactures.setBounds(55, 81, 171, 29);
		panel_factures.add(this.comboBox_MesFactures);
		this.comboBox_MesFactures.addActionListener(this.gestionAccueil);

		// Remplir le JComboBox avec les identifiants des logements
		try {
			List<String> identifiantsLogements = this.daoBien.getAllIdBien();
			identifiantsLogements.add(0, "ID du logement");

			// Ajouter les identifiants au modèle du JComboBox
			DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<>(
					identifiantsLogements.toArray(new String[0]));
			this.comboBox_MesFactures.setModel(modelComboBox);
		} catch (SQLException e) {
			e.printStackTrace();
			// Gestion de l'erreur SQL, par exemple, afficher un message à l'utilisateur
			JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des identifiants de logement.",
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}

		///////////////////////////////////////////////////////////////////
		// LAYERED MES ASSURANCES
		// ////////////////////////////////////////////////////////////////
		this.layeredPane_MesAssurances = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesAssurances, BorderLayout.CENTER);
		this.layeredPane_MesAssurances.setLayout(new BorderLayout(0, 0));

		JPanel panel_MesAssurances = new JPanel();
		panel_MesAssurances.setBackground(Color.WHITE);
		this.layeredPane_MesAssurances.add(panel_MesAssurances);
		panel_MesAssurances.setLayout(null);

		// Tableaux et scrollPane
		JScrollPane scrollPane_MesAssurances = new JScrollPane();
		scrollPane_MesAssurances.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPane_MesAssurances.setBounds(55, 124, 643, 270);
		panel_MesAssurances.add(scrollPane_MesAssurances);

		this.table_MesAssurances = new JTable();
		this.table_MesAssurances.setSelectionBackground(new Color(0, 102, 204));
		this.table_MesAssurances
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
						new String[] { "Logement", "n\u00B0 Police", "Montant", "Date \u00E9cheance", "Prestataire",
								"Adresse", "n\u00B0 T\u00E9l\u00E9phone" }));
		this.table_MesAssurances.setBounds(40, 53, 668, 130);
		this.table_MesAssurances.getSelectionModel().addListSelectionListener(this.gestionTableAssurance);
		scrollPane_MesAssurances.setViewportView(this.table_MesAssurances);

		// Labels
		JLabel lbl_MesAssurances = new JLabel("Mes Assurances");
		lbl_MesAssurances.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MesAssurances.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MesAssurances.setBounds(244, 22, 216, 43);
		panel_MesAssurances.add(lbl_MesAssurances);

		// Séparateurs
		JSeparator separator_mesAssurances = new JSeparator();
		separator_mesAssurances.setForeground(new Color(0, 102, 204));
		separator_mesAssurances.setBounds(258, 63, 190, 2);
		panel_MesAssurances.add(separator_mesAssurances);

		// Boutons généraux
		JButton btn_MesAssurances_Charger = new JButton("Charger");
		btn_MesAssurances_Charger.setForeground(Color.WHITE);
		btn_MesAssurances_Charger.setBackground(new Color(0, 102, 204));
		btn_MesAssurances_Charger.setBounds(118, 449, 94, 31);
		btn_MesAssurances_Charger.addActionListener(this.gestionAccueil);
		btn_MesAssurances_Charger.setName("btn_MesAssurances_Charger");
		panel_MesAssurances.add(btn_MesAssurances_Charger);

		JButton btn_MesAssurances_Modifier = new JButton("Modifier");
		btn_MesAssurances_Modifier.setForeground(Color.WHITE);
		btn_MesAssurances_Modifier.setBackground(new Color(0, 102, 204));
		btn_MesAssurances_Modifier.setBounds(396, 449, 99, 31);
		btn_MesAssurances_Modifier.addActionListener(this.gestionAccueil);
		btn_MesAssurances_Modifier.setName("btn_MesAssurances_Modifier");
		panel_MesAssurances.add(btn_MesAssurances_Modifier);

		JButton btn_MesAssurances_Inserer = new JButton("Insérer");
		btn_MesAssurances_Inserer.setForeground(Color.WHITE);
		btn_MesAssurances_Inserer.setBackground(new Color(0, 102, 204));
		btn_MesAssurances_Inserer.setBounds(258, 449, 94, 31);
		btn_MesAssurances_Inserer.addActionListener(this.gestionAccueil);
		btn_MesAssurances_Inserer.setName("btn_MesAssurances_Inserer");
		panel_MesAssurances.add(btn_MesAssurances_Inserer);

		JButton btn_MesAssurances_Supprimer = new JButton("Supprimer");
		btn_MesAssurances_Supprimer.setForeground(Color.WHITE);
		btn_MesAssurances_Supprimer.setBackground(new Color(0, 102, 204));
		btn_MesAssurances_Supprimer.setBounds(539, 449, 106, 31);
		btn_MesAssurances_Supprimer.addActionListener(this.gestionAccueil);
		btn_MesAssurances_Supprimer.setName("btn_MesAssurances_Supprimer");
		panel_MesAssurances.add(btn_MesAssurances_Supprimer);

		// ComboBox
		this.comboBox_MesAssurances = new JComboBox<String>();
		this.comboBox_MesAssurances.setBounds(55, 80, 130, 29);
		panel_MesAssurances.add(this.comboBox_MesAssurances);
		this.comboBox_MesAssurances.addActionListener(this.gestionAccueil);

		// Remplir le JComboBox avec les identifiants des logements
		try {
			List<String> identifiantsLogements = this.daoBien.getAllIdBien();
			identifiantsLogements.add(0, "ID du logement");

			// Ajouter les identifiants au modèle du JComboBox
			DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<>(
					identifiantsLogements.toArray(new String[0]));

			this.comboBox_MesAssurances.setModel(modelComboBox);
		} catch (SQLException e) {
			e.printStackTrace();
			// Gestion de l'erreur SQL, par exemple, afficher un message à l'utilisateur
			JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des identifiants de logement.",
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}

//		///////////////////////////////////////////////////////////////////
//		// LAYERED REGULARISATION DES CHARGES
//		// ////////////////////////////////////////////////////////////////
		this.layeredPane_RegularisationDesCharges = new JLayeredPane();
		this.contentPane.add(this.layeredPane_RegularisationDesCharges, BorderLayout.CENTER);
		this.layeredPane_RegularisationDesCharges.setLayout(new BorderLayout(0, 0));

		JPanel panel_RegularisationDesCharges = new JPanel();
		panel_RegularisationDesCharges.setBackground(Color.WHITE);
		this.layeredPane_RegularisationDesCharges.add(panel_RegularisationDesCharges);
		panel_RegularisationDesCharges.setLayout(null);

		// Tableaux et scrollPane
		JScrollPane scrollPane_Regularisation = new JScrollPane();
		scrollPane_Regularisation.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPane_Regularisation.setBounds(28, 121, 697, 303);
		panel_RegularisationDesCharges.add(scrollPane_Regularisation);

		this.tableRegularisation = new JTable();
		this.tableRegularisation.setSelectionBackground(new Color(0, 102, 204));
		this.tableRegularisation.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, }, new String[] { "Période du", "au",
						"Total charges réelles", "Charges garage", "Total des provisions", "RESTE" }));
		this.tableRegularisation.getColumnModel().getColumn(0).setPreferredWidth(65);
		this.tableRegularisation.getColumnModel().getColumn(1).setPreferredWidth(65);
		this.tableRegularisation.setBounds(40, 53, 668, 130);
		scrollPane_Regularisation.setViewportView(this.tableRegularisation);

		// Labels
		JLabel lbl_RegularisationDesCharges = new JLabel("Régularisation des Charges");
		lbl_RegularisationDesCharges.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_RegularisationDesCharges.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_RegularisationDesCharges.setBounds(244, 22, 216, 43);
		panel_RegularisationDesCharges.add(lbl_RegularisationDesCharges);

		// Séparateurs
		JSeparator separator_RegularisationDesChargess = new JSeparator();
		separator_RegularisationDesChargess.setForeground(new Color(0, 102, 204));
		separator_RegularisationDesChargess.setBounds(258, 63, 190, 2);
		panel_RegularisationDesCharges.add(separator_RegularisationDesChargess);

		// JComboBox
		this.comboBox_Regularisation = new JComboBox<String>();
		this.comboBox_Regularisation.setModel(new DefaultComboBoxModel<String>(new String[] { "Locataire" }));
		this.comboBox_Regularisation.setBounds(55, 81, 130, 29);
		panel_RegularisationDesCharges.add(this.comboBox_Regularisation);
		this.comboBox_Regularisation.addActionListener(this.gestionAccueil);

		// Remplir le JComboBox avec les identifiants des locataires
		try {
			List<String> identifiantsLocataires = this.daoLocataire.getAllIdLocataire();
			identifiantsLocataires.add(0, "ID locataire");
			// Ajouter les identifiants au modèle du JComboBox
			DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<>(
					identifiantsLocataires.toArray(new String[0]));
			this.comboBox_Regularisation.setModel(modelComboBox);
		} catch (SQLException e) {
			e.printStackTrace();
		}

////		////////////////////////////////////////////////////////////////////////////
////		// LAYERED
////		// DOCUMENTS////////////////////////////////////////////////////////////////
		this.layeredPane_MesDocuments = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesDocuments, BorderLayout.CENTER);
		this.layeredPane_MesDocuments.setLayout(new BorderLayout(0, 0));

		JPanel panel_MesDocuments = new JPanel();
		panel_MesDocuments.setBackground(Color.WHITE);
		this.layeredPane_MesDocuments.add(panel_MesDocuments);
		panel_MesDocuments.setLayout(null);

		JLabel lbl_MesDocuments = new JLabel("Mes Documents");
		lbl_MesDocuments.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_MesDocuments.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_MesDocuments.setBounds(244, 22, 216, 43);
		panel_MesDocuments.add(lbl_MesDocuments);

		JSeparator separator_MesDocuments = new JSeparator();
		separator_MesDocuments.setForeground(new Color(0, 102, 204));
		separator_MesDocuments.setBounds(258, 63, 190, 2);
		panel_MesDocuments.add(separator_MesDocuments);

		// Tableau et scroll
		JScrollPane scrollPane_MesDocuments = new JScrollPane();
		scrollPane_MesDocuments.setBorder(new LineBorder(new Color(0, 102, 204), 2, true));
		scrollPane_MesDocuments.setBounds(55, 124, 643, 270);
		panel_MesDocuments.add(scrollPane_MesDocuments);

		this.table_MesDocuments = new JTable();
		this.table_MesDocuments
				.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
						new String[] { "Nom", "Montant", "Année" }));
		this.table_MesDocuments.setBounds(40, 53, 668, 130);
		scrollPane_MesDocuments.setViewportView(this.table_MesDocuments);

		// Boutons généraux
		JButton btn_MesDocuments_Charger = new JButton("Charger");
		btn_MesDocuments_Charger.setForeground(Color.WHITE);
		btn_MesDocuments_Charger.setBackground(new Color(0, 102, 204));
		btn_MesDocuments_Charger.setBounds(118, 449, 94, 31);
		btn_MesDocuments_Charger.addActionListener(this.gestionAccueil);
		btn_MesDocuments_Charger.setName("btn_MesDocuments_Charger");
		panel_MesDocuments.add(btn_MesDocuments_Charger);

		JButton btn_MesDocuments_generer_annexe = new JButton("Génerer une annexe");
		btn_MesDocuments_generer_annexe.setForeground(Color.WHITE);
		btn_MesDocuments_generer_annexe.setBackground(new Color(0, 102, 204));
		btn_MesDocuments_generer_annexe.setBounds(468, 449, 167, 31);
		btn_MesDocuments_generer_annexe.addActionListener(this.gestionAccueil);
		btn_MesDocuments_generer_annexe.setName("btn_MesDocuments_generer_annexe");
		panel_MesDocuments.add(btn_MesDocuments_generer_annexe);

		JButton btn_MesDocuments_Inserer_Impots = new JButton("Insérer un impôt");
		btn_MesDocuments_Inserer_Impots.setForeground(Color.WHITE);
		btn_MesDocuments_Inserer_Impots.setBackground(new Color(0, 102, 204));
		btn_MesDocuments_Inserer_Impots.setBounds(271, 449, 130, 31);
		btn_MesDocuments_Inserer_Impots.addActionListener(this.gestionAccueil);
		btn_MesDocuments_Inserer_Impots.setName("btn_MesDocuments_Inserer_Impots");
		panel_MesDocuments.add(btn_MesDocuments_Inserer_Impots);

		// ComboBox
		this.comboBox_MesDocuments = new JComboBox<String>();
		this.comboBox_MesDocuments.setBounds(55, 80, 130, 29);
		panel_MesDocuments.add(this.comboBox_MesDocuments);
		this.comboBox_MesDocuments.addActionListener(this.gestionAccueil);

		// Remplir le JComboBox avec les identifiants des logements
		try {
			List<String> identifiantsLogements = this.daoBien.getAllIdBien();
			identifiantsLogements.add(0, "ID du logement");

			// Ajouter les identifiants au modèle du JComboBox
			DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<>(
					identifiantsLogements.toArray(new String[0]));

			this.comboBox_MesDocuments.setModel(modelComboBox);

		} catch (SQLException e) {
			e.printStackTrace();
			// Gestion de l'erreur SQL, par exemple, afficher un message à l'utilisateur
			JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des identifiants de logement.",
					"Erreur", JOptionPane.ERROR_MESSAGE);
		}

//		PDFListe pdfViewer = new PDFListe();
//		panel_MesDocuments.add(pdfViewer, BorderLayout.CENTER);

////////////////////////////////////////////////////////////////////////////
////// LAYERED
////// ARCHIVES
////////////////////////////////////////////////////////////////

		this.layeredPane_MesArchives = new JLayeredPane();
		this.contentPane.add(this.layeredPane_MesArchives, BorderLayout.CENTER);
		this.layeredPane_MesArchives.setLayout(new BorderLayout(0, 0));

		JPanel panel_MesArchives = new JPanel();
		panel_MesArchives.setBackground(Color.WHITE);
		this.layeredPane_MesArchives.add(panel_MesArchives);
		panel_MesArchives.setLayout(null);

		JLabel lbl_test = new JLabel("TEST");
		lbl_test.setBounds(0, 0, 755, 511);
		lbl_test.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_test.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_MesArchives.add(lbl_test);

	}

	public JLayeredPane getLayeredPane_MesDocuments() {
		return this.layeredPane_MesDocuments;
	}

	public JLayeredPane getLayeredPane_MesArchives() {
		return this.layeredPane_MesArchives;
	}

	public JLayeredPane getLayeredPane_Accueil() {
		return this.layeredPane_Accueil;
	}

	public JLayeredPane getLayeredPane_MesLocations() {
		return this.layeredPane_MesLocations;
	}

	public JLayeredPane getLayeredPane_MesBiens() {
		return this.layeredPane_MesBiens;
	}

	public JLayeredPane getLayeredPane_MesTravaux() {
		return this.layeredPane_MesTravaux;
	}

	public JLayeredPane getLayeredPane_MesChargesLocatives() {
		return this.layeredPane_MesFactures;
	}

	public JLayeredPane getLayeredPane_MesAssurances() {
		return this.layeredPane_MesAssurances;
	}

	public JLayeredPane getLayeredPane_RegularisationDesCharges() {
		return this.layeredPane_RegularisationDesCharges;
	}

	@Override
	public JPanel getContentPane() {
		return this.contentPane;
	}

	// --------- GETTERS --------//
	public JTable getTableBiens() {
		return this.tableMesBiens;
	}

	public JTable getTableLogementsParBien() {
		return this.tableMesBiens_Logements;
	}

	public JTable getTableLocations() {
		return this.table_MesLocations;
	}

	public JTable getTableAssurances() {
		return this.table_MesAssurances;
	}

	public JTable getTableTravaux() {
		return this.table_MesTravaux;
	}

	public JTable getTableChargesLocatives() {
		return this.table_MesFactures;
	}

	public JTable getTableDocuments() {
		return this.table_MesDocuments;
	}

	public JTextField getTextField_loyer() {
		return this.textField_loyer;
	}

	public JTextField getTextField_provisionCharges() {
		return this.textField_provisionCharges;
	}

	public JTextField getTextField_caution() {
		return this.textField_caution;
	}

	public JTextField getTextField_dateEmission() {
		return this.textField_dateEmission;
	}

	public JTextField getTextField_dateEcheance() {
		return this.textField_datePaiement;
	}

	public JTextField getTextField_paye() {
		return this.textField_paye;
	}

	public JTextField getTextField_restantDu() {
		return this.textField_restantDu;
	}

	public JTable getTableRegularisation() {
		return this.tableRegularisation;
	}

	public JComboBox<String> getComboBox_Regularisation() {
		return this.comboBox_Regularisation;
	}

	public JComboBox<String> getComboBox_MesAssurances() {
		return this.comboBox_MesAssurances;
	}

	public JComboBox<String> getComboBox_MesChargesLocatives() {
		return this.comboBox_MesFactures;
	}

	public JComboBox<String> getComboBox_MesDocuments() {
		return this.comboBox_MesDocuments;
	}

	public JPanel getPanel_1() {
		return this.panel_1;
	}

	public void setPanel_1(JPanel panel_1) {
		this.panel_1 = panel_1;
	}

	public JPanel getPanel_2() {
		return this.panel_2;
	}

	public void setPanel_2(JPanel panel_2) {
		this.panel_2 = panel_2;
	}

	public JPanel getPanel_3() {
		return this.panel_3;
	}

	public void setPanel_3(JPanel panel_3) {
		this.panel_3 = panel_3;
	}

	public JPanel getPanel_4() {
		return this.panel_4;
	}

	public void setPanel_4(JPanel panel_4) {
		this.panel_4 = panel_4;
	}
}