package ru.yandex.triangle.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.yandex.triangle.Rtriangle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class RtriangleImplTest {

    Rtriangle triangle;

    @Before
    public void setUp() {
        triangle = new RtriangleImpl();
    }

    @Ignore("This need, because it is core test")
    @Test
    public void object_is_triangle() {
        throw new NotImplementedException(); //TODO
    }

    @Test
    public void object_is_right_triangle() {
        int[] sidesOfTriangle = Utils.getSortedSquaresSides(triangle);

        int sumSquaresCathetus = sidesOfTriangle[0] + sidesOfTriangle[1];
        int squareHypotenuse = sidesOfTriangle[2];

        assertEquals(
                "Sum of squares cathetus must equals square hypotenuse",
                sumSquaresCathetus,
                squareHypotenuse
        );
    }

    private static class Utils {

        public static int[] getSortedSquaresSides(Rtriangle rtriangle) {
            return Stream.of(
                    Math.pow((rtriangle.getApexX2() - rtriangle.getApexX1()), 2) + (int) Math.pow((rtriangle.getApexY2() - rtriangle.getApexY1()), 2),
                    Math.pow((rtriangle.getApexX3() - rtriangle.getApexX1()), 2) + (int) Math.pow((rtriangle.getApexY3() - rtriangle.getApexY1()), 2),
                    Math.pow((rtriangle.getApexX3() - rtriangle.getApexX2()), 2) + (int) Math.pow((rtriangle.getApexY3() - rtriangle.getApexY2()), 2)
            ).sorted().mapToInt(
                    Double::intValue
            ).toArray();
        }
    }
}
