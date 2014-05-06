package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Member implements Comparable<Member>{
	private int id;
	private String name;
	private Rank rank;
	private int level;
	private int trophies;
	private Date joinedDate;
	private List<Donations> donations;
	private List<WarAttack> warAttacks;
	private Clan clan;
	
	public Member(Clan clan) {
		donations = new ArrayList<Donations>();
		this.clan = clan;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getTrophies() {
		return trophies;
	}
	
	public void setTrophies(int trophies) {
		this.trophies = trophies;
	}
	
	public Date getJoinedDate() {
		return joinedDate;
	}
	
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	public List<Donations> getAllDonations() {
		return donations;
	}
	
	public int getDonations(Period p) {
		if(p != null) {
			for (Donations periodDonations : donations) {
				if(p.equals(periodDonations.getPeriod())) {
					return periodDonations.getDonations();
				}
			}
		}
		return 0;
	}
	
	public void setDonations(int donations, Period p) {		
		//in case donations already exist
		for (Donations periodDonations : this.donations) {
			if(p.equals(periodDonations.getPeriod())) {
				periodDonations.setDonations(donations);
				//TODO persist donations
				return;
			}
		}
		//in case a new donations is required
		Donations newDonations = new Donations();
		newDonations.setPeriod(p);
		newDonations.setDonations(donations);
		this.donations.add(newDonations);
		//TODO persist donations
	}
	
	public List<WarAttack> getWarAttacks() {
		return warAttacks;
	}
	
	public void setWarAttacks(List<WarAttack> warAttacks) {
		this.warAttacks = warAttacks;
	}
	
	public void addWarAttack(WarAttack warAttack) {
		warAttacks.add(warAttack);
	}
	
	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
	
	public float getAverageDonations(List<Period> periods, int windowSize) {
		int donationSum = 0;
		Period currentP = Period.getTodaysPeriod(periods);
		donationSum += getDonations(currentP);
		Period previousP = currentP;
		for(int i = 1 ; i < windowSize ; i++) {
			previousP = Period.getPreviousPeriod(previousP, periods);
			donationSum += getDonations(previousP);
		}
		
		return donationSum/windowSize;
	}

	@Override
	public int compareTo(Member o) {
		return MemberComparators.TROPHIES.compare(this, o);
	}
	
	public static class MemberComparators {
		public static Comparator<Member> TROPHIES = new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				return o1.trophies - o2.trophies;
			}
		};
		//TODO add more comparator options: date, donations, etc...
	}
}
