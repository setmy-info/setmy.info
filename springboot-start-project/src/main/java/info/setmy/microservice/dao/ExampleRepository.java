package info.setmy.microservice.dao;

import info.setmy.microservice.models.ExampleModel;
import jakarta.inject.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
@Repository
public interface ExampleRepository extends JpaRepository<ExampleModel, Long> {
}
