package com.mcelrea.firstservertry;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    private Role role;
    private String username;
    private boolean alive = true;

    public Player(String username) {
        this.username = username;
    }

    public void assignRole(Role role) {
        this.role = role;
    }

    public void draw(SpriteBatch batch) {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return "Player{" +
                "role=" + role +
                ", username='" + username + '\'' +
                ", alive=" + alive +
                '}';
    }
}
