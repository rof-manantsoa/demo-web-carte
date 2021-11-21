package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataTypeUtils {

    public static List<String> getRandList(String[] arr){
        if(arr == null || arr.length == 0)
            return new ArrayList<>();
        List<String> stringList = Arrays.asList(arr);
        Collections.shuffle(stringList);
        return  stringList;
    }

    public static int getRandomInt(int max){
        return ThreadLocalRandom.current().nextInt(max);
    }
}
