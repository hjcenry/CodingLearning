package org.hjc.algorithm.searchPath;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/18 0:37
 **/
public class Vector2 {
    int x;
    int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 clonePoint() {
        return new Vector2(this.x, this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector2 vector2 = (Vector2) o;
        return x == vector2.x && y == vector2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2 createUp() {
        Vector2 clonePoint = this.clonePoint();
        clonePoint.y = clonePoint.y - 1;
        return clonePoint;
    }

    public Vector2 createDown() {
        Vector2 clonePoint = this.clonePoint();
        clonePoint.y = clonePoint.y + 1;
        return clonePoint;
    }

    public Vector2 createLeft() {
        Vector2 clonePoint = this.clonePoint();
        clonePoint.x = clonePoint.x - 1;
        return clonePoint;
    }

    public Vector2 createRight() {
        Vector2 clonePoint = this.clonePoint();
        clonePoint.x = clonePoint.x + 1;
        return clonePoint;
    }
}
