package persistence;

import java.util.Calendar;
import java.util.Date;

import model.Clan;
import model.Member;
import model.Period;
import model.Rank;

public class FileData extends Data {
	private static FileData instance = new FileData();
	private int id = 0;
	
	private FileData() {
	}
	
	public static Data getInstance() {
		return instance;
	}
	
	@Override
	public void loadClans() {
		super.loadClans();
		Clan c1 = createClan(0);
		Clan c2 = createClan(1);
		clans.put(c1.getId(), c1);
		clans.put(c2.getId(), c2);
		System.out.println("Clans loaded (2) from file");
	}
	
	private Clan createClan(int selector) {
		Clan clan = new Clan(selector == 0 ? "Viva Colombia 3" : "Viva Colombia");
		Member m1 = new Member(clan);
		m1.setName(selector == 0 ? "John" : "Camilo");
		m1.setRank(selector == 0 ? Rank.LEADER : Rank.COLEADER);
		m1.setTrophies(selector == 0 ? 1900 : 1600);
		m1.setDonations(305, Period.getTodaysPeriod(periods));
		Calendar c = Calendar.getInstance();
		c.set(2014, 3, 23);
		m1.setDonations(900, Period.getPeriodForAnyDate(c.getTime(), periods));
		m1.setLevel(67);
		m1.setJoinedDate(new Date());
		m1.setId(id++);
		Member m2 = new Member(clan);
		m2.setName(selector == 0 ? "Maria" : "Blazy");
		m2.setRank(selector == 0 ? Rank.MEMBER : Rank.ELDER);
		m2.setTrophies(selector == 0 ? 1300 : 1200);
		m2.setDonations(295, Period.getTodaysPeriod(periods));
		m2.setLevel(73);
		m2.setJoinedDate(new Date());
		m2.setId(id++);
		Member m3 = new Member(clan);
		m3.setName(selector == 0 ? "Juanita" : "Laura");
		m3.setRank(selector == 0 ? Rank.ELDER : Rank.LEADER);
		m3.setTrophies(selector == 0 ? 1600 : 1900);
		m3.setDonations(193, Period.getTodaysPeriod(periods));
		m3.setLevel(59);
		m3.setJoinedDate(new Date());
		m3.setId(id++);
		Member m4 = new Member(clan);
		m4.setName("Andres");
		m4.setRank(Rank.ELDER);
		m4.setTrophies(1456);
		m4.setDonations(48, Period.getTodaysPeriod(periods));
		m4.setLevel(88);
		m4.setJoinedDate(new Date());
		m4.setId(id++);
		Member m5 = new Member(clan);
		m5.setName("Tom");
		m5.setRank(Rank.MEMBER);
		m5.setTrophies(2300);
		m5.setDonations(679, Period.getTodaysPeriod(periods));
		m5.setLevel(95);
		m5.setJoinedDate(new Date());
		m5.setId(id++);
		clan.addMember(m1);
		clan.addMember(m2);
		clan.addMember(m3);
		clan.addMember(m4);
		clan.addMember(m5);
		clan.setId(selector);
		return clan;
	}
	
	@Override
	public void loadPeriods() {
		super.loadPeriods();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(2014, 2, 31); //origin is Monday March 31, 2014
		//strip time part
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    //end strip
		Date originStart = cal.getTime();
		Period originRange = new Period();
		originRange.setId(0);
		originRange.setStartDate(originStart);
		Date endDate = Period.getEndDateForStart(originStart);
		Date today = new Date();
		originRange.setEndDate(endDate);
		periods.add(originRange);
		for(int i = 0 ; endDate.before(today) ; i++) {
			cal.add(Calendar.DATE, Period.PERIOD_DAYS);
			Period range = new Period();
			range.setId(i + 1);
			range.setStartDate(cal.getTime());
			endDate = Period.getEndDateForStart(cal.getTime());
			range.setEndDate(endDate);
			periods.add(range);
		}
		for (Period p : periods) {
			System.out.printf("Period loaded %s --- %s from file\n", p.getStartDate(), p.getEndDate());
		}
	}
	
	@Override
	public void createMember(Clan c, Member m) {
		// TODO Auto-generated method stub
		super.createMember(c, m);
		m.setId(id++);
		System.out.printf("Member <%s> has been created (file) and given id <%s>\n", m, m.getId());
	}

	@Override
	public void editMember(Member m, String nName, Integer nTrophies, Date nDate, Integer nLevel, Rank nRank, Integer nDonations, Integer nPeriodId) {
		// TODO Auto-generated method stub
		super.editMember(m, nName, nTrophies, nDate, nLevel, nRank, nDonations, nPeriodId);
		System.out.printf("Member %s has been edited (file)\n", m);
	}

	@Override
	public void deleteMember(Member m) {
		// TODO Auto-generated method stub
		super.deleteMember(m);
		System.out.printf("Member %s has been deleted (file)\n", m);
		
	}
}
