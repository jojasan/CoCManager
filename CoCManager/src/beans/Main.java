package beans;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.Period;
import model.Rank;
import model.RestrictionType;
import persistence.Data;
import persistence.FileData;

@ManagedBean(eager=true)
@ApplicationScoped
public class Main {
	private Data data;
	
	public Main() {
		System.out.println("Starting up CoC...");
		System.out.println("Using FileData instance");
		data = FileData.getInstance();
		data.loadPeriods();
		data.loadClans();
	}
	
	public Map<String, Integer> getPeriods() {
		List<Period> periods = data.getPeriods();
		LinkedHashMap<String, Integer> result = new LinkedHashMap<String, Integer>();		
		for (Period p : periods) {
			String start = new SimpleDateFormat("MMM-dd").format(p.getStartDate());
			String end = new SimpleDateFormat("MMM-dd").format(p.getEndDate());
			result.put(start + " to " + end, p.getId());
		}
		return result;
	}
	
	public Rank[] getRanks() {
		return Rank.values();
	}
	
	public RestrictionType[] getRestrictionTypes() {
		return data.getRestrictionTypes();
	}
	
	public String getRestrictionAttributeName(String rName) {
		if(rName.equals("Min. Trophies")) {
			return "minTrophies";
		} else if(rName.equals("Min. Level")) {
			return "minLevel";
		} else if(rName.equals("Min. Donations")) {
			return "minDonations";
		}
		return null;
	}
}
