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

		if(sqrY-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrY-1 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrY+1 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrY+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrY+1 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrY-1 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
	}
}

