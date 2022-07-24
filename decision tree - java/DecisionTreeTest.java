package sol;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Test;
import src.DecisionTreeCSVParser;
import src.Row;
import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTest {
    private List<Row> rows;
    private List<String> attributes;
    private Dataset testData;
    private TreeGenerator treeGenerator;


    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public DecisionTreeTest() {
        this.rows = new ArrayList<>(DecisionTreeCSVParser.parse("data/training.csv"));
        this.attributes = new ArrayList<>(this.rows.get(0).getAttributes());
        this.testData = new Dataset(this.attributes, this.rows);
        this.treeGenerator = new TreeGenerator();
        List<Row> dataObjects = DecisionTreeCSVParser.parse("data/training.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        //this.testData = new Dataset(attributeList, dataObjects);
        Leaf veggie1 = new Leaf("Vegetable");
        Leaf fruit1 = new Leaf("Fruit");
        Edge medium1 = new Edge("Medium",veggie1);
        Edge high1 = new Edge("High", fruit1);
        List<Edge> list1 = new ArrayList<>();
        list1.add(medium1);
        list1.add(high1);
        Node calories1 = new Node("Calories", list1, "Fruit");
        Edge low2 = new Edge("low", veggie1);
        Edge high2 = new Edge("high", fruit1);
        List<Edge> list2 = new ArrayList<>();
        list2.add(low2);
        list2.add(high2);
        Node highProtein = new Node("High Protein", list2, "Vegetable");
        Edge orange = new Edge("Orange", calories1);
        Edge green = new Edge("green", highProtein);
        List<Edge> finalList = new ArrayList<>();
        finalList.add(orange);
        finalList.add(green);
        Node color = new Node("Color", finalList, "Vegetable");

    }
    @Test
    public void testDebug(){
        List<String> attributes = new ArrayList<>();
        attributes.add("color");
        attributes.add("highProtein");
        attributes.add("calories");
        attributes.add("foodType");
        TreeGenerator tree = new TreeGenerator();
        Dataset testingData = new Dataset(attributes, DecisionTreeCSVParser.parse("data/fruits/training"));
        tree.generateTree(testingData, "color"); }
//        DecisionTreeCSVParser.parse("data/studentTest/testing");

        @Test
    public void testExample() {
//        Leaf veggie1 = new Leaf("Vegetable");
//        Leaf fruit1 = new Leaf("Fruit");
//        Edge medium1 = new Edge("Medium",veggie1);
//        Edge high1 = new Edge("High", fruit1);
//        List<Edge> list1 = new ArrayList<>();
//        list1.add(medium1);
//        list1.add(high1);
//        Node calories1 = new Node("Calories", list1, "Fruit");
//        Edge low2 = new Edge("low", veggie1);
//        Edge high2 = new Edge("high", fruit1);
//        List<Edge> list2 = new ArrayList<>();
//        list2.add(low2);
//        list2.add(high2);
//        Node highProtein = new Node("High Protein", list2, "Vegetable");
//        Edge orange = new Edge("Orange", calories1);
//        Edge green = new Edge("green", highProtein);
//        List<Edge> finalList = new ArrayList<>();
//        finalList.add(orange);
//        finalList.add(green);
//        Node color = new Node("Color", finalList, "Vegetable");
        //Node calories1 = new Node("Calories", )
        assertEquals(6, 1 + 2 + 3);
    }

    @Test
    public void testPartition() {
        Assert.assertEquals(3, this.testData.partition("color").size());
        Assert.assertEquals(2, this.testData.partition("highProtein").size());
        Assert.assertEquals(3, this.testData.partition("calories").size());
    }

    @Test
    public void testAllSameValue(){
    Assert.assertFalse(this.testData.allSameValue("color"));
    }

    @Test
    public void testGetDefault(){
    Assert.assertEquals("green", this.testData.getDefault("color"));
    Assert.assertEquals("vegetable", this.testData.getDefault("foodType"));
    }

    @Test (expected = RuntimeException.class)
    public void testGetDefaultException(){
        this.testData.getDefault("apple");
    }


    @Test
    public void testGetDecision(){
    this.treeGenerator.generateTree(this.testData, "foodType");
    Assert.assertEquals("fruit", this.treeGenerator.getDecision(this.rows.get(1)));
    }

    /**
     * Tests the generateTree helper method in the TreeGenerator class
     */
    @Test
    public void testGenerateTree(){

    }

    /**
     * Prints out the any random attribute
     */
    @Test
    public void testGenerateRandomAttribute(){
        System.out.println(this.testData.getRandomAttribute());
    }

    @Test (expected = RuntimeException.class)
    public void testGetRandomAtt(){
        List<String> newString = new ArrayList<>();
        List<Row> newRow = new ArrayList<>();
        Dataset data = new Dataset(newString, newRow);
        data.getRandomAttribute();
    }
    /**
     * tests the method that gets the list of rows
     */
    @Test
    public void testGetRowList(){
        List<Row> rowList = new ArrayList<>(this.rows);
        Assert.assertEquals(rowList, this.testData.getDataObjects());
    }

    /**
     * Tests the size method in the Dataset class by checking whether the size method returns 6(the expected size of rows)
     */
    @Test
    public void testSize(){
        Assert.assertEquals(6, this.testData.size());
    }
}

//test partition
//runtime exceptions
//getters
//methods in dataset class
//all same value
//getDefault
