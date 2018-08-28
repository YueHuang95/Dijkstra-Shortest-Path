package edu.sdsu.cs.datastructures;
import edu.sdsu.cs.datastructures.GraphVertex;
public class WeightedEdge<E> implements IEdge<E> {
    String source;
    String destination;
    int weight;
    public WeightedEdge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public String getSource() {
        return this.source;
    }
    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public String toString() {
        return String.format("\n%s ->%s : %d",source, destination,weight);//sb.toString();
    }
}