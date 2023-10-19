package org.hjc.algorithm.searchPath.entry;

import org.hjc.algorithm.searchPath.Vector2;

import java.util.Objects;

/**
 * @author hejincheng
 * @version 1.0
 * @date 2023/10/19 10:22
 **/
public class BFSNode {

    private Vector2 vector2;
    private BFSNode parent;

    public BFSNode(Vector2 vector2) {
        this.vector2 = vector2;
    }

    public Vector2 getVector2() {
        return vector2;
    }

    public void setVector2(Vector2 vector2) {
        this.vector2 = vector2;
    }

    public BFSNode getParent() {
        return parent;
    }

    public void setParent(BFSNode parent) {
        this.parent = parent;
    }

    public BFSNode createUpNode() {
        Vector2 clonedPoint = this.vector2.createUp();
        BFSNode childNode = new BFSNode(clonedPoint);
        childNode.setParent(this);
        return childNode;
    }

    public BFSNode createDownNode() {
        Vector2 clonedPoint = this.vector2.createDown();
        BFSNode childNode = new BFSNode(clonedPoint);
        childNode.setParent(this);
        return childNode;
    }

    public BFSNode createLeftNode() {
        Vector2 clonedPoint = this.vector2.createLeft();
        BFSNode childNode = new BFSNode(clonedPoint);
        childNode.setParent(this);
        return childNode;
    }

    public BFSNode createRightNode() {
        Vector2 clonedPoint = this.vector2.createRight();
        BFSNode childNode = new BFSNode(clonedPoint);
        childNode.setParent(this);
        return childNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BFSNode bfsNode = (BFSNode) o;
        return Objects.equals(vector2, bfsNode.vector2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector2);
    }

    public void copyFrom(BFSNode bfsNode) {
        this.parent = bfsNode.parent;
    }
}
