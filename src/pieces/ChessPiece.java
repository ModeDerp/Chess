package pieces;

import java.util.ArrayList;

import chess.Square;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class ChessPiece extends Group{

	public ChessPiece(Color c) {

		String name = this.getClass().getSimpleName().toUpperCase();
		String src;
		if(c == Color.BLACK) {
			src = "/images/BLACK_" + name + ".png";
		}
		else {
			src = "/images/WHITE_" + name + ".png";
		}
		Image image = new Image(src);
		ImageView iv = new ImageView(image);
		iv.setFitHeight(Square.SIZE);
		iv.setFitWidth(Square.SIZE);
		this.getChildren().add(iv);
	}

	public abstract ArrayList<Square> returnPath(Square sqr, Color col);
}
