package pieces;

import java.util.ArrayList;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Rook extends ChessPiece{

	public Rook(Color c) {
		super(c);
	}

	public ArrayList<Square> returnPath(Square sqr,Color c) {
		ArrayList<Square> sqrArr = new ArrayList<Square>();
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		//Tornets path uppåt
		for (int i = 1; i <= sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Tornets path Nedåt
		for (int i = 1; i <= 7-sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Tornets path åt vänster
		for (int i = 1; i <= sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Tornets path åt höger
		for (int i = 1; i <= 7-sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}

		return sqrArr;
	}
}