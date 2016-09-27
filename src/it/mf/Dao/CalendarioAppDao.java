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
	
	public List<CalendarioApp> getListaAppelli(Integer facId, Integer cdsId, Integer adId, Integer doceMatricola, String doceRuolo, Date dataAppello, Date aDataAppello){
		
//		DataCalendario dao = getAllStrPr(); //Ottengo un oggetto con tutti gli intervalli di data
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select ce.FAC_ID, ce.FAC_DES, ce.CDS_ID, ce.CDS_COD, ce.CDS_DES, ce.AD_ID, ce.AD_GEN_DES, ce.TIPO_ISCR_DES, ce.DOCE_MATRICOLA, ");
		sb.append("ce.DOCE_NOME, ce.DOCE_COGNOME, ce.DATA_INIZIO_APP ");
	if(doceRuolo != null){
		sb.append(", ca.DES ");
	}
		sb.append("from v10_rpt_calendario_esami ce ");
	if(doceRuolo != null){
		sb.append("inner join v10_rpt_commissioni_app ca on ce.APP_ID=ca.APP_ID ");
	}
		sb.append("where ");
		sb.append("ce.FAC_ID = ? ");
		
		if(cdsId > 0)
			sb.append("and ce.CDS_ID = " + cdsId + " ");
		
		if(adId > 0)
			sb.append("and ce.AD_ID = " + adId + " ");
		
		if(doceRuolo != null && !doceRuolo.isEmpty())
			sb.append("and lower(ca.RUOLO_COD) like ('%" + doceRuolo.toLowerCase().replaceAll("'","''") + "%' ) ");
			
		
//		if(id != 0){
//			sb.append("and DATA_INIZIO_APP between ? and ? ");
//			
//		} else{
			
			if(doceMatricola > 0)
				sb.append("and ce.DOCE_MATRICOLA = " + doceMatricola + " ");
			
			if(aDataAppello != null){
				sb.append("and ce.DATA_INIZIO_APP between ? and ? ");
			}
			else if((dataAppello != null)){
				sb.append("and ce.DATA_INIZIO_APP = ? ");
			}
			else{
				sb.append("and ce.DATA_INIZIO_APP >= curDate() ");
			}
//		}
		
		sb.append("ORDER BY DATA_INIZIO_APP ");
		
		List<CalendarioApp> retValue = new ArrayList<CalendarioApp>();
		
		try {
			
			PreparedStatement ps = getConnection().prepareStatement(sb.toString());
			ps.setInt(1, facId);

			if(aDataAppello != null){
				ps.setDate(2, new java.sql.Date(dataAppello.getTime()));
				ps.setDate(3, new java.sql.Date(aDataAppello.getTime()));
			}
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
				calendarioApp.setDoceRuolo(rs.getString("DES"));
				calendarioApp.setDataAppello(rs.getDate("DATA_INIZIO_APP"));
				calendarioApp.setTr(0);
				retValue.add(calendarioApp);
				
				
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	
	/*CONTROLLO DI SOVRAPPOSIZIONE*/
	public List<CalendarioApp> getCheckOverlay(Integer facId, Integer cdsId, Integer adId, Integer doceMatricola, String doceRuolo, Date dataAppello, Date aDataAppello, Integer idDoc){
		
		
		Integer count = 0;
		/*CONTROLLO SE ESISTE ALMENO UN RECORD PER IL DOCENTE SELEZIONATO IN UNA CERTA DATA*/
		StringBuffer sb = new StringBuffer();
		
		sb.append("select count(*) num_record ");
		sb.append("from v10_rpt_calendario_esami ce ");
	if(doceRuolo != null){
		sb.append("inner join v10_rpt_commissioni_app ca on ce.APP_ID=ca.APP_ID ");
	}
		sb.append("where ");
		if(aDataAppello != null){
			sb.append("ce.DATA_INIZIO_APP between ? and ? ");
		}
		else if((dataAppello != null)){
			sb.append("ce.DATA_INIZIO_APP = ? ");
		}
		
		if(facId > 0)
			sb.append("and ce.FAC_ID = " + facId + " ");
		
		if(cdsId > 0)
			sb.append("and ce.CDS_ID = " + cdsId + " ");
		
		if(adId > 0)
			sb.append("and ce.AD_ID = " + adId + " ");
		
		if(doceMatricola > 0)
			sb.append("and ce.DOCE_MATRICOLA = " + doceMatricola + " ");
		
		if(doceRuolo != null && !doceRuolo.isEmpty())
			sb.append("and lower(ca.RUOLO_COD) like ('%" + doceRuolo.toLowerCase().replaceAll("'","''") + "%' ) ");
		
		List<CalendarioApp> retValue = new ArrayList<CalendarioApp>();
		
		try {
			
			PreparedStatement ps = getConnection().prepareStatement(sb.toString());

			if(aDataAppello != null){
				ps.setDate(1, new java.sql.Date(dataAppello.getTime()));
				ps.setDate(2, new java.sql.Date(aDataAppello.getTime()));
			}
			else if(dataAppello != null) {
				ps.setDate(1, new java.sql.Date(dataAppello.getTime()));
			}
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
				
						count = rs.getInt("num_record");
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
			if(count > 0){ //SE IL DOCENTE HA ALMENO UN APPELLO IN DATA
				
				DataCalendario dao = getAllStrPr(); //Ottengo un oggetto con tutti gli intervalli di data
				Calendar datamin = Calendar.getInstance();
				datamin.setTime(dataAppello);
				Calendar dataMAX = Calendar.getInstance();
				
				if(aDataAppello != null){
					dataMAX.setTime(aDataAppello);
				}else{
					dataMAX.setTime(dataAppello);
				}
				
				datamin.add(Calendar.DATE, dao.getDataMin());
				dataMAX.add(Calendar.DATE, dao.getDataMax());
					
				try {
					CallableStatement cs;
					cs = this.getConnection().prepareCall("{call check_overlay(?,?,?,'*',?,?)}");   //call check_doce(0,0,0,1559,'*',20160924,0);
					
					cs.setInt(1, facId);
					cs.setInt(2, cdsId);
					cs.setInt(3, adId);
					
//					if(doceRuolo != null){
//						cs.setString(5, doceRuolo);
//					} else{
//						cs.setString(5, "*");
//					}
					cs.setDate(4, new java.sql.Date(datamin.getTimeInMillis()));
					cs.setDate(5, new java.sql.Date(dataMAX.getTimeInMillis()));
					
					ResultSet rs = cs.executeQuery();
					
					CalendarioApp calendarioApp = null;
					
					while(rs.next()) {
						
						calendarioApp = new CalendarioApp();
						
						calendarioApp.setFacId(rs.getInt("ce.FAC_ID"));
						calendarioApp.setFacDes(rs.getString("ce.FAC_DES"));
						calendarioApp.setCdsId(rs.getInt("ce.CDS_ID"));
						calendarioApp.setCdsCod(rs.getString("ce.CDS_COD"));
						calendarioApp.setCdsDes(rs.getString("ce.CDS_DES"));
						calendarioApp.setAdId(rs.getInt("ce.AD_ID"));
						calendarioApp.setAdGenDes(rs.getString("ce.AD_GEN_DES"));
						calendarioApp.setTipoIscrDes(rs.getString("ce.TIPO_ISCR_DES"));
						calendarioApp.setDoceMatricola(rs.getInt("ce.DOCE_MATRICOLA"));
						calendarioApp.setDoceNome(rs.getString("ce.DOCE_NOME"));
						calendarioApp.setDoceCognome(rs.getString("ce.DOCE_COGNOME"));
						calendarioApp.setDoceRuolo(rs.getString("ca.DES"));
						calendarioApp.setDataAppello(rs.getDate("DATA_INIZIO_APP"));
						calendarioApp.setTr(0);
						Integer DOCE_MATRICOLA = rs.getInt("DOCE_MATRICOLA");
						
						if(!DOCE_MATRICOLA.equals(idDoc)){
							
							Date DATA_INIZIO_APP= rs.getDate("DATA_INIZIO_APP");
							Calendar data_inizio_app = Calendar.getInstance();
							data_inizio_app.setTime(DATA_INIZIO_APP);
							Calendar dataAppCal = Calendar.getInstance();
							dataAppCal.setTime(dataAppello);
							
							Calendar dataAppCal2 = Calendar.getInstance();
							
							if(aDataAppello != null){
								dataAppCal2.setTime(aDataAppello);
							}
							
							Calendar datamin1 = Calendar.getInstance();
							datamin1.setTime(dataAppello);
							Calendar dataMAX1 = Calendar.getInstance();
							
							if(aDataAppello != null){
								dataMAX1.setTime(aDataAppello);
							}else{
								dataMAX1.setTime(dataAppello);
							}
							
							Calendar datamin4 = Calendar.getInstance();
							datamin4.setTime(dataAppello);
							Calendar dataMAX4 = Calendar.getInstance();
							
							if(aDataAppello != null){
								dataMAX4.setTime(aDataAppello);
							}else{
								dataMAX4.setTime(dataAppello);
							}
							
							datamin1.add(Calendar.DATE, dao.getOneDaymin());
							dataMAX1.add(Calendar.DATE, dao.getOneDaymax());
							datamin4.add(Calendar.DATE, dao.getFourDaymin());
							dataMAX4.add(Calendar.DATE, dao.getFourDaymax());
							
							/*Setta il tipo record di tutti gli appelli dentro l'intervallo di data e con docente diverso da quello selezionato nel filtro*/
							
							if((data_inizio_app.equals(dataAppCal))||( (data_inizio_app.after(dataAppCal))&&(data_inizio_app.before(dataAppCal2)) )){
								calendarioApp.setTr(1); //AVVISO DI TIPO 1 (appello nello stesso giorno)
							}
							else if((data_inizio_app.after(datamin1))&&(data_inizio_app.before(dataMAX1))){
								calendarioApp.setTr(2); //AVVISO DI TIPO 2 (appello a distanza di un giorno)
							}
							else if((data_inizio_app.after(datamin4))&&(data_inizio_app.before(dataMAX4))){
								calendarioApp.setTr(3); //AVVISO DI TIPO 3 (appello a distanza di 4 giorni)
							}
							
							
							
						}
						
						retValue.add(calendarioApp);
					}
					}catch (SQLException e) {
						e.printStackTrace();
					}

				
			}

		return retValue;
		
	}
	
	/*Selezione i record di un docente e quelli dei suoi docenti "rivali", con lo stesso filtro*/
	public List<CalendarioApp> getCheckOverlayDoc(Integer facId, Integer cdsId, Integer adId, Integer doceMatricola, String doceRuolo, Date dataAppello, Date aDataAppello, Integer idDoc, String[] multiSelected){
		
		List<CalendarioApp> retValue = new ArrayList<CalendarioApp>();
		//HO SBAGLIATO CAZZOOOOOOO! 1) CONTROLLARE SE IL DOCENTE SELEZIONATO HA ALMENO UN APPELLO IN QUELLA DATA. 
		// 2) CONTROLLARE GLI APPELLI NELL'INTERVALLO DI TEMPO DEFINITO
		Integer count = 0;
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("select count(*) num_record ");
		sb.append("from v10_rpt_calendario_esami ce ");
		sb.append("inner join v10_rpt_commissioni_app ca on ce.APP_ID=ca.APP_ID ");
		sb.append("where ");
		
		if(aDataAppello != null){
			sb.append("ce.DATA_INIZIO_APP between ? and ? ");
		}
		else if((dataAppello != null)){
			sb.append("ce.DATA_INIZIO_APP = ? ");
		}
		
		if(facId > 0)
			sb.append("and ce.FAC_ID = " + facId + " ");
		
		if(cdsId > 0)
			sb.append("and ce.CDS_ID = " + cdsId + " ");
		
		if(adId > 0)
			sb.append("and ce.AD_ID = " + adId + " ");
		
		if(doceMatricola > 0)
			sb.append("and ce.DOCE_MATRICOLA = " + doceMatricola + " ");
		
		if(doceRuolo != null && !doceRuolo.isEmpty())
			sb.append("and lower(ca.RUOLO_COD) like ('%" + doceRuolo.toLowerCase().replaceAll("'","''") + "%' ) ");
		
		
		try {
			
			PreparedStatement ps = getConnection().prepareStatement(sb.toString());

			if(aDataAppello != null){
				ps.setDate(1, new java.sql.Date(dataAppello.getTime()));
				ps.setDate(2, new java.sql.Date(aDataAppello.getTime()));
			}
			else if(dataAppello != null) {
				ps.setDate(1, new java.sql.Date(dataAppello.getTime()));
			}
			
			ResultSet rs = ps.executeQuery();
			
				if(rs.next()) {
				
						count = rs.getInt("num_record");
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(count > 0){ //SE IL DOCENTE HA ALMENO UN APPELLO IN DATA
			
			DataCalendario dao = getAllStrPr(); //Ottengo un oggetto con tutti gli intervalli di data
			Calendar datamin = Calendar.getInstance();
			datamin.setTime(dataAppello);
			Calendar dataMAX = Calendar.getInstance();
			
			if(aDataAppello != null){
				dataMAX.setTime(aDataAppello);
			}else{
				dataMAX.setTime(dataAppello);
			}
			
			datamin.add(Calendar.DATE, dao.getDataMin());
			dataMAX.add(Calendar.DATE, dao.getDataMax());
			
			StringBuffer bs = new StringBuffer();
			
			/*RICAVO TUTTI GLI APPELLI DEL DOCENTE SELEZIONATO E DEI SUOI "AVVERSARI" CON LO STESSO FILTRO*/
			bs.append("select ce.FAC_ID, ce.FAC_DES, ce.CDS_ID, ce.CDS_COD, ce.CDS_DES, ce.AD_ID, ce.AD_GEN_DES, ce.TIPO_ISCR_DES, ce.DOCE_MATRICOLA, ");
			bs.append("ce.DOCE_NOME, ce.DOCE_COGNOME, ce.DATA_INIZIO_APP ");
			bs.append(", ca.DES ");
			bs.append("from v10_rpt_calendario_esami ce ");
			bs.append("inner join v10_rpt_commissioni_app ca on ce.APP_ID=ca.APP_ID ");
			bs.append("where ");
			bs.append("ce.FAC_ID = ? ");
			
			if(cdsId > 0)
				bs.append("and ce.CDS_ID = " + cdsId + " ");
			
			if(adId > 0)
				bs.append("and ce.AD_ID = " + adId + " ");
			
			if(doceRuolo != null && !doceRuolo.isEmpty())
				bs.append("and lower(ca.RUOLO_COD) like ('%" + doceRuolo.toLowerCase().replaceAll("'","''") + "%' ) ");
			
			if(doceMatricola > 0)
				bs.append("and ce.DOCE_MATRICOLA = " + doceMatricola + " ");
			
			for(String i: multiSelected ){
				
				bs.append("or ce.DOCE_MATRICOLA = " + i + " ");
			}
				
			bs.append("and ce.DATA_INIZIO_APP between ? and ? ");

			bs.append("ORDER BY DATA_INIZIO_APP ");
			
			try {
				
				PreparedStatement ps = getConnection().prepareStatement(bs.toString());
				ps.setInt(1, facId);

				if(aDataAppello != null){
					ps.setDate(2, new java.sql.Date(datamin.getTimeInMillis()));
					ps.setDate(3, new java.sql.Date(dataMAX.getTimeInMillis()));
				}
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
					calendarioApp.setDoceRuolo(rs.getString("DES"));
					calendarioApp.setDataAppello(rs.getDate("DATA_INIZIO_APP"));
					calendarioApp.setTr(0);
					
					Integer DOCE_MATRICOLA = rs.getInt("DOCE_MATRICOLA");
					
					if(!DOCE_MATRICOLA.equals(idDoc)){
						
						Date DATA_INIZIO_APP= rs.getDate("DATA_INIZIO_APP");
						Calendar data_inizio_app = Calendar.getInstance();
						data_inizio_app.setTime(DATA_INIZIO_APP);
						Calendar dataAppCal = Calendar.getInstance();
						dataAppCal.setTime(dataAppello);
						
						Calendar dataAppCal2 = Calendar.getInstance();
						
						if(aDataAppello != null){
							dataAppCal2.setTime(aDataAppello);
						}
						
						Calendar datamin1 = Calendar.getInstance();
						datamin1.setTime(dataAppello);
						Calendar dataMAX1 = Calendar.getInstance();
						
						if(aDataAppello != null){
							dataMAX1.setTime(aDataAppello);
						}else{
							dataMAX1.setTime(dataAppello);
						}
						
						Calendar datamin4 = Calendar.getInstance();
						datamin4.setTime(dataAppello);
						Calendar dataMAX4 = Calendar.getInstance();
						
						if(aDataAppello != null){
							dataMAX4.setTime(aDataAppello);
						}else{
							dataMAX4.setTime(dataAppello);
						}
						
						datamin1.add(Calendar.DATE, dao.getOneDaymin());
						dataMAX1.add(Calendar.DATE, dao.getOneDaymax());
						datamin4.add(Calendar.DATE, dao.getFourDaymin());
						dataMAX4.add(Calendar.DATE, dao.getFourDaymax());
						
						/*Setta il tipo record di tutti gli appelli dentro l'intervallo di data e con docente diverso da quello selezionato nel filtro*/
						
						if((data_inizio_app.equals(dataAppCal))||( (data_inizio_app.after(dataAppCal))&&(data_inizio_app.before(dataAppCal2)) )){
							calendarioApp.setTr(1); //AVVISO DI TIPO 1 (appello nello stesso giorno)
						}
						else if((data_inizio_app.after(datamin1))&&(data_inizio_app.before(dataMAX1))){
							calendarioApp.setTr(2); //AVVISO DI TIPO 2 (appello a distanza di un giorno)
						}
						else if((data_inizio_app.after(datamin4))&&(data_inizio_app.before(dataMAX4))){
							calendarioApp.setTr(3); //AVVISO DI TIPO 3 (appello a distanza di 4 giorni)
						}
						
						
						
					}
					
					retValue.add(calendarioApp);
				}
				}catch (SQLException e) {
					e.printStackTrace();
				}

			
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
