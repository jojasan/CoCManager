package beans;

import persistence.DataInterface;
import persistence.PropertiesDataInterface;

public class BaseBean {
	protected DataInterface data;
	
	public BaseBean() {
		//TODO get real database instance when implemented
		data = PropertiesDataInterface.getInstance();
	}
}
