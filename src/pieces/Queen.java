package pieces;

import java.util.ArrayList;

import chess.ChessBoard;
import chess.Square;
import javafx.scene.paint.Color;

public class Queen extends ChessPiece{

	public Queen(Color c) {
		super(c);
	}
	
	public ArrayList<Square> returnPath(Square sqr, Color c){
		ArrayList<Square> sqrArr = new ArrayList<Square>();
		int sqrX = sqr.getX();
		int sqrY = sqr.getY();
		
		//Steg upp
		for (int i = 1; i <= sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg ner
		for (int i = 1; i <= 7-sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg åt vänster
		for (int i = 1; i <= sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg åt höger
		for (int i = 1; i <= 7-sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg upp och måt vänster
		for (int i = 1; i < 8; i++) {
			if(sqrY-i < 0 || sqrX-i < 0) break;
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX-i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg upp och mot höger
		for (int i = 1; i < 8; i++) {
			if(sqrY-i < 0 || sqrX+i > 7) break;
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX+i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg ner och mot höger
		for (int i = 1; i < 8; i++) {
			if(sqrY+i > 7 || sqrX+i > 7) break;
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX+i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		//Steg ner och mot vänster
		for (int i = 1; i < 8; i++) {
			if(sqrY+i > 7 || sqrX-i < 0) break;
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX-i);
			if(s.hasPiece() && s.isSameColor()) break;
			else if(s.hasPiece()) {
				sqrArr.add(s);
				break;
			}
			sqrArr.add(s);
		}
		return sqrArr;
	}
}