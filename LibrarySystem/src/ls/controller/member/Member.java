package ls.controller.member;

import javafx.scene.control.Hyperlink;

public class Member {
	private String idNumber;
	private String name;
	private String address;
	private String phoneNo;
	private Hyperlink editLink;

	public Member(String idNumber, String name, String address, String phoneNo) {
		this.idNumber = idNumber;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
		editLink = new Hyperlink("Edit Me");
		
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public Hyperlink getEditLink() {
		return editLink;
	}
}
