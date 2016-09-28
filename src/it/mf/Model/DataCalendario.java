package it.mf.Model;

public class DataCalendario {
	
	private Integer dataMin;
	private Integer dataMax;
	private Integer oneDaymin;
	private Integer oneDaymax;
	private Integer fourDaymin;
	private Integer fourDaymax;
	private Integer distance;
	
	public Integer getDataMin() {
		return dataMin;
	}
	public void setDataMin(Integer dataMin) {
		this.dataMin = dataMin;
	}
	public Integer getDataMax() {
		return dataMax;
	}
	public void setDataMax(Integer dataMax) {
		this.dataMax = dataMax;
	}
	public Integer getOneDaymin() {
		return oneDaymin;
	}
	public void setOneDaymin(Integer oneDaymin) {
		this.oneDaymin = oneDaymin;
	}
	public Integer getOneDaymax() {
		return oneDaymax;
	}
	public void setOneDaymax(Integer oneDaymax) {
		this.oneDaymax = oneDaymax;
	}
	public Integer getFourDaymin() {
		return fourDaymin;
	}
	public void setFourDaymin(Integer fourDaymin) {
		this.fourDaymin = fourDaymin;
	}
	public Integer getFourDaymax() {
		return fourDaymax;
	}
	public void setFourDaymax(Integer fourDaymax) {
		this.fourDaymax = fourDaymax;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "Docente [dataMin=" + dataMin + ", dataMax=" + dataMax + ", oneDaymin=" + oneDaymin +  ", oneDaymax=" + oneDaymax + ", fourDaymin=" + fourDaymin + ", fourDaymax=" + fourDaymax +  ", distance=" + distance + "]";
	}
	

}
