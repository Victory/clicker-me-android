package victory.sandbox.mytestapp;

/**
 * Created by user on 10/15/2014.
 */
public class ListfulContent {
    private String name, info;

    ListfulContent (String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName () {
        return name;
    }

    public String getInfo () {
        return info;
    }
}
