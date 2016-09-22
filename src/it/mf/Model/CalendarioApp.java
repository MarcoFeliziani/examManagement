package it.mf.Model;

import java.util.Date;


public class CalendarioApp {
	
	private Integer facId;
	private String facDes;
	private Integer cdsId;
	private String cdsCod;
	private String cdsDes;
	private Integer adId;
	private String adGenDes; //Nome dell'appello
	private String tipoIscrDes; //Orale o Scritto
	private Integer doceMatricola; 
	private String doceNome;
	private String doceCognome;
	private String doceRuolo;
	private Date dataAppello;
	private Date aDataAppello;
	private Integer tr;
	
	public Integer getFacId() {
		return facId;
	}
	public void setFacId(Integer facId) {
		this.facId = facId;
	}
	public String getFacDes() {
		return facDes;
	}
	public void setFacDes(String facDes) {
		this.facDes = facDes;
	}
	public Integer getCdsId() {
		return cdsId;
	}
	public void setCdsId(Integer cdsId) {
		this.cdsId = cdsId;
	}
	public String getCdsCod() {
		return cdsCod;
	}
	public void setCdsCod(String cdsCod) {
		this.cdsCod = cdsCod;
	}
	public String getCdsDes() {
		return cdsDes;
	}
	public void setCdsDes(String cdsDes) {
		this.cdsDes = cdsDes;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getAdGenDes() {
		return adGenDes;
	}
	public void setAdGenDes(String adGenDes) {
		this.adGenDes = adGenDes;
	}
	public String getTipoIscrDes() {
		return tipoIscrDes;
	}
	public void setTipoIscrDes(String tipoIscrDes) {
		this.tipoIscrDes = tipoIscrDes;
	}
	public Integer getDoceMatricola() {
		return doceMatricola;
	}
	public void setDoceMatricola(Integer doceMatricola) {
		this.doceMatricola = doceMatricola;
	}
	public String getDoceNome() {
		return doceNome;
	}
	public void setDoceNome(String doceNome) {
		this.doceNome = doceNome;
	}
	public String getDoceCognome() {
		return doceCognome;
	}
	public void setDoceCognome(String doceCognome) {
		this.doceCognome = doceCognome;
	}
	public String getDoceRuolo() {
		return doceRuolo;
	}
	public void setDoceRuolo(String doceRuolo) {
		this.doceRuolo = doceRuolo;
	}
	public String getNomeCompleto() {
		return doceNome + " " + doceCognome;
	}
	public Date getDataAppello() {
		return dataAppello;
	}
	public void setDataAppello(Date dataAppello) {
		this.dataAppello = dataAppello;
	}
	public Date getADataAppello() {
		return aDataAppello;
	}
	public void setADataAppello(Date aDataAppello) {
		this.aDataAppello = aDataAppello;
	}
	public Integer getTr() {
		return tr;
	}
	public void setTr(Integer tr) {
		this.tr = tr;
	}
	public String getCompletaDesCds() {
		return "[" + cdsCod + "]" + " " + cdsDes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarioApp other = (CalendarioApp) obj;
		if (facId == null) {
			if (other.facId != null)
				return false;
		} else if (!facId.equals(other.facId))
			return false;
		if (dataAppello == null) {
			if (other.dataAppello != null)
				return false;
		} else if (!dataAppello.equals(other.dataAppello))
			return false;
		if (facDes == null) {
			if (other.facDes != null)
				return false;
		} else if (!facDes.equals(other.facDes))
			return false;
		if (cdsId == null) {
			if (other.cdsId != null)
				return false;
		} else if (!cdsId.equals(other.cdsId))
			return false;
		if (cdsDes == null) {
			if (other.cdsDes != null)
				return false;
		} else if (!cdsDes.equals(other.cdsDes))
			return false;
		if (adId == null) {
			if (other.adId != null)
				return false;
		} else if (!adId.equals(other.adId))
			return false;
		if (adGenDes == null) {
			if (other.adGenDes != null)
				return false;
		} else if (!adGenDes.equals(other.adGenDes))
			return false;
		if (tipoIscrDes == null) {
			if (other.tipoIscrDes != null)
				return false;
		} else if (!tipoIscrDes.equals(other.tipoIscrDes))
			return false;
		if (doceMatricola == null) {
			if (other.doceMatricola != null)
				return false;
		} else if (!doceMatricola.equals(other.doceMatricola))
			return false;
		if (doceNome == null) {
			if (other.doceNome != null)
				return false;
		} else if (!doceNome.equals(other.doceNome))
			return false;
		if (doceCognome == null) {
			if (other.doceCognome != null)
				return false;
		} else if (!doceCognome.equals(other.doceCognome))
			return false;
		if (dataAppello == null) {
			if (other.dataAppello != null)
				return false;
		} else if (!dataAppello.equals(other.dataAppello))
			return false;
		if (aDataAppello == null) {
			if (other.aDataAppello != null)
				return false;
		} else if (!aDataAppello.equals(other.aDataAppello))
			return false;
		if (doceRuolo == null) {
			if (other.doceRuolo != null)
				return false;
		} else if (!doceRuolo.equals(other.doceRuolo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CalendarioApp [facId=" + facId + ", facDes=" + facDes + ", cdsId=" + cdsId + ", cdsDes=" + cdsDes + 
				", adId=" + adId + ", adGenDes=" + adGenDes + ", tipoIscrDes=" + tipoIscrDes + ", doceMatricola=" + doceMatricola + 
				", doceCognome=" + doceCognome + ", dataAppello=" + dataAppello + ", aDataAppello=" + aDataAppello + ", tr=" + tr + ", doceRuolo=" + doceRuolo + "]";
	}

}
