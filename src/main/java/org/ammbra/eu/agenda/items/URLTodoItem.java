package org.ammbra.eu.agenda.items;

import java.time.LocalDate;

public class URLTodoItem extends TodoItem {

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

	@Override
	public boolean hasAttachment() {
		return url != null && !url.isEmpty();
	}
}
