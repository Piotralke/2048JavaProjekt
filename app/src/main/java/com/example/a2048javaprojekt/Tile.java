package com.example.a2048javaprojekt;

/**
 * Klasa kafelka
 */
public class Tile extends Cell {
    private final int value;
    private Tile[] mergedFrom = null;

    /**
     * Konstruktor klasy
     * @param x parametr x
     * @param y parametr y
     * @param value wartosc
     */
    public Tile(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    /**
     * Konstruktor klasy
     * @param cell pole
     * @param value wartosc
     */
    public Tile(Cell cell, int value) {
        super(cell.getX(), cell.getY());
        this.value = value;
    }

    /**
     * Aktualizuje pozycje
     * @param cell pole
     */
    public void updatePosition(Cell cell) {
        this.setX(cell.getX());
        this.setY(cell.getY());
    }

    /**
     *
     * @return getter wartosci
     */
    public int getValue() {
        return this.value;
    }

    /**
     *
     * @return getter mergedFrom
     */
    public Tile[] getMergedFrom() {
        return mergedFrom;
    }

    /**
     * setter mergedFrom
     * @param tile kafelek
     */
    public void setMergedFrom(Tile[] tile) {
        mergedFrom = tile;
    }
}

