package site.backrer.decisiveBattle.Entity;

public class Vector3 {
    private double x;
    private double y;
    private double z;
    private String wordName;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(double x, double y, double z, String wordName) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.wordName = wordName;
    }

    public String getWordName() {
        return wordName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
