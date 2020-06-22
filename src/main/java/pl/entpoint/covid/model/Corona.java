package pl.entpoint.covid.model;

/**
 * @author Mateusz Dąbek
 * @created 22 cze 2020
 * 
 */

public class Corona {

	private String province;
	private String country;
	private int latestTotalCases;
	private int lastDayDiffrent;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

		
	public int getLastDayDiffrent() {
		return lastDayDiffrent;
	}

	public void setLastDayDiffrent(int lastDayDiffrent) {
		this.lastDayDiffrent = lastDayDiffrent;
	}	
}
