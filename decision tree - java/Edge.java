package sol;

public class Edge {
    /**
     * This is the edge class it represents the attribute
     * values(arrows) in the visual representation of a decision tree.
     */
    private ITreeNode tree;
    private String attribute;

    /**
     * This is the constructor of the edge class, it takes in the attribute
     * value it represents and the node it points to
     * @param attribute
     * @param tree
     */
    public Edge(String attribute, ITreeNode tree){
        this.attribute = attribute;
        this.tree = tree;
    }

    /**
     * Returns the attribute of the edge
     * @return the string representing the edge
     */
    public String getEdge(){
        return this.attribute;
    }

    /**
     *Returns the Tree
     * @return returns the tree in ITree node
     */
    public ITreeNode getTree(){
        return this.tree;
    }

    /**
     * Returns the node that the edge points to
     * @return returns the tree
     */
    public ITreeNode getNode(){
        return this.tree;
    }

}
