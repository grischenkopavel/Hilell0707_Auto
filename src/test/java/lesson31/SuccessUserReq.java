package lesson31;/*
Created by Pavel Gryshchenko on 16.11.2022
*/

/*
POJO class for SuccessUserReq
 */
class SuccessUserReq {
    private Integer id;
    private String token;

    public SuccessUserReq() {

    }

    public SuccessUserReq(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
