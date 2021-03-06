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
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg ner
		for (int i = 1; i <= 7-sqrY; i++) {
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg �t v�nster
		for (int i = 1; i <= sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX-i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg �t h�ger
		for (int i = 1; i <= 7-sqrX; i++) {
			Square s = ChessBoard.map.get(sqrY).get(sqrX+i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg upp och m�t v�nster
		for (int i = 1; i < 8; i++) {
			if(sqrY-i < 0 || sqrX-i < 0) break;
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX-i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg upp och mot h�ger
		for (int i = 1; i < 8; i++) {
			if(sqrY-i < 0 || sqrX+i > 7) break;
			Square s = ChessBoard.map.get(sqrY-i).get(sqrX+i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg ner och mot h�ger
		for (int i = 1; i < 8; i++) {
			if(sqrY+i > 7 || sqrX+i > 7) break;
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX+i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		//Steg ner och mot v�nster
		for (int i = 1; i < 8; i++) {
			if(sqrY+i > 7 || sqrX-i < 0) break;
			Square s = ChessBoard.map.get(sqrY+i).get(sqrX-i);
			sqrArr.add(s);
			if(s.hasPiece()) break;
		}
		return sqrArr;
	}
}