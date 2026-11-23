package info.setmy.crawler.elt.services;

import info.setmy.crawler.elt.models.DataConnectionTraversal;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jooq.impl.DSL;

import static org.jooq.SQLDialect.H2;
import static org.jooq.impl.DSL.table;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JOOQService {

    public DataConnectionTraversal fillJooq(final DataConnectionTraversal dataConnectionTraversal) {
        return dataConnectionTraversal.toBuilder()
            .dslContext(DSL.using(dataConnectionTraversal.dataSource(), H2))
            .build();
    }

    /**
     * Rows of record_entity, read with jOOQ from the same Hikari pool as Hibernate.
     */
    public int countRecords(final DataConnectionTraversal dataConnectionTraversal) {
        return dataConnectionTraversal.dslContext().fetchCount(table("record_entity"));
    }
}
