package com.hui.algorithm;

import java.util.*;


/**
 * 数据结构与算法分析 Java 语言描述 第二版
 * @author: shenhaizhilong
 * @date: 2018/8/9 11:57
 */
public class Graph {

    private static final int INFINITY = Integer.MAX_VALUE;
    private HashMap<String, Vertex> map;
    private final Vertex max = new Vertex("max");

    public Graph(int size)
    {
        this.map = new HashMap<>(size);
    }

    public Vertex getVertex(String name)
    {
        return map.getOrDefault(name, null);
    }


    /**
     * add a vertex in this graph, if the vertex with "name" already
     * existed, we do nothing, just return the existed vertex
     * @param name
     * @return
     */
    public Vertex addVertex(String name)
    {

        Vertex vertex =getVertex(name);

        // if not exist, create a new Vertex
        if(vertex == null)
        {
            vertex = new Vertex(name);
            map.put(name, vertex);
        }
        return vertex;
    }

    public void addEdge(String sourceName, String destName, int weight)
    {
        Vertex s = addVertex(sourceName);
        Vertex d = addVertex(destName);
        Edge edge = new Edge(d, weight);
        if(!s.adjSet.contains(edge))
        {
            s.adjSet.add(edge);
        }

    }

    public void clear()
    {
        map.clear();
    }


    /**
     * O(|E| + |V|)
     * @throws CycleFoundException
     */
    public void topSort() throws CycleFoundException
    {
        List<Vertex> q = new LinkedList<>();
        int counter = 0;
        for (Vertex v :
                map.values()) {
            if(v.inDegree == 0)
            {
                q.add(v);
            }
        }

        while (!q.isEmpty())
        {
            Vertex v = ((LinkedList<Vertex>) q).pollFirst();
            v.topNumber = ++counter; // assign next number

            //delete v from q and update it's adjacent vertex
            for (Edge edge :
                    v.adjSet) {
                Vertex w = edge.vertex;
                w.inDegree--;
                if(w.inDegree == 0)
                {
                    q.add(w);
                }
            }
        }

        if(counter != map.values().size())
            throw new CycleFoundException();
    }


    /**
     * unWeighted graph , O(|E| + |V|)
     * @param vertexName the source vertex name
     */
    public void unWeighted(String vertexName)
    {
        LinkedList<Vertex> queue = new LinkedList<>();
        // initialization all the distance to impossible value
        // some vertexes are not reachable
        for (Vertex v :
                map.values()) {
            v.dist = Integer.MIN_VALUE;
        }
        Vertex s = getVertex(vertexName);
        if(s == null){
            System.out.println("can'd find vertex: " + vertexName);
            return;
        }

        // source point
        s.dist = 0;
        queue.add(s);
        while (!queue.isEmpty())
        {
            // visit once
            Vertex v = queue.pollFirst();
            // process all the adjacent vertex
            for (Edge edge :
                    v.adjSet) {

                Vertex w = edge.vertex;
                // if w already visited, we don't need to visit it.
                if(w.dist == Integer.MIN_VALUE)
                {
                    // update it's adjacent vertex distance
                    w.dist = v.dist + 1;
                    w.path = v;
                    queue.add(w);
                }
            }
        }
    }

    /**
     *  Print shortest path to v
     *  assume that path exists
     * @param name
     */
    public void printPath(String name)
    {
        Vertex vertex = getVertex(name);
        if(vertex == null)
        {
            System.out.println("can't find destination vertex: " + name);
        }else if(vertex.dist == INFINITY)
        {
            System.out.println();
            System.out.println("Destination vertex " + name + " is unreachable!");
            return;
        }
        printPath(vertex);
    }


    /**
     * For weighted and without negative edge, Print shortest path to v after dijkstra has run.
     * assume that path exists
     * @param v
     */
    private void printPath(Vertex v)
    {
        if(v.path != null)
        {
            printPath(v.path);
            System.out.print(" to ");
        }else {
            System.out.println();
        }
        System.out.print(v);
    }

    /**
     * dijkstra algorithm must be  run before run  function printPath
     * @param vertexName
     */

    public void  dijkstra(String vertexName)
    {
        Vertex vertex = getVertex(vertexName);
        dijkstra(vertex);
    }

    private void dijkstra(Vertex s)
    {
        if(s == null)return;
        s.dist = 0;
        while (true)
        {
            Vertex min = getMinVertex();
            if(min == max)
            {
                break;
            }

            min.visited = true;
            for (Edge edge :
                    min.adjSet) {
                if(!edge.vertex.visited)
                {
                    int cost = edge.weight;
                    if(min.dist + cost < edge.vertex.dist)
                    {
                        //update edge.vertex
                        edge.vertex.dist = min.dist + cost;
                        edge.vertex.path = min;
                    }
                }
            }

        }

    }

