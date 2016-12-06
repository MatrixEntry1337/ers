package ers;

import java.sql.Timestamp;

public class Reim {
	private int id;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private User author;
	private User resolver;
	private Status status;
	private Type type;
	
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
	
	public void setAuthor(int id) {
		this.author = new User();
		this.author.setId(id);
	}
	
	public User getResolver() {
		return resolver;
	}
	
	public void setResolver(int id) {
		this.resolver = new User();
		this.resolver.setId(id);
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(int id, String status) {
		this.status = new Status();
		this.status.setId(id);
		this.status.setStatus(status);
	}
	

	public Type getType() {
		return type;
	}
	
	public void setType(int id, String type) {
		this.type = new Type();
		this.type.setId(id);
		this.type.setType(type);
	}

	@Override
	public String toString() {
		return "Reim [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + "]\n";
	}
}
