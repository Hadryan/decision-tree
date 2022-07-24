package sol;

import src.ITreeGenerator;
import src.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {

    private ITreeNode rootNode;

    /**
     * This helper method returns a node or a leaf. It is used to
     * generate the tree
     * @param trainingData
     * @param targetAttribute
     * @return
     */
    private ITreeNode generateTreeHelper(Dataset trainingData,
                                         String targetAttribute,
                                         List<String> attributes) {
        if (trainingData.allSameValue(targetAttribute) ||
                trainingData.getAttributeList().isEmpty()) {
            return new Leaf(trainingData.getDefault(targetAttribute));
        } else {
            List<Edge> edges = new ArrayList<>();
            Random random = new Random();
            int upperBound = trainingData.getAttributeList().size();
            int randomNum = random.nextInt(upperBound);
            String randomAttribute =
                    trainingData.getAttributeList().get(randomNum);
            List<Dataset> subsets =
                    trainingData.partition(randomAttribute);
            for(Dataset dataset : subsets){
                Edge babyEdge = new Edge(dataset.findEdgeAtt(randomAttribute),
                        this.generateTreeHelper(dataset,
                                targetAttribute,
                                attributes));
                edges.add(babyEdge);
            }
            return new Node(randomAttribute, edges,
                    trainingData.getDefault(targetAttribute));
        }
    }

    /**
     * This is the generate tree method that generates the tree using the
     * generateTreeHelper method
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        this.rootNode = generateTreeHelper(trainingData, targetAttribute,
                trainingData.getAttributeList());
    }

    /**
     * Gets teh decision of a row when called on it
     * @param datum the datum to lookup a decision for
     * @return
     */
    @Override
    public String getDecision(Row datum) {
        return this.rootNode.getDecision(datum);
    }
}