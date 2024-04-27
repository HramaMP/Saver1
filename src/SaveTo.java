import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface SaveTo {
    String path();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Saver {
}

@SaveTo(path = "c:\\file.txt")
class TextContainer {
    String text = "Sample text";

    @Saver
    public void save(String path) {
        try (PrintWriter writer = new PrintWriter(new File(path))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}