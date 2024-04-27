import java.lang.reflect.*;

public class Saver1 {
    public static void main(String[] args) {
        TextContainer container = new TextContainer();
        Class<?> cls = container.getClass();

        if (cls.isAnnotationPresent(SaveTo.class)) {
            SaveTo annotation = cls.getAnnotation(SaveTo.class);
            String path = annotation.path();

            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    try {
                        method.invoke(container, path);
                        System.out.println("Text successfully saved to " + path);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
