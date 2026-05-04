package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoFile")
@Profile("file")
public class DaoFile implements IDao {
    @Override
    public double getValue() {
        System.out.println("Source: Fichier");
        return 180.0;
    }
}