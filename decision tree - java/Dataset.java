package sol;

import src.IDataset;
import src.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A class that implements the IDataset interface,
 * representing a training data set
 */
public class Dataset implements IDataset {
    private List<String> attributes;
    private List<Row> rowList;

    /**
     * Constructor for the Dataset class
     *
     * @param dataObjects
     * @param attribute
     */
    public Dataset(List<String> attribute, List<Row> dataObjects) {
        this.attributes = attribute;
        this.rowList = dataObjects;
    }

    /**
     * Returns the list of all attributes
     *
     * @return the attribute list
     */
    @Override
    public List<String> getAttributeList() {
        return this.attributes;
    }

    /**
     * Returns the list of rows
     *
     * @returna list of rows
     */
    @Override
    public List<Row> getDataObjects() {
        return this.rowList;
    }

    /**
     * Returns the number of rows
     *
     * @return number of rows
     */
    @Override
    public int size() {
        return this.rowList.size();
    }

    /**
     * Checks if the data objects have the same attribute value
     *
     * @param attribute the attribute you are testing for the same value
     * @return true if all attributes have the same value
     */
    public boolean allSameValue(String attribute) {
        String firstAttribute =
                this.rowList.get(0).getAttributeValue(attribute);
        for (Row row : this.rowList) {
            if (firstAttribute.equals(row.getAttributeValue(attribute))) {
                continue;
            } else {
                return false;
            }
        }
        return false;
    }


    /**
     * Checks whether the row list is empty
     *
     * @return true if the list is empty
     */
    private boolean isEmpty() {
        return (this.rowList == null);
    }

    /**
     * Returns a list of distinct values
     *
     * @param attribute the attribute we want the destinct value of
     * @return a list of distinct values
     */
    public List<String> getDistinctValue(String attribute) {
        List<String> distinctValues = new ArrayList<>();
        for (Row row : this.rowList) {
            String attributeValue = row.getAttributeValue(attribute);
            if (!distinctValues.contains(attributeValue)) {
                distinctValues.add(attributeValue);
            }
        }
        return distinctValues;
    }

    /**
     * Filters the list, to return the updated list without the attribute
     *
     * @ param the original list of string ans the string you want rmoved
     * @return a list of strings where the desired stting is rempves
     */
    private List<String> removeString(List<String> ogSt,
                                      String removed) {
        List<String> updatedList = new ArrayList<>(ogSt);
        updatedList.remove(removed);
        return updatedList;
    }

    /**
     * Method that splits the attributes in to smaller datasets
     * with the same attributes
     *
     * @param  choiceAttribute the attribute we want split on
     * @return a list of partitioned datasets
     */
    public List<Dataset> partition(String choiceAttribute) {
        List<Dataset> subsets = new ArrayList<>();
        List<String> distinctValues = this.getDistinctValue(choiceAttribute);
        List<String> attributes = new ArrayList<>(this.attributes);
        List<String> newAttribute = this.removeString(attributes,
                                                      choiceAttribute);
        for (String value : distinctValues) {
            List<Row> objectsCurrentValue = new ArrayList<>();
            for (Row row : this.rowList) {
                if (row.getAttributeValue(choiceAttribute).equals(value)) {
                    objectsCurrentValue.add(row);
                }
            }
            subsets.add(new Dataset(newAttribute, objectsCurrentValue));
        }
        return subsets;
    }

    /**
     * Returns an attribute value of a given attribute
     *
     * @param attribute the value you want the attribute of
     * @return the attribute value
     */
    private String getAttributeValue(String attribute) {
        return this.rowList.get(0).getAttributeValue(attribute);
    }


    /**
     * Gets the default value for every attribute(node)
     *
     * @param attribute the attribute you want the default of
     * @return the default value
     */
    public String getDefault(String attribute) {
        if (!isEmpty()) {
            int currentMax = 0;
            String currentVariable = null;
            for (Dataset data : this.partition(attribute)) {
                if (data.size() > currentMax) {
                    currentMax = data.size();
                    currentVariable = data.getAttributeValue(attribute);
            } else if (data.size() == currentMax) {
                int random = (int) (Math.random() * 2 + 1);
                if (random == 1) {
                    currentMax = data.size();
                    currentVariable = data.getAttributeValue(attribute);
                }
                }
            }
            return currentVariable;
        } else {
            throw new RuntimeException("No such attribute");
        }
    }

    /**
     * Gets a random attribute
     * @return the random attribute value found
     */
    public String getRandomAttribute(){
        Random random = new Random();
        if (this.attributes.size() == 0) {
            throw new RuntimeException("There are no more attributes! 😢");
        } else {
            int randomNumber = random.nextInt(this.attributes.size());
            return this.attributes.get(randomNumber);
        }
    }

    /**
     * Returns the value of the edge
     * @param attribute the attribute we want the value of
     * @return the string of the value from edge
     */
    public String findEdgeAtt(String attribute){
        return this.getDistinctValue(attribute).get(0);
    }
}
