package info.setmy.ai.tensoflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.tensorflow.ConcreteFunction;
import org.tensorflow.Graph;
import org.tensorflow.Operation;
import org.tensorflow.Session;
import org.tensorflow.Signature;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.ndarray.impl.sequence.SingleElementSequence;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

import static org.assertj.core.api.Assertions.assertThat;
import static org.tensorflow.proto.framework.DataType.DT_DOUBLE;

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

    @Test
    public void test2() {
        // f(x, y) = z = a*x + b*y
        final Graph graph = new Graph();

        final TFloat64 numberA = TFloat64.scalarOf(3.0);
        final Operation a = graph.opBuilder("Const", "a")
            .setAttr("dtype", numberA.dataType())
            .setAttr("value", numberA)
            .build();

        final TFloat64 numberB = TFloat64.scalarOf(2.0);
        final Operation b = graph.opBuilder("Const", "b")
            .setAttr("dtype", numberB.dataType())
            .setAttr("value", numberB)
            .build();

        final Operation x = graph.opBuilder("Placeholder", "x")
            .setAttr("dtype", DT_DOUBLE)
            .build();

        final Operation y = graph.opBuilder("Placeholder", "y")
            .setAttr("dtype", DT_DOUBLE)
            .build();

        final Operation ax = graph.opBuilder("Mul", "ax")
            .addInput(a.output(0))
            .addInput(x.output(0))
            .build();
        final Operation by = graph.opBuilder("Mul", "by")
            .addInput(b.output(0))
            .addInput(y.output(0))
            .build();
        final Operation z = graph.opBuilder("Add", "z")
            .addInput(ax.output(0))
            .addInput(by.output(0))
            .build();

        final Tensor tensorX = TFloat64.scalarOf(3.0);
        final Tensor tensorY = TFloat64.scalarOf(3.0);
        final Session sess = new Session(graph);
        final Tensor resultTensor = sess.runner()
            .fetch("z")
            .feed("x", tensorX)
            .feed("y", tensorY)
            .run()
            .<TFloat64>get(0);
        //.expect(Double.class);
        final TFloat64 result = (TFloat64) resultTensor;
        ///assertThat(((SingleElementSequence) result.scalars()).toString()).isEqualTo("xxx");
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
