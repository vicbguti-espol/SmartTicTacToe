/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tree;

/**
 *
 * @author arauj
 */
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
