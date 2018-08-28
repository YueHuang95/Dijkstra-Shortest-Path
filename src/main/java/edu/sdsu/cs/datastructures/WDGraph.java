package edu.sdsu.cs.datastructures;
import java.util.*;

public class WDGraph<V, E> implements IGraph<V,E> {
    List<IVertex<V>> cities;
    List<List<IEdge<E>>> edges;
    List<IEdge<E>> path;  //used to store all edges about the path
    HashMap<V,Integer> distance = new HashMap<>();  //instantiate a distance hash map

    public WDGraph() {
        this.cities = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.path = new ArrayList<>();
    }
    @Override
    public Iterable<IVertex<V>> vertices() {
        return cities;
    }

    @Override
    public Iterable<IEdge<E>> edges() {
        List<IEdge<E>> list = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.get(i).size(); j++) {
                list.add(edges.get(i).get(j));
            }
        }
        return list;
    }

    @Override
    public int numEdges() {
        int count = 0;
        for (int i = 0; i < edges.size(); i++) {
            count += edges.get(i).size();
        }
        return count;
    }

    @Override
    public int numVertices() {
        return cities.size();
    }

    @Override
    public int minimumDistance(IVertex<V> start, IVertex<V> end) {
        return distance.get(end.getName());
    }

    @Override
    public Iterable<IEdge<E>> shortestPath(IVertex<V> start, IVertex<V> end) {
        HashSet<V> visited = new HashSet<>();
        //set all distance value to infinity
        for (IVertex<V> vertex : cities) {
            distance.put(vertex.getName(),Integer.MAX_VALUE);
        }
        distance.replace(start.getName(),0);  //setting starting node distance to 0
        PriorityQueue<Entry<V,Integer>> minHeap = new PriorityQueue<>();  //used to store all neighbors
        minHeap.offer(new Entry(start.getName(),0));  //enqueue the starting Node into heap
        while (!minHeap.isEmpty()) {
            Entry<V,Integer> curNode = minHeap.poll();  //each time we poll the nearest node out
            if (!visited.contains(curNode.getKey())) {  //if have not yet visited
                visited.add(curNode.getKey());  //we add current node to visited HashSet
                int index = indexOfCity(curNode.getKey());//get the corresponding edges of current Node
                for (IEdge<E> edge :  edges.get(index)) {  //for every edges of current node
                    if (curNode.getValue() + edge.getWeight() < distance.get((V)edge.getDestination())) {  //if new distance is smaller than current value in HashMap
                        distance.replace((V)edge.getDestination(), distance.get(curNode.getKey()) + edge.getWeight());
                        //this step is to find destination city and set it's previous city to current edge source
                        int idx = indexOfCity((V)edge.getDestination());
                        cities.get(idx).setPrev(curNode.getKey());
                    }
                    if (!visited.contains((V)edge.getDestination())) {  //if this is unvisited neighbor
                        Entry<V, Integer> newPair = new Entry<>((V)edge.getDestination(), distance.get((V)edge.getDestination()));
                        minHeap.offer(newPair);
                    }
                }
            }
        }
        printPath(end);
        return path;
    }

    @Override
    public void connectVertices(IVertex<V> start, IVertex<V> end, int weight) {
        IEdge<E> edge = new WeightedEdge<E>((String)start.getName(),(String)end.getName(), weight);
        addEdge(edge);
    }

    @Override
    public void addVertex(IVertex<V> toAdd) {
        cities.add(toAdd);
        edges.add(new ArrayList<IEdge<E>>());
    }

    @Override
    public void addEdge(IEdge<E> toAdd) {
        //find the index of start vertex in the cities list first,
        int index = 0;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(toAdd.getSource())) {
                index = i;
            }
        }
        edges.get(index).add(toAdd);
    }
    //get the corresponding index of edges of current Node
    public int indexOfCity(V cityName) {
        int index = 0;
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(cityName)) {
                index = i;
                break;
            }
        }
        return index;
    }
    public void printPath(IVertex<V> city) {
        if (city.getPrev() == null) return;
        int index = indexOfCity(city.getPrev());  //get previous city's index
        for (IEdge<E> edge : edges.get(index)) {  //find the edge that goes from previous city to current city
            if (edge.getDestination().equals(city.getName())) {
                int idx = indexOfCity((V) edge.getSource());  //get the index of previous city of previous city
                printPath(cities.get(idx));
                path.add(edge);
                break;
            }
        }
    }
}
class Entry<V,E> implements Comparable<Entry<V,E>> {
    V key;
    int value;

    public Entry(V key, int value) {
        this.key = key;
        this.value = value;
    }
    public V getKey() {
        return this.key;
    }
    public int getValue() {
        return this.value;
    }
    @Override
    public int compareTo(Entry<V,E> other) {
        if (this.value == other.value) {
            return 0;
        }
        return this.value < other.value? -1 : 1;
    }

}