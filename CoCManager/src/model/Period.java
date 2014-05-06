package model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Period {
	public static final int START_DATE_INDEX = 0;
	public static final int END_DATE_INDEX = 0;
	public static final int PERIOD_DAYS = 14;
	private Date startDate;
	private Date endDate;
	private int id;
	
	public static Date getEndDateForStart(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, PERIOD_DAYS-1);
		return c.getTime();
	}
	
	public static Period getPeriodForAnyDate(Date date, List<Period> periods) {
		for (Period p : periods) {
			if((date.after(p.startDate) || date.equals(p.startDate)) && 
					(date.before(p.endDate) || date.equals(p.endDate))) {
				return p;
			}
		}
		return null;
	}
	
	public static Period getPeriodFromId(int id, List<Period> periods) {
		for (Period p : periods) {
			if(p.id == id) {
				return p;
			}
		}
		return null;
	}
	
	public static Period getTodaysPeriod(List<Period> periods) {
		return getPeriodForAnyDate(new Date(), periods);
	}
	
	public static Period getPreviousPeriod(Period p, List<Period> periods) {
		for (int i = 0 ; i < periods.size(); i++ ) {
			if(p.equals(periods.get(i)) && i != 0) {
				return periods.get(i-1);
			}
		}
		return null;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
