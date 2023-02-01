package consoleApplication.Jobs;

import java.io.Serializable;

public class Accountant extends Job implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Accountant";
	}

	@Override
	public String[] skills() {
		return new String[] {"GAAP", "QuickBooks", "Xero", "Bookkeeping", "Accounting", "Microsoft Excel",
				"Bank Reconciliation", "Wave accounting", "Financial consulting", "Financial Statement",
				"Data Entry", "Budget Management", "MicroSoft Office", "Personal Finance", "Income tax return"};
	}

	@Override
	public String[] services() {
		return new String[] {"Pay($)","Management duration", "Financial statements", "Book keeping", "Accounting advisory",
				"Receipt management", "Report creation", "Payroll", "Accounting tools setup",
				"Account audit"};
	}

}
