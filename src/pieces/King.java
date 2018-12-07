package pieces;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class King extends ChessPiece{

	public King(Color c) {
		super(c);
	}
	public void showPath(Square sqr,Color c) {
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();

		//Steg upp
		if(sqrY-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg upp och �t h�ger
		if(sqrY-1 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg �t h�ger
		if(sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg ner och �t h�ger
		if(sqrY+1 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg ner
		if(sqrY+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg ner och �t v�nster
		if(sqrY+1 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg �t v�nster
		if(sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//Steg upp och �t v�nster
		if(sqrY-1 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
	}
}

