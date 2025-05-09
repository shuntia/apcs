import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class CheckersController implements ActionListener, MouseListener {

    private CheckersView view;
    private CheckersModel model;

    public CheckersController(CheckersView v, CheckersModel m) {
        view = v;
        model = m;
        v.getNewGameButton().addActionListener(this);
        v.getResignButton().addActionListener(this);
        v.addMouseListener(this);
    }

    /**
     * Respond to user's click on one of the two buttons.
     */
    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == view.getNewGameButton())
            doNewGame();
        else if (src == view.getResignButton())
            doResign();
    }

    /**
     * Start a new game
     */
    void doNewGame() {
        if (model.isGameInProgress() == true) {
            // This should not be possible, but it doesn't hurt to check.
            view.setMessageText("Finish the current game first!");
            return;
        }
        model.setUpGame(); // Set up the pieces.
    }

    /**
     * Current player resigns. Game ends. Opponent wins.
     */
    void doResign() {
        if (model.isGameInProgress() == false) { // Should be impossible.
            view.setMessageText("There is no game in progress!");
            return;
        }
        if (model.getCurrentPlayer() == CheckersModel.RED)
            gameOver("RED resigns.  BLACK wins.");
        else
            gameOver("BLACK resigns.  RED wins.");
    }

    /**
     * The game ends. The parameter, str, is displayed as a message
     * to the user. The states of the buttons are adjusted so players
     * can start a new game. This method is called when the game
     * ends at any point in this class.
     */
    void gameOver(String str) {
        view.setMessageText(str);
        view.getNewGameButton().setEnabled(true);
        view.getResignButton().setEnabled(false);
        model.setGameInProgress(false);
    }

    /**
     * This is called by mousePressed() when a player clicks on the
     * square in the specified row and col. It has already been checked
     * that a game is, in fact, in progress.
     */
    void doClickSquare(int row, int col) {

        /*
         * If the player clicked on one of the pieces that the player
         * can move, mark this row and col as selected and return. (This
         * might change a previous selection.) Reset the message, in
         * case it was previously displaying an error message.
         */

        CheckersMove[] legalMoves = model.getLegalMoves();

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col) {
                model.setSelectedRow(row);
                model.setSelectedCol(col);
                if (model.getCurrentPlayer() == CheckersModel.RED)
                    view.setMessageText("RED:  Make your move.");
                else
                    view.setMessageText("BLACK:  Make your move.");
                view.repaint();
                return;
            }

        /*
         * If no piece has been selected to be moved, the user must first
         * select a piece. Show an error message and return.
         */

        if (model.getSelectedRow() < 0) {
            view.setMessageText("Click the piece you want to move.");
            return;
        }

        /*
         * If the user clicked on a square where the selected piece can be
         * legally moved, then make the move and return.
         */

        for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == model.getSelectedRow() && legalMoves[i].fromCol == model.getSelectedCol()
                    && legalMoves[i].toRow == row && legalMoves[i].toCol == col) {
                doMakeMove(legalMoves[i]);
                return;
            }

        /*
         * If we get to this point, there is a piece selected, and the square where
         * the user just clicked is not one where that piece can be legally moved.
         * Show an error message.
         */

        view.setMessageText("Click the square you want to move to.");

    } // end doClickSquare()

    /**
     * This is called when the current player has chosen the specified
     * move. Make the move, and then either end or continue the game
     * appropriately.
     */
    void doMakeMove(CheckersMove move) {

        model.makeMove(move);

        CheckersMove[] legalMoves = model.getLegalMoves();
        int currentPlayer = model.getCurrentPlayer();
        /*
         * If the move was a jump, it's possible that the player has another
         * jump. Check for legal jumps starting from the square that the player
         * just moved to. If there are any, the player must jump. The same
         * player continues moving.
         */

        if (move.isJump()) {
            legalMoves = model.getLegalJumpsFrom(currentPlayer, move.toRow, move.toCol);
            if (legalMoves != null) {
                if (currentPlayer == CheckersModel.RED)
                    view.setMessageText("RED:  You must continue jumping.");
                else
                    view.setMessageText("BLACK:  You must continue jumping.");
                model.setSelectedRow(move.toRow); // Since only one piece can be moved, select it;
                model.setSelectedCol(move.toCol);
                view.repaint();
                return;
            }
        }

        /*
         * The current player's turn is ended, so change to the other player.
         * Get that player's legal moves. If the player has no legal moves,
         * then the game ends.
         */

        if (currentPlayer == CheckersModel.RED) {
            currentPlayer = CheckersModel.BLACK;
            legalMoves = model.getLegalMovesForPlayer(currentPlayer);
            if (legalMoves == null)
                gameOver("BLACK has no moves.  RED wins.");
            else if (legalMoves[0].isJump())
                view.setMessageText("BLACK:  Make your move.  You must jump.");
            else
                view.setMessageText("BLACK:  Make your move.");
        } else {
            currentPlayer = CheckersModel.RED;
            legalMoves = model.getLegalMovesForPlayer(currentPlayer);
            if (legalMoves == null)
                gameOver("RED has no moves.  BLACK wins.");
            else if (legalMoves[0].isJump())
                view.setMessageText("RED:  Make your move.  You must jump.");
            else
                view.setMessageText("RED:  Make your move.");
        }

        /*
         * Set selectedRow = -1 to record that the player has not yet selected
         * a piece to move.
         */

        model.setSelectedRow(-1);

        /*
         * As a courtesy to the user, if all legal moves use the same piece, then
         * select that piece automatically so the user won't have to click on it
         * to select it.
         */

        if (legalMoves != null) {
            boolean sameStartSquare = true;
            for (int i = 1; i < legalMoves.length; i++)
                if (legalMoves[i].fromRow != legalMoves[0].fromRow
                        || legalMoves[i].fromCol != legalMoves[0].fromCol) {
                    sameStartSquare = false;
                    break;
                }
            if (sameStartSquare) {
                model.setSelectedRow(legalMoves[0].fromRow);
                model.setSelectedCol(legalMoves[0].fromCol);
            }
        }

        /* Make sure the board is redrawn in its new state. */

    } // end doMakeMove();

    /**
     * Draw a checkerboard pattern in gray and lightGray. Draw the
     * checkers. If a game is in progress, hilite the legal moves.
     */
    public void paintComponent(Graphics g) {

        /* Turn on antialiasing to get nicer ovals. */

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* Draw a two-pixel black border around the edges of the canvas. */

        g.setColor(Color.black);
        g.drawRect(0, 0, view.getSize().width - 1, view.getSize().height - 1);
        g.drawRect(1, 1, view.getSize().width - 3, view.getSize().height - 3);

        /* Draw the squares of the checkerboard and the checkers. */

        for (int row = 0; row < model.board.length; row++) {
            for (int col = 0; col < model.board[0].length; col++) {
                if (row % 2 == col % 2)
                    g.setColor(Color.LIGHT_GRAY);
                else
                    g.setColor(Color.GRAY);
                g.fillRect(2 + col * 20, 2 + row * 20, 20, 20);
                switch (model.pieceAt(row, col)) {
                    case CheckersModel.RED:
                        g.setColor(Color.RED);
                        g.fillOval(4 + col * 20, 4 + row * 20, 15, 15);
                        break;
                    case CheckersModel.BLACK:
                        g.setColor(Color.BLACK);
                        g.fillOval(4 + col * 20, 4 + row * 20, 15, 15);
                        break;
                    case CheckersModel.RED_KING:
                        g.setColor(Color.RED);
                        g.fillOval(4 + col * 20, 4 + row * 20, 15, 15);
                        g.setColor(Color.WHITE);
                        g.drawString("K", 7 + col * 20, 16 + row * 20);
                        break;
                    case CheckersModel.BLACK_KING:
                        g.setColor(Color.BLACK);
                        g.fillOval(4 + col * 20, 4 + row * 20, 15, 15);
                        g.setColor(Color.WHITE);
                        g.drawString("K", 7 + col * 20, 16 + row * 20);
                        break;
                }
            }
        }

        /*
         * If a game is in progress, hilite the legal moves. Note that legalMoves
         * is never null while a game is in progress.
         */

        CheckersMove[] legalMoves = model.getLegalMoves();
        int selectedRow = model.getSelectedRow();
        int selectedCol = model.getSelectedCol();

        if (model.isGameInProgress()) {
            /* First, draw a 2-pixel cyan border around the pieces that can be moved. */

            g.setColor(Color.cyan);
            for (int i = 0; i < legalMoves.length; i++) {
                g.drawRect(2 + legalMoves[i].fromCol * 20, 2 + legalMoves[i].fromRow * 20, 19, 19);
                g.drawRect(3 + legalMoves[i].fromCol * 20, 3 + legalMoves[i].fromRow * 20, 17, 17);
            }
            /*
             * If a piece is selected for moving (i.e. if selectedRow >= 0), then
             * draw a 2-pixel white border around that piece and draw green borders
             * around each square that that piece can be moved to.
             */
            if (selectedRow >= 0) {
                g.setColor(Color.white);
                g.drawRect(2 + selectedCol * 20, 2 + selectedRow * 20, 19, 19);
                g.drawRect(3 + selectedCol * 20, 3 + selectedRow * 20, 17, 17);
                g.setColor(Color.green);
                for (int i = 0; i < legalMoves.length; i++) {
                    if (legalMoves[i].fromCol == selectedCol && legalMoves[i].fromRow == selectedRow) {
                        g.drawRect(2 + legalMoves[i].toCol * 20, 2 + legalMoves[i].toRow * 20, 19, 19);
                        g.drawRect(3 + legalMoves[i].toCol * 20, 3 + legalMoves[i].toRow * 20, 17, 17);
                    }
                }
            }
        }

    } // end paintComponent()

    /**
     * Respond to a user click on the board. If no game is in progress, show
     * an error message. Otherwise, find the row and column that the user
     * clicked and call doClickSquare() to handle it.
     */
    public void mousePressed(MouseEvent evt) {
        if (model.isGameInProgress() == false)
            view.setMessageText("Click \"New Game\" to start a new game.");
        else {
            int col = (evt.getX() - 2) / 20;
            int row = (evt.getY() - 2) / 20;
            if (col >= 0 && col < 8 && row >= 0 && row < 8)
                doClickSquare(row, col);
        }
    }

    public void mouseReleased(MouseEvent evt) {
    }

    public void mouseClicked(MouseEvent evt) {
    }

    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }

}
