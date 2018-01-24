package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Cluster;
import model.Point;

public class ClusterTest {

    public static final int GRID_SIZE = 2000;
    public static final int RADIUS = 100;
    public static final int NUM_POINTS = 10000;
    
    public static void main(String[] args) {
        List<Cluster> clusterList = new ArrayList<>();
        
        Random r = new Random(System.currentTimeMillis());
        for (int i=0; i< NUM_POINTS; i++){
            
            Point p = new Point(r.nextInt(GRID_SIZE), r.nextInt(GRID_SIZE));
            Cluster c = new Cluster();
            c.points.add(p);
            c.setCenter();
            
            boolean foundCluster = false;
            
            for (Cluster cluster : clusterList) {
                if (calcDistance(cluster.center, c.center) < RADIUS) {
                    cluster.merge(c);
                    foundCluster = true;
                    break;
                }
            }
            
            if (!foundCluster) clusterList.add(c);
        }
        
        System.out.println(clusterList.size());
        clusterList.forEach(cluster -> {
            if (cluster.points.size() > 15) {
                cluster.points.forEach(System.out::print);
                System.out.println();
            }
        });

    }

    public static double calcDistance( Point p1, Point p2) {
        return Math.sqrt(
                    (p1.x - p2.x) * (p1.x - p2.x)  + (p1.y - p2.y) * (p1.y - p2.y)
                );                
    }
    
    public static List<Cluster> reduce(List<Cluster> clusterList){
        List<Cluster> returnList = new ArrayList<>();
        
        for (int i=0; i<clusterList.size(); i++) {
            Cluster c0 = clusterList.get(i);
            
            boolean found = false;
            for (int j=i+1; j<clusterList.size(); j++){
                Cluster c1 = clusterList.get(j);                
                if (calcDistance(c0.center, c1.center) < RADIUS){
                    found = true;
                    c0.merge(c1);
                    returnList.add(c0);
                }
            }
            
            if (!found) {
                returnList.add(c0);
            }
            
        }
        
        return returnList;
    }
}