    private Vertex getMinVertex()
    {
        Vertex min = max;
        for (Vertex w :
                map.values()) {
            if (w.visited == false && w.dist < min.dist) {
                min = w;
            }
        }
        return min;
    }


    public static class Vertex
    {
        String name;                  // human readable name
        HashSet<Edge> adjSet;  // adjacent vertex HashSet
        boolean visited;    // whether this vertex is visited ?
        int dist;        // distance/cost from the source to this vertex
        int topNumber;
        int inDegree;
        Vertex path;     // last vertex

        public Vertex(String name, int dist, boolean visited)
        {
            this.name = name;
            this.adjSet = new HashSet<>();
            this.visited = visited;
            this.dist = dist;
        }

        public Vertex(String name)
        {
            this.name = name;
            this.adjSet = new HashSet<>();
            this.visited = false;
            this.dist = INFINITY;

        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this)return true;
            if(!(obj instanceof Vertex))return false;
            Vertex w = (Vertex) obj;
            return this.name.equals(w.name) &
                    this.dist == w.dist &
                    this.path == w.path &
                    this.inDegree == w.inDegree &
                    this.topNumber == w.topNumber &
                    this.visited == w.visited &
                    this.adjSet == w.adjSet;
        }

        @Override
        public int hashCode() {

            return this.name.hashCode()*13 +
                    this.dist*31 +
                    this.path.hashCode()*37 +
                    this.adjSet.hashCode()*73 +
                    this.inDegree*41 +
                    this.topNumber*17 +
                    Boolean.hashCode(this.visited);
        }
    }

    private static class Edge
    {
        final Vertex vertex;
        final int weight;
        int hash;

        public Edge(Vertex vertex, int weight)
        {
            this.vertex = vertex;
            this.weight = weight;
        }


        // 只比较权重值与顶点的名字是否都想相同，如果相同则认为相等
        @Override
        public boolean equals(Object obj) {
           if(obj == this)return true;
           if(!(obj instanceof Edge))return false;
            Edge w = (Edge)obj;
           return this.weight == w.weight && this.vertex.name.equals(w.vertex.name);
        }


        @Override
        public int hashCode() {
            if(hash == 0)
                hash = this.weight*37 + this.vertex.name.hashCode()*31;
            return hash;
        }
    }

    private static class CycleFoundException extends Exception
    {
        @Override
        public String getMessage() {
            return "cycle found exception";
        }
    }

    public static void main(String[] args) {


        // unweighted graph
        Graph graph = new Graph(7);
        graph.addEdge("v1", "v2",1);
        graph.addEdge("v1", "v4",1);
        graph.addEdge("v2", "v4",1);
        graph.addEdge("v2", "v5",1);
        graph.addEdge("v3", "v1",1);
        graph.addEdge("v3", "v6",1);
        graph.addEdge("v4", "v3",1);
        graph.addEdge("v4", "v5",1);
        graph.addEdge("v4", "v6",1);
        graph.addEdge("v4", "v7",1);
        graph.addEdge("v5", "v7",1);
        graph.addEdge("v7", "v6",1);


        //calculate shortest path of unWeighted graph , the started  Vertex is v3
        graph.unWeighted("v3");
        //print the shortest path from v3 to vn
        System.out.println("The shortest path from v3:");
        for(int i=1;i<=7;i++){
            graph.printPath("v"+i);
        }

        Graph weightedGraph = new Graph(7);
        weightedGraph.addEdge("v1", "v2",2);
        weightedGraph.addEdge("v1", "v4",1);
        weightedGraph.addEdge("v2", "v4",3);
        weightedGraph.addEdge("v2", "v5",10);
        weightedGraph.addEdge("v3", "v1",4);
        weightedGraph.addEdge("v3", "v6",5);
        weightedGraph.addEdge("v4", "v3",2);
        weightedGraph.addEdge("v4", "v5",2);
        weightedGraph.addEdge("v4", "v6",8);
        weightedGraph.addEdge("v4", "v7",4);
        weightedGraph.addEdge("v5", "v7",6);
        weightedGraph.addEdge("v7", "v6",1);
        weightedGraph.addVertex("v8");

        //calculate shortest path of Weighted graph , the started  Vertex is v1
        weightedGraph.dijkstra("v1");
        //print the shortest path from v1 to vn
        System.out.println("The shortest path from v1:");
        for(int i=1;i<=8;i++){
            weightedGraph.printPath("v"+i);
        }


    }

}
