package org.ammbra.eu.agenda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TodoFx extends Application {

	public static void main(String[] args) {
		launch(args); 
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