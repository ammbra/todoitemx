package org.ammbra.eu.agenda.items;

import javafx.scene.image.Image;

import java.time.LocalDate;

public final class ImageTodoItem extends URLTodoItem implements Todo {

	private Image image;

	public ImageTodoItem(String title, String description, String url, Image image, LocalDate createdOn, LocalDate deadline) {
		super(title, description, url, createdOn, deadline);
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
