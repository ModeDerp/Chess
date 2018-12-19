package pieces;

import java.util.ArrayList;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class King extends ChessPiece{

	public King(Color c) {
		super(c);
	}

	public ArrayList<Square> returnPath(Square sqr, Color c){
		ArrayList<Square> sqrArr = new ArrayList<Square>();
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();

		//Steg upp
		if(sqrY-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX);
			sqrArr.add(s);
		}
		//Steg upp och åt höger
		if(sqrY-1 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+1);
			sqrArr.add(s);
		}
		//Steg åt höger
		if(sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+1);
			sqrArr.add(s);
		}
		//Steg ner och åt höger
		if(sqrY+1 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+1);
			sqrArr.add(s);
		}
		//Steg ner
		if(sqrY+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX);
			sqrArr.add(s);
		}
		//Steg ner och åt vänster
		if(sqrY+1 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-1);
			sqrArr.add(s);
		}
		//Steg åt vänster
		if(sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-1);
			sqrArr.add(s);
		}
		//Steg upp och åt vänster
		if(sqrY-1 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-1);
			sqrArr.add(s);
		}
		return sqrArr;
	}

	public ArrayList<Square> tryCastle(Square sqr) {
		ArrayList<Square> sqrArr = new ArrayList<Square>();
		if((sqr.getPieceColor() == Color.WHITE && !Square.whiteKingHasMoved) || (sqr.getPieceColor() == Color.BLACK && !Square.blackKingHasMoved)) {
			int sqrX = sqr.getX();
			int sqrY = sqr.getY();
			if(ChessBoard.map.get(sqrY).get(sqrX+3).getPiece() instanceof Rook) {
				if(!ChessBoard.map.get(sqrY).get(sqrX+1).hasPiece() && !ChessBoard.map.get(sqrY).get(sqrX+2).hasPiece()) {
					if(!ChessBoard.map.get(sqrY).get(sqrX).isSquareCheck() && !ChessBoard.map.get(sqrY).get(sqrX+1).isSquareCheck() && !ChessBoard.map.get(sqrY).get(sqrX+2).isSquareCheck()) {
						sqrArr.add(ChessBoard.map.get(sqrY).get(sqrX+3));
					}
				}
			}
			if(ChessBoard.map.get(sqrY).get(sqrX-3).getPiece() instanceof Rook) {
				if(!ChessBoard.map.get(sqrY).get(sqrX).isSquareCheck() && !ChessBoard.map.get(sqrY).get(sqrX-1).isSquareCheck() && !ChessBoard.map.get(sqrY).get(sqrX-2).isSquareCheck()) {
					if(!ChessBoard.map.get(sqrY).get(sqrX-1).hasPiece() && !ChessBoard.map.get(sqrY).get(sqrX-2).hasPiece()) {
						sqrArr.add(ChessBoard.map.get(sqrY).get(sqrX-3));
					}
				}
			}
		}
		return sqrArr;
	}
}

