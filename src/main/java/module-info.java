module org.ammbra.eu.agenda.todoitemx {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.logging;


	opens org.ammbra.eu.agenda to javafx.fxml;
	exports org.ammbra.eu.agenda;
	exports org.ammbra.eu.agenda.items;
	opens org.ammbra.eu.agenda.items to javafx.fxml;
}