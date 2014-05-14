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
	private Integer donations;
	private Rank rank;
	private Member member;
	private Integer periodId;
	
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
			m.setJoinedDate(joinedDate);
			m.setTrophies(trophies);
			m.setRank(rank);
			data.createMember(clan, m);
		}
		resetForm();
		return null;
	}
	
	public String delete(int id, Clan clan) {
		if(clan != null) {
			data.deleteMember(clan.getMember(id));
		}
		return null;
	}

	public String load(int id, Clan clan, int periodId) {
		member = clan.getMember(id);
		if(member != null) {
			this.periodId = periodId;
			name = member.getName();
			trophies = member.getTrophies();
			level = member.getLevel();
			joinedDate = member.getJoinedDate();
			rank = member.getRank();
			selectedRank = rank.name();
			donations = member.getDonations(Period.getPeriodFromId(periodId, data.getPeriods()));
		}
		return null;
	}
	
	public String edit() {
		if(member != null) {
			data.editMember(member, name, trophies, joinedDate, level, rank, donations, periodId);
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
	
	public Integer getDonations() {
		return donations;
	}

	@Override
	public void setDonations(Integer donations) {
		this.donations = donations;
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
	public Clan getClan() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClan(Clan clan) {
		// TODO Auto-generated method stub
		
	}
}
