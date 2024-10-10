package domaine;
import java.util.function.Predicate;

public class PredicatGenreHomme implements Predicate<Employe> {
    @Override
    public boolean test(Employe e) {
        return e.getGenre() == Genre.HOMME;
    }
}
