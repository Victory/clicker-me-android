package victory.sandbox.mytestapp;

public class ListItemModel {
    private String main;

    ListItemModel(String mainMessage) {
        setMain(mainMessage);
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String toString () {
        return getMain();
    }

}
