package com.example.a2048javaprojekt;


/**
 * Klasa animacji kafelka
 */
class AnimationCell extends Cell {
    public final int[] extras;
    private final int animationType;
    private final long animationTime;
    private final long delayTime;
    private long timeElapsed;

    /**
     * Konstruktor klasy
     * @param x zmienna x
     * @param y zmienna y
     * @param animationType typ animacji
     * @param length długość animacji
     * @param delay opóźnienie
     * @param extras
     */
    public AnimationCell(int x, int y, int animationType, long length, long delay, int[] extras) {
        super(x, y);
        this.animationType = animationType;
        animationTime = length;
        delayTime = delay;
        this.extras = extras;
    }

    /**
     * @return getter zmiennej animationType
     */
    public int getAnimationType() {
        return animationType;
    }

    /**
     *
     * @param timeElapsed zwraca czas pomiedzy klatkami
     */
    public void tick(long timeElapsed) {
        this.timeElapsed = this.timeElapsed + timeElapsed;
    }

    /**
     *
     * @return zwraca, czy animacja się zakończyła
     */
    public boolean animationDone() {
        return animationTime + delayTime < timeElapsed;
    }

    /**
     *
     * @return zwraca procent wykonania animacji
     */
    public double getPercentageDone() {
        return Math.max(0, 1.0 * (timeElapsed - delayTime) / animationTime);
    }

    /**
     *
     * @return sprawdza, czy animacja nadal trwa
     */
    public boolean isActive() {
        return (timeElapsed >= delayTime);
    }

}

