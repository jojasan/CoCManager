package model;

import java.util.ArrayList;
import java.util.List;

public class Clan {
	private int id;
	private String name;
	private List<Member> members;
	private List<War> wars;
	private List<Restriction> restrictions;
	private String description;
	
	public Clan(String name) {
		this.name = name;
		members = new ArrayList<Member>(50);
		wars = new ArrayList<War>();
		restrictions = new ArrayList<Restriction>();
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
	
	public List<Member> getMembers() {
		return members;
	}
	
	public Member getMember(int id) {
		for (Member m : members) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	public void addMember(Member member) {
		members.add(member);
		member.setClan(this);
		//TODO: persist member
	}
	
	public void removeMember(int id) {
		members.remove(getMember(id));
		//TODO persist remove
	}
	
	public void addWar(War war) {
		wars.add(war);
		//TODO persist add war
	}
	
	public void setSortCriteria() {
		
	}
	
	public List<Restriction> getRestrictions() {
		return restrictions;
	}

	public void addRestriction(Restriction r) {
		restrictions.add(r);
		//TODO persist add restriction
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name + "_id:" + id;
	}
}
