package tree;

import dstructure.Tree;

public class MiniMaxTree<E> extends Tree<E> {
    private int utility;

    public MiniMaxTree() {
    }

    public MiniMaxTree(E content) {
        super(content);
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }
}
