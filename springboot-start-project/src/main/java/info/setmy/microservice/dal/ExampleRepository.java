package info.setmy.microservice.dal;

import info.setmy.microservice.domain.ExampleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<ExampleModel, Long> {
}
