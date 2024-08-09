// <editor-fold defaultstate="collapsed" desc="Hidden part">
package info.setmy.examples.lessons.level1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:imre.tabur@mail.ee">Imre Tabur</a>
 */
public class Lesson1DataTypesTest {

    @Test
    void f() {
// </editor-fold>

        /*
    This is multi
    line example
    comment.
         */
        // Coment starting from // characters until end of the line
        /* ====== Primitive data types ====== */
        // 8 bit - min -128 and max 127. Default 0.
        byte byteNumber;

        // 16 bit - min -32768 and max 32767. Default 0.
        short shortNumber;

        // 32 bit - min -xxxx and max xxxx. Default 0.
        int intNumber;

        // 64 bit - min -xxxx and max xxxx. Default 0L.
        long longNumber;

        // 32-bit IEEE 754 floating point. Default 0.0f.
        float floatNumber;

        // 64-bit IEEE 754 floating point. Default 0.0d.
        double doubleNumber;

        // size is not defined. Only values: true or false. Default false.
        boolean trueOrFalse;

        // 16-bit Unicode character. Min '\u0000' (or 0) and a max '\uffff' (or 65535). Default '\u0000'.
        char character;

        /* ====== More complex data types ====== */
        // Text holder.
        String thisIsText;

        // Date holder
        Date date;

        LocalDate localDate;

        //
        BigDecimal bigDecimal;

        //
        BigInteger bigInteger;

        //
        List list;

        //
        Set set;

        Map map;

        Byte byteObject;

        Short shortNumberObject;

        Integer integerObject;

        Long longNumberObject;

        Float floatNumberObject;

        Double doubleNumberObject;

        Boolean trueOrFalseObject;

        // <editor-fold defaultstate="collapsed" desc="Setting values">
        thisIsText = "Hello World";
        date = new Date();
        localDate = LocalDate.now();
        bigDecimal = new BigDecimal("123.45");
        bigInteger = new BigInteger("54321");
        list = new ArrayList();
        set = new HashSet();
        map = new HashMap();
        // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Hidden part">
    }
}
// </editor-fold>
