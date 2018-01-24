package main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FindGcd {

    public static void main(String... args){
        int a = 2750;
        int b = 150;
        
        System.out.println(euclid(a, b));
        
        erasthones(a).forEach(n -> {
            System.out.print(n + ",");
        });
    }
    
    public static int euclid(int a, int b) {
        
        if (b == 0) return a;
        
        int r = a % b;        
        
        return euclid(b, r);
    }
    
    public static List<Integer> erasthones (int a) {
        List<Integer> primeList = new CopyOnWriteArrayList<>();
        
        for (int i=2; i<=a; i++){
            primeList.add(i);           
        }
        
        primeList.forEach(p -> {
            int r = p + p;
            
            while (r <= a) {  
                int j = primeList.indexOf(r);
                if (j > 0) primeList.remove(j);
                r += p;     
            }
        });
        
        return primeList;
    }
}
