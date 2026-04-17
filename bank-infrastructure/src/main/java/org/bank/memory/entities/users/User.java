package org.bank.memory.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bank.memory.entities.accounts.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания пользователя.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "hair_color", nullable = false)
    private HairColor hairColor;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_login"),
            inverseJoinColumns = @JoinColumn(name = "friend_login")
    )
    private List<User> friends = new ArrayList<>();

    /**
     * Конструктор для создания пользователя.
     *
     * @param login     логин пользователя
     * @param name      имя пользователя
     * @param age       возраст пользователя
     * @param gender    пол пользователя
     * @param hairColor цвет волос пользователя
     */
    public User(String login, String name, int age, String gender, HairColor hairColor) {

        this.login = login;

        this.name = name;

        this.age = age;

        this.gender = gender;

        this.hairColor = hairColor;

        this.friends = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    public User() {
    }

    /**
     * Метод для добавления друга.
     *
     * @param friend экземпляр друга
     */
    public void addFriend(User friend) {
        this.getFriends().add(friend);
    }

    /**
     * Метод для удаления друга.
     *
     * @param friend друга
     */
    public void removeFriend(User friend) {
        this.getFriends().remove(friend);
    }
}
