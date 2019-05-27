package com.unemployed.bankapp.logic.users;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private String psid;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String personalid) {

        this.displayName = displayName;
        this.psid = personalid;
    }

    String getDisplayName() {
        return displayName;
    }

    String getPersonalid() { return psid; }
}
