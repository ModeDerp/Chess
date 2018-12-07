package pieces;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Knight extends ChessPiece{

	public Knight(Color c) {
		super(c);
	}
	public void showPath(Square sqr, Color c) {
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		
		//H�stens path tv� steg upp och ett steg �t h�ger
		if(sqrY-2 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-2).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg �t h�ger och ett steg upp
		if(sqrY-1 >= 0 && sqrX+2 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg �t h�ger och ett steg ner
		if(sqrY+1 <= 7 && sqrX+2 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg ner och ett steg �t h�ger
		if(sqrY+2 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+2).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg ner och ett steg �t v�nster
		if(sqrY+2 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+2).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg �t v�nster och ett steg ner
		if(sqrY+1 <= 7 && sqrX-2 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg �t v�nster och ett steg upp
		if(sqrY-1 >= 0 && sqrX-2 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		//H�stens path tv� steg upp och ett steg �t v�nster
		if(sqrY-2 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-2).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
	}
}

