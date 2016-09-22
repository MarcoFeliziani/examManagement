package it.mf.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import it.mf.Dao.Dao;
import it.mf.Model.Cds;

public class CdsDao extends Dao {
	
	private static final String TABLE_NAME = "cds";

	public CdsDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}

	public List<Cds> getAll() {
		List<Cds> retValue = new ArrayList<Cds>();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from cds "); 

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Cds cds = assignBean(rs);
				retValue.add(cds);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	Cds assignBean(ResultSet rs) throws SQLException {
		Cds retValue = new Cds();
		retValue.setCdsId(rs.getInt("CDS_ID"));
		retValue.setCdsCod(rs.getString("CDS_COD"));
		retValue.setCdsDes(rs.getString("CDS_DES"));
		return retValue;
	}
	
	public TreeSet<Cds> getAllOrdered() {
		return new TreeSet<Cds>(getAll());
	}

}
