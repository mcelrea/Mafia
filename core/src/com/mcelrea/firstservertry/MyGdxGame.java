package com.mcelrea.firstservertry;

import com.badlogic.gdx.Game;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MyGdxGame extends Game {

    public static GameplayScreen gameplayScreen;
    public static PauseScreen pauseScreen;
    public static StartScreen startScreen;

    private Socket socket;

    @Override
    public void create() {
        gameplayScreen = new GameplayScreen(this);
        pauseScreen = new PauseScreen(this);
        startScreen = new StartScreen(this);

        connectSocket();

        setScreen(startScreen);
    }

    private void connectSocket() {
        try{
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        }catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
