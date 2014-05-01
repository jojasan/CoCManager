package model;


public class Donations {
	private Period period;
	private int donations;
	
	public int getDonations() {
		return donations;
	}
	
	public void setDonations(int donations) {
		this.donations = donations;
	}
	
	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
}
