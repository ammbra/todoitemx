package org.ammbra.eu.agenda.items;

import javafx.scene.image.Image;

import java.time.LocalDate;

public final class ImageTodoItem extends TodoItem {

	private Image image;

	public ImageTodoItem(String title, String description, Image image, String priority, LocalDate createdOn, LocalDate deadline) {
		if (deadline.isBefore(createdOn))
			throw new IllegalArgumentException("Cannot create item with deadline before its creation date");
		super(title, description, priority, createdOn, deadline);
		this.image = image;
	}

	public Image getImage() {
		return image;
	}
}
