package info.setmy.crawler.dal;

import lombok.extern.log4j.Log4j2;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.ExecuteContext;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListener;

import javax.sql.DataSource;

import static org.jooq.SQLDialect.H2;

@Log4j2
public class JooqContextFactory {

    private static final JooqContextFactory INSTANCE = new JooqContextFactory();

    public static JooqContextFactory getInstance() {
        return INSTANCE;
    }

    public DSLContext newDSLContext(final DataSource dataSource) {
        /*
        final Settings settings = new Settings()
            .withExecuteLogging(true)
            .withQueryTimeout(10)
            .withRenderFormatted(true);
        final DSLContext dslContext = DSL.using(dataSource, H2, settings);
        return dslContext;
        */

        final Settings settings = new Settings()
            .withExecuteLogging(true)
            .withRenderFormatted(false)
            .withParamType(ParamType.NAMED)
            .withQueryTimeout(5)
            .withExecuteWithOptimisticLocking(true);

        final Configuration config = new DefaultConfiguration()
            .set(dataSource)
            .set(H2)
            .set(settings)
            .set(new DefaultExecuteListener() {

                @Override
                public void renderEnd(final ExecuteContext ctx) {
                    log.info("SQL: " + ctx.sql());
                }

                @Override
                public void bindEnd(final ExecuteContext ctx) {
                    log.info("Bind variables: ");
                }
            });

        return DSL.using(config);
    }
}
