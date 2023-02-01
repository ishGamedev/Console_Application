package consoleApplication.Jobs;

import java.io.Serializable;

public class DataAnalyst extends Job implements Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "DataAnalyst";
	}

	@Override
	public String[] skills() {
		return new String[] {"SQL", "Statistical programming", "Machine Learning", "Probability", "Statistics",
				"Data management", "Statistical Visualization", "Econometrics", "Databases"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Revisions", "Delivery days", "Data source connectivity", "Web embedding",
				"Interactive visuals", "Dashboards"};
	}

}
