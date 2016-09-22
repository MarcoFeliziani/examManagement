package it.mf.Model;

public class Ruolo implements Comparable<Ruolo> {
	
	private String ruoloCod;
	private String ruoloDes;
	
	public String getRuoloCod() {
		return ruoloCod;
	}
	public void setRuoloCod(String ruoloCod) {
		this.ruoloCod = ruoloCod;
	}
	public String getRuoloDes() {
		return ruoloDes;
	}
	public void setRuoloDes(String ruoloDes) {
		this.ruoloDes = ruoloDes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruolo other = (Ruolo) obj;
		if (ruoloCod == null) {
			if (other.ruoloCod != null)
				return false;
		} else if (!ruoloCod.equals(other.ruoloCod))
			return false;
		if (ruoloDes == null) {
			if (other.ruoloDes != null)
				return false;
		} else if (!ruoloDes.equals(other.ruoloDes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ruolo [ruoloCod=" + ruoloCod + ", ruoloDes=" + ruoloDes + "]";
	}
	@Override
	public int compareTo(Ruolo o) {
		
		int r = ruoloDes.compareToIgnoreCase(o.ruoloDes);
		if (r != 0)
			return r;
		return 0;
	}


}
