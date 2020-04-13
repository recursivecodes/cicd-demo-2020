package codes.recursive.model;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @AutoPopulated
    private UUID id;
    @NotNull
    @Size(min = 3, max = 75)
    private String firstName;
    @NotNull
    @Size(min = 5, max = 75)
    private String lastName;
    @Size(min = 1, max = 150)
    private int age;
    @Nullable
    @Email
    @Size(max = 500)
    private String email;

    public User(@NotNull @Size(min = 3, max = 75) String firstName, @NotNull @Size(min = 5, max = 75) String lastName, @Size(min = 1, max = 150) int age, @Nullable @Email @Size(max = 500) String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }
}
