package businessLogic.account;

import businessLogic.user.UserLogic;
import model.UserType;

public class LoginLogic {
    private UserLogic userLogic;

    public LoginLogic() {
        this.userLogic = new UserLogic();
    }

    /**
     *
     * @param email user email
     * @param password user password
     * @return user type
     */
    public UserType login(String email, String password){
        if((UserLogic.currentUser = userLogic.getAdminByEmailAndPassword(email, password)) != null){
            return UserType.ADMIN;
        }
        if((UserLogic.currentUser = userLogic.getEmployeeByEmailAndPassword(email, password)) != null){
            return UserType.EMPLOYEE;
        }
        if((UserLogic.currentUser = userLogic.getClientByEmailAndPassword(email, password)) != null){
            return UserType.CLIENT;
        }
        return UserType.NO_USER;
    }
}
