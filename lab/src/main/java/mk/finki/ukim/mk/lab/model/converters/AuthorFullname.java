package mk.finki.ukim.mk.lab.model.converters;

import java.io.Serializable;

public class AuthorFullname implements Serializable {
    private String name;
    private String surname;

    public AuthorFullname() {}

    public AuthorFullname(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}


