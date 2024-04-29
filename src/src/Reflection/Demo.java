package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

public class Demo {

    public static void main(String[] args)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
            SecurityException, InvocationTargetException, NoSuchFieldException {
        // Class eagleClass = Class.forName("Eagle");
        // or
        // Eagle bird = new Eagle();
        // Class eagleClass = bird.getClass();

        // or

        Class eagleClass = Eagle.class;

        // System.out.println(eagleClass.getName());
        // System.out.println(Modifier.toString(eagleClass.getModifiers()));

        // only public it will return not private of Eagle and its super class
        // Method[] methods = eagleClass.getMethods();

        // for (Method method : methods) {
        // System.out.println("method breed " + method.getName());
        // System.out.println("return type " + method.getReturnType());
        // System.out.println("class breed " + method.getDeclaringClass());

        // Class[] classes = method.getParameterTypes();

        // for (Class cls : classes) {

        // System.out.println("parameters type " + cls.getName());
        // }
        // }

        // all methods of only Eagle
        // Method[] methods2 = eagleClass.getDeclaredMethods();

        // for (Method method : methods2) {
        // System.out.println("method breed " + method.getName() + " return type " +
        // method.getReturnType()
        // + " parameters type ");
        // Stream.of(method.getParameterTypes())
        // .forEach((a) -> System.out.println(a.getName()));
        // }

        // invoking the method

        // Object object = eagleClass.newInstance();

        // Method doSome = eagleClass.getMethod("doSome", String.class, boolean.class,
        // int.class);

        // doSome.invoke(object, "duky", true, 100);

        // reflection of fields

        // only public field will return
        // Field[] fields = eagleClass.getFields();
        // for (Field field : fields) {
        // System.out.println("field breed " + field.getName() + " type " +
        // field.getType());
        // }

        // // all fields
        // Field[] fields2 = eagleClass.getDeclaredFields();
        // for (Field field : fields2) {
        // System.out.println("field breed " + field.getName() + " type " +
        // field.getType());
        // }

        // for public fields
        // Eagle eagle = (Eagle) eagleClass.newInstance();

        // Field field = eagleClass.getDeclaredField("breed");
        // field.set(eagle, "ducky");
        // System.out.println(eagle.breed);

        // // for private fields

        // Field field2 = eagleClass.getDeclaredField("canSwim");
        // field2.setAccessible(true);
        // field2.set(eagle, true);

        // if (field2.getBoolean(eagle)) {
        // System.out.println(" field set to true ");
        // }

        // reflection of constructor . important it breaks singleton pattern

        Constructor[] constructors = eagleClass.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            System.out.println("modifier " + Modifier.toString(constructor.getModifiers()));

            constructor.setAccessible(true);

            Eagle eagle = (Eagle) constructor.newInstance();

            eagle.doSome("brown", false, 100);
        }
    }

}
