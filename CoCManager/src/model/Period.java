package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		c.add(Calendar.DATE, PERIOD_DAYS-1); //add period days
		c.add(Calendar.HOUR_OF_DAY, 23);
		c.add(Calendar.MINUTE, 59);
		c.add(Calendar.SECOND, 59);
		return c.getTime();
	}
	
	public static Period getPeriodForAnyDate(Date date, List<Period> periods) {
		//strip out dates time
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    Date sDate = cal.getTime();
		for (Period p : periods) {
			if((sDate.after(p.startDate) || sDate.equals(p.startDate)) && 
					(sDate.before(p.endDate) || sDate.equals(p.endDate))) {
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
