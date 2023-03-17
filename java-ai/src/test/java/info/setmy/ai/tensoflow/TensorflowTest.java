package info.setmy.ai.tensoflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Constant;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Mul;
import org.tensorflow.types.TFloat64;
import org.tensorflow.types.TInt32;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://github.com/tensorflow/java/#tensorflow-version-support
 * https://github.com/tensorflow/java/tree/master/tensorflow-core/tensorflow-core-api/src/test/java/org/tensorflow
 * https://github.com/tensorflow/java-models/tree/master/tensorflow-examples/src/main/java/org/tensorflow/model/examples
 */
public class TensorflowTest {

    private final Logger log = LogManager.getLogger(getClass());

    @Test
    public void singleParameterAndResultExample() {
        log.info("Hello TensorFlow {}", TensorFlow.version());
        final ConcreteFunction doublingFunction = ConcreteFunction.create(this::singleParameterAndResultFunctionSignature);
        final TInt32 number = TInt32.scalarOf(10);
        final Tensor resultTensor = doublingFunction.call(number);
        final int result = ((TInt32) resultTensor).getInt();
        log.info("{} doubled is {}", number.getInt(), result);
        assertThat(result).isEqualTo(20);
    }

    private Signature singleParameterAndResultFunctionSignature(final Ops ops) {
        final Placeholder<TInt32> placeholder = ops.placeholder(TInt32.class);
        final Add<TInt32> add = ops.math.add(placeholder, placeholder);
        return Signature.builder()
            .output("dbl", add)
            .input("x", placeholder)
            .build();
    }

    @Test
    public void multiParameterAndResultExample() {
        log.info("Hello TensorFlow {}", TensorFlow.version());
        final ConcreteFunction doublingFunction = ConcreteFunction.create(this::multiParameterAndResultfunctionSignature);
        final TInt32 x = TInt32.scalarOf(10);
        final TInt32 y = TInt32.scalarOf(20);
        final Map<String, Tensor> map = new HashMap<>();
        map.put("x", x);
        map.put("y", y);
        final Map<String, Tensor> resultMap = doublingFunction.call(map);
        final Tensor resultTensor = resultMap.get("dbl");
        final int result = ((TInt32) resultTensor).getInt();
        log.info("{} doubled is {}", x.getInt(), result);
        assertThat(result).isEqualTo(30);
    }

    private Signature multiParameterAndResultfunctionSignature(final Ops ops) {
        final Placeholder<TInt32> placeholderX = ops.placeholder(TInt32.class);
        final Placeholder<TInt32> placeholderY = ops.placeholder(TInt32.class);
        final Add<TInt32> add = ops.math.add(placeholderX, placeholderY);
        return Signature.builder()
            .output("dbl", add)
            .input("x", placeholderX)
            .input("y", placeholderY)
            .build();
    }

    @Test
    public void test2() {
        try (
            final Graph graph = new Graph();
            final Session sess = new Session(graph)
        ) {
            final Ops tf = Ops.create(graph);

            final Add<TInt32> addOperand = tf.math.add(
                tf.constant(1),
                tf.constant(2)
            );
            final Operation addOperation = addOperand.op();

            assertThat(addOperation.outputListLength("z")).isEqualTo(1);
        }
    }

