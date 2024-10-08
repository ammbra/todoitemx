package org.ammbra.eu.agenda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//import module javafx.controls;
//import module javafx.fxml;
//import module java.logging;

public class TodoFx extends Application {

	void main() {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws IOException {

		Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
				.getResource("/fxml/todoitemx.fxml")));
		Scene scene = new Scene(root);

		primaryStage.setTitle("ToDo App");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}