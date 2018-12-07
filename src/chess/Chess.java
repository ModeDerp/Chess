package chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chess extends Application{

	public void start(Stage primaryStage) throws Exception {

		ChessBoard chessBoard = new ChessBoard();
		Scene scene = new Scene(chessBoard,8*Square.SIZE,8*Square.SIZE);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();
	}
}
