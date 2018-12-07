package pieces;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Rook extends ChessPiece{

	public Rook(Color c) {
		super(c);
	}
	public void showPath(Square sqr,Color c) {
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		//Tornets path uppåt
		for (int i = 1; i <= sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				s.addPath();
				break;
			}
			s.addPath();
		}
		//Tornets path Nedåt
		for (int i = 1; i <= 7-sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				s.addPath();
				break;
			}
			s.addPath();
		}
		//Tornets path åt vänster
		for (int i = 1; i <= sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				s.addPath();
				break;
			}
			s.addPath();
		}
		//Tornets path åt höger
		for (int i = 1; i <= 7-sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				s.addPath();
				break;
			}
			s.addPath();
		}
	}
}
