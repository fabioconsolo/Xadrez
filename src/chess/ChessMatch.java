package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private Color currentPlayer;
	private int turn;
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn =1;
		currentPlayer = Color.WHITE;
		inicialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
		
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}

		}
		return mat;

	}
	
			public boolean[][] possibleMoves(ChessPosition sourcePosition){
				Position position = sourcePosition.toPosition();
				ValidadeSourcePosition(position);
				return board.piece(position).possibleMoves();
				
				
			}
			
			
			
			
	public ChessPiece performChessMove(ChessPosition sourcePOsition ,ChessPosition  targetPosition ) {
		
		Position source = sourcePOsition.toPosition();
		Position target = targetPosition.toPosition();
		ValidadeSourcePosition(source);
		validadeTargetPosition(source,target);
		Piece capturedPiece = makeMove(source , target);
		nextTrun();
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Position source , Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
		
		
		
	}
	
	private void ValidadeSourcePosition(Position position) {
		
		if (!board.ThereisAPiece(position)) {
			throw new ChessException("N�o existe pe�a nessa posi��o do tabuleiro ");
		} 
		
		if(currentPlayer !=((ChessPiece) board.piece(position)).getColor()) {
			
			throw new ChessException("A pe�a escolhida � do outro jogador");
			
			
		}
		
		if(!board.piece(position).isThereAntPossibleMove()) {
			throw new ChessException("N�o a movimentos possiveis para essa pe�a " );
		}
		
	}
	
	private void validadeTargetPosition(Position source , Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("A pe�a escolhida n pode se movimentar para a posi��o de destino");
			
		}
		
	}
	
	
	private void nextTrun() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK :Color.WHITE;
		
	}
	
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void inicialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}