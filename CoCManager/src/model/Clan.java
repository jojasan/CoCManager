package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Clan {
	private int id;
	private String name;
	private List<Member> members;
	private List<War> wars;
	private Comparator<Member> sortCriteria;
	
	public Clan(String name) {
		this.name = name;
		members = new ArrayList<Member>(50);
		wars = new ArrayList<War>();
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
		if(sortCriteria == null) {
			Collections.sort(members);
			Collections.reverse(members); //defaults to by trophies
		} else {
			Collections.sort(members, Collections.reverseOrder(sortCriteria));			
		}
		return members;
	}
	
	public void addMember(Member member) {
		members.add(member);
		//TODO: persist member
	}
	
	public void removeMember(int id) {
		members.remove(id);
		//TODO persist remove
	}
	
	public void addWar(War war) {
		wars.add(war);
	}
	
	public void setSortCriteria() {
		
	}
}
