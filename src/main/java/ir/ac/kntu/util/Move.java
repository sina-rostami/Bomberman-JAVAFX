package ir.ac.kntu.util;

public enum Move {
    RIGHT(6), LEFT(4), UP(8), DOWN(2);
    private final int dir;

    Move(int num) {
        dir = num;
    }

    public int getDir() {
        return dir;
    }
}
