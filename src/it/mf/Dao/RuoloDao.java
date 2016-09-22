package it.mf.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import it.mf.Model.Ruolo;

public class RuoloDao extends Dao {
	
	private static final String TABLE_NAME = "v10_rpt_commissioni_app";
	
	public RuoloDao(){
		
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	public List<Ruolo> getAll() {
		List<Ruolo> retValue = new ArrayList<Ruolo>();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct "); 
		sb.append("ruolo_cod, des "); 
		sb.append("from v10_rpt_commissioni_app "); 

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Ruolo ruolo = assignBean(rs);
				retValue.add(ruolo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	Ruolo assignBean(ResultSet rs) throws SQLException {
		Ruolo retValue = new Ruolo();
		retValue.setRuoloCod(rs.getString("ruolo_cod"));
		retValue.setRuoloDes(rs.getString("des"));
		return retValue;
	}
	
	public TreeSet<Ruolo> getAllOrdered() {
		return new TreeSet<Ruolo>(getAll());
	}

}
