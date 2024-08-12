package org.ammbra.eu.agenda.items;

import java.time.LocalDate;

public abstract class TodoItem {
	private String title;
	private String description;
	private LocalDate createdOn;
	private LocalDate deadline;

	public TodoItem(String title, String description, LocalDate createdOn, LocalDate deadline) {
		this.title = title;
		this.description = description;
		this.createdOn = createdOn;
		this.deadline = deadline;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public abstract boolean hasAttachment();
}
