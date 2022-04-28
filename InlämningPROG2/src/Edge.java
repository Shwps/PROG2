import java.util.Objects;

public class Edge<T> {

    private final T destination;
    private int weight;
    private final String name;

    public Edge(T destination, int weight, String name) {
        this.destination = destination;
        this.weight = weight;
        this.name = name;
    }

    public T getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int newWeight) {
        if (newWeight < 0) {
            throw new IllegalArgumentException();
        } else {
            this.weight = newWeight;
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Edge edge) {
            return Objects.equals(name, edge.name) &&
                    Objects.equals(destination, edge.destination) && edge.getWeight() == weight;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + name.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("till ").append(getDestination()).append(" med ").append(name).append(" tar ").append(weight);
        return sb.toString();
    }
}
