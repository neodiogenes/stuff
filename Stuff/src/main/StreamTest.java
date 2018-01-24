package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    public static void main (String[] args) {
       StreamTest.run();
    }
    
    public static void run(){
        List<Integer> intList = new ArrayList<Integer>();
        
        int i = 0;
        while (i++ < 6000){
            intList.add(i);
        }
        
        intList.forEach(j -> {
            System.out.println(j);
        });
        
        Stream<String> stream = intList.stream().map( k -> k.toString());
        try (PrintWriter pw = new PrintWriter("output.txt", "UTF-8")) {
            stream.forEachOrdered(pw::println);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        Long sum = 
                intList.stream().mapToLong(Integer::intValue).sum();
        
        System.out.println(sum);
    }
    
    public static void run2() {
        String[] desc = {"alpha", "beta", "gamma"};
        
        StringBuffer buff = new StringBuffer("start: ");
        new ArrayList<String>(Arrays.asList(desc))
            .forEach(v -> buff.append(v + ": "));
        
        System.out.println(buff.toString().trim());
        
    }
}
