package it.mf.Model;

public class Docente implements Comparable<Docente>{
	
		private Integer doceMatricola;
		private Integer doceId;
		private String doceCognome;
		private String doceNome;
		
		public Integer getDoceMatricola() {
			return doceMatricola;
		}
		public void setDoceMatricola(Integer doceMatricola) {
			this.doceMatricola = doceMatricola;
		}
		public Integer getDoceId() {
			return doceId;
		}
		public void setDoceId(Integer doceId) {
			this.doceId = doceId;
		}
		public String getDoceCognome() {
			return doceCognome;
		}
		public void setDoceCognome(String doceCognome) {
			this.doceCognome = doceCognome;
		}
		public String getDoceNome() {
			return doceNome;
		}
		public void setDoceNome(String doceNome) {
			this.doceNome = doceNome;
		}
		public String getNomeCompleto() {
			return doceCognome + " " + doceNome;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Docente other = (Docente) obj;
			if (doceMatricola == null) {
				if (other.doceMatricola != null)
					return false;
			} else if (!doceMatricola.equals(other.doceMatricola))
				return false;
			if (doceCognome == null) {
				if (other.doceCognome != null)
					return false;
			} else if (!doceCognome.equals(other.doceCognome))
				return false;
			if (doceNome == null) {
				if (other.doceNome != null)
					return false;
			} else if (!doceNome.equals(other.doceNome))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Docente [doceMatricola=" + doceMatricola + ", doceId=" + doceId + ", doceCognome=" + doceCognome + ", doceNome=" + doceNome + "]";
		}
		@Override
		public int compareTo(Docente o) {
			
			int r = doceCognome.compareToIgnoreCase(o.doceCognome);
			if (r != 0)
				return r;
			return doceMatricola-o.doceMatricola;
		}

}
