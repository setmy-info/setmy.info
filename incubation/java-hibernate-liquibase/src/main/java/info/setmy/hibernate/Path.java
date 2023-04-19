package info.setmy.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "path")
public class Path {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trajectory", columnDefinition = "geometry")
    private Geometry trajectory;

    public Path() {
    }

    public Path(Geometry trajectory) {
        this.trajectory = trajectory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geometry getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(Geometry trajectory) {
        this.trajectory = trajectory;
    }
}
