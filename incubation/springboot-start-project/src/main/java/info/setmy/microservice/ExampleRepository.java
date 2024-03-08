package info.setmy.microservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleModel, Long> {
}
