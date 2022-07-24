package sol;
import java.util.List;
import src.Row;

/**
 * This is the node class, it represents the nodes that hold attributes
 */
public class Node implements ITreeNode{
    private String attribute;
    private List<Edge> edge;
    private String defaultValue;

    /**
     * This is the constructor of the node class
     * @param attribute
     * @param edge
     * @param defaultValue
     */
    public Node(String attribute, List<Edge> edge, String defaultValue){
        this.attribute = attribute;
        this.edge = edge;
        this.defaultValue = defaultValue;
    }

    /**
     * Returns the decision of a node of a given row
     * @param forDatum the datum to lookup a decision for
     * @return the decision of the node
     */
    @Override
    public String getDecision(Row forDatum) {
        for (Edge outgoingEdge : this.edge) {
            if (outgoingEdge.getEdge().equals
                    (forDatum.getAttributeValue(this.attribute))) {
                return outgoingEdge.getNode().getDecision(forDatum);
            }
        }
        return this.defaultValue;
    }
}
