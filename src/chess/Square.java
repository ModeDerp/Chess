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

		//Om man klickar på en ruta
		this.setOnMouseClicked(event->{
			if(won == true) return;
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
					ArrayList<Square> sqrArr = cp.returnPath(this,selectedSquare.getPieceColor());
					for (Square square : sqrArr) {
						square.addPath();
					}
				}
				//Återställer rutan, detta gör så att man kan klicka på den valda rutan för att återställa den
				else {
					r.setFill(bgColor);
					selected = false;
					selectedSquare = null;
				}
				if(isSquareCheck(this , turn)) System.out.println("Check!!");
				else System.out.println("Not Check!!");
			}
			//Om istället har klickat på en ruta med "path"
			else if(hasPath()) {
				//Om rutan man klickar på har en pjäs av annan färg eller ingen pjäs
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
		changeTurn();
	}
	
	/**
	 * Byter tur mellan svart och vit och skriver ut vems tur det är.
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
	public void removeIndPath() {
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