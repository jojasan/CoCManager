package persistence;

import java.util.List;
import java.util.Map;

import model.Clan;
import model.Member;
import model.Period;

public interface DataInterface {
	public void loadClans();
	public void loadPeriods();
	public Map<String, Clan> getClans();
	public List<Period> getPeriods();
	public void saveClan(Clan clan);
	public void saveMember(Member member);
}
