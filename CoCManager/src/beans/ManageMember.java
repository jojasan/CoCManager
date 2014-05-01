package beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Clan;
import model.Member;
import model.Period;
import model.Rank;

@ManagedBean
@SessionScoped
public class ManageMember extends BaseBean implements IMember {
	private String selectedRank;
	private String name;
	private Integer trophies;
	private Integer level;
	private Date joinedDate;
	private Rank rank;
	private Member member;
	
	public String getSelectedRank() {
		return selectedRank;
	}
	
	public void setSelectedRank(String selectedRank) {
		this.selectedRank = selectedRank;
		rank = Rank.valueOf(selectedRank);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTrophies() {
		return trophies;
	}

	public void setTrophies(Integer trophies) {
		this.trophies = trophies;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joined) {
		this.joinedDate = joined;
	}
	
	public String create(Clan clan) {
		if(clan != null) {
			Member m = new Member(clan);
			m.setName(name);
			m.setLevel(level);
			m.setDonations(0, Period.getTodaysPeriod(data.getPeriods()));
			System.out.println("Date for new member is: " + joinedDate);
			m.setJoinedDate(joinedDate);
			m.setTrophies(trophies);
			m.setRank(rank);
			clan.addMember(m);
		}
		resetForm();
		return null;
	}
	
	public String delete(int index, Clan clan) {
		if(clan != null) {
			clan.removeMember(index);
		}
		return null;
	}

	public String load(int index, Clan clan) {
		member = clan.getMembers().get(index);
		if(member != null) {
			name = member.getName();
			trophies = member.getTrophies();
			level = member.getLevel();
			joinedDate = member.getJoinedDate(); //TODO there is a bug loading the date
			rank = member.getRank();
			selectedRank = rank.name();
		}
		return null;
	}
	
	public String edit() {
		if(member != null) {
			member.setName(name);
			member.setTrophies(trophies);
			member.setJoinedDate(joinedDate);
			member.setLevel(level);
			member.setRank(rank);
		}
		resetForm();
		return null;
	}
	
	private void resetForm() {
		name = null;
		trophies = null;
		selectedRank = null;
		level = null;
		joinedDate = null;
		rank = null;
		member = null;
	}
	
	@Override
	public Integer getDonations(Period p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDonations(Integer donations, Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDonations(int donations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Clan getClan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClan(Clan clan) {
		// TODO Auto-generated method stub
		
	}
}