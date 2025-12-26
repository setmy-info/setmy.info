package info.setmy;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.armedbear.lisp.Bignum;
import org.armedbear.lisp.Cons;
import org.armedbear.lisp.DoubleFloat;
import org.armedbear.lisp.Fixnum;
import org.armedbear.lisp.Function;
import org.armedbear.lisp.HashTable;
import org.armedbear.lisp.Lisp;
import org.armedbear.lisp.LispCharacter;
import org.armedbear.lisp.LispInteger;
import org.armedbear.lisp.LispObject;
import org.armedbear.lisp.Ratio;
import org.armedbear.lisp.SimpleString;
import org.armedbear.lisp.SimpleVector;
import org.armedbear.lisp.SingleFloat;
import org.armedbear.lisp.Symbol;
import org.armedbear.lisp.scripting.AbclScriptEngineFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LispIT {

    ScriptEngineManager manager;
    ScriptEngine engine;

    @BeforeEach
    void before() {
        manager = new ScriptEngineManager();
        manager.registerEngineName("abcl", new AbclScriptEngineFactory());
        engine = manager.getEngineByName("abcl");
        if (engine == null) {
            throw new RuntimeException("ABCL ScriptEngine not found!");
        }
    }

    @Test
    void probe() throws ScriptException {
        Object result = engine.eval("(print \"Hello World!\")");
        System.out.println("");
        assertThat(result).isNotNull().isEqualTo("Hello World!");
    }

    @Test
    void addFunctionEval1() throws ScriptException {
        Object result = engine.eval("(+ 2 3)");
        assertThat((Number) result).isEqualTo(5);
    }

    @Test
    void addFunctionCall() throws ScriptException {
        Object function = engine.eval("(defun add (a b) (+ a b))");
        System.out.println("Calculator instance: " + function);
        System.out.println("Calculator class: " + function.getClass());
        if (function instanceof org.armedbear.lisp.Symbol sym) {
            System.out.println("Name: " + sym.getName());      // → "ADD"
            System.out.println("Object: " + sym.printObject());  // → "ADD"
            LispObject a = LispInteger.getInstance(4);
            LispObject b = LispInteger.getInstance(5);
            LispObject result = sym.execute(a, b);
            System.out.println("Result = " + result);
            assertThat((Number) result.intValue()).isEqualTo(9);

            Function function2 = (Function) engine.eval("(symbol-function 'add)");
            LispObject func2Result = function2.execute(a, b);
            System.out.println("Result = " + func2Result);
            assertThat((Number) func2Result.intValue()).isEqualTo(9);
        }
    }

    @Test
    void addFunctionEval2() throws ScriptException {
        engine.eval("(defun add (a b) (+ a b))");
        Object result = engine.eval("(add 2 3)");
        assertThat((Number) result).isEqualTo(5);
    }

    @Test
    void lispCallsJavaStatic() throws ScriptException {
        Object multiply = engine.eval("(java:jstatic \"multiply\" \"info.setmy.MathUtil\" 6 7)");
        assertThat((Number) multiply).isEqualTo(42);
    }

    @Test
    void lispCallsJavaInstance() throws ScriptException {
        Object calc1 = getEval("(java:jnew \"info.setmy.Calculator\")");
        engine.put("calc1", calc1);
        Object addResult = engine.eval("(java:jcall \"add\" calc1 4 5)");
        assertThat((Number) addResult).isEqualTo(9);

        Object calc2 = getEval("(defparameter calc2 (java:jstatic \"getInstance\" \"info.setmy.Calculator\"))");
        addResult = engine.eval("(java:jcall \"add\" calc2 4 5)");
        assertThat((Number) addResult).isEqualTo(9);

        Object cal3 = getEval("(setq cal3 (java:jnew \"info.setmy.Calculator\"))");
        addResult = engine.eval("(java:jcall \"add\" cal3 4 5)");
        assertThat((Number) addResult).isEqualTo(9);

        Object calc4 = engine.eval("(defparameter *calc* (java:jnew \"info.setmy.Calculator\"))");
    }

    private Object getEval(final String script) throws ScriptException {
        Object object = engine.eval(script);
        System.out.println("=========================");
        System.out.println("Calculator instance: " + object);
        System.out.println("Calculator class: " + object.getClass());
        System.out.println("=========================");
        return object;
    }

    /*
     * ~/.abcl-init.lisp
     * ---------------
     * (require :asdf)
     * (load (merge-pathnames "quicklisp/setup.lisp"
     *                   (user-homedir-pathname)))
     *
     * (load (make-pathname :name "myscript" :type "lisp") :verbose t) ;; Classpath?
     * (load (uiop:pathname "myscript.lisp" :relative-to #P"/") :verbose t) ;; Classpath?
     * engine.eval("(load \"/myscript.lisp\")"); // src/main/resources
     * engine.eval("(load \"/lisp/myscript.lisp\")"); // src/main/resources/lisp/myscript.lisp
     * (load "https://example.com/mylisp.lisp")
     *
     * */
    @Test
    @Disabled
    void quicklisp() throws ScriptException {
        engine.eval("(require :asdf)");
        engine.eval("(load (merge-pathnames \"quicklisp/setup.lisp\" (user-homedir-pathname)))");
        engine.eval("(ql:quickload :cl-ppcre)");
        engine.eval("(format t \"Regexp match: ~a~%\" (cl-ppcre:scan-to-strings \"foo\" \"foobar\"))");
    }

    @Test
    @Disabled
    void dataTypes() throws ScriptException {
        engine.eval("(require :asdf)");
        engine.eval("(load (merge-pathnames \"quicklisp/setup.lisp\" (user-homedir-pathname)))");
        engine.eval("(load \"src/test/lisp/data-types.lisp\")");

        Object lispResult = engine.eval("(make-test-values)");
        assertThat(lispResult).isInstanceOf(org.armedbear.lisp.Cons.class);

        LispObject list = (LispObject) lispResult;
        Map<String, Object> map = new HashMap<>();

        for (LispObject cell = list; cell instanceof Cons cons; cell = cons.cdr()) {
            Cons pair = (Cons) cons.car();
            Symbol symbol = (Symbol) pair.car();
            String key = symbol.getStringValue();
            Object value = lispToJava(pair.cdr());
            map.put(key, value);
        }

        assertThat(map.get("FIXNUM")).isInstanceOf(Integer.class).isEqualTo(42);
        assertThat(map.get("SMALL-NEGATIVE")).isEqualTo(-7);
        assertThat(map.get("BIGNUM")).isInstanceOf(BigInteger.class).isEqualTo(new BigInteger("9999999999999999999999999999"));
        assertThat(map.get("RATIO")).isInstanceOf(Pair.class);
        ImmutablePair<?, ?> r1 = (ImmutablePair<?, ?>) map.get("RATIO");
        assertThat(r1.getLeft()).isEqualTo(BigInteger.valueOf(3));
        assertThat(r1.getRight()).isEqualTo(BigInteger.valueOf(4));

        ImmutablePair<?, ?> r2 = (ImmutablePair<?, ?>) map.get("BIG-RATIO");
        assertThat(r2.getLeft()).isEqualTo(new BigInteger("9999999999999999999"));
        assertThat(r2.getRight()).isEqualTo(BigInteger.valueOf(5));

        assertThat(map.get("FLOAT")).isInstanceOf(Float.class).isEqualTo(1.5f);
        assertThat(map.get("DOUBLE")).isInstanceOf(Double.class).isEqualTo(3.1415926535897930);
        assertThat(map.get("STRING")).isEqualTo("hello");
        assertThat(map.get("CHAR")).isInstanceOf(Character.class).isEqualTo('A');
        assertThat(map.get("NIL")).isNull();
        assertThat(map.get("T")).isInstanceOf(Boolean.class).isEqualTo(true);

        Cons list1 = (Cons) map.get("LIST");
        List<Object> resultList = consToJavaList(list1);
        assertThat(resultList).containsExactly(1, 2, 3);

        Cons plist = (Cons) map.get("PLIST");
        List<Object> resultPlist = consToJavaList(plist);

        Cons assoList = (Cons) map.get("ASSO-LIST");
        List<Object> resultAssoList = consToJavaList(assoList);

        SimpleVector vector = (SimpleVector) map.get("VECTOR");
        HashTable hash = (HashTable) map.get("HASH");
    }

    private List<Object> consToJavaList(LispObject obj) {
        final List<Object> result = new ArrayList<>();
        while (obj instanceof Cons cons) {
            result.add(lispToJava(cons.car()));
            obj = cons.cdr();
        }
        if (obj != Lisp.NIL) {
            throw new IllegalArgumentException("Improper list, cdr not NIL");
        }
        return result;
    }

    private LispObject javaToLisp(Object o) {
        return switch (o) {
            case null -> Lisp.NIL;
            case Boolean bool -> bool ? Lisp.T : Lisp.NIL;
            case Byte b -> Fixnum.getInstance(b.intValue());
            case Character c -> LispCharacter.getInstance(c);
            case Short s -> Fixnum.getInstance(s.intValue());
            case Integer i -> Fixnum.getInstance(i);
            case Long l -> LispInteger.getInstance(l);
            case BigInteger bi -> Bignum.getInstance(bi);
            case Float f -> new SingleFloat(f);
            case Double d -> new DoubleFloat(d);
            case String s -> new SimpleString(s);
            case Pair<?, ?> p -> {
                Object a = p.getLeft();
                Object b = p.getRight();
                if (a instanceof BigInteger A && b instanceof BigInteger B) {
                    yield new Ratio(A, B);
                }
                throw new IllegalArgumentException("Pair must be <BigInteger,BigInteger>");
            }
            default -> throw new IllegalArgumentException("Unsupported type: " + o);
        };
    }

    private Object lispToJava(LispObject o) {
        if (o == Lisp.NIL) return null;
        if (o == Lisp.T) return true;
        if (o instanceof Fixnum f) return f.value;
        if (o instanceof Bignum bi) return bi.value;
        if (o instanceof LispCharacter c) return c.getValue();
        if (o instanceof LispInteger li) return li.intValue();
        if (o instanceof SingleFloat sf) return sf.getValue();
        if (o instanceof DoubleFloat df) return df.getValue();
        if (o instanceof SimpleString s) return s.getStringValue();
        if (o instanceof Ratio r) {
            BigInteger num = r.numerator();
            BigInteger den = r.denominator();
            return Pair.of(num, den);
        }
        return o;
    }
}
