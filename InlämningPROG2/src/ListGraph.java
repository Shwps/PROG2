import java.util.*;

public class ListGraph<T> implements Graph<T> {

    private final Map<T, Set<Edge<T>>> nodes = new HashMap<>();

    public void add(T node) {
        nodes.putIfAbsent(node, new HashSet<>());
    }

    public void connect(T nodeOne, T nodeTwo, String name, int weight) {
        if (!nodes.containsKey(nodeOne) || !nodes.containsKey(nodeTwo)) {
            throw new NoSuchElementException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        } else {
            Set<Edge<T>> nodeOneEdges = nodes.get(nodeOne);
            Set<Edge<T>> nodeTwoEdges = nodes.get(nodeTwo);
            if (Objects.nonNull(getEdgeBetween(nodeOne, nodeTwo))) {
                throw new IllegalStateException();
            } else {
                nodeOneEdges.add(new Edge<T>(nodeTwo, weight, name));
                nodeTwoEdges.add(new Edge<T>(nodeOne, weight, name));
            }
        }
    }

    @Override
    public void setConnectionWeight(T nodeOne, T nodeTwo, int weight) {
        if (!nodes.containsKey(nodeOne) || !nodes.containsKey(nodeTwo)) {
            throw new NoSuchElementException();
        }
        if (weight < 0) {
            throw new IllegalArgumentException();
        }
        getEdgeBetween(nodeTwo, nodeOne).setWeight(weight);
        getEdgeBetween(nodeOne, nodeTwo).setWeight(weight);
    }

    public void disconnect(T nodeOne, T nodeTwo) {
        if (!nodes.containsKey(nodeOne) || !nodes.containsKey(nodeTwo)) {
            throw new NoSuchElementException();
        } else {
            Edge<T> edgeOne = getEdgeBetween(nodeTwo, nodeOne);
            Edge<T> edgeTwo = getEdgeBetween(nodeOne, nodeTwo);
            if (!Objects.nonNull(edgeOne) || !Objects.nonNull(edgeTwo)) {
                throw new IllegalStateException();
            } else {
                nodes.get(nodeOne).remove(getEdgeBetween(nodeOne, nodeTwo));
                nodes.get(nodeTwo).remove(getEdgeBetween(nodeTwo, nodeOne));
            }
        }
    }

    public void remove(T node) {
        if (!nodes.containsKey(node)) {
            throw new NoSuchElementException();
        }
        Set<Edge<T>> nodeEdges = new HashSet<>(nodes.get(node));
        for (Edge<T> edge : nodeEdges) {
            disconnect(edge.getDestination(), node);
        }
        nodes.remove(node);
    }

    public Set<T> getNodes() {
        return nodes.keySet();
    }

    public Collection<Edge<T>> getEdgesFrom(T node) {
        if (!nodes.containsKey(node)) {
            throw new NoSuchElementException();
        }
        return Collections.unmodifiableSet(nodes.get(node));
    }

    public Edge<T> getEdgeBetween(T nodeOne, T nodeTwo) {
        if (!nodes.containsKey(nodeOne) || !nodes.containsKey(nodeTwo)) {
            throw new NoSuchElementException();
        }
        for (Edge<T> edge : nodes.get(nodeOne)) {
            if (edge.getDestination().equals(nodeTwo)) {
                return edge;
            }
        }
        return null;
    }

    public boolean pathExists(T nodeOne, T nodeTwo) {
        if (!nodes.containsKey(nodeOne) || !nodes.containsKey(nodeTwo)) {
            return false;
        }
        Set<T> visited = new HashSet<>();
        depthFirstPathExists(nodeOne, visited);
        return visited.contains(nodeTwo);
    }

    @Override
    public List<Edge<T>> getPath(T from, T to) {
        Map<T, T> connections = new HashMap<>();
        connections.put(from, null);

        LinkedList<T> queue = new LinkedList<>();
        queue.add(from);
        while (!queue.isEmpty()) {
            T node = queue.pollFirst();
            for (Edge<T> edge : nodes.get(node)) {
                T destination = edge.getDestination();
                if (!connections.containsKey(destination)) {
                    connections.put(destination, node);
                    queue.add(destination);
                }
            }
        }
        if (!connections.containsKey(to)) {
            return null;
        }
        return gatherPath(from, to, connections);
    }


    private List<Edge<T>> gatherPath(T from, T to, Map<T, T> connection) {
        LinkedList<Edge<T>> path = new LinkedList<>();
        T current = to;
        while (!current.equals(from)) {
            T next = connection.get(current);
            Edge<T> edge = getEdgeBetween(next, current);
            path.addFirst(edge);
            current = next;
        }
        return Collections.unmodifiableList(path);
    }

    private void depthFirstPathExists(T currentNode, Set<T> visited) {
        visited.add(currentNode);
        for (Edge<T> edge : nodes.get(currentNode)) {
            if (!visited.contains(edge.getDestination())) {
                depthFirstPathExists(edge.getDestination(), visited);
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T node : nodes.keySet()) {
            sb.append(node).append(": ").append(getEdgesFrom(node)).append("\n");
        }
        return sb.toString();
    }
}
