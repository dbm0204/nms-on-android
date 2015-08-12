package app.nms.utils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import app.nms.graphics.DrawableNetworkComponent;

/**
 * Created by Ben on 7/22/2015.
 */
public class ComputePath {
    public static int[] computePaths(int source, int N, Double[][] adjacencyMatrix) {

        PriorityQueue<DijkstraNode> vertexQueue = new PriorityQueue<>();
        HashSet<Integer> doneSet = new HashSet<>();

        Double minDistance[] = new Double[N];
        Arrays.fill(minDistance, Double.MAX_VALUE);

        int finalPrevNode[] = new int[N];
        Arrays.fill(finalPrevNode, -1);

        doneSet.add(source);
        vertexQueue.add(new DijkstraNode(source, 0, source));
        minDistance[source] = 0.0;
        finalPrevNode[source] = source;

        while (!vertexQueue.isEmpty()) {
            DijkstraNode u = vertexQueue.remove();
            int uId = u.getId();
            if (!doneSet.contains(uId))
                finalPrevNode[uId] = u.getPrevNode();
            doneSet.add(uId);

            // Now evaluate neighbours
            for(int i=0;i<N; ++i)
            {
                if (doneSet.contains(i))
                    continue;

                if(adjacencyMatrix[uId][i] == Double.MAX_VALUE)
                    continue;

                double newDistance = minDistance[uId] + adjacencyMatrix[uId][i];
                if (newDistance < minDistance[i])
                    minDistance[i] = newDistance;

                vertexQueue.add(new DijkstraNode(i, minDistance[i], uId));
            }
        }

        return finalPrevNode;
    }
}

