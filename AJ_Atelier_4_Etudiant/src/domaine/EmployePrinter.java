package domaine;

import java.util.function.Consumer;

public class EmployePrinter implements Consumer<Employe> {
    @Override
    public void accept(Employe e) {
        System.out.println(e);
    }
}
