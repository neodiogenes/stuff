package main;

public class StaticNonAbstractClass extends StaticAbstractClass implements StaticInterface {

    @Override
    public boolean abstractMethod(int input) {
        return StaticAbstractClass.staticNonAbstractMethod(input);
    }

    @Override
    public boolean interfaceMethod(int input) {
        return StaticAbstractClass.staticNonAbstractMethod(input);
    }



}
