package modele.dao.requetes.select;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import modele.Charge;
import modele.dao.requetes.Requete;

public class RequeteSelectChargeByLogement implements Requete<Charge> {

	@Override
	public String requete() {
		return "SELECT * FROM Charge WHERE Id_Bien = ?";
	}

	@Override
	public void parametres(PreparedStatement prSt, String... id) throws SQLException {
		prSt.setString(1, id[0]);
	}

	@Override
	public void parametres(PreparedStatement prSt, Charge data) throws SQLException {
	}
}
