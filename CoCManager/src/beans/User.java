package beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Clan;
import model.Member;
import model.Period;

@ManagedBean
@SessionScoped
public class User extends BaseBean {
	private String selectedClan;
	private Period selectedPeriod;
	private int selectedPeriodId = data.getPeriods().size() - 1;
	
	public String getSelectedClan() {
		return selectedClan;
	}

	public void setSelectedClan(String selectedClan) {
		this.selectedClan = selectedClan;
	}
	
	public Set<String> getClanChoices() {
		return data.getClans().keySet();
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
		
		//build result map according to selectedPeriod
		List<Map<String, Object>> members = new ArrayList<Map<String,Object>>();
		for (Member m : clan.getMembers()) {
			Map<String, Object> item = new LinkedHashMap<String, Object>();
			item.put("name", m.getName());
			item.put("trophies", m.getTrophies());
			item.put("rank", m.getRank());
			item.put("level", m.getLevel());
			item.put("joined", m.getJoinedDate());
			item.put("donations", m.getDonations(Period.getPeriodFromId(selectedPeriodId, data.getPeriods())));
			item.put("id", m.getId());
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
}
