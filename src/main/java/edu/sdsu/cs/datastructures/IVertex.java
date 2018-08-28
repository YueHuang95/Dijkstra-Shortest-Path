package edu.sdsu.cs.datastructures;

public interface IVertex <V> {
    public V getName();
    public V getPrev();
    public void setPrev(V value);
}
