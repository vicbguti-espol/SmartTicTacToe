package dstructure;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<E> {
    E content;
    List<Tree<E>> children;

    public TreeNode(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }
}
