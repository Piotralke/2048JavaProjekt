package com.example.a2048javaprojekt;

import java.util.ArrayList;

/**
 * Klasa planszy
 */
public class Grid {

    public final Tile[][] field;
    public final Tile[][] undoField;
    private final Tile[][] bufferField;

    /**
     * Konstruktor klasy
     * @param sizeX rozmiar x
     * @param sizeY rozmiar y
     */
    public Grid(int sizeX, int sizeY) {
        field = new Tile[sizeX][sizeY];
        undoField = new Tile[sizeX][sizeY];
        bufferField = new Tile[sizeX][sizeY];
        clearGrid();
        clearUndoGrid();
    }

    /**
     * @return zwraca losowo wybrane pole na planszy
     */
    public Cell randomAvailableCell() {
        ArrayList<Cell> availableCells = getAvailableCells();
        if (availableCells.size() >= 1) {
            return availableCells.get((int) Math.floor(Math.random() * availableCells.size()));
        }
        return null;
    }

    /**
     * @return zwraca dostępne pola na planszy
     */
    private ArrayList<Cell> getAvailableCells() {
        ArrayList<Cell> availableCells = new ArrayList<>();
        for (int xx = 0; xx < field.length; xx++) {
            for (int yy = 0; yy < field[0].length; yy++) {
                if (field[xx][yy] == null) {
                    availableCells.add(new Cell(xx, yy));
                }
            }
        }
        return availableCells;
    }

    /**
     * @return sprawdza, czy są jeszcze wolne pola na planszy
     */
    public boolean isCellsAvailable() {
        return (getAvailableCells().size() >= 1);
    }

    /**
     * @param cell dane pole
     * @return sprawdza, czy dane pole jest wolne
     */
    public boolean isCellAvailable(Cell cell) {
        return !isCellOccupied(cell);
    }
    /**
     * @param cell dane pole
     * @return sprawdza, czy dane pole jest zajęte
     */
    public boolean isCellOccupied(Cell cell) {
        return (getCellContent(cell) != null);
    }

    /**
     * @param cell dane pole
     * @return zwraca wartość danego pola
     */
    public Tile getCellContent(Cell cell) {
        if (cell != null && isCellWithinBounds(cell)) {
            return field[cell.getX()][cell.getY()];
        } else {
            return null;
        }
    }
    /**
     * @param x pozycja x danego pola
     * @param y pozycja y danego pola
     * @return zwraca wartość danego pola
     */
    public Tile getCellContent(int x, int y) {
        if (isCellWithinBounds(x, y)) {
            return field[x][y];
        } else {
            return null;
        }
    }

    /**
     * @param cell dane pole
     * @return sprawdza, czy dane pole znajduje się w granicach
     */
    public boolean isCellWithinBounds(Cell cell) {
        return 0 <= cell.getX() && cell.getX() < field.length
                && 0 <= cell.getY() && cell.getY() < field[0].length;
    }

    /**
     * @param x pozycja x danego pola
     * @param y pozycja y danego pola
     * @return sprawdza, czy dane pole znajduje się w granicach
     */
    private boolean isCellWithinBounds(int x, int y) {
        return 0 <= x && x < field.length
                && 0 <= y && y < field[0].length;
    }

    /**
     * Wstawia daną płytkę
     * @param tile dana płytka
     */
    public void insertTile(Tile tile) {
        field[tile.getX()][tile.getY()] = tile;
    }

    /**
     * Usuwa daną płytkę
     * @param tile dana płytka
     */
    public void removeTile(Tile tile) {
        field[tile.getX()][tile.getY()] = null;
    }

    /**
     *  Zapisuje układ płytek, aby móc cofnąć do poprzedniego ruchu
     */
    public void saveTiles() {
        for (int xx = 0; xx < bufferField.length; xx++) {
            for (int yy = 0; yy < bufferField[0].length; yy++) {
                if (bufferField[xx][yy] == null) {
                    undoField[xx][yy] = null;
                } else {
                    undoField[xx][yy] = new Tile(xx, yy, bufferField[xx][yy].getValue());
                }
            }
        }
    }
    /**
     *  Zapisuje układ płytek do buffera
     */
    public void prepareSaveTiles() {
        for (int xx = 0; xx < field.length; xx++) {
            for (int yy = 0; yy < field[0].length; yy++) {
                if (field[xx][yy] == null) {
                    bufferField[xx][yy] = null;
                } else {
                    bufferField[xx][yy] = new Tile(xx, yy, field[xx][yy].getValue());
                }
            }
        }
    }
    /**
     *  Cofnięcie ruchu
     */
    public void revertTiles() {
        for (int xx = 0; xx < undoField.length; xx++) {
            for (int yy = 0; yy < undoField[0].length; yy++) {
                if (undoField[xx][yy] == null) {
                    field[xx][yy] = null;
                } else {
                    field[xx][yy] = new Tile(xx, yy, undoField[xx][yy].getValue());
                }
            }
        }
    }

    /**
     * Wyczyszczenie planszy
     */
    public void clearGrid() {
        for (int xx = 0; xx < field.length; xx++) {
            for (int yy = 0; yy < field[0].length; yy++) {
                field[xx][yy] = null;
            }
        }
    }

    private void clearUndoGrid() {
        for (int xx = 0; xx < field.length; xx++) {
            for (int yy = 0; yy < field[0].length; yy++) {
                undoField[xx][yy] = null;
            }
        }
    }
}
