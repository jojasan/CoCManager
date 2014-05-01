package model;

public class WarAttack {
	private Member attacker;
	private int stars;
	
	public WarAttack(Member attacker, int stars) {
		this.attacker = attacker;
		this.stars = stars;
	}

	public Member getAttacker() {
		return attacker;
	}

	public void setAttacker(Member attacker) {
		this.attacker = attacker;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
}
