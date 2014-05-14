package beans;

import persistence.Data;
import persistence.FileData;

public class BaseBean {
	protected Data data;
	
	public BaseBean() {
		//TODO get real database instance when implemented
		data = FileData.getInstance();
	}
}
