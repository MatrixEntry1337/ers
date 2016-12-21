package com.ers.beans;

import java.sql.Timestamp;
import java.util.Comparator;

public class Reim implements Comparable<Reim>{
	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private String abbrev;
	private User author;
	private User resolver;
	private Status status;
	private Type type;
	
	public Reim(){
		super();
	}
	
	public Reim(int id, double amount, Timestamp submitted, Timestamp resolved, 
			String description, User author, User resolver, Status status, Type type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getAmount() {
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

	public void setAmount(double amount) {
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
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public User getResolver() {
		return resolver;
	}
	
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	

	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reim [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reim other = (Reim) obj;
		if (id != other.id)
			return false;
		return true;
	}

	static class ReimStatusComparator implements Comparator<Reim>{

		@Override
		public int compare(Reim one, Reim two) {
			return one.status.compareTo(two.status);
		}
		
	}
	
	static class ReimTypeComparator implements Comparator<Reim>{

		@Override
		public int compare(Reim one, Reim two) {
			return one.type.compareTo(two.type);
		}
		
	}
	
	@Override
	public int compareTo(Reim other) {
		return this.id - other.id;
	}
	
}
