package edu.sdsu.cs;
import edu.sdsu.cs.datastructures.*;
import java.io.*;
import java.util.*;

public class GraphApp {
    public static void main( String[] args ) {
        if (args.length != 2 && args.length != 0) {
            System.out.printf("Error: Incorrct number of input arguments (0 or 2 expected), %d provided", args.length);
            System.exit(1);
        }
        if (args.length == 0) {
            WDGraph<String,Integer> graph = new WDGraph<>();
            try{
                BufferedReader cityReader = new BufferedReader(new FileReader("cities.csv"));
                BufferedReader edgeReader = new BufferedReader(new FileReader("edges.csv"));
                String line1;
                String line2;
                while((line1 = cityReader.readLine())!=null) {
                    String item[] = line1.split(",");
                    String city = item[0];
                    IVertex<String> vertex = new GraphVertex<>(city);
                    graph.addVertex(vertex);
                }
                while((line2 = edgeReader.readLine()) != null) {
                    String item[] = line2.split(",");
                    IVertex<String> source = new GraphVertex<>(item[0]);
                    IVertex<String> des = new GraphVertex<>(item[1]);
                    graph.connectVertices(source, des, Integer.parseInt(item[2]));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("City numbers :" + graph.numVertices());
            System.out.println("Edge numbers :" + graph.numEdges());
            System.out.println(graph.edges());
            List<IVertex<String>> list = (List<IVertex<String>>) graph.vertices();
            System.out.println("minimum cost from start to end " + graph.shortestPath(list.get(0),list.get(list.size() - 1)));
            System.out.printf("which is " + graph.minimumDistance(list.get(0),list.get(list.size() - 1)));
        }
        else {
            WDGraph<String,Integer> graph = new WDGraph<>();
            try{
                BufferedReader cityReader = new BufferedReader(new FileReader(args[0]));
                BufferedReader edgeReader = new BufferedReader(new FileReader(args[1]));
                String line1;
                String line2;
                while((line1 = cityReader.readLine())!=null) {
                    String item[] = line1.split(",");
                    String city = item[0];
                    IVertex<String> vertex = new GraphVertex<>(city);
                    graph.addVertex(vertex);
                }
                while((line2 = edgeReader.readLine()) != null) {
                    String item[] = line2.split(",");
                    IVertex<String> source = new GraphVertex<>(item[0]);
                    IVertex<String> des = new GraphVertex<>(item[1]);
                    graph.connectVertices(source, des, Integer.parseInt(item[2]));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("City numbers :" + graph.numVertices());
            System.out.println("Edge numbers :" + graph.numEdges());
            System.out.println(graph.edges());
            List<IVertex<String>> list = (List<IVertex<String>>) graph.vertices();
            System.out.println("minimum cost from start to end " + graph.shortestPath(list.get(0),list.get(list.size() - 1)));
            System.out.printf("which is " + graph.minimumDistance(list.get(0),list.get(list.size() - 1)));

        }


    }
}
