package boardgame;

public class Board {
	private int rows =8  ;
	private int columns=8 ;
	private Piece[][] pieces;

	public Board(int row, int colums) {

		if (rows < 1 || colums < 1) {
			throw new BoardExeption(
		"Erro criando o tabuleiro , é necessario que tenha pelo menos 1 linha e 1 coluna");
}
		this.rows = row;
		this.columns = colums;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) { 
		throw new BoardExeption("Posição não existente");
		}
		return pieces[row][column];

	}

	public Piece piece(Position position) {
		if (!PositionExists(position)) 
			throw new BoardExeption("Posição não existente");
		
		return pieces[position.getRow()][position.getColumn()];

	}

	public void placePiece(Piece piece, Position position) {
		if (ThereisAPiece(position)) {
			throw new BoardExeption("Já tem uma peça na posição " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;

	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;

	}

	public boolean PositionExists(Position position) {

		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean ThereisAPiece(Position position) {
		if (!PositionExists(position)) {
			throw new BoardExeption("Posição não existente 3");}
		return piece(position) != null;
		

	}
}
