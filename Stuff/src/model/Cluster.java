package model;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    
    public Point center;    
    public List<Point> points = new ArrayList<>();
    
    public Cluster() {
    }
    
    public void setCenter() {
        this.center = new Point(
                points
                    .stream()
                    .mapToDouble(p -> p.x)
                    .average()
                    .getAsDouble(),
        
                points
                    .stream()
                    .mapToDouble(p -> p.y)
                    .average()
                    .getAsDouble()
        );
    }
    
    public void merge(Cluster c) {
        this.points.addAll(c.points);
        setCenter();
    }
    
    public String toString() {
        return "center:(" + center.x + ", " + center.y + "), numPoints:" + points.size();
    }
}
