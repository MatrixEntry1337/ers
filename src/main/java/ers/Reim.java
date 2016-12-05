package ers;

import java.sql.Timestamp;

public class Reim {
	private int id;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private User author;
	private int resolver;
	private int statusId;
	private int typeId;
	
	public Reim(){
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(int author) {
		this.author = new User();
		this.author.setId(author);
	}
	
	public int getResolver() {
		return resolver;
	}
	
	public void setResolver(int resolver) {
		this.resolver = resolver;
	}
	
	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	

	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	@Override
	public String toString() {
		return "Reim [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", statusId="
				+ statusId + ", typeId=" + typeId + "]\n";
	}
}
