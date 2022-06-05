package com.example.a2048javaprojekt;

/**
 * Klasa przechowująca dane kafelka
 */
public class Cell {
    private int x;
    private int y;

    /**
     *
     * @param x wpolrzedna x
     * @param y wpolrzedna y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return getter zmiennej x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @param x setter zmiennej x
     */
    void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return getter zmiennej y
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @param y setter zmiennej y
     */
    void setY(int y) {
        this.y = y;
    }
}

