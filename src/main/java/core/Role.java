package core;

/**
 * Created by ivsi on 2/3/2016.
 */
public enum Role {

    ADMIN("ADMIN"),
    USER("USER");

    final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
