package persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Clan;
import model.Member;
import model.Period;
import model.Rank;
import model.RestrictionType;

public abstract class Data {
	protected HashMap<Integer, Clan> clans;
	protected List<Period> periods;
	protected RestrictionType[] restrictionTypes;
	
	public void loadClans() {
		if(clans == null) {
			System.out.println("Loading clans");
			clans = new LinkedHashMap<Integer, Clan>();			
		}
	}
	
	public void loadPeriods() {
		if(periods == null) {
			System.out.println("Loading periods");
			periods = new LinkedList<Period>();			
		}
	}
	
	public Map<Integer, Clan> getClans() {
		return clans;
	}
	
	public List<Period> getPeriods() {
		return periods;
	}
	
	public void createMember(Clan c, Member m) {
		System.out.printf("Creating member <%s> at clan <%s>\n", m, c);
		if(!clans.get(c.getId()).equals(c)) {
			System.out.printf("Cannot create member <%s>. Clan <%s> not found in memory map\n", m, c);
			return; //TODO really, it should be an exception
		}
		c.addMember(m);
	}
	
	public void editMember(Member m, String nName, Integer nTrophies, Date nDate, Integer nLevel, Rank nRank, Integer nDonations, Integer nPeriodId) {
		if(m == null) {
			System.out.println("Cannot create member. Member is null");
			return; //TODO really, it should be an exception
		}
		System.out.printf("Editing member <%s> at clan <%s>\n", m, m.getClan());
		m.setName(nName);
		m.setTrophies(nTrophies);
		m.setJoinedDate(nDate);
		m.setLevel(nLevel);
		m.setRank(nRank);
		m.setDonations(nDonations, Period.getPeriodFromId(nPeriodId, periods));
	}
	
	public void deleteMember(Member m) {
		if(m == null) {
			System.out.println("Cannot delete member. Member is null");
			return; //TODO really, it should be an exception
		}
		System.out.printf("Deleting member <%s> at clan <%s>\n", m, m.getClan());
		m.getClan().removeMember(m.getId());
	}
	
	public void createClan(Clan c) {
		if(c == null) {
			System.out.println("Cannot create clan. Clan is null");
			return; //TODO really, it should be an exception
		}
		System.out.printf("Creating clan <%s>\n", c);
		clans.put(c.getId(), c);
	}
	
	public static Data getInstance() {
		//not used
		return null;
	}
	
	public RestrictionType[] getRestrictionTypes() {
		if(restrictionTypes == null) { //TODO load from FileData
			restrictionTypes = new RestrictionType[3];
			RestrictionType rT1 = new RestrictionType("Min. Trophies", "trophies");
			RestrictionType rT2 = new RestrictionType("Min. Level", "level");
			RestrictionType rT3 = new RestrictionType("Min. Donations", "donations");
			restrictionTypes[0] = rT1;
			restrictionTypes[1] = rT2;
			restrictionTypes[2] = rT3;
		}
		return restrictionTypes;
	}
	
	public RestrictionType findRestrictionType(String name) {
		for(RestrictionType r : restrictionTypes) {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}
}
