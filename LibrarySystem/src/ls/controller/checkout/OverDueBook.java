package ls.controller.checkout;

public class OverDueBook {
	public String ISBN;
	public String title;
	private int copyNo;
	private String memberName;
	private String dueDate;
	private String status;
	public OverDueBook(String ISBN, String title, int copyNo,
			String memberName, String dueDate,String status) {
		this.ISBN = ISBN;
		this.title = title;
		this.copyNo = copyNo;
		this.memberName = memberName;
		this.dueDate = dueDate;
		this.status=status;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitle() {
		return title;
	}

	public int getCopyNo() {
		return copyNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getStatus() {
		return status;
	}

}
