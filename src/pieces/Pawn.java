package pieces;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Pawn extends ChessPiece{

	public Pawn(Color c) {
		super(c);
	}
	
	public void showPath(Square sqr, Color col) {
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		int moves = 2;
		if((col == Color.BLACK && sqrY >= 2) || (col == Color.WHITE && sqrY <= 5)) moves = 1;
		for (int i = 1; i <= moves; i++) {
			if(sqr.getPieceColor() == Color.WHITE) {
				if(sqrY-i < 0) break;
				else if(ChessBoard.map.get(sqrY-i).get(sqrX).hasPiece()) break;
				ChessBoard.map.get(sqrY-i).get(sqrX).addPath();
			}
			else {
				if(sqrY+i > 7) break;
				else if(ChessBoard.map.get(sqrY+i).get(sqrX).hasPiece()) break;
				ChessBoard.map.get(sqrY+i).get(sqrX).addPath();
			}
		}
		if(col == Color.BLACK && sqrY+1 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-1);
			if(s.hasPiece() && !s.isSameColor()) {
				s.addPath();
				
			}
		}
		if(col == Color.BLACK && sqrY+1 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+1);
			if(s.hasPiece() && !s.isSameColor()) {
				s.addPath();
			}
		}
		if(col == Color.WHITE && sqrY-1 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-1);
			if(s.hasPiece() && !s.isSameColor()) {
				s.addPath();
			}
		}
		if(col == Color.WHITE && sqrY-1 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+1);
			if(s.hasPiece() && !s.isSameColor()) {
				s.addPath();
			}
		}
	}
}
