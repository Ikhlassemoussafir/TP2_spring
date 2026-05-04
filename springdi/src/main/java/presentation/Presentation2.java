package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"dao", "metier", "config"})
public class Presentation2 {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext();

        // Variante C : aucun profil
        // Changer dao.target dans app.properties pour basculer

        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier metier = ctx.getBean(IMetier.class);
        System.out.println("Résultat = " + metier.calcul());

        ctx.close();
    }
}