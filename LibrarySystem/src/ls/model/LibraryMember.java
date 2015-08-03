package ls.model;

import java.io.Serializable;

public class LibraryMember extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3668658717010296900L;
	private String memberId;
	private CheckoutRecord checkoutRecord;

	public LibraryMember(String firstName, String lastName, String telephone,
			Address address, String memberId) {
		super(firstName, lastName, telephone, address);
		this.memberId = memberId;
		checkoutRecord = new CheckoutRecord();
	}

	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}

	@Override
	public String toString() {
		return "[" + memberId + ":" + super.getFirstName() + ", "
				+ super.getLastName() + "]";
	}

}
