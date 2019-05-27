package com.unemployed.bankapp.logic.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Patterns;

import com.unemployed.bankapp.data.user.LoginRepository;
import com.unemployed.bankapp.data.user.Result;
import com.unemployed.bankapp.data.user.LoggedInUser;
import com.unemployed.bankapp.R;
import com.unemployed.bankapp.data.user.user;
import com.unemployed.bankapp.logic.users.LoggedInUserView;
import com.unemployed.bankapp.logic.users.LoginFormState;
import com.unemployed.bankapp.logic.users.LoginResult;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    private user us;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        int psid = Integer.parseInt(username);
        int pass = Integer.parseInt(password);
        Result<LoggedInUser> result = loginRepository.login(psid, pass);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            if(!us.getNombre().isEmpty()){
                if(us.getCedula() == data.getUserId()){
                    if(us.getPassword() == data.getPassword()){
                        loginResult.setValue(new LoginResult(new LoggedInUserView(us.getNombre(),String.valueOf(us.getCedula()))));
                    }else{
                        loginResult.setValue(new LoginResult(R.string.incorrect_password));
                    }
                }else{
                    loginResult.setValue(new LoginResult(R.string.invalid_username));
                }
            }else {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }

        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }


}
