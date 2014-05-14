package beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Clan;
import model.Member;
import model.Period;

@ManagedBean
@SessionScoped
public class User extends BaseBean {
	private Integer selectedClan;
	private Period selectedPeriod;
	private int selectedPeriodId = data.getPeriods().size() - 1;
	private int avWindowSize = 4;
	private Comparator<Member> sortCriteria = Member.MemberComparators.TROPHIES; //default order is by trophies
	
	public Integer getSelectedClan() {
		return selectedClan;
	}

	public void setSelectedClan(Integer selectedClan) {
		this.selectedClan = selectedClan;
	}
	
	public Collection<Clan> getClanChoices() {
		return data.getClans().values();
	}
	
	public Clan getClan() {
		Clan clan = data.getClans().get(selectedClan);
		if(clan == null) {
			return null;
		}
		return clan;
	}
	
	public List<Map<String, Object>> getClanMembers() {
		Clan clan = data.getClans().get(selectedClan);
		if(clan == null) {
			return null;
		}
		
		ArrayList<Member> clanCopy = new ArrayList<Member>(clan.getMembers());
		Collections.sort(clanCopy, Collections.reverseOrder(sortCriteria));
		
		//build result map according to selectedPeriod
		List<Map<String, Object>> members = new ArrayList<Map<String,Object>>();
		for (Member m : clanCopy) {
			Map<String, Object> item = new LinkedHashMap<String, Object>();
			item.put("name", m.getName());
			item.put("trophies", m.getTrophies());
			item.put("rank", m.getRank());
			item.put("level", m.getLevel());
			item.put("joined", m.getJoinedDate());
			item.put("donations", m.getDonations(Period.getPeriodFromId(selectedPeriodId, data.getPeriods())));
			item.put("id", m.getId());
			item.put("avDonations", m.getAverageDonations(data.getPeriods(), avWindowSize));
			members.add(item);
		}
		return members;
	}

	public Period getSelectedPeriod() {
		return selectedPeriod;
	}

	public int getSelectedPeriodId() {
		return selectedPeriodId;
	}

	public void setSelectedPeriodId(int selectedPeriodId) {
		this.selectedPeriodId = selectedPeriodId;
		selectedPeriod = Period.getPeriodFromId(selectedPeriodId, data.getPeriods());
	}
	
	public String nextPeriod() {
		selectedPeriodId++;
		return null;
	}
	
	public String previousPeriod() {
		selectedPeriodId--;
		return null;
	}
}
