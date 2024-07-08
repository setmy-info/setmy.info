package info.setmy.models.math.geometry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReferenceOfFrame3DTest {

    ReferenceOfFrame3D reference1;
    ReferenceOfFrame3D reference2;
    ReferenceOfFrame3D reference3;
    Point3D position1;
    Point3D position2;
    Point3D position3;

    final Point3D shift = new Point3D(1.0D, 1.0D, 1.0D);
    final Point3D coordinate = new Point3D(1.0D, 1.0D, 1.0D);

    @BeforeEach
    public void before() {
        position1 = new Point3D(0.0D, 0.0D, 0.0D);
        reference1 = ReferenceOfFrame3D.builder()
            .position(position1)
            .geometryProperties(GeometryProperties.builder().name("Sub 1 Geometry").build())
            .build();

        position2 = new Point3D(1.0D, 1.0D, 1.0D);
        reference2 = ReferenceOfFrame3D.builder()
            .position(position2)
            .geometryProperties(GeometryProperties.builder().name("Sub 2 Geometry").build())
            .build();

        position3 = new Point3D(2.0D, 2.0D, 2.0D);
        reference3 = ReferenceOfFrame3D.builder()
            .position(position3)
            .geometryProperties(GeometryProperties.builder().name("Sub 3 Geometry").build())
            .build();

        reference1.add(reference2);
        reference2.add(reference3);
    }

    @Test
    public void test() {
        reference1.transform(shift);

        final Point3D result = reference3.calculate(coordinate);

        assertThat(result.getX()).isEqualTo(5.0D);
        assertThat(result.getY()).isEqualTo(5.0D);
        assertThat(result.getZ()).isEqualTo(5.0D);
    }

    @Test
    public void test2() {
        reference2.transform(shift);

        final Point3D result = reference3.calculate(coordinate);

        assertThat(result.getX()).isEqualTo(5.0D);
        assertThat(result.getY()).isEqualTo(5.0D);
        assertThat(result.getZ()).isEqualTo(5.0D);
    }

    @Test
    public void test3() {
        reference3.transform(shift);

        final Point3D result = reference3.calculate(coordinate);

        assertThat(result.getX()).isEqualTo(5.0D);
        assertThat(result.getY()).isEqualTo(5.0D);
        assertThat(result.getZ()).isEqualTo(5.0D);
    }

    @Test
    public void test_all() {
        reference1.transform(shift);
        reference2.transform(shift);
        reference3.transform(shift);

        final Point3D result = reference3.calculate(coordinate);

        assertThat(result.getX()).isEqualTo(7.0D);
        assertThat(result.getY()).isEqualTo(7.0D);
        assertThat(result.getZ()).isEqualTo(7.0D);
    }
}
