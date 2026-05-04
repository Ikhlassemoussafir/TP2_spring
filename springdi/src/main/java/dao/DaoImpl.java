package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component("daoImpl")
@Profile("prod")
public class DaoImpl implements IDao {
    @Override
    public double getValue() {
        System.out.println("Source: Production (DB)");
        return 100.0;
    }
}