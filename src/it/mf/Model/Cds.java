package it.mf.Model;

public class Cds implements Comparable<Cds> {
	
	private Integer cdsId;
	private String cdsCod;
	private String cdsDes;
	
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
		Cds other = (Cds) obj;
		if (cdsId == null) {
			if (other.cdsId != null)
				return false;
		} else if (!cdsId.equals(other.cdsId))
			return false;
		if (cdsCod == null) {
			if (other.cdsCod != null)
				return false;
		} else if (!cdsCod.equals(other.cdsCod))
			return false;
		if (cdsDes == null) {
			if (other.cdsDes != null)
				return false;
		} else if (!cdsDes.equals(other.cdsDes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cds [cdsId=" + cdsId + ", cdsCod=" + cdsCod + ", cdsDes=" + cdsDes + "]";
	}
	@Override
	public int compareTo(Cds o) {
		
		int r = cdsDes.compareToIgnoreCase(o.cdsDes);
		if (r != 0)
			return r;
		return cdsId-o.cdsId;
	}

}
