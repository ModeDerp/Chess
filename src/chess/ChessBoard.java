package chess;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard extends Group{
	
	public static ArrayList<ArrayList<Square>> map = new ArrayList<ArrayList<Square>>();
	
	//Add method for adding and removing pieces
	
	public ChessBoard() {
		for (int y = 0; y < 8; y++) {
			ArrayList<Square> row = new ArrayList<Square>();
			for (int x = 0; x < 8; x++) {
				
				Color c;
				if(x%2 == 0) {
					if(y%2 == 0) {
						c = Color.WHITE;
					}
					else {
						c = Color.GRAY;
					}
				}
				else {
					if(y%2 == 0) {
						c = Color.GRAY;
					}
					else {
						c = Color.WHITE;
					}
				}
				
				Square sqr = new Square(x,y,c);
				row.add(sqr);
				this.getChildren().add(sqr);
				
				if(y == 1) {
					ChessPiece pcs = new Pawn(Color.BLACK);
					sqr.addPiece(pcs, Color.BLACK);
				}
				else if(y == 6) {
					ChessPiece pcs = new Pawn(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}
				else if(y == 0 && (x == 0 || x == 7)) {
					ChessPiece pcs = new Rook(Color.BLACK);
					sqr.addPiece(pcs, Color.BLACK);
				}
				else if(y == 7 && (x == 0 || x == 7)) {
					ChessPiece pcs = new Rook(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}
				else if(y == 0 && (x == 1 || x == 6)) {
					ChessPiece pcs = new Bishop(Color.BLACK);
					sqr.addPiece(pcs, Color.BLACK);
				}
				else if(y == 7 && (x == 1 || x == 6)) {
					ChessPiece pcs = new Bishop(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}
				else if(y == 0 && (x == 2 || x == 5)) {
					ChessPiece pcs = new Knight(Color.BLACK);
					sqr.addPiece(pcs, Color.BLACK);
				}
				else if(y == 7 && (x == 2 || x == 5)) {
					ChessPiece pcs = new Knight(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}
				else if(y == 0 && (x == 4)) {
					ChessPiece pcs = new King(Color.BLACK);
					sqr.addPiece(pcs, Color.BLACK);
				}
				else if(y == 7 && (x == 4)) {
					ChessPiece pcs = new King(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}
				else if(y == 0 && (x == 3)) {
					ChessPiece pcs = new Queen(Color.BLACK);
					sqr.addPiece(pcs, Color.BLACK);
				}
				else if(y == 7 && (x == 3)) {
					ChessPiece pcs = new Queen(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}else if(y == 7 && (x == 3)) {
					ChessPiece pcs = new Queen(Color.WHITE);
					sqr.addPiece(pcs, Color.WHITE);
				}
			}			
			map.add(row);
		}
	}
}
