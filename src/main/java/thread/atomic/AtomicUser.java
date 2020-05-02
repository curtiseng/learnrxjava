package thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

class User {
    String username;
    int age;

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * @author yangzifeng
 */
public class AtomicUser {

    public static void main(String[] args) {
        User marry = new User("marry", 25);
        User bob = new User("bob", 27);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(marry);

        System.out.println(atomicReference.compareAndSet(marry, bob) + "\t" + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(marry, bob) + "\t" + atomicReference.get().toString());
    }
}
