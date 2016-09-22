package it.mf.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import it.mf.Dao.Dao;
import it.mf.Model.Docente;

public class DocenteDao extends Dao {
	
	private static final String TABLE_NAME = "docente";

	public DocenteDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	public List<Docente> getAll() {
		List<Docente> retValue = new ArrayList<Docente>();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from docenti "); 

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Docente docente = assignBean(rs);
				retValue.add(docente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	Docente assignBean(ResultSet rs) throws SQLException {
		Docente retValue = new Docente();
		retValue.setDoceNome(rs.getString("DOCE_NOME"));
		retValue.setDoceCognome(rs.getString("DOCE_COGNOME"));
		retValue.setDoceMatricola(rs.getInt("DOCE_MATRICOLA"));
		return retValue;
	}
	
	public TreeSet<Docente> getAllOrdered() {
		return new TreeSet<Docente>(getAll());
	}

}
