package lesson19.factory_example;/*
Created by Pavel Gryshchenko on 06.10.2022
*/

import lesson19.TestSet_1;
import lesson19.TestSet_2;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryClassExample {
    @Factory()
    @Test
    public Object[] getFactoryTests(){
        Object[] factory = new Object[2];
        factory[0] = new TestSet_1();
        factory[1] = new TestSet_2();
        return factory;
    }
}
