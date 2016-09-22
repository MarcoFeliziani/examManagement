package it.mf.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import it.mf.Dao.Dao;
import it.mf.Model.Ad;

public class AdDao extends Dao {
	
	private static final String TABLE_NAME = "ad";

	public AdDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	public List<Ad> getAll() {
		List<Ad> retValue = new ArrayList<Ad>();
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from ad "); 

		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			while (rs.next()) {
				Ad ad = assignBean(rs);
				retValue.add(ad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retValue;
	}
	
	Ad assignBean(ResultSet rs) throws SQLException {
		Ad retValue = new Ad();
		retValue.setAdId(rs.getInt("AD_ID"));
		retValue.setAdGenCod(rs.getString("AD_GEN_COD"));
		retValue.setAdGenDes(rs.getString("AD_GEN_DES"));
		retValue.setTipoIscrDes(rs.getString("TIPO_ISCR_DES"));
		retValue.setTipoIscrCod(rs.getString("TIPO_ISCR_COD"));
		return retValue;
	}
	
	public TreeSet<Ad> getAllOrdered() {
		return new TreeSet<Ad>(getAll());
	}

}
