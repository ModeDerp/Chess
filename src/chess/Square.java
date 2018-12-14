package chess;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pieces.ChessPiece;
import pieces.King;
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
	public static Boolean chess = false;
	public static Boolean won = false;

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
			if(won == true) return;
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
					ArrayList<Square> sqrArr = cp.returnPath(this,selectedSquare.getPieceColor());
					for (Square square : sqrArr) {
						square.addPath();
					}
				}
				//�terst�ller rutan, detta g�r s� att man kan klicka p� den valda rutan f�r att �terst�lla den
				else {
					r.setFill(bgColor);
					selected = false;
					selectedSquare = null;
				}
				if(isSquareCheck(this , turn)) System.out.println("Check!!");
				else System.out.println("Not Check!!");
			}
			//Om ist�llet har klickat p� en ruta med "path"
			else if(hasPath()) {
				//Om rutan man klickar p� har en pj�s av annan f�rg eller ingen pj�s
				if(!isSameColor() || this.getPieceColor() == null) {
					if(cp instanceof King) {
						won = true;
						if(turn == Color.WHITE) {
							System.out.println("White Won");
						}
						else if(turn == Color.BLACK) {
							System.out.println("Black Won");
						}
					}
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
	
	/**
	 * Kollar om den Square som kallar metoden har en pj�s
	 * @return Boolean
	 */
	public boolean hasPiece() {
		return this.cp != null;
	}

	/**
	 * L�gger till en pj�s p� den Square som kallar metoden
	 * @param pcs pj�s objektet
	 * @param c f�rgen p� pj�sen
	 */
	public void addPiece(ChessPiece pcs, Color c) {
		//Om en bonde har tagit sig �ver planen blir den en drottning
		if(((c == Color.BLACK && ypos == 7) || (c == Color.WHITE && ypos == 0)) && pcs instanceof Pawn) pcs = new Queen(c);
		this.getChildren().add(pcs);
		this.cp = pcs;
		pieceColor = c;
	}
	
	/**
	 * Flyttar en pj�s genom att ta bort den pj�s som finns i den Square som finns i variabeln selectedSquare och l�gga till en av samma typ i den Square som kalla metoden
	 */
	public void movePiece() {
		this.addPiece(selectedSquare.getPiece(), selectedSquare.getPieceColor());
		selectedSquare.removePiece();
		changeTurn();
	}
	
	/**
	 * Byter tur mellan svart och vit och skriver ut vems tur det �r.
	 */
	
	public void changeTurn() {
		if(won == true) return;
		if(check() == Color.BLACK) System.out.println("Check, Black King");
		if(check() == Color.WHITE) System.out.println("Check, White King");
		if(turn == Color.WHITE) {
			turn = Color.BLACK;
			System.out.println("Black's Turn");
			System.out.println("------------");
		}
		else if(turn == Color.BLACK) {
			turn = Color.WHITE;
			System.out.println("White's Turn");
			System.out.println("------------");
		}
	}

	/**
	 * Returnerar ChessPiece av den Square som kallar metoden
	 * @return ChessPiece
	 */
	public ChessPiece getPiece() {
		return this.cp;
	}

	/**
	 * Returnerar x v�rdet av den Square som kallar metoden
	 * @return int x
	 */
	public int getX() {
		return this.xpos;
	}

	/**
	 * Returnerar y v�rdet av den Square som kallar metoden
	 * @return int y
	 */
	public int getY() {
		return this.ypos;
	}

	/**
	 * Returnerar f�rgen av pj�sen inom den Square som kallar metoden
	 * @return Color c av en en pj�s
	 */
	public Color getPieceColor() {
		return this.pieceColor;
	}
	
	/**
	 * L�gger till en ny "path" p� den Square som kallar metoden
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
	 * Tar bort individuell path p� den Square som kallar metoden
	 */
	public void removeIndPath() {
		this.getChildren().remove(cir);
		cir = null;
	}

	/**
	 * Tar bort all path genom att kalla p� metoden removeIndPath() f�r varje path i arrayen pathArr
	 */
	public void removeAllPath() {
		for (Square sqr : pathArr) {
			sqr.removeIndPath();
		}
		pathArr.clear();
	}

	/**
	 * Tar bort pj�sen av den Square som kallar metoden
	 */
	public void removePiece() {
		this.getChildren().remove(cp);
		cp = null;
		pieceColor = null;
	}

	/**
	 * J�mf�r f�rgen av den pj�s av den Square som kallar metoden mot f�rgen av pj�sen av den Square som finns i variabeln selectedSquare
	 * @return Boolean
	 */
	public boolean isSameColor() {
		if(getPieceColor() == selectedSquare.getPieceColor()) {
			return true;
		}
		else return false;
	}
	
	public Color check() {
		Color king = null;
		for (ArrayList<Square> list : ChessBoard.map) {
			for (Square s : list) {
				if(s.hasPiece()) {
					ArrayList<Square> s2 = s.getPiece().returnPath(s, s.getPieceColor());
					for (Square s3 : s2) {
						if((s3.getPiece() instanceof King) && (s3.getPieceColor() != s.getPieceColor())) king = s3.getPieceColor();
					}
				}
			}
		}
		return king;
	}
	public Boolean isKingCheck(Color c) {
		if(c == check()) return true;
		else return false;
	}
	public Boolean isSquareCheck(Square sqr, Color turnCol) {
		sqr.r.setFill(Color.BLUE);
		Boolean bool = false;
		for (ArrayList<Square> list : ChessBoard.map) {
			for (Square s : list) {
				
				if(s.hasPiece() && (s.getPieceColor() != turnCol)) {
					ArrayList<Square> s2 = s.getPiece().returnPath(s, s.getPieceColor());
					for (Square s3 : s2) {
						s3.r.setFill(Color.YELLOW);
						if(s3.xpos == sqr.xpos) {
							System.out.println(s3.ypos);
						}
						if((s3 == sqr)) {
							bool = true;
							System.out.println("BOOL");
							}
					}
				}
			}
		}
		System.out.println(bool);
		return bool;
	}
}