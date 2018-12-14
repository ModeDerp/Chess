package pieces;

import java.util.ArrayList;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Pawn extends ChessPiece{

	public Pawn(Color c) {
		super(c);
	}

	public ArrayList<Square> returnPath(Square sqr, Color c){
		ArrayList<Square> sqrArr = new ArrayList<Square>();
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		int moves = 2;
		//Om bonden färdats minst ett block sänks längden den kan färdas från 2 till 1
		if((c == Color.BLACK && sqrY >= 2) || (c == Color.WHITE && sqrY <= 5)) moves = 1;
		for (int i = 1; i <= moves; i++) {
			if(sqr.getPieceColor() == Color.WHITE) {
				if(sqrY-i < 0) break;
				else {
					Square s = ChessBoard.map.get(sqrY-i).get(sqrX);
					if(s.hasPiece()) break;
					sqrArr.add(s);
				}
			}
			else {
				if(sqrY+i > 7) break;
				else {
					Square s = ChessBoard.map.get(sqrY+i).get(sqrX);
					if(s.hasPiece()) break;
					sqrArr.add(s);
				}
			}
		}
		//Attack, svart bonde åt vänster
		if(c == Color.BLACK && sqrY+1 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-1);
			if(s.hasPiece() && !s.isSameColor()) {
				sqrArr.add(s);
			}
		}
		//Attack, svart bonde åt höger
		if(c == Color.BLACK && sqrY+1 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+1);
			if(s.hasPiece() && !s.isSameColor()) {
				sqrArr.add(s);
			}
		}
		//Attack, vit bonde åt vänster
		if(c == Color.WHITE && sqrY-1 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-1);
			if(s.hasPiece() && !s.isSameColor()) {
				sqrArr.add(s);
			}
		}
		//Attack, vit bonde åt höger
		if(c == Color.WHITE && sqrY-1 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+1);
			if(s.hasPiece() && !s.isSameColor()) {
				sqrArr.add(s);
			}
		}
		return sqrArr;
	}
}