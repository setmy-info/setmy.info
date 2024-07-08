package info.setmy.models.math.geometry;

import info.setmy.models.math.geometry.base.GeometryProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReferenceOfFrame3DDoubleTest {

    ReferenceOfFrame3DDouble reference;
    ReferenceOfFrame3DDouble reference1;
    ReferenceOfFrame3DDouble reference2;
    Point3DDouble position;
    Point3DDouble position1;
    Point3DDouble position2;

    @BeforeEach
    public void before() {
        position = new Point3DDouble();
        position.setX(0.0D);
        position.setY(0.0D);
        position.setZ(0.0D);
        reference = new ReferenceOfFrame3DDouble(position, new ArrayList<>());
        reference.setGeometryProperties(GeometryProperties.builder().name("Root Geometry").build());

        position1 = new Point3DDouble();
        position1.setX(1.0D);
        position1.setY(1.0D);
        position1.setZ(1.0D);
        reference1 = new ReferenceOfFrame3DDouble(position1, new ArrayList<>());
        reference1.setGeometryProperties(GeometryProperties.builder().name("Sub 1 Geometry").build());

        position2 = new Point3DDouble();
        position2.setX(2.0D);
        position2.setY(2.0D);
        position2.setZ(2.0D);
        reference2 = new ReferenceOfFrame3DDouble(position2, new ArrayList<>());
        reference2.setGeometryProperties(GeometryProperties.builder().name("Sub 2 Geometry").build());

        reference.add(reference1);
        reference1.add(reference2);
    }

    @Test
    public void test() {
        final Point3DDouble shift = new Point3DDouble();
        shift.setX(1.0D);
        shift.setY(2.0D);
        shift.setZ(3.0D);
        final Point3DDouble coordinate = new Point3DDouble();
        coordinate.setX(1D);
        coordinate.setY(1D);
        coordinate.setZ(1D);
        reference1.transform(shift);
    }
}
