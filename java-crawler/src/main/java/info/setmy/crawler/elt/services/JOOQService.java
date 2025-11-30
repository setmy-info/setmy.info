package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.DataConnectionTraversal;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;

import static org.jooq.SQLDialect.H2;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JOOQService {

    public DataConnectionTraversal fillJooq(final DataConnectionTraversal dataConnectionTraversal) {
        return dataConnectionTraversal.toBuilder()
            .dslContext(DSL.using(dataConnectionTraversal.dataSource(), H2))
            .build();
    }
}
