package domaine;

import java.util.function.Function;

public class EmployeToNomFunction implements Function<Employe, String> {
    @Override
    public String apply(Employe e) {
        return e.getNom();
    }
}
