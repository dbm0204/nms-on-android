package app.nms.utils;

public class DijkstraNode implements Comparable<DijkstraNode>
{
    private final int mId;
    private final double minDistance;
    private final int prevId;
    public DijkstraNode previous;
    public DijkstraNode(int id, double minDistance, int prevId) {
        this.mId = id;
        this.minDistance = minDistance;
        this.prevId = prevId;
    }
    public int getPrevNode()
    {
        return prevId;
    }
    public int getId(){
        return mId;
    }
    public int compareTo(DijkstraNode other)
    {
        return Double.compare(minDistance,other.minDistance);
    }
}
