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
	
	/**
	 * Kollar om den Square som kallar metoden har en pjäs
	 * @return Boolean
	 */
	public boolean hasPiece() {
		return this.cp != null;
	}

	/**
	 * Lägger till en pjäs på den Square som kallar metoden
	 * @param pcs pjäs objektet
	 * @param c färgen på pjäsen
	 */
	public void addPiece(ChessPiece pcs, Color c) {
		//Om en bonde har tagit sig över planen blir den en drottning
		if(((c == Color.BLACK && ypos == 7) || (c == Color.WHITE && ypos == 0)) && pcs instanceof Pawn) pcs = new Queen(c);
		this.getChildren().add(pcs);
		this.cp = pcs;
		pieceColor = c;
	}
	
	/**
	 * Flyttar en pjäs genom att ta bort den pjäs som finns i den Square som finns i variabeln selectedSquare och lägga till en av samma typ i den Square som kalla metoden
	 */
	public void movePiece() {
		this.addPiece(selectedSquare.getPiece(), selectedSquare.getPieceColor());
		selectedSquare.removePiece();
		if(turn == Color.WHITE) turn = Color.BLACK;
		else if(turn == Color.BLACK) turn = Color.WHITE;
	}

	/**
	 * Returnerar ChessPiece av den Square som kallar metoden
	 * @return ChessPiece
	 */
	public ChessPiece getPiece() {
		return this.cp;
	}

	/**
	 * Returnerar x värdet av den Square som kallar metoden
	 * @return int x
	 */
	public int getX() {
		return this.xpos;
	}

	/**
	 * Returnerar y värdet av den Square som kallar metoden
	 * @return int y
	 */
	public int getY() {
		return this.ypos;
	}

	/**
	 * Returnerar färgen av pjäsen inom den Square som kallar metoden
	 * @return Color c av en en pjäs
	 */
	public Color getPieceColor() {
		return this.pieceColor;
	}
	
	/**
	 * Lägger till en ny "path" på den Square som kallar metoden
	 */
	public void addPath() {
		cir = new Circle(10);
		cir.setTranslateX(SIZE/2);
		cir.setTranslateY(SIZE/2);
		cir.setFill(Color.RED);
		this.getChildren().add(cir);
		pathArr.add(this);
	}

	/**
	 * Kollar om den Square som kallar metoden har en "path"
	 * @return Boolean 
	 */
	public boolean hasPath() {
		return this.cir != null;
	}

	/**
	 * Tar bort individuell path på den Square som kallar metoden
	 */
	private void removeIndPath() {
		this.getChildren().remove(cir);
		cir = null;
	}

	/**
	 * Tar bort all path genom att kalla på metoden removeIndPath() för varje path i arrayen pathArr
	 */
	public void removeAllPath() {
		for (Square sqr : pathArr) {
			sqr.removeIndPath();
		}
		pathArr.clear();
	}

	/**
	 * Tar bort pjäsen av den Square som kallar metoden
	 */
	public void removePiece() {
		this.getChildren().remove(cp);
		cp = null;
		pieceColor = null;
	}

	/**
	 * Jämför färgen av den pjäs av den Square som kallar metoden mot färgen av pjäsen av den Square som finns i variabeln selectedSquare
	 * @return Boolean
	 */
	public boolean isSameColor() {
		if(getPieceColor() == selectedSquare.getPieceColor()) {
			return true;
		}
		else return false;
	}
}