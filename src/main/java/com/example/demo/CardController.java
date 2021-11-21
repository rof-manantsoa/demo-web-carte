package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Controller
public class CardController {

   private final String [] CARTE_COLEUR = { "carreau", "coeur", "pique", "trefle"};
   private final String [] CARTE_VALEUR = {
           "as", "roi", "reine", "valet", "9",
           "8", "7", "6", "5", "4", "3", "2"
   };

   private CardService cardService;

   public CardController(@Autowired CardService cardService) {
      this.cardService = cardService;
   }

   @GetMapping("/")
   public String index(Model model){

      List<String> colors = DataTypeUtils.getRandList(CARTE_COLEUR);
      List<String> valeurs = DataTypeUtils.getRandList(CARTE_VALEUR);

      List<String> combinaisons = this.cardService.colorValuesCombinaison(valeurs, colors, 10);


      model.addAttribute("colors", colors);
      model.addAttribute("colors_images", colors.stream()
              .map(it -> join(
                      "/images/",
                      valeurs.get(DataTypeUtils.getRandomInt(valeurs.size())),"_", it, ".png"))
              .collect(Collectors.toList()));

      model.addAttribute("valeurs", valeurs);
      model.addAttribute("valeurs_images", valeurs.stream()
              .map(it ->  join("/images/", it, "_",
                      colors.get(DataTypeUtils.getRandomInt(colors.size())), ".png"))
              .collect(Collectors.toList()));


      List<String> combinaisonOrdered = this.cardService.orderRandomCombinaison(combinaisons, valeurs, colors);

      model.addAttribute("cartes1", combinaisons.stream()
              .map(it ->  join("/images/", it ,".png"))
              .collect(Collectors.toList()));

      model.addAttribute("cartes2", combinaisonOrdered.stream()
              .map(it ->  join("/images/", it, ".png"))
              .collect(Collectors.toList()));

      return "cartes";
   }
}
