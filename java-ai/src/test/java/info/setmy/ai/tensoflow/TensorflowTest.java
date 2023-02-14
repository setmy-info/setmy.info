package info.setmy.ai.tensoflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.tensorflow.*;
import org.tensorflow.op.Ops;
import org.tensorflow.op.core.Placeholder;
import org.tensorflow.op.math.Add;
import org.tensorflow.op.math.Sub;
import org.tensorflow.types.TFloat32;
import org.tensorflow.types.TInt32;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://github.com/tensorflow/java/#tensorflow-version-support
 * https://github.com/tensorflow/java/tree/master/tensorflow-core/tensorflow-core-api/src/test/java/org/tensorflow
 * https://github.com/tensorflow/java-models/tree/master/tensorflow-examples/src/main/java/org/tensorflow/model/examples
 */
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

    @Test
    public void test2() {
        try (
            final Graph graph = new Graph();
            final Session sess = new Session(graph)
        ) {
            final Ops tf = Ops.create(graph);

            final Operation addOperation = tf.math.add(
                tf.constant(1),
                tf.constant(2)
            ).op();

            assertThat(addOperation.outputListLength("z")).isEqualTo(1);
        }
    }

    @Test
    public void test3() {
        try (Graph graph = new Graph();
             ConcreteFunction function =
                 ConcreteFunction.create(this::plusFiveMinusTwo)) {
            Ops tf = Ops.create(graph);
            Operand<TFloat32> a = tf.constant(10f);
            Operand<TFloat32> result = (Operand<TFloat32>) function.call(tf, a);
            try (Session sess = new Session(graph);
                 TFloat32 t = (TFloat32) sess.runner().fetch(result).run().get(0)) {
                assertEquals(13f, t.getFloat());
            }
        }
    }

    private Signature plusFiveMinusTwo(final Ops tf) {
        final Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
        try (final ConcreteFunction plusFive = ConcreteFunction.create(this::plusFive);
             final ConcreteFunction minusTwo = ConcreteFunction.create(this::minusTwo)) {
            final Operand<TFloat32> result = (Operand<TFloat32>) minusTwo.call(
                tf,
                plusFive.call(tf, input)
            );
            return Signature.builder()
                .key("plusFiveMinusTwo")
                .input("x", input)
                .output("y", result)
                .build();
        }
    }

    private Signature plusFive(final Ops tf) {
        final Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
        final Add<TFloat32> output = tf.math.add(input, tf.constant(5.0f));
        return Signature.builder()
            .key("plusFive")
            .input("x", input)
            .output("y", output)
            .build();
    }

    private Signature minusTwo(final Ops tf) {
        final Placeholder<TFloat32> input = tf.placeholder(TFloat32.class);
        final Sub<TFloat32> output = tf.math.sub(input, tf.constant(2.0f));
        return Signature.builder()
            .key("minusTwo")
            .input("x", input)
            .output("y", output)
            .build();
    }
    /*
    @Test
    public void test4() {
        // // z = f(x, y) = a*x + b*y
        try (
            final Graph graph = new Graph();
            final Session sess = new Session(graph)
        ) {
            final Ops tf = Ops.create(graph);
            //final Scope scope = tf.scope();

            // Constants
            final Operation a = tf.withName("a").constant(1.0).op();
            final Operation b = tf.withName("b").constant(2.0).op();

            // Variables
            final TFloat64 x = TFloat64.scalarOf(3.0);
            final TFloat64 y = TFloat64.scalarOf(4.0);

            final Placeholder<TFloat64> doubleTypePlaceHolderX = tf.placeholder(TFloat64.class);
            final Placeholder<TFloat64> doubleTypePlaceHolderY = tf.placeholder(TFloat64.class);
            final Placeholder<TFloat64> doubleTypePlaceHolderAX = tf.placeholder(TFloat64.class);
            final Placeholder<TFloat64> doubleTypePlaceHolderBY = tf.placeholder(TFloat64.class);
            final Placeholder<TFloat64> doubleTypePlaceHolder = tf.placeholder(TFloat64.class);

            final Mul<TFloat64> multiplyAX = tf.math.mul(doubleTypePlaceHolder, doubleTypePlaceHolder);
            final Signature axMultiplySignature = Signature.builder()
                .input("a", a.output(0))
                .input("x", doubleTypePlaceHolderX)
                .output("ax", multiplyAX)
                .build();
            final ConcreteFunction axMultiplyFunction = ConcreteFunction.create(axMultiplySignature, sess);

            final Mul<TFloat64> multiplyBY = tf.math.mul(doubleTypePlaceHolder, doubleTypePlaceHolder);
            final Signature byMultiplySignature = Signature.builder()
                .input("b", b.output(0))
                .input("y", doubleTypePlaceHolderY)
                .output("by", multiplyBY)
                .build();
            final ConcreteFunction byMultiplyFunction = ConcreteFunction.create(byMultiplySignature, sess);

            final Add<TFloat64> add = tf.math.add(doubleTypePlaceHolder, doubleTypePlaceHolder);
            final Signature addFunctionSignature = Signature.builder()
                .input("ax", multiplyAX.asOutput())
                .input("by", multiplyBY.asOutput())
                .output("z", add)
                .build();
            final ConcreteFunction addFunction = ConcreteFunction.create(addFunctionSignature, sess);

            final TFloat64 result = (TFloat64) sess.runner()
                .feed("x", x)
                .feed("y", y)
                .fetch("z")
                .run()
                .get(0);

            assertThat(result.toString()).isEqualTo("xxx");
        }
    }
    */

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
