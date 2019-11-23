package com.olimp;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Given two different positions on a chess board, find the least number of moves it would take a knight to get from one to the other. The positions will be passed as two arguments in algebraic notation. For example, knight("a3", "b5") should return 1.
 * <p>
 * The knight is not allowed to move off the board. The board is 8x8.
 * <p>
 * For information on knight moves, see https://en.wikipedia.org/wiki/Knight_%28chess%29
 * <p>
 * For information on algebraic notation, see https://en.wikipedia.org/wiki/Algebraic_notation_%28chess%29
 */

public class Main {

    public static void main(String[] args) {
        System.out.println(Chess.knight("a1", "b3"));
    }

    public static class Chess {

        //the idea is to go to next possible moves and flag current as non-passable
        //until we reach the destination
        private static class KnightMove {
            int x, y, moveCount;

            KnightMove(int x, int y, int moveCount) {
                this.x = x;
                this.y = y;
                this.moveCount = moveCount;
            }

            KnightMove getNextAvailableMove(boolean horizontalMoveFirst, boolean goRight, boolean goTop, char[][] board) {
                int nextX, nextY;
                int dx, dy;
                if (horizontalMoveFirst) {
                    dx = goRight ? 2 : -2;
                    dy = goTop ? 1 : -1;
                } else {
                    dx = goRight ? 1 : -1;
                    dy = goTop ? 2 : -2;
                }
                nextX = x + dx;
                nextY = y + dy;
                if (nextX < 0 || nextX > 7 || nextY < 0 || nextY > 7 || board[nextX][nextY] == '*') {
                    return null;
                }
                return new KnightMove(nextX, nextY, moveCount + 1);
            }
        }

        public static int knight(String start, String finish) {
            int xStart = start.charAt(0) - 'a';
            int xEnd = finish.charAt(0) - 'a';
            int yStart = start.charAt(1) - '1';
            int yEnd = finish.charAt(1) - '1';

            boolean areValidMovesProvided = IntStream.of(xStart, yStart, xEnd, yEnd).allMatch(i -> (i >= 0 && i < 8));
            if (!areValidMovesProvided) {
                throw new IllegalArgumentException();
            }
            if (xStart == xEnd && yStart == yEnd) { //case we are already in position
                return 0;
            }

            Queue<KnightMove> moveQueue = new ArrayDeque<>();
            moveQueue.add(new KnightMove(xStart, yStart, 0));
            char[][] board = new char[8][8];
            board[xStart][yStart] = '*';

            while (!moveQueue.isEmpty()) {
                KnightMove currentMove = moveQueue.poll();

                List<KnightMove> nextMoves = Stream.of(currentMove.getNextAvailableMove(true, true, true, board),
                        currentMove.getNextAvailableMove(true, true, false, board),
                        currentMove.getNextAvailableMove(true, false, true, board),
                        currentMove.getNextAvailableMove(true, false, false, board),
                        currentMove.getNextAvailableMove(false, true, true, board),
                        currentMove.getNextAvailableMove(false, true, false, board),
                        currentMove.getNextAvailableMove(false, false, true, board),
                        currentMove.getNextAvailableMove(false, false, false, board))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

                for (KnightMove move : nextMoves) {
                    if (move.x == xEnd && move.y == yEnd) {
                        return move.moveCount;
                    } else {
                        board[move.x][move.y] = '*';
                        moveQueue.add(move);
                    }
                }
            }
            return 0;
        }
    }

}
