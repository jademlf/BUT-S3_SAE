package modele.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modele.Assurance;
import modele.Bien;
import modele.Entreprise;
import modele.dao.requetes.delete.RequeteDeleteAssurance;
import modele.dao.requetes.select.RequeteSelectAssurance;
import modele.dao.requetes.select.RequeteSelectAssuranceById;
import modele.dao.requetes.select.RequeteSelectAssuranceByLogement;
import modele.dao.requetes.sousProgramme.SousProgramme;
import modele.dao.requetes.sousProgramme.SousProgrammeInsertAssurance;
import modele.dao.requetes.update.RequeteUpdateAssurance;

public class DaoAssurance extends DaoModele<Assurance> implements Dao<Assurance> {

	@Override
	public void create(Assurance donnees) throws SQLException {
		SousProgramme<Assurance> sp = new SousProgrammeInsertAssurance();
		CallableStatement st = CictOracleDataSource.getConnectionBD().prepareCall(sp.appelSousProgramme());
		sp.parametres(st, donnees);
		st.execute();
	}

	@Override
	public void update(Assurance donnees) throws SQLException {
		this.miseAJour(new RequeteUpdateAssurance(), donnees);
	}

	@Override
	public void delete(Assurance donnees) throws SQLException {
		this.miseAJour(new RequeteDeleteAssurance(), donnees);
	}

	@Override
	protected Assurance creerInstance(ResultSet curseur) throws SQLException {
		Assurance assurance = null;
		try {
			// Récupérer l'identifiant de l'immeuble
			String idBien = curseur.getString("Id_Bien");
			DaoBien daoBien = new DaoBien();
			Bien bien = daoBien.findById(idBien);

			// Récupérer l'identifiant de l'entreprise
			String siretEntreprise = curseur.getString("SIRET");
			DaoEntreprise daoEntreprise = new DaoEntreprise();
			Entreprise entreprise = daoEntreprise.findById(siretEntreprise);

			assurance = new Assurance(curseur.getString("numero_police"), curseur.getFloat("montant"), bien, entreprise);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assurance;
	}

	@Override
	public List<Assurance> findAll() throws SQLException {
		return this.find(new RequeteSelectAssurance());
	}

	@Override
	public Assurance findById(String... id) throws SQLException {
		List<Assurance> assurances = this.find(new RequeteSelectAssuranceById(), id);
		if (assurances.isEmpty()) {
			return null;
		}
		return assurances.get(0);
	}

	// ---------------- AUTRES METHODES ----------------//

	public List<Assurance> findByLogement(String idImmeuble) throws SQLException {
		return this.find(new RequeteSelectAssuranceByLogement(), idImmeuble);
	}
	
	public Assurance findByLogementObject(String idImmeuble) throws SQLException {
		List<Assurance> assurances = this.find(new RequeteSelectAssuranceByLogement(), idImmeuble);
		if (assurances.isEmpty()) {
			return null;
		}
		return assurances.get(0);
	}

}
