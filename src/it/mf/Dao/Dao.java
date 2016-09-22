package it.mf.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import it.mf.Util.Database;

public abstract class Dao {

	private Connection connection;
	
	protected Dao() {
		connection = Database.getConnection();
	}
	
	Connection getConnection() {
		return connection;
	}
	
	public static void stmtPara(PreparedStatement stmt, int parameterPos, int type, Object value) throws SQLException {
		
		if (value == null) {
			stmt.setNull(parameterPos, type);			
		} else if (type == Types.VARCHAR) {
			stmt.setString(parameterPos, (String)value);
		} else if (type == Types.INTEGER || type == Types.BIGINT) {
			stmt.setInt(parameterPos, (Integer)value);
		} else if(type == Types.DATE) {
			stmt.setDate(parameterPos, (java.sql.Date)value);
		}

	}
	
	abstract String getTableName();
	
	public void delete(Integer id) {
		
		if (id == null || id == 0)
			return;
		
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement("delete from " + getTableName() + " where app_tr_1=?");
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public Object getById(int id) {
		Object obj = new Object();
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement("select * from " + getTableName() + " where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				obj = assignBean(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	
	abstract Object assignBean(ResultSet rs) throws SQLException;


}
