package ers;

public class Status {
	private int id;
	private String status;
	
	public Status() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + "]";
	}

	
}
