package data.controlers;

import data.table.MobileCircularObstacle;
import utils.math.Vec2;
import utils.math.VectPolar;

import java.util.Objects;

public class Cup extends MobileCircularObstacle {
    private final Cup.Color color;

    public Cup(Vec2 position, int radius, Cup.Color color) {
        super(position, radius);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "MobileCircularObstacle=" + super.toString() +
                ", color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cup cup = (Cup) o;
        return color == cup.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color);
    }

    public enum Color {
        GREEN,
        RED;
    }
}
