package lesson31;/*
Created by Pavel Gryshchenko on 16.11.2022
*/

/*
POJO class for Register
 */
class Register {
    private String email;
    private String password;

    public Register() {

    }

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Register(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
