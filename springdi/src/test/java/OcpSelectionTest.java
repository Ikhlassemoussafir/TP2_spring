import metier.IMetier;
import org.junit.After;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.Presentation2;

import static org.junit.Assert.assertEquals;

public class OcpSelectionTest {

    private AnnotationConfigApplicationContext ctx;

    @After
    public void tearDown() {
        if (ctx != null) ctx.close();
    }

    // ─────────────────────────────────────
    // Mécanisme 1 — @Profile
    // ─────────────────────────────────────

    @Test
    public void devProfile_choisitDao2_300() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(300.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
    }

    @Test
    public void prodProfile_choisitDao_200() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(200.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
    }

    @Test
    public void fileProfile_choisitDaoFile_360() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("file");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(360.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
    }

    @Test
    public void apiProfile_choisitDaoApi_440() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("api");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(440.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
    }

    // ─────────────────────────────────────
    // Mécanisme 4 — Propriété externe
    // ─────────────────────────────────────

    @Test
    public void property_daoApi_440() {
        System.setProperty("dao.target", "daoApi");
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("property");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(440.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
        System.clearProperty("dao.target");
    }

    @Test
    public void property_daoFile_360() {
        System.setProperty("dao.target", "daoFile");
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("property");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(360.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
        System.clearProperty("dao.target");
    }

    @Test
    public void property_dao2_300() {
        System.setProperty("dao.target", "dao2");
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("property");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(300.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
        System.clearProperty("dao.target");
    }

    @Test
    public void property_daoImpl_200() {
        System.setProperty("dao.target", "daoImpl");
        ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("property");
        ctx.register(Presentation2.class);
        ctx.refresh();
        assertEquals(200.0, ctx.getBean(IMetier.class).calcul(), 1e-6);
        System.clearProperty("dao.target");
    }
}