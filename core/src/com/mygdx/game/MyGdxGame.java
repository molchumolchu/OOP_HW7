package com.mygdx.game;

import static com.mygdx.game.oopSrc.Task_1.Main.gameOverBlack;
import static com.mygdx.game.oopSrc.Task_1.Main.gameOverWhite;
import static com.mygdx.game.oopSrc.Task_1.Main.generateCommand;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.oopSrc.Task_1.Hero;
import com.mygdx.game.oopSrc.Task_1.Main;
import com.mygdx.game.oopSrc.Task_1.Vector2;
import com.mygdx.game.oopSrc.Task_1.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture crossBowMan, monk, peasant, pikeman, rouge, sniper, wizard;

	Random rand=new Random();
	ArrayList<Hero> heroesWhite;
	ArrayList<Hero> heroesBlack;
	ArrayList<Hero> heroOrder;


	
	@Override
	public void create () {
		heroesWhite = new ArrayList<>();
		heroesBlack=new ArrayList<>();
		heroOrder=new ArrayList<>();
		batch=new SpriteBatch();

		heroesWhite = generateCommand(0,1);
		heroesBlack = generateCommand(3,10);
		heroOrder.addAll(heroesBlack);
		heroOrder.addAll(heroesWhite);
		heroOrder.sort(((o1, o2) -> o2.getInitiative()- o1.getInitiative())); //сортировка лямбдой




//		batch = new SpriteBatch();
		this.crossBowMan = new Texture("units/Crossbower.png");
		this.monk = new Texture("units/Monk.png");
		this.peasant = new Texture("units/Peasant.png");
		this.pikeman = new Texture("units/Pikeman.png");
		this.rouge = new Texture("units/Rouge.png");
		this.sniper = new Texture("units/Sniper.png");
		this.wizard = new Texture("units/Wizard.png");
//		Main.main(null);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
//		batch.draw(sniper, 0, 0);
		heroOrder.forEach(n->{
			Vector2 pos = new Vector2(n.getCoords()[1], n.getCoords()[0]);
			pos.posX *= sniper.getWidth();
			pos.posY*= (double) Gdx.graphics.getHeight() /10-10;
			switch (n.getType().charAt(0)){
				case 's':
					batch.draw(sniper, pos.posX, pos.posY);
					break;
				case 'p':
					batch.draw(pikeman, pos.posX, pos.posY);
					break;
				case 'c':
					batch.draw(crossBowMan, pos.posX, pos.posY);
					break;
				case 'm':
					batch.draw(monk, pos.posX, pos.posY);
					break;
				case 'w':
					batch.draw(wizard, pos.posX, pos.posY);
					break;
				case 'a':
					batch.draw(peasant, pos.posX, pos.posY);
					break;
				case 'r':
					batch.draw(rouge, pos.posX, pos.posY);
					break;
			}

		});
		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			boolean flag;
			while (true){
				View.view();
				if (gameOverBlack()){
					flag = true;
					break;
				}
				if (gameOverWhite()){
					flag = false;
					break;
				}
				for (Hero h: heroOrder) {
					if (heroesBlack.contains(h)){
						h.gameStep(heroesWhite, heroesBlack);
					} else {
						h.gameStep(heroesBlack, heroesWhite);
					}
				}
				Scanner scanner = new Scanner(System.in);
				scanner.nextLine();
			}
			if (flag){
				System.out.println("Победила команда белых");
			} else {
				System.out.println("Победила команда черных");
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.peasant.dispose();
		this.pikeman.dispose();
		this.crossBowMan.dispose();
		this.monk.dispose();
		this.wizard.dispose();
		this.rouge.dispose();
		this.sniper.dispose();
	}
}
