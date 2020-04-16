package info.setmy.microservice.dao;

import info.setmy.microservice.models.ExampleModel;
import javax.inject.Named;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author <a href="mailto:imre.tabur@eesti.ee">Imre Tabur</a>
 */
@Named("exampleRepository")
public interface ExampleRepository extends JpaRepository<ExampleModel, Long> {
}
