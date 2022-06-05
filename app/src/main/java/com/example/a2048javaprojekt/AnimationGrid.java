package com.example.a2048javaprojekt;

import java.util.ArrayList;

/**
 *  Klasa animacji planszy
 */
public class AnimationGrid {
    public final ArrayList<AnimationCell> globalAnimation = new ArrayList<>();
    private final ArrayList<AnimationCell>[][] field;
    private int activeAnimations = 0;
    private boolean oneMoreFrame = false;

    /**
     * Konstruktor klasy
     * @param x parametr x
     * @param y parametr y
     */
    public AnimationGrid(int x, int y) {
        field = new ArrayList[x][y];

        for (int xx = 0; xx < x; xx++) {
            for (int yy = 0; yy < y; yy++) {
                field[xx][yy] = new ArrayList<>();
            }
        }
    }

    /**
     * Rozpoczyna animacje
     * @param x zmienna x
     * @param y zmienna y
     * @param animationType typ animacji
     * @param length długość animacji
     * @param delay opóźnienie
     * @param extras
     */
    public void startAnimation(int x, int y, int animationType, long length, long delay, int[] extras) {
        AnimationCell animationToAdd = new AnimationCell(x, y, animationType, length, delay, extras);
        if (x == -1 && y == -1) {
            globalAnimation.add(animationToAdd);
        } else {
            field[x][y].add(animationToAdd);
        }
        activeAnimations = activeAnimations + 1;
    }

    /**
     * Wykonuje klatkę dla każdej animacji, jeśli jakaś się skończyła to ją anuluje
     * @param timeElapsed upłynięty czas
     */
    public void tickAll(long timeElapsed) {
        ArrayList<AnimationCell> cancelledAnimations = new ArrayList<>();
        for (AnimationCell animation : globalAnimation) {
            animation.tick(timeElapsed);
            if (animation.animationDone()) {
                cancelledAnimations.add(animation);
                activeAnimations = activeAnimations - 1;
            }
        }

        for (ArrayList<AnimationCell>[] array : field) {
            for (ArrayList<AnimationCell> list : array) {
                for (AnimationCell animation : list) {
                    animation.tick(timeElapsed);
                    if (animation.animationDone()) {
                        cancelledAnimations.add(animation);
                        activeAnimations = activeAnimations - 1;
                    }
                }
            }
        }

        for (AnimationCell animation : cancelledAnimations) {
            cancelAnimation(animation);
        }
    }

    /**
     * @return zwraca, czy animacja jest aktywna
     */
    public boolean isAnimationActive() {
        if (activeAnimations != 0) {
            oneMoreFrame = true;
            return true;
        } else if (oneMoreFrame) {
            oneMoreFrame = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param x zmienna x
     * @param y zmienna y
     * @return zwraca pole o danych wposlrzednych
     */
    public ArrayList<AnimationCell> getAnimationCell(int x, int y) {
        return field[x][y];
    }

    /**
     * Anulowanie wszystkich animacji
     */
    public void cancelAnimations() {
        for (ArrayList<AnimationCell>[] array : field) {
            for (ArrayList<AnimationCell> list : array) {
                list.clear();
            }
        }
        globalAnimation.clear();
        activeAnimations = 0;
    }

    /**
     * Anulowanie jednej animacji
     * @param animation Animacja do anulowania
     */
    private void cancelAnimation(AnimationCell animation) {
        if (animation.getX() == -1 && animation.getY() == -1) {
            globalAnimation.remove(animation);
        } else {
            field[animation.getX()][animation.getY()].remove(animation);
        }
    }

}

