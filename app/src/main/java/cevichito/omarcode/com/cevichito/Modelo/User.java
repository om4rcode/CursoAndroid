package cevichito.omarcode.com.cevichito.Modelo;

public class User {

    // los nombres de las variables deben de ser iguales a las de la DB.
    private String Name;
    private String Password;
    private String Email;

    public User() {
    }

    public User(String name, String password) {
        this.Name = name;
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
