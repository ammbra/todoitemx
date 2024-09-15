package org.ammbra.eu.agenda;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import org.ammbra.eu.agenda.items.TodoItem;

import java.time.LocalDate;

public class DateEditor extends TableCell<TodoItem, LocalDate> {

	private DatePicker datePicker;

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			setText(null);
			datePicker = new DatePicker(getItem());
			datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			datePicker.setOnAction((_) -> commitEdit(datePicker.getValue()));
			setGraphic(datePicker);
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getItem().toString());
		setGraphic(null);
	}

	@Override
	public void updateItem(LocalDate item, boolean empty) {
		super.updateItem(item, empty);
        String stringifiedDate = (empty || isEditing()) ? null : LocalDate.now().toString();
        DatePicker display = (empty || !isEditing()) ? null : datePicker;
        setText(stringifiedDate);
        setGraphic(display);
	}

}