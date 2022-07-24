package sol;

import src.Row;

/**
 * this is a class that implemenst ITreeNode
 * the class is meant to represnet Leafs
 */
public class Leaf implements ITreeNode{

    private String value;

    public Leaf(String value){
        this.value = value;
    }

    /**
     * Returns the value of the leaf (attribute value)
     * @param forDatum the datum to lookup a decision for
     * @return
     */
    @Override
    public String getDecision(Row forDatum) {
        return this.value;
    }
}
