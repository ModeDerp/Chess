package chess;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pieces.ChessPiece;
import pieces.Pawn;
import pieces.Queen;

public class Square extends Group{

	public static double SIZE = 50;
	private Rectangle r;
	private Color bgColor;
	private boolean selected = false;
	public static Square selectedSquare;
	private ChessPiece cp;
	private Color pieceColor;
	private int xpos;
	private int ypos;
	private Circle cir;
	public static ArrayList<Square> pathArr = new ArrayList<Square>();

	public static Color turn = Color.WHITE;

	public Square(int x, int y,Color c) {
		r = new Rectangle(SIZE,SIZE);
		r.setFill(c);
		bgColor = c;

		this.setTranslateX(x*SIZE);
		xpos = x;
		this.setTranslateY(y*SIZE);
		ypos = y;

		this.getChildren().add(r);
		
		//Om man klickar på en ruta
		this.setOnMouseClicked(event->{
			//Kollar om man klickat på en pjäs och om pjäsen har samma färg som "turn" vilket är variabeln som håller koll på vems tur det är
			if(hasPiece() && getPieceColor() == turn) {
				removeAllPath();
				//Om rutan man klickar på inte är vald väljs den
				if(!selected) {
					r.setFill(Color.ORANGE);
					selected = true;
					//Om det finns en selectedsquare alltså en vald ruta återställs den
					if(selectedSquare != null) {
						selectedSquare.r.setFill(selectedSquare.bgColor);
						selectedSquare.selected = false;
					}
					selectedSquare = this;
					cp.showPath(this, selectedSquare.getPieceColor());
				}
				//Återställer rutan, detta gör så att man kan klicka på den valda rutan för att återställa den
				else {
					r.setFill(bgColor);
					selected = false;
					selectedSquare = null;
				}
			}
			//Om istället har klickat på en ruta med "path"
			else if(hasPath()) {
				//Om rutan man klickar på har en pjäs av annan färg eller ingen pjäs
				if(!isSameColor() || this.getPieceColor() == null) {
					removePiece(); //Tar bort eventuella pjäser av annan färg, dock måste det inte finnas en pjäs att ta bort
					movePiece();
					removeAllPath();
					selectedSquare.r.setFill(selectedSquare.bgColor);
					selectedSquare.selected = false;
				}
			}
			//Om man klickat på en ruta utan path och pjäs, alltså en tom ruta, återställs den valda rutan om en sådan finns
			else if(selectedSquare != null) {
				selectedSquare.r.setFill(selectedSquare.bgColor);
				selectedSquare.selected = false;
				removeAllPath();
			}
		});
	}

	public boolean hasPiece() {
		return this.cp != null;
	}

	public void addPiece(ChessPiece pcs, Color c) {
		//Om en bonde har tagit sig över planen blir den en drottning
		if(((c == Color.BLACK && ypos == 7) || (c == Color.WHITE && ypos == 0)) && pcs instanceof Pawn) pcs = new Queen(c);
		this.getChildren().add(pcs);
		this.cp = pcs;
		pieceColor = c;
	}

	public void movePiece() {
		this.addPiece(selectedSquare.getPiece(), selectedSquare.getPieceColor());
		selectedSquare.removePiece();
		if(turn == Color.WHITE) turn = Color.BLACK;
		else if(turn == Color.BLACK) turn = Color.WHITE;
	}

	public ChessPiece getPiece() {
		return this.cp;
	}

	public int getX() {
		return this.xpos;
	}

	public int getY() {
		return this.ypos;
	}

	public Color getPieceColor() {
		return this.pieceColor;
	}
	public void addPath() {
		cir = new Circle(10);
		cir.setTranslateX(SIZE/2);
		cir.setTranslateY(SIZE/2);
		cir.setFill(Color.RED);
		this.getChildren().add(cir);
		pathArr.add(this);
	}

	public boolean hasPath() {
		return this.cir != null;
	}

	public void removeIndPath() {
		this.getChildren().remove(cir);
		cir = null;
	}

	public void removeAllPath() {
		for (Square sqr : pathArr) {
			sqr.removeIndPath();
		}
		pathArr.clear();
	}

	public void removePiece() {
		this.getChildren().remove(cp);
		cp = null;
		pieceColor = null;
	}
	public boolean isSameColor() {
		if(getPieceColor() == selectedSquare.getPieceColor()) {
			return true;
		}
		else return false;
	}
}