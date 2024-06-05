package info.setmy.microservice;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Switches {

    public static final boolean UNIT_TEST_FAILS = false;
    public static final boolean VINTAGE_UNIT_TEST_FAILS = false;

    public static final boolean INTEGRATION_TEST_FAILS = false;
    public static final boolean VINTAGE_INTEGRATION_TEST_FAILS = false;

    public static final boolean E2E_TEST_FAILS = false;
    public static final boolean E2E_VINTAGE_INTEGRATION_TEST_FAILS = false;

    public static final boolean E2E_TEST_CUCUMBER_FAILS = false;
}
