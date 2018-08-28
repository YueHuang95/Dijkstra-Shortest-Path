package edu.sdsu.cs.datastructures;
//Each GraphVertex is actually an object than contains String.
public class GraphVertex<V> implements IVertex<V> {
    V vertex;
    V prev;
    //constructor is used to instantiate each specific city name
    public GraphVertex(V city) {
        this.vertex = city;
        this.prev = null;
    }
    public V getPrev() {
        return this.prev;
    }
    public void setPrev(V value) {
        this.prev = value;
    }
    public V getName() {
        return vertex;
    }
    public String toString() {
        return String.format("%s\n",vertex);
    }


}
