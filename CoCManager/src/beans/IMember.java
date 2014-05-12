package beans;

import java.util.Date;

import model.Clan;
import model.Period;

public interface IMember {	
	public String getName();
	
	public void setName(String name);
	
	public String getSelectedRank();
	
	public void setSelectedRank(String rank);
	
	public Integer getLevel();
	
	public void setLevel(Integer level);
	
	public Integer getTrophies();
	
	public void setTrophies(Integer trophies);
	
	public Date getJoinedDate();
	
	public void setJoinedDate(Date joinedDate);
	
	public Integer getDonations(Period p);
	
	public void setDonations(Integer donations, Date date);
	
	public void setDonations(Integer donations);
	
	public Clan getClan();

	public void setClan(Clan clan);
}
