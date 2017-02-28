package com.mcelrea.firstservertry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.JOptionPane;

/**
 * Created by mcelrea on 2/7/2017.
 */
public class GameplayScreen implements Screen{

    private MyGdxGame game;
    Array<Player> otherPlayers;
    Player player;
    BitmapFont font;
    SpriteBatch batch; //draws graphics
    private static final float WORLD_WIDTH = 800;
    private static final float WORLD_HEIGHT = 600;
    private ShapeRenderer shapeRenderer; //draw shapes
    private Camera camera; //the players view of the world
    private Viewport viewport; //control the view of the world
    private static final int MAFIA=1, SHERIFF=2, DOCTOR=3, DAY=4;
    private int gameState = MAFIA;
    private Player lastKill;

    public GameplayScreen(MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    private void assignRoles() {
        //assign mafia
        int mafiaAssigned = 0;
        while(mafiaAssigned != 2) {
            int choice = (int) (Math.random() * otherPlayers.size+1);
            if(choice == otherPlayers.size) {
                if(player.getRole() == null) {
                    player.assignRole(new Role(Role.MAFIA));
                    mafiaAssigned++;
                }
            }
            else if(otherPlayers.get(choice).getRole() == null) {
                otherPlayers.get(choice).assignRole(new Role(Role.MAFIA));
                mafiaAssigned++;
            }
        }

        //assign sheriff
        int sheriffAssigned = 0;
        while(sheriffAssigned != 1) {
            int choice = (int) (Math.random() * otherPlayers.size+1);
            if(choice == otherPlayers.size) {
                if(player.getRole() == null) {
                    player.assignRole(new Role(Role.SHERIFF));
                    sheriffAssigned++;
                }
            }
            else if(otherPlayers.get(choice).getRole() == null) {
                otherPlayers.get(choice).assignRole(new Role(Role.SHERIFF));
                sheriffAssigned++;
            }
        }

        //assign doctor
        int doctorAssigned = 0;
        while(doctorAssigned != 1) {
            int choice = (int) (Math.random() * otherPlayers.size+1);
            if(choice == otherPlayers.size) {
                if(player.getRole() == null) {
                    player.assignRole(new Role(Role.DOCTOR));
                    doctorAssigned++;
                }
            }
            else if(otherPlayers.get(choice).getRole() == null) {
                otherPlayers.get(choice).assignRole(new Role(Role.DOCTOR));
                doctorAssigned++;
            }
        }

        //everyone else should be a villager
        for(int i=0; i < otherPlayers.size; i++) {
            if(otherPlayers.get(i).getRole() == null) {
                otherPlayers.get(i).assignRole(new Role(Role.VILLAGER));
            }
        }
        if(player.getRole() == null) {
            player.assignRole(new Role(Role.VILLAGER));
        }

        //print out and see
        for(int i=0; i < otherPlayers.size; i++) {
            System.out.println(otherPlayers.get(i).getUsername() + " " + otherPlayers.get(i).getRole());
        }
        System.out.println(player.getUsername() + " " + player.getRole());
    }

    @Override
    public void show() {
        otherPlayers = new Array<Player>();
        otherPlayers.add(new Player("Riley"));
        otherPlayers.add(new Player("Thomas"));
        otherPlayers.add(new Player("Portillo"));
        otherPlayers.add(new Player("Rojas"));
        otherPlayers.add(new Player("Max"));
        otherPlayers.add(new Player("Christian"));
        otherPlayers.add(new Player("Cyrus"));
        otherPlayers.add(new Player("Vicente"));
        otherPlayers.add(new Player("Raymond"));
        player = new Player("McElrea");
        assignRoles();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        getUserInput();
        gameAI();

        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        batch.begin();
        if(player.isAlive()) {
            font.setColor(Color.GREEN);
        }
        else {
            font.setColor(Color.RED);
        }
        font.draw(batch, player.getUsername(),400,150);
        font.draw(batch, player.getRole().toString(),400,100);
        for(int i=0; i < otherPlayers.size; i++) {
            if(otherPlayers.get(i).isAlive()) {
                font.setColor(Color.GREEN);
            }
            else {
                font.setColor(Color.RED);
            }
            font.draw(batch, otherPlayers.get(i).getUsername(),700,550-(i*(550/otherPlayers.size)));
        }
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        shapeRenderer.begin();
        shapeRenderer.end();
    }

    private void gameAI() {
        if(gameState == MAFIA) {
            mafiaAI();
        }
        else if(gameState == SHERIFF) {
            sheriffAI();
        }
        else if(gameState == DOCTOR) {
            doctorAI();
        }
        else if(gameState == DAY) {
            dayAI();
        }
    }

    private void dayAI() {

    }

    //portillo
    private void doctorAI() {

    }

    //thomas
    private void sheriffAI() {
        if (player.getRole().equals(Role.SHERIFF)) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog(null, "choose one player (1- " + otherPlayers.size + ")"));
            if (otherPlayers.get(choice - 1).getRole().equals(Role.MAFIA)) {
                JOptionPane.showMessageDialog(null, "True");
            } else
                JOptionPane.showMessageDialog(null, "False;");

            gameState = DOCTOR;
        }
        gameState = DOCTOR;
    }

    //mcelrea
    private void mafiaAI() {
        Array<Player> mafiaosos = getMafia();
        System.out.println("Mafia size: " + mafiaosos.size);
        for(int i=0; i < mafiaosos.size; i++) {
            System.out.println(mafiaosos.get(i));
        }

        boolean done = false;
        while(!done) {
            int choice = (int) (Math.random() * (otherPlayers.size+1));
            if(choice == otherPlayers.size && !player.getRole().equals(Role.MAFIA)) {
                System.out.println(player + " TARGETED");
                player.setAlive(false);
                lastKill = player;
                done = true;
            }
            else {
                Player p = otherPlayers.get(choice);
                if(!p.getRole().equals(Role.MAFIA)) {
                    System.out.println(p + " TARGETED");
                    p.setAlive(false);
                    lastKill = p;
                    done=true;
                }
            }
        }

        gameState = SHERIFF;
    }

    private Array<Player> getMafia() {
        Array<Player> mafiasos = new Array<Player>();
        for(int i=0; i < otherPlayers.size; i++) {
            if(otherPlayers.get(i).getRole().equals(Role.MAFIA) && otherPlayers.get(i).isAlive()) {
                mafiasos.add(otherPlayers.get(i));
            }
        }
        if(player.getRole().equals(Role.MAFIA) && player.isAlive()) {
            mafiasos.add(player);
        }
        return mafiasos;
    }

    private void getUserInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            game.setScreen(MyGdxGame.pauseScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
