package org.ammbra.eu.agenda.items;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class ImageTodoItem extends TodoItem {

	private Image image;

	public ImageTodoItem(String title, String description, Image image, LocalDate createdOn, LocalDate deadline) {
		if (deadline.isBefore(createdOn))
			throw new IllegalArgumentException("Cannot create item with deadline before its creation date");
		super(title, description, createdOn, deadline);
		this.image = image;
	}


	public Image getImage() {
		return image;
	}

	@Override
	public boolean hasAttachment() {
		return image != null;
	}
}
