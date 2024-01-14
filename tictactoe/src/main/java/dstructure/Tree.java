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

    @Override
    public String toString() {
        return this.root.content.toString();
    }
    
    public void traverse(Operation operation){
        int level = 0;
        Queue<Tree<E>> queue = new LinkedList<>();
        queue.offer(this);
        
        while (!queue.isEmpty()){
            Tree<E> subtree = queue.poll();
            operation.op(subtree);
            //System.out.println("---");
            //if (!subtree.isLeaf()){
                for (Tree<E> sTree: subtree.getChildren()){
                    queue.offer(sTree);
                    //System.out.println(sTree.getContent());
                }
            //}
            
            //System.out.println("---");
        }
    }
    
    public static void main(String[] args) {
        Tree tree = new Tree(1);
        tree.addChildren(2);
        tree.addChildren(3);
        tree.addChildren(4);
        tree.addChildren(5);
        tree.addChildren(6);
        Operation createTree = new Operation() {
            @Override
            public void op(Object o) {
                System.out.println(o);
                Tree tree = (Tree) o;
                for(int i = 0; i < 5; i++) {
                    tree.addChildren(i);
                }
            }
        };
        tree.traverse(createTree);
    }
}
