package com.mcelrea.firstservertry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by mcelrea on 2/7/2017.
 */
public class PauseScreen implements Screen {

    private MyGdxGame game;

    public PauseScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        getUserInput();
    }

    private void getUserInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            game.setScreen(MyGdxGame.gameplayScreen);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            game.setScreen(MyGdxGame.startScreen);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
