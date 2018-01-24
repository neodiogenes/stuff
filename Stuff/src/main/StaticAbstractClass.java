package main;

public abstract class StaticAbstractClass {
    
    public abstract boolean abstractMethod(int input);
    
    static public boolean staticNonAbstractMethod(int input){
        return (input % 2 == 0);
    }
}
