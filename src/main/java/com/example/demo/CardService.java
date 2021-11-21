package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CardService {

   public List<String> colorValuesCombinaison(final List<String> values, final List<String> colors){
      List<String> listC = new ArrayList<>();
      for(String a : values){
         for (String b : colors){
            listC.add(a + "_" + b);
         }
      }
      return listC;
   }

   public List<String> colorValuesCombinaison(final List<String> values, final List<String> colors, int sizeToTake){
      List<String> list =  this.colorValuesCombinaison(values, colors);
      Collections.shuffle(list);
      return  list.stream().limit(sizeToTake).collect(Collectors.toList());
   }

   public List<String> orderRandomCombinaison(final List<String> combinaisons,
                                              final List<String> orderValues,
                                              final List<String> orderColors){
      final Map<String, Integer> mapValues = new HashMap<>();

      for(String s : combinaisons){

         String a = s.split("_")[0];

         int val = orderValues.indexOf(a);

         mapValues.put(s, val);

      }

      return mapValues.entrySet().stream().sorted((o1, o2) -> compareValueWithColor(orderColors, o1, o2))
              .map(Map.Entry::getKey).collect(Collectors.toList());
   }

   private int compareValueWithColor(final List<String> colors,
                                     final Map.Entry<String, Integer> valeurOrdered1,
                                     final Map.Entry<String, Integer> valeurOrdered2){
      int c = valeurOrdered1.getValue().compareTo(valeurOrdered2.getValue());
      if(c == 0){
         Integer index1 = colors.indexOf(valeurOrdered1.getKey().split("_")[1]);
         Integer index2 = colors.indexOf(valeurOrdered2.getKey().split("_")[1]);
         return  index1.compareTo(index2);
      }else
         return c;
   }

}
