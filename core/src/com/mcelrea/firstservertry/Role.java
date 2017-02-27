package com.mcelrea.firstservertry;

public class Role {
    public static final int VILLAGER=1, DOCTOR=2, SHERIFF=3, MAFIA=4;
    private int myRole;

    public Role(int role) {
        myRole = role;
    }

    @Override
    public String toString() {
        String out = "";
        if (myRole == VILLAGER) {
            out = "VILLAGER";
        }
        else if (myRole == MAFIA) {
            out = "MAFIA";
        }
        else if (myRole == DOCTOR) {
            out = "DOCTOR";
        }
        else if (myRole == SHERIFF) {
            out = "SHERIFF";
        }
        return "Role{" +
                "myRole=" + out +
                '}';
    }

    public boolean equals(Role otherRole) {
        return myRole == otherRole.myRole;
    }

    public boolean equals(int otherRole) {
        return myRole == otherRole;
    }
}
