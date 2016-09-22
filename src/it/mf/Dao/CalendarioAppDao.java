package it.mf.Dao;

import java.util.List;


import it.mf.Model.CalendarioApp;
import it.mf.Model.DataCalendario;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarioAppDao extends Dao {
	
	private static final String TABLE_NAME = "v10_rpt_calendario_esami";

	public CalendarioAppDao() {
		super();
	}
	
	@Override
	String getTableName() {
		return TABLE_NAME;
	}
	
	public List<CalendarioApp> getListaAppelli(Integer facId, Integer cdsId, Integer adId, Integer doceMatricola, String doceRuolo, Date dataAppello, Integer id){
		
		DataCalendario dao = getAllStrPr(); //Ottengo un oggetto con tutti gli intervalli di data
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select FAC_ID, FAC_DES, CDS_ID, CDS_COD, CDS_DES, AD_ID, AD_GEN_DES, TIPO_ISCR_DES, DOCE_MATRICOLA, ");
		sb.append("DOCE_NOME, DOCE_COGNOME, DATA_INIZIO_APP ");
		sb.append("from v10_rpt_calendario_esami ");
	if(doceRuolo != null){
		sb.append("inner join v10_rpt_commissioni_app ca on e.APP_ID=ca.APP_ID");
	}
		sb.append("where ");
		sb.append("FAC_ID = ? ");
		sb.append("and DATA_INIZIO_APP >= curDate() ");
		
		if(cdsId > 0)
			sb.append("and CDS_ID = " + cdsId + " ");
		
		if(adId > 0)
			sb.append("and AD_ID = " + adId + " ");
		
		if(id != 0){
			sb.append("and DATA_INIZIO_APP between ? and ? ");
			
		} else{
			
			if(doceMatricola > 0)
				sb.append("and DOCE_MATRICOLA = " + doceMatricola + " ");
		
			if((dataAppello != null)){
				sb.append("and DATA_INIZIO_APP = ? ");
			}
//			else{
//				sb.append("and DATA_INIZIO_APP >= curDate()");
//			}
		}
		
		sb.append("ORDER BY DATA_INIZIO_APP ");
		
		List<CalendarioApp> retValue = new ArrayList<CalendarioApp>();
		
		try {
			
			PreparedStatement ps = getConnection().prepareStatement(sb.toString());
			ps.setInt(1, facId);
			
			if(id != 0){
				Calendar datamin = Calendar.getInstance();
				datamin.setTime(dataAppello);
				Calendar dataMAX = Calendar.getInstance();
				dataMAX.setTime(dataAppello);
				datamin.add(Calendar.DATE, dao.getDataMin());
				dataMAX.add(Calendar.DATE, dao.getDataMax());
//				datamin.add(Calendar.DATE, -4);
//				dataMAX.add(Calendar.DATE, 4);
				ps.setDate(2, new java.sql.Date(datamin.getTimeInMillis()));
				ps.setDate(3, new java.sql.Date(dataMAX.getTimeInMillis()));
			}
//			else if((dataAppello != null)&&(aDataAppello != null)){
//				ps.setDate(2, new java.sql.Date(dataAppello.getTime()));
//				ps.setDate(3, new java.sql.Date(aDataAppello.getTime()));
//			}
			else if(dataAppello != null) {
				ps.setDate(2, new java.sql.Date(dataAppello.getTime()));
			}
			
			ResultSet rs = ps.executeQuery();
			
			CalendarioApp calendarioApp = null;
			
			while (rs.next()) {
				
				calendarioApp = new CalendarioApp();
				
				calendarioApp.setFacId(rs.getInt("FAC_ID"));
				calendarioApp.setFacDes(rs.getString("FAC_DES"));
				calendarioApp.setCdsId(rs.getInt("CDS_ID"));
				calendarioApp.setCdsCod(rs.getString("CDS_COD"));
				calendarioApp.setCdsDes(rs.getString("CDS_DES"));
				calendarioApp.setAdId(rs.getInt("AD_ID"));
				calendarioApp.setAdGenDes(rs.getString("AD_GEN_DES"));
				calendarioApp.setTipoIscrDes(rs.getString("TIPO_ISCR_DES"));
				calendarioApp.setDoceMatricola(rs.getInt("DOCE_MATRICOLA"));
				calendarioApp.setDoceNome(rs.getString("DOCE_NOME"));
				calendarioApp.setDoceCognome(rs.getString("DOCE_COGNOME"));
				calendarioApp.setDataAppello(rs.getDate("DATA_INIZIO_APP"));
				calendarioApp.setTr(0);
			
		if(id > 0){
				
				Integer DOCE_MATRICOLA = rs.getInt("DOCE_MATRICOLA");
				
				if(!DOCE_MATRICOLA.equals(id)){ //CONTROLLARE CHE SE IL PROF NELLA DATA DI APPELLO STABILITA NON C'è ALLORA NON VISUALIZZARE ALCUN APPELLO
					
						Date DATA_INIZIO_APP= rs.getDate("DATA_INIZIO_APP");
						Calendar data_inizio_app = Calendar.getInstance();
						data_inizio_app.setTime(DATA_INIZIO_APP);
						Calendar dataAppCal = Calendar.getInstance();
						dataAppCal.setTime(dataAppello);
						Calendar datamin1 = Calendar.getInstance();
						datamin1.setTime(dataAppello);
						Calendar dataMAX1 = Calendar.getInstance();
						dataMAX1.setTime(dataAppello);
						Calendar datamin4 = Calendar.getInstance();
						datamin4.setTime(dataAppello);
						Calendar dataMAX4 = Calendar.getInstance();
						dataMAX4.setTime(dataAppello);
						datamin1.add(Calendar.DATE, dao.getOneDaymin());
						dataMAX1.add(Calendar.DATE, dao.getOneDaymax());
//						datamin1.add(Calendar.DATE, -2);
//						dataMAX1.add(Calendar.DATE, 2);
						datamin4.add(Calendar.DATE, dao.getFourDaymin());
						dataMAX4.add(Calendar.DATE, dao.getFourDaymax());
//						datamin4.add(Calendar.DATE, -5);
//						dataMAX4.add(Calendar.DATE, 5);
						
						/*Setta il tipo record di tutti gli appelli dentro l'intervallo di data e con docente diverso da quello selezionato nel filtro*/
							
							if(data_inizio_app.equals(dataAppCal)){
								calendarioApp.setTr(1); //AVVISO DI TIPO 1 (appello nello stesso giorno)
							}
							else if((data_inizio_app.after(datamin1))&&(data_inizio_app.before(dataMAX1))){
								calendarioApp.setTr(2); //AVVISO DI TIPO 2 (appello a distanza di un giorno)
							}
							else if((data_inizio_app.after(datamin4))&&(data_inizio_app.before(dataMAX4))){
								calendarioApp.setTr(3); //AVVISO DI TIPO 3 (appello a distanza di 4 giorni)
							}
							
				}
		}
						
				retValue.add(calendarioApp);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	
	CalendarioApp assignBean(ResultSet rs) throws SQLException {
		
		CalendarioApp retValue = new CalendarioApp();
		
		retValue.setFacId(rs.getInt("FAC_ID"));
		retValue.setFacDes(rs.getString("FAC_DES"));
		retValue.setCdsId(rs.getInt("CDS_ID"));
		retValue.setCdsDes(rs.getString("CDS_DES"));
		retValue.setAdId(rs.getInt("AD_ID"));
		retValue.setAdGenDes(rs.getString("AD_GEN_DES"));
		retValue.setTipoIscrDes(rs.getString("TIPO_ISCR_DES"));
		retValue.setDoceMatricola(rs.getInt("DOCE_MATRICOLA"));
		retValue.setDoceNome(rs.getString("DOCE_NOME"));
		retValue.setDoceCognome(rs.getString("DOCE_COGNOME"));
		retValue.setDataAppello(rs.getDate("DATA_INIZIO_APP"));
		
		return retValue;
	}
	
	/*Il metodo estrae gli intervalli di data necessari successivamente per il controllo*/
	public DataCalendario getAll(){
		
		DataCalendario retValue = new DataCalendario();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select "); 
		sb.append("* "); 
		sb.append("from utility "); 
		
		try {
			Statement statement = getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sb.toString());
			if(rs.next()) {
				retValue.setDataMin(rs.getInt("DATA_MIN"));
				retValue.setDataMax(rs.getInt("DATA_MAX"));
				retValue.setOneDaymin(rs.getInt("ONE_DAYMIN"));
				retValue.setOneDaymax(rs.getInt("ONE_DAYMAX"));
				retValue.setFourDaymin(rs.getInt("FOUR_DAYMIN"));
				retValue.setFourDaymax(rs.getInt("FOUR_DAYMAX"));
				
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return retValue;
	}
	
public DataCalendario getAllStrPr(){
		
		DataCalendario retValue = new DataCalendario();
		
		
		try {
			CallableStatement cs;
			cs = this.getConnection().prepareCall("{call sl_data()}");
			
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				retValue.setDataMin(rs.getInt("DATA_MIN"));
				retValue.setDataMax(rs.getInt("DATA_MAX"));
				retValue.setOneDaymin(rs.getInt("ONE_DAYMIN"));
				retValue.setOneDaymax(rs.getInt("ONE_DAYMAX"));
				retValue.setFourDaymin(rs.getInt("FOUR_DAYMIN"));
				retValue.setFourDaymax(rs.getInt("FOUR_DAYMAX"));
				
			}
	}catch (SQLException e) {
		e.printStackTrace();
	}
		return retValue;
	}

}
