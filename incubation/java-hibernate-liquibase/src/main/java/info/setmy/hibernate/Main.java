package info.setmy.hibernate;

import liquibase.exception.LiquibaseException;
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
        final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Path.class)
            .buildSessionFactory();

        // create a new path object
        final GeometryFactory geometryFactory = new GeometryFactory();
        final Coordinate[] coordinates = {new Coordinate(0, 0), new Coordinate(1, 1)};
        final LineString lineString = geometryFactory.createLineString(coordinates);
        Path path = new Path(lineString);

        // save the path object
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(path);
            session.getTransaction().commit();
        }

        // retrieve the path object
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            final Path retrievedPath = session.get(Path.class, path.getId());
            System.out.println("Retrieved path: " + retrievedPath.getTrajectory());
            session.getTransaction().commit();
        }

        // close the session factory
        sessionFactory.close();
    }
}
