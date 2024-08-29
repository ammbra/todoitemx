package org.ammbra.eu.agenda.items;

import java.time.LocalDate;

public non-sealed class URLTodoItem extends TodoItem implements Todo {

	private String url;

	public URLTodoItem(String title, String description, String url, LocalDate createdOn, LocalDate deadline) {
		if (deadline.isBefore(createdOn))
			throw new IllegalArgumentException("Cannot create item with deadline before its creation date");
		super(title, description, createdOn, deadline);
		this.url = url;
	}


	public String getUrl() {
		return url;
	}
}
