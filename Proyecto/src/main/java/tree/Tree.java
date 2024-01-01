package tree;

import java.util.ArrayList;
import java.util.Iterator;

public class Tree<E> {
    private TreeNode<E> root;
    
    private class TreeNode<E> {
        private E content;
        private ArrayList<Tree<E>> children;

        public TreeNode(E content) {
            this.content = content;
            this.children = new ArrayList<>();
        }
    }
    
    public Tree() {
        this.root = null;
    }
    
    public Tree(E content) {
        root = new TreeNode<>(content);
    }
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public E getRoot() {
        return root.content;
    }
    
    public void setRoot(E content) {
        this.root.content = content;
    }
    
    public boolean isLeaf() {
        return this.root.children.isEmpty();
    }
    
    public void addChildren(E content) {
        this.root.children.add(new Tree<>(content));
    }
    
    public Iterator<Tree<E>> getChildrenIterator() {
        return this.root.children.iterator();
    }
    
}
