package dstructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree<E> {
    private TreeNode<E> root;
    
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
    
    public List<Tree<E>> getChildren(){
        return root.children;
    }
    
    public E getContent(){
        return root.content;
    }
    
    public void traverse(){
        Queue<Tree<E>> queue = new LinkedList<>();
        queue.offer(this);
        
        while (!queue.isEmpty()){
            Tree<E> subtree = queue.poll();
            System.out.println("---");
            if (!subtree.isLeaf()){
                for (Tree<E> sTree: subtree.getChildren()){
                    queue.offer(sTree);
                    System.out.println(sTree.getContent());
                }
            }
            
            System.out.println("---");
        }
    }
}
