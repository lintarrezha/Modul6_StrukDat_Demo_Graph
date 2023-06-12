import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjacencyList;

    Graph(){
        adjacencyList = new HashMap<>();
    }

    void addVertex(int vertex){
        adjacencyList.put(vertex, new ArrayList<>());
    }

    void addEdgeDirected(int source, int target){
        if(!adjacencyList.containsKey(source)){
            addVertex(source);
        }
        if(!adjacencyList.containsKey(target)){
            addVertex(target);
        }

        adjacencyList.get(source).add(target);
    }

    void addEdgeUndirected(int source, int target){
        if(!adjacencyList.containsKey(source)){
            addVertex(source);
        }
        if(!adjacencyList.containsKey(target)){
            addVertex(target);
        }
        adjacencyList.get(source).add(target);
        adjacencyList.get(target).add(source);

    }

    List<Integer> getNeighbors(int vertex){
        return adjacencyList.get(vertex);
    }

    void printGraph(){
        for(int vertex : adjacencyList.keySet()){
            System.out.print("Vertex "+vertex+" is connected to: ");
            List<Integer> neighbors = adjacencyList.get(vertex);
            for (int neighbor : neighbors){
                System.out.print(neighbor+" ");
            }
            System.out.println();
        }
    }

    List<Integer> DFS(int startVertex){
        List<Integer> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startVertex);

        while (!stack.isEmpty()){
            int currentVertex = stack.pop();
            if(!visited.contains(currentVertex)){
                visited.add(currentVertex);

                List<Integer> neighbors = adjacencyList.get(currentVertex);
                for (int neighbor : neighbors){
                    if (!visited.contains(neighbor)){
                        stack.push(neighbor);
                    }
                }
            }
        } return visited;
    }

    List<Integer> BFS (int startVertex){
        List<Integer> visited = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startVertex);

        while (!queue.isEmpty()){
            int currentVertex = queue.poll();

            if(!visited.contains(currentVertex)){
                visited.add(currentVertex);

                List<Integer> neighbors = adjacencyList.get(currentVertex);
                for (int neighbor : neighbors){
                    if(!visited.contains(neighbor)){
                        queue.offer(neighbor);
                    }
                }
            }
        } return visited;
    }
    public static void main(String[] args) {
        Graph graph = new Graph();

        //add vertex
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addVertex(9);
        graph.addVertex(10);
        graph.addVertex(11);

        //Directed edges
        graph.addEdgeDirected(1, 4);
        graph.addEdgeDirected(4, 6);
        graph.addEdgeDirected(5, 3);
        graph.addEdgeDirected(5, 7);
        graph.addEdgeDirected(8, 11);
        graph.addEdgeDirected(11,10);

        //Unidrected edges
        graph.addEdgeUndirected(1, 2);
        graph.addEdgeUndirected(2, 5);
        graph.addEdgeUndirected(5, 9);
        graph.addEdgeUndirected(6, 7);
        graph.addEdgeUndirected(9, 8);
        graph.addEdgeUndirected(7, 11);

        graph.printGraph();
        List<Integer> bfs = graph.BFS(10);
        System.out.println("BFS Traversal: " + bfs);

        List<Integer> dfs = graph.DFS(10);
        System.out.print("DFS Traversal: " + dfs);

    }
}