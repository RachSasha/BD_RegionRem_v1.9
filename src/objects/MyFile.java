package objects;

/**
 * Created by alien on 17.10.2017.
 */
public class MyFile
{
    private String name;
    private String path;

    public MyFile(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
