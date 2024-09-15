package org.ammbra.eu.agenda;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.ammbra.eu.agenda.items.TodoItem;

import java.util.Objects;

public class PriorityCellCallback implements Callback<TableColumn<TodoItem, String>, TableCell<TodoItem, String>> {

	@Override
	public TableCell<TodoItem, String> call(TableColumn<TodoItem, String> todoItemStringTableColumn) {
		return new TableCell<>() {
			@Override
			protected void updateItem(String value, boolean b) {
				super.updateItem(value, b);
				if (!Objects.isNull(value) && !value.isEmpty()) {
					String style = processColor(value);
					System.out.println(style);
					setText(value);
					setStyle(style);
				}
			}
		};
	}

	private String processColor(String newValue) {
		return "-fx-background-color: red;";
	}
}