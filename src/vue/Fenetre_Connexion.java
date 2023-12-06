package vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controleur.GestionConnexion;
import modele.dao.CictOracleDataSource;

import java.awt.Dimension;

public class Fenetre_Connexion extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldUtilisateur;
    private JPasswordField textFieldMdp;
    private GestionConnexion gestionClic;
    
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Fenetre_Connexion frame = new Fenetre_Connexion();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public Fenetre_Connexion() {
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitre = new JLabel("Connexion");
        lblTitre.setBounds(180, 10, 100, 30);
        contentPane.add(lblTitre);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(120, 50, 200, 120);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblUtilisateur = new JLabel("Nom d'utilisateur:");
        lblUtilisateur.setBounds(10, 10, 120, 20);
        panel.add(lblUtilisateur);

        textFieldUtilisateur = new JTextField();
        textFieldUtilisateur.setBounds(10, 30, 180, 20);
        panel.add(textFieldUtilisateur);
        textFieldUtilisateur.setColumns(10);

        JLabel lblMdp = new JLabel("Mot de passe:");
        lblMdp.setBounds(10, 60, 120, 20);
        panel.add(lblMdp);

        textFieldMdp = new JPasswordField();
        textFieldMdp.setBounds(10, 80, 180, 20);
        panel.add(textFieldMdp);

        JButton btnConnecter = new JButton("Se connecter");
        btnConnecter.setForeground(Color.WHITE);
        btnConnecter.setBackground(new Color(0, 102, 204));
        btnConnecter.setBounds(90, 195, 120, 30);
        contentPane.add(btnConnecter);
        
        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setForeground(Color.WHITE);
        btnAnnuler.setBackground(new Color(0, 102, 204));
        btnAnnuler.setBounds(220, 195, 120, 30);
        contentPane.add(btnAnnuler);

        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setVisible(true);
        
        this.gestionClic = new GestionConnexion(this);
        btnConnecter.addActionListener(this.gestionClic);
        btnAnnuler.addActionListener(this.gestionClic);
           
    }
    
    public String getNomUtilisateur() {
    	return textFieldUtilisateur.getText();
    }
    
    public String getMdp() {
    	return new String(textFieldMdp.getPassword());
    }
}