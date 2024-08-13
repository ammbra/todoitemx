package org.ammbra.eu.agenda.items;

public sealed interface Todo permits TodoItem, URLTodoItem, ImageTodoItem {
	public boolean hasAttachment();
}
