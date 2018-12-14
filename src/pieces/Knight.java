package pieces;

import java.util.ArrayList;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Knight extends ChessPiece{

	public Knight(Color c) {
		super(c);
	}
	
	public ArrayList<Square> returnPath(Square sqr, Color c){
		ArrayList<Square> sqrArr = new ArrayList<Square>();
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		
		//Hästens path två steg upp och ett steg åt höger
		if(sqrY-2 >= 0 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY-2).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg åt höger och ett steg upp
		if(sqrY-1 >= 0 && sqrX+2 <= 7) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX+2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg åt höger och ett steg ner
		if(sqrY+1 <= 7 && sqrX+2 <= 7) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX+2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg ner och ett steg åt höger
		if(sqrY+2 <= 7 && sqrX+1 <= 7) {
			Square s = ChessBoard.map.get(sqrY+2).get(sqrX+1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg ner och ett steg åt vänster
		if(sqrY+2 <= 7 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY+2).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg åt vänster och ett steg ner
		if(sqrY+1 <= 7 && sqrX-2 >= 0) {
			Square s = ChessBoard.map.get(sqrY+1).get(sqrX-2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg åt vänster och ett steg upp
		if(sqrY-1 >= 0 && sqrX-2 >= 0) {
			Square s = ChessBoard.map.get(sqrY-1).get(sqrX-2);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		//Hästens path två steg upp och ett steg åt vänster
		if(sqrY-2 >= 0 && sqrX-1 >= 0) {
			Square s = ChessBoard.map.get(sqrY-2).get(sqrX-1);
			if((s.hasPiece() && !s.isSameColor()) || !s.hasPiece()) {
				sqrArr.add(s);
			}
		}
		return sqrArr;
	}
}