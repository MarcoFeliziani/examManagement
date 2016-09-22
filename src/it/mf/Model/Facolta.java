package it.mf.Model;


public class Facolta implements Comparable<Facolta> {
	
	private Integer facId;
	private String facCod;
	private String facDes;
	
	public Integer getFacId() {
		return facId;
	}
	public void setFacId(Integer facId) {
		this.facId = facId;
	}
	public String getFacCod() {
		return facCod;
	}
	public void setFacCod(String facCod) {
		this.facCod = facCod;
	}
	public String getFacDes() {
		return facDes;
	}
	public void setFacDes(String facDes) {
		this.facDes = facDes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facolta other = (Facolta) obj;
		if (facId == null) {
			if (other.facId != null)
				return false;
		} else if (!facId.equals(other.facId))
			return false;
		if (facCod == null) {
			if (other.facCod != null)
				return false;
		} else if (!facCod.equals(other.facCod))
			return false;
		if (facDes == null) {
			if (other.facDes != null)
				return false;
		} else if (!facDes.equals(other.facDes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Facolta [facId=" + facId + ", facCod=" + facCod + ", facDes=" + facDes + "]";
	}
	@Override
	public int compareTo(Facolta o) {
		
		int r = facCod.compareToIgnoreCase(o.facCod);
		if (r != 0)
			return r;
		return facId-o.facId;
	}

}
