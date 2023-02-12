package info.setmy.ai.tensoflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Signature;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TInt32;

import static org.assertj.core.api.Assertions.assertThat;

public class TensorflowTest {

    private final Logger log = LogManager.getLogger(getClass());

    @Test
    public void test() {
        log.info("Hello TensorFlow {}", TensorFlow.version());
        final ConcreteFunction doublingFunction = ConcreteFunction.create(this::functionSignature);
        final TInt32 number = TInt32.scalarOf(10);
        final Tensor resultTensor = doublingFunction.call(number);
        final int result = ((TInt32) resultTensor).getInt();
        log.info("{} doubled is {}", number.getInt(), result);
        assertThat(result).isEqualTo(20);
    }

    private Signature functionSignature(final Ops ops) {
        final Placeholder<TInt32> placeholder = ops.placeholder(TInt32.class);
        final Add<TInt32> add = ops.math.add(placeholder, placeholder);
        return Signature.builder()
            .input("x", placeholder)
            .output("dbl", add)
            .build();
    }

}
