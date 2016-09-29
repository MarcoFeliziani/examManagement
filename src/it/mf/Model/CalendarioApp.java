package it.mf.Model;

import java.util.Date;


public class CalendarioApp implements Comparable<CalendarioApp>{
	
	private Integer appId;
	private Integer facId;
	private String facDes;
	private Integer cdsId;
	private String cdsCod;
	private String cdsDes;
	private Integer adId;
	private String adGenDes; //Nome dell'appello
	private String tipoIscrDes; //Orale o Scritto
	private Integer doceId; 
	private String doceNome;
	private String doceCognome;
	private String doceRuolo;
	private Date dataAppello;
	private Date aDataAppello;
	private Integer appIdVs;
	private Integer facIdVs;
	private String facDesVs;
	private Integer cdsIdVs;
	private String cdsCodVs;
	private String cdsDesVs;
	private Integer adIdVs;
	private String adGenDesVs; //Nome dell'appello
	private String tipoIscrDesVs; //Orale o Scritto
	private Integer doceIdVs; 
	private String doceNomeVs;
	private String doceCognomeVs;
	private String doceRuoloVs;
	private Date dataAppelloVs;
	private Integer tr;
	
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public Integer getTr() {
		return tr;
	}
	public void setTr(Integer tr) {
		this.tr = tr;
	}
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
	public Integer getDoceId() {
		return doceId;
	}
	public void setDoceId(Integer doceId) {
		this.doceId = doceId;
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
	public String getCompletaDesCds() {
		return "[" + cdsCod + "]" + " " + cdsDes;
	}
	public Integer getFacIdVs() {
		return facIdVs;
	}
	public Integer getAppIdVs() {
		return appIdVs;
	}
	public void setAppIdVs(Integer appIdVs) {
		this.appIdVs = appIdVs;
	}
	public void setFacIdVs(Integer facIdVs) {
		this.facIdVs = facIdVs;
	}
	public String getFacDesVs() {
		return facDesVs;
	}
	public void setFacDesVs(String facDesVs) {
		this.facDesVs = facDesVs;
	}
	public Integer getCdsIdVs() {
		return cdsIdVs;
	}
	public void setCdsIdVs(Integer cdsIdVs) {
		this.cdsIdVs = cdsIdVs;
	}
	public String getCdsCodVs() {
		return cdsCodVs;
	}
	public void setCdsCodVs(String cdsCodVs) {
		this.cdsCodVs = cdsCodVs;
	}
	public String getCdsDesVs() {
		return cdsDesVs;
	}
	public void setCdsDesVs(String cdsDesVs) {
		this.cdsDesVs = cdsDesVs;
	}
	public Integer getAdIdVs() {
		return adIdVs;
	}
	public void setAdIdVs(Integer adIdVs) {
		this.adIdVs = adIdVs;
	}
	public String getAdGenDesVs() {
		return adGenDesVs;
	}
	public void setAdGenDesVs(String adGenDesVs) {
		this.adGenDesVs = adGenDesVs;
	}
	public String getTipoIscrDesVs() {
		return tipoIscrDesVs;
	}
	public void setTipoIscrDesVs(String tipoIscrDesVs) {
		this.tipoIscrDesVs = tipoIscrDesVs;
	}
	public Integer getDoceIdVs() {
		return doceIdVs;
	}
	public void setDoceIdVs(Integer doceIdVs) {
		this.doceIdVs = doceIdVs;
	}
	public String getDoceNomeVs() {
		return doceNomeVs;
	}
	public void setDoceNomeVs(String doceNomeVs) {
		this.doceNomeVs = doceNomeVs;
	}
	public String getDoceCognomeVs() {
		return doceCognomeVs;
	}
	public void setDoceCognomeVs(String doceCognomeVs) {
		this.doceCognomeVs = doceCognomeVs;
	}
	public String getDoceRuoloVs() {
		return doceRuoloVs;
	}
	public void setDoceRuoloVs(String doceRuoloVs) {
		this.doceRuoloVs = doceRuoloVs;
	}
	public Date getDataAppelloVs() {
		return dataAppelloVs;
	}
	public void setDataAppelloVs(Date dataAppelloVs) {
		this.dataAppelloVs = dataAppelloVs;
	}
	public String getNomeCompletoVs() {
		return doceNomeVs + " " + doceCognomeVs;
	}
	public String getCompletaDesCdsVs() {
		return "[" + cdsCodVs + "]" + " " + cdsDesVs;
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
		if (doceId == null) {
			if (other.doceId != null)
				return false;
		} else if (!doceId.equals(other.doceId))
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
		return "CalendarioApp [facId=" + facId + ", facDes=" + facDes + ", appId=" + appId + ", cdsId=" + cdsId + ", cdsDes=" + cdsDes + 
				", adId=" + adId + ", adGenDes=" + adGenDes + ", tipoIscrDes=" + tipoIscrDes + ", doceId=" + doceId + 
				", doceCognome=" + doceCognome + ", dataAppello=" + dataAppello + ", doceRuolo=" + doceRuolo + 
				", facIdVs=" + facIdVs + ", facDesVs=" + facDesVs + ", appIdVs=" + appIdVs + ", cdsIdVs=" + cdsIdVs + ", cdsDesVs=" + cdsDesVs + 
				", adIdVs=" + adIdVs + ", adGenDesVs=" + adGenDesVs + ", tipoIscrDesVs=" + tipoIscrDesVs + ", doceIdVs=" + doceIdVs + 
				", doceCognomeVs=" + doceCognomeVs + ", doceRuoloVs=" + doceRuoloVs +"]";
	}
	@Override
	public int compareTo(CalendarioApp o) {
		
		int r = facDes.compareToIgnoreCase(o.facDes);
		if (r != 0)
			return r;
		return appId-o.appId;
	}

}