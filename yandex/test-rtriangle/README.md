Задание от 01.12.17

public interface Rtriangle {
int getApexX1();
int getApexY1();
int getApexX2();
int getApexY2();
int getApexX3();
int getApexY3();
}

Методы возвращают 6 чисел, которые являются координатами трех вершин прямоугольного треугольника в декартовой системе координат
Есть метод, возвращающий прямоугольный треугольник:
public final class RtriangleProvider {
public static Rtriangle getRtriangle() {
...
}
}
Напишите код JUnit-теста, который будет проверять, действительно ли метод getRtriangle возвращает прямоугольный треугольник.

