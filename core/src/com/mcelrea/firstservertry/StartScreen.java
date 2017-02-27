package com.mcelrea.firstservertry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by mcelrea on 2/7/2017.
 */
public class StartScreen implements Screen{

    private MyGdxGame game;

    public StartScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {

    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        getUserInput();
    }

    private void getUserInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(MyGdxGame.gameplayScreen);
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
