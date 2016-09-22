package it.mf.Model;

public class Ad implements Comparable<Ad> {
	
	private Integer adId;
	private String adGenCod;
	private String adGenDes; //Nome dell'appello
	private String tipoIscrDes; //Orale o Scritto
	private String tipoIscrCod; //Sigla (S:scritto/O:Orale)
	
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public String getAdGenCod() {
		return adGenCod;
	}
	public void setAdGenCod(String adGenCod) {
		this.adGenCod = adGenCod;
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
	public String getTipoIscrCod() {
		return tipoIscrCod;
	}
	public void setTipoIscrCod(String tipoIscrCod) {
		this.tipoIscrCod = tipoIscrCod;
	}
	public String getCompletaDesAd() {
		return "[" + adGenCod + "]" + " " + adGenDes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ad other = (Ad) obj;
		if (adId == null) {
			if (other.adId != null)
				return false;
		} else if (!adId.equals(other.adId))
			return false;
		if (adGenCod == null) {
			if (other.adGenCod != null)
				return false;
		} else if (!adGenCod.equals(other.adGenCod))
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
		if (tipoIscrCod == null) {
			if (other.tipoIscrCod != null)
				return false;
		} else if (!tipoIscrCod.equals(other.tipoIscrCod))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ad [adId=" + adId + ", adGenCod=" + adGenCod + ", adGenDes=" + adGenDes + ", tipoIscrDes=" + tipoIscrDes + ", tipoIscrCod=" + tipoIscrCod + "]";
	}
	@Override
	public int compareTo(Ad o) {
		
		int r = adGenDes.compareToIgnoreCase(o.adGenDes);
		if (r != 0)
			return r;
		return adId-o.adId;
	}

}