    @Test
    public void test4() {
        // // z = f(x, y) = a*x + b*y
        try (
            final Graph graph = new Graph();
            final Session sess = new Session(graph)
        ) {
            final Ops tf = Ops.create(graph);
            //final Scope scope = tf.scope();

            // Variables
            final TFloat64 x = TFloat64.scalarOf(3.0);
            final TFloat64 y = TFloat64.scalarOf(4.0);

            // Constants
            final Constant<TFloat64> a = tf.withName("a").constant(1.0);
            final Constant<TFloat64> b = tf.withName("b").constant(2.0);

            final Placeholder<TFloat64> placeHolderX = tf.withName("x").placeholder(TFloat64.class);
            final Mul<TFloat64> multiplyAX = tf.math.mul(a, placeHolderX); // Mul -> Operand
            final Signature axMultiplySignature = Signature.builder()
                .key("multiplyAX")
                .output("ax", multiplyAX)
                .input("x", placeHolderX)
                .build();
            final ConcreteFunction axMultiplyFunction = ConcreteFunction.create(axMultiplySignature, sess);

            final Placeholder<TFloat64> placeHolderY = tf.withName("y").placeholder(TFloat64.class);
            final Mul<TFloat64> multiplyBY = tf.math.mul(b, placeHolderY); // Mul -> Operand
            final Signature byMultiplySignature = Signature.builder()
                .key("multiplyBY")
                .output("by", multiplyBY)
                .input("y", placeHolderY)
                .build();
            final ConcreteFunction byMultiplyFunction = ConcreteFunction.create(byMultiplySignature, sess);

            final Placeholder<TFloat64> axPlaceHolder = tf.withName("ax").placeholder(TFloat64.class);
            final Placeholder<TFloat64> byPlaceHolder = tf.withName("by").placeholder(TFloat64.class);
            final Add<TFloat64> add = tf.math.add(axPlaceHolder, byPlaceHolder); // Add -> Operand
            final Signature addFunctionSignature = Signature.builder()
                .key("add")
                .output("z", add)
                .input("ax", axPlaceHolder)
                .input("by", byPlaceHolder)
                .build();
            final ConcreteFunction addFunction = ConcreteFunction.create(addFunctionSignature, sess);

            final Map<String, Tensor> callResult = addFunction.call(new HashMap<>() {{
                put("ax", axMultiplyFunction.call(x));
                put("by", byMultiplyFunction.call(y));
            }});

            final Tensor z = callResult.get("z");
            final TFloat64 fZ = (TFloat64) z;

            /*
            assertThat(fZ).isEqualTo("xxx");

            final TFloat64 result = (TFloat64) sess.runner()
                .feed("x", x)
                .feed("y", y)
                .fetch("z")
                .run()
                .get(0);

            assertThat(result).isEqualTo("xxx");
            */
        }
    }

    /*
    @Test
    public void test5() {
        // f(x, y) = z = a*x + b*y
        try (
            final Graph graph = new Graph();
            final Session sess = new Session(graph)
        ) {
            final Ops tf = Ops.create(graph);
            final Scope scope = tf.scope();

            final Operation opA = (GraphOperation) tf.withName("opA").constant(3.0).op();
            final TFloat64 numberA = TFloat64.scalarOf(3.0);
            final Operation a = graph.opBuilder("Const", "a")
                .setAttr("dtype", numberA.dataType())
                .setAttr("value", numberA)
                .build();

            final Operation opB = (GraphOperation) tf.withName("opB").constant(2.0).op();
            final TFloat64 numberB = TFloat64.scalarOf(2.0);
            final Operation b = graph.opBuilder("Const", "b")
                .setAttr("dtype", numberB.dataType())
                .setAttr("value", numberB)
                .build();

            final Placeholder<TFloat64> placeholderX = tf.withName("placeholderX").placeholder(TFloat64.class);
            final Operation x = graph.opBuilder("Placeholder", "x")
                .setAttr("dtype", DT_DOUBLE)
                .build();
            final Placeholder<TFloat64> placeholderY = tf.withName("placeholderY").placeholder(TFloat64.class);
            final Operation y = graph.opBuilder("Placeholder", "y")
                .setAttr("dtype", DT_DOUBLE)
                .build();

            final Operation multiplyAX = graph.opBuilder("Mul", "ax")
                .addInput(a.output(0))
                .addInput(x.output(0))
                .build();
            final Operation multiplyBY = graph.opBuilder("Mul", "by")
                .addInput(b.output(0))
                .addInput(y.output(0))
                .build();

            final Operation addToZ = graph.opBuilder("Add", "z")
                .addInput(multiplyAX.output(0))
                .addInput(multiplyBY.output(0))
                .build();

            final Tensor tensorX = TFloat64.scalarOf(3.0);
            final Tensor tensorY = TFloat64.scalarOf(6.0);

            final TFloat64 resultTensor = (TFloat64) sess.runner()
                .fetch("z")
                .feed("x", tensorX)
                .feed("y", tensorY)
                .run()
                .get(0);

            final TFloat64 result = (TFloat64) resultTensor;
            // assertThat(((TFloat64)tensorX).asRawTensor().toString()).isEqualTo("xxx");
            // assertThat(((TFloat64)tensorY).getObject(0)).isEqualTo("xxx");
            // assertThat(((SingleElementSequence) result.scalars()).toString()).isEqualTo("xxx");
        }
    }
    */
}
