package main;

public class Stuff {

    static final int IMAGE_COUNT = 75;
    int imageIndex = 0;
    
    public static void main(String[] args) {
        Stuff s = new Stuff();
        for (int i=0; i<1000; i++)
            s.testMod();

    }
    
    
    public void testMod(){
        this.imageIndex = (++this.imageIndex) % IMAGE_COUNT;
        System.out.println(this.imageIndex);
    }

}
