module org.ammbra.eu.agenda {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.logging;
	requires jdk.unsupported;


	opens org.ammbra.eu.agenda to javafx.fxml;
	exports org.ammbra.eu.agenda;
	exports org.ammbra.eu.agenda.items;
	opens org.ammbra.eu.agenda.items to javafx.fxml;
}