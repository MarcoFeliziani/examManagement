package it.mf.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import it.mf.Dao.Dao;
import it.mf.Model.Facolta;

public class FacoltaDao extends Dao {
	
	private static final String TABLE_NAME = "facolta";

	public FacoltaDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	public List<Facolta> getAll() {
		List<Facolta> retValue = new ArrayList<Facolta>();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from facolta "); 

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Facolta facolta = assignBean(rs);
				retValue.add(facolta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	Facolta assignBean(ResultSet rs) throws SQLException {
		Facolta retValue = new Facolta();
		retValue.setFacId(rs.getInt("FAC_ID"));
		retValue.setFacCod(rs.getString("FAC_COD"));
		retValue.setFacDes(rs.getString("FAC_DES"));
		return retValue;
	}
	
	public TreeSet<Facolta> getAllOrdered() {
		return new TreeSet<Facolta>(getAll());
	}
}
