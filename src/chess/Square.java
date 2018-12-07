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
		
		//Om man klickar p� en ruta
		this.setOnMouseClicked(event->{
			//Kollar om man klickat p� en pj�s och om pj�sen har samma f�rg som "turn" vilket �r variabeln som h�ller koll p� vems tur det �r
			if(hasPiece() && getPieceColor() == turn) {
				removeAllPath();
				//Om rutan man klickar p� inte �r vald v�ljs den
				if(!selected) {
					r.setFill(Color.ORANGE);
					selected = true;
					//Om det finns en selectedsquare allts� en vald ruta �terst�lls den
					if(selectedSquare != null) {
						selectedSquare.r.setFill(selectedSquare.bgColor);
						selectedSquare.selected = false;
					}
					selectedSquare = this;
					cp.showPath(this, selectedSquare.getPieceColor());
				}
				//�terst�ller rutan, detta g�r s� att man kan klicka p� den valda rutan f�r att �terst�lla den
				else {
					r.setFill(bgColor);
					selected = false;
					selectedSquare = null;
				}
			}
			//Om ist�llet har klickat p� en ruta med "path"
			else if(hasPath()) {
				//Om rutan man klickar p� har en pj�s av annan f�rg eller ingen pj�s
				if(!isSameColor() || this.getPieceColor() == null) {
					removePiece(); //Tar bort eventuella pj�ser av annan f�rg, dock m�ste det inte finnas en pj�s att ta bort
					movePiece();
					removeAllPath();
					selectedSquare.r.setFill(selectedSquare.bgColor);
					selectedSquare.selected = false;
				}
			}
			//Om man klickat p� en ruta utan path och pj�s, allts� en tom ruta, �terst�lls den valda rutan om en s�dan finns
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
		//Om en bonde har tagit sig �ver planen blir den en drottning
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