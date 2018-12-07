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
		
		if(sqrY-2 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-2).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
		if(sqrY-1 >= 0 && sqrX+2 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				s.addPath();
			}
		}
	}
}

