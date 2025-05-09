public class CheckersModel {

    static final int EMPTY = 0,
            RED = 1,
            RED_KING = 2,
            BLACK = 3,
            BLACK_KING = 4;

    int[][] board; // board[r][c] is the contents of row r, column c.

    boolean gameInProgress; // Is a game currently in progress?

    /* The next three variables are valid only when the game is in progress. */

    int currentPlayer; // Whose turn is it now? The possible values
                       // are CheckersModel.RED and CheckersModel.BLACK.

    int selectedRow, selectedCol; // If the current player has selected a piece to
                                  // move, these give the row and column
                                  // containing that piece. If no piece is
                                  // yet selected, then selectedRow is -1.

    CheckersMove[] legalMoves; // An array containing the legal moves for the
                               // current player.

    public CheckersModel() {
        board = new int[8][8];
        setUpGame();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int player) {
        currentPlayer = player;

    }

    /**
     * Set up the board with checkers in position for the beginning
     * of a game. Note that checkers can only be found in squares
     * that satisfy row % 2 == col % 2. At the start of the game,
     * all such squares in the first three rows contain black squares
     * and all such squares in the last three rows contain red squares.
     */
    void setUpGame() {
        currentPlayer = CheckersModel.RED; // RED moves first.
        legalMoves = getLegalMovesForPlayer(CheckersModel.RED); // Get RED's legal moves.
        selectedRow = -1; // RED has not yet selected a piece to move.

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row % 2 == col % 2) {
                    if (row < 3)
                        board[row][col] = BLACK;
                    else if (row > 4)
                        board[row][col] = RED;
                    else
                        board[row][col] = EMPTY;
                } else {
                    board[row][col] = EMPTY;
                }
            }
        }
    } // end setUpGame()

    /**
     * Return the contents of the square in the specified row and column.
     */
    int pieceAt(int row, int col) {
        return board[row][col];
    }

    /**
     * Make the specified move. It is assumed that move
     * is non-null and that the move it represents is legal.
     */
    void makeMove(CheckersMove move) {
        makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
    }

    /**
     * Make the move from (fromRow,fromCol) to (toRow,toCol). It is
     * assumed that this move is legal. If the move is a jump, the
     * jumped piece is removed from the board. If a piece moves to
     * the last row on the opponent's side of the board, the
     * piece becomes a king.
     */
    void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        board[toRow][toCol] = board[fromRow][fromCol];
        board[fromRow][fromCol] = EMPTY;
        if (fromRow - toRow == 2 || fromRow - toRow == -2) {
            // The move is a jump. Remove the jumped piece from the board.
            int jumpRow = (fromRow + toRow) / 2; // Row of the jumped piece.
            int jumpCol = (fromCol + toCol) / 2; // Column of the jumped piece.
            board[jumpRow][jumpCol] = EMPTY;
        }
        if (toRow == 0 && board[toRow][toCol] == RED)
            board[toRow][toCol] = RED_KING;
        if (toRow == 7 && board[toRow][toCol] == BLACK)
            board[toRow][toCol] = BLACK_KING;
    }

    /**
     * Return an array containing all the legal CheckersMoves
     * for the specified player on the current board. If the player
     * has no legal moves, null is returned. The value of player
     * should be one of the constants RED or BLACK; if not, null
     * is returned. If the returned value is non-null, it consists
     * entirely of jump moves or entirely of regular moves, since
     * if the player can jump, only jumps are legal moves.
     */
    CheckersMove[] getLegalMovesForPlayer(int player) {

        if (player != RED && player != BLACK)
            return null;

        int playerKing; // The constant representing a King belonging to player.
        if (player == RED)
            playerKing = RED_KING;
        else
            playerKing = BLACK_KING;

        ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // Moves will be stored in this list.

        /*
         * First, check for any possible jumps. Look at each square on the board.
         * If that square contains one of the player's pieces, look at a possible
         * jump in each of the four directions from that square. If there is
         * a legal jump in that direction, put it in the moves ArrayList.
         */
        // use board.length; and board[0].length
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == player || board[row][col] == playerKing) {
                    if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
                        moves.add(new CheckersMove(row, col, row + 2, col + 2));
                    if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
                        moves.add(new CheckersMove(row, col, row - 2, col + 2));
                    if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
                        moves.add(new CheckersMove(row, col, row + 2, col - 2));
                    if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
                        moves.add(new CheckersMove(row, col, row - 2, col - 2));
                }
            }
        }

        /*
         * If any jump moves were found, then the user must jump, so we don't
         * add any regular moves. However, if no jumps were found, check for
         * any legal regular moves. Look at each square on the board.
         * If that square contains one of the player's pieces, look at a possible
         * move in each of the four directions from that square. If there is
         * a legal move in that direction, put it in the moves ArrayList.
         */

        if (moves.size() == 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board[row][col] == player || board[row][col] == playerKing) {
                        if (canMove(player, row, col, row + 1, col + 1))
                            moves.add(new CheckersMove(row, col, row + 1, col + 1));
                        if (canMove(player, row, col, row - 1, col + 1))
                            moves.add(new CheckersMove(row, col, row - 1, col + 1));
                        if (canMove(player, row, col, row + 1, col - 1))
                            moves.add(new CheckersMove(row, col, row + 1, col - 1));
                        if (canMove(player, row, col, row - 1, col - 1))
                            moves.add(new CheckersMove(row, col, row - 1, col - 1));
                    }
                }
            }
        }

        /*
         * If no legal moves have been found, return null. Otherwise, create
         * an array just big enough to hold all the legal moves, copy the
         * legal moves from the ArrayList into the array, and return the array.
         */

        if (moves.size() == 0)
            return null;
        else {
            CheckersMove[] moveArray = new CheckersMove[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = moves.get(i);
            return moveArray;
        }

    } // end getLegalMoves

    public CheckersMove[] getLegalMoves() {
        return legalMoves;
    }

    public void setLegalMoves(CheckersMove[] legalMoves) {
        this.legalMoves = legalMoves;
    }

    /**
     * Return a list of the legal jumps that the specified player can
     * make starting from the specified row and column. If no such
     * jumps are possible, null is returned. The logic is similar
     * to the logic of the getLegalMoves() method.
     */

    // ***this is needed b/c once you jump, you can move again IF THE NEXT IS A JUMP
    public CheckersMove[] getLegalJumpsFrom(int player, int row, int col) {
        if (player != RED && player != BLACK)
            return null;
        int playerKing; // The constant representing a King belonging to player.
        if (player == RED)
            playerKing = RED_KING;
        else
            playerKing = BLACK_KING;
        ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>(); // The legal jumps will be stored in this
                                                                       // list.
        if (board[row][col] == player || board[row][col] == playerKing) {
            if (canJump(player, row, col, row + 1, col + 1, row + 2, col + 2))
                moves.add(new CheckersMove(row, col, row + 2, col + 2));
            if (canJump(player, row, col, row - 1, col + 1, row - 2, col + 2))
                moves.add(new CheckersMove(row, col, row - 2, col + 2));
            if (canJump(player, row, col, row + 1, col - 1, row + 2, col - 2))
                moves.add(new CheckersMove(row, col, row + 2, col - 2));
            if (canJump(player, row, col, row - 1, col - 1, row - 2, col - 2))
                moves.add(new CheckersMove(row, col, row - 2, col - 2));
        }
        if (moves.size() == 0)
            return null;
        else {
            CheckersMove[] moveArray = new CheckersMove[moves.size()];
            for (int i = 0; i < moves.size(); i++)
                moveArray[i] = moves.get(i);
            return moveArray;
        }
    } // end getLegalMovesFrom()

    /**
     * This is called by the two previous methods to check whether the
     * player can legally jump from (r1,c1) to (r3,c3). It is assumed
     * that the player has a piece at (r1,c1), that (r3,c3) is a position
     * that is 2 rows and 2 columns distant from (r1,c1) and that
     * (r2,c2) is the square between (r1,c1) and (r3,c3).
     */
    private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {

        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
            return false; // (r3,c3) is off the board.

        if (board[r3][c3] != EMPTY)
            return false; // (r3,c3) already contains a piece.

        if (player == RED) {
            if (board[r1][c1] == RED && r3 > r1)
                return false; // Regular red piece can only move up.
            if (board[r2][c2] != BLACK && board[r2][c2] != BLACK_KING)
                return false; // There is no black piece to jump.
            return true; // The jump is legal.
        } else {
            if (board[r1][c1] == BLACK && r3 < r1)
                return false; // Regular black piece can only move downn.
            if (board[r2][c2] != RED && board[r2][c2] != RED_KING)
                return false; // There is no red piece to jump.
            return true; // The jump is legal.
        }

    } // end canJump()

    /**
     * This is called by the getLegalMoves() method to determine whether
     * the player can legally move from (r1,c1) to (r2,c2). It is
     * assumed that (r1,r2) contains one of the player's pieces and
     * that (r2,c2) is a neighboring square.
     */
    private boolean canMove(int player, int r1, int c1, int r2, int c2) {

        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
            return false; // (r2,c2) is off the board.

        if (board[r2][c2] != EMPTY)
            return false; // (r2,c2) already contains a piece.

        if (player == RED) {
            if (board[r1][c1] == RED && r2 > r1)
                return false; // Regular red piece can only move down.
            return true; // The move is legal.
        } else {
            if (board[r1][c1] == BLACK && r2 < r1)
                return false; // Regular black piece can only move up.
            return true; // The move is legal.
        }

    } // end canMove()

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void setGameInProgress(boolean gameInProgress) {
        this.gameInProgress = gameInProgress;
    }

    public int getSelectedCol() {
        return selectedCol;
    }

    public void setSelectedCol(int col) {
        selectedCol = col;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(int row) {
        selectedRow = row;
    }

}
