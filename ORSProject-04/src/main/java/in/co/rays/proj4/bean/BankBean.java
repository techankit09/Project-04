package in.co.rays.proj4.bean;

public class BankBean extends BaseBean {
	
	private String AccountHolderName;
	
	private String AccountNo;
	
	private String AccountType;
	
	private String AccountBalance;
	
	private String branch;
	
	private Integer PhoneNo;

	public String getAccountHolderName() {
		return AccountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		AccountHolderName = accountHolderName;
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getAccountBalance() {
		return AccountBalance;
	}

	public void setAccountBalance(String accountBalance) {
		AccountBalance = accountBalance;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		branch = branch;
	}
	public Integer getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		PhoneNo = phoneNo;
	}

	@Override
	 public String getKey() {
        return id + "";
    }
	
    @Override
    public String getValue() {
        return AccountNo;
    }
	
}
