package ru.javawebinar.topjava.util;

/**
 * Created by Brad on 18.07.2017.
 */
public class GeneratorID {

    private static volatile GeneratorID instance;
    private volatile int id = 0;

    public static GeneratorID getInstance() {
        GeneratorID localInstance = instance;
        if (localInstance == null) {
            synchronized (GeneratorID.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GeneratorID();
                }
            }
        }
        return localInstance;
    }

    public synchronized int getNextId(){
        return ++id;
    }
}
