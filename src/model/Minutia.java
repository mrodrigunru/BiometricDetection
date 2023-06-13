package model;

public class Minutia {
    private int X;
    private int Y;
    private int tipo;  // 1->corte - 3->bifurcacion
    private double angulo;

    public Minutia(int X, int Y, int tipo) {
        this.X = X;
        this.Y = Y;
        this.tipo = tipo;
        angulo = 0.0;
    }
    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public int getTipo() {
        return this.tipo;
    }

    public double getAngulo() {
        return this.angulo;
    }


}
