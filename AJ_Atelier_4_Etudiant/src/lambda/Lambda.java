package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static List<Integer> allMatches(List<Integer> list, Predicate<Integer> match) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            if (match.test(i)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static List<Integer> transformAll(List<Integer> list, Function<Integer, Integer> transform) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(transform.apply(i));
        }
        return result;
    }


}
