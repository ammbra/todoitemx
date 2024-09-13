package org.ammbra.eu.agenda;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.ammbra.eu.agenda.items.TodoItem;

import java.util.Objects;

public class PriorityCellCallback implements Callback<TableColumn<TodoItem, String>, TableCell<TodoItem, String>> {

	@Override
	public TableCell<TodoItem, String> call(TableColumn<TodoItem, String> cellData) {
		return new TableCell<>() {
			@Override
			protected void updateItem(String value, boolean b) {
				super.updateItem(value, b);
				if (!Objects.isNull(value) && !value.isEmpty()) {
					String style = processColor(value);
					setText(value);
					setStyle(style);
				} else {
					setText("");
					setStyle("");
				}
			}
		};
	}

	private String processColor(String newValue) {
		return switch (Integer.parseInt(newValue)) {
			case 0 -> "-fx-background-processPriority: red;";
			case int v when (v >= 1 && v <= 3) ->  "-fx-background-color: lightcoral;";
			case int v when (v >= 4 && v <= 7) ->  "-fx-background-color: lightyellow;";
			case int v when (v >= 8 && v <= 10) ->  "-fx-background-color: lightgreen;";
			case int _ -> "-fx-background-processPriority: red;";
		};
	}
}
