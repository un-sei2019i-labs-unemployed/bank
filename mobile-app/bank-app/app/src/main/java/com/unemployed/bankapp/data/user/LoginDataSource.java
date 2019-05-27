package com.unemployed.bankapp.data.user;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(int psid, int password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser = new LoggedInUser(psid,password);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
