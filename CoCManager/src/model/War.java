package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class War {
	private String enemyClan;
	private List<WarAttack> attacks;
	private Date started;
	
	public War(String enemyClan) {
		this.enemyClan = enemyClan;
		attacks = new ArrayList<WarAttack>(100);
	}
	
	public String getEnemyClan() {
		return enemyClan;
	}
	
	public void addAttack(WarAttack attack) {
		attacks.add(attack);
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}
}
