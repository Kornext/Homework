import ru.sberbank.jschool.homework.classloaders.*;

public class TestPluginClass implements Plugin {

    private String message;

    public TestPluginClass() {
        this.message = "Working! Oh my God, its working!";
    }

    @Override
    public void run(String[] urls) {
        System.out.println(message);
    }
}
