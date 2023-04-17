package info.setmy.hibernate;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

public class Main {

    public static void main(String[] args) throws LiquibaseException {

        LiquibaseService.init();

        // create a session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Path.class)
                .buildSessionFactory();

        // create a new path object
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate[] coordinates = {new Coordinate(0, 0), new Coordinate(1, 1)};
        LineString lineString = geometryFactory.createLineString(coordinates);
        Path path = new Path("");

        // save the path object
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(path);
            session.getTransaction().commit();
        }

        // retrieve the path object
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Path retrievedPath = session.get(Path.class, path.getId());
            System.out.println("Retrieved path: " + retrievedPath.getTrajectory());
            session.getTransaction().commit();
        }

        // close the session factory
        sessionFactory.close();
    }
}
