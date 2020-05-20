package com.dune.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DuneGame extends ApplicationAdapter {
	private class Tank {
		private Vector2 position;
		private Texture texture;
		private float angle;
		private float speed;

		public Tank(float x, float y) {
			this.position = new Vector2(x, y);
			this.texture = new Texture("tank.png");
			this.speed = 200.0f;
		}

		public void update(float dt) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				angle += 180.0f * dt;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				angle -= 180.0f * dt;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				position.x += speed * MathUtils.cosDeg(angle) * dt;
				position.y += speed * MathUtils.sinDeg(angle) * dt;
			}
		}

		public void render(SpriteBatch batch) {
			batch.draw(texture, position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle, 0, 0, 80, 80, false, false);
		}

		public void dispose() {
			texture.dispose();
		}
	}

	private class Circle {
		private Vector2 position;
		private Texture texture;

		public Circle(float x, float y) {
			this.position = new Vector2(x, y);
			this.texture = new Texture("circle.png");
		}

	public void update(){
		if(Math.abs(tank.position.x - circle.position.x) < 10){
			circle.position.x = (float) (60 + Math.random() * 1000);
		}
		if(Math.abs(tank.position.y - circle.position.y) < 5){
			circle.position.x = (float) (60 + Math.random() * 700);
		}
	}

		public void render(SpriteBatch batch) {
			batch.draw(texture, position.x - 60, position.y - 60, 60, 60, 120, 120, 1, 1, 0, 0, 0, 120, 120, false, false);
		}

		public void dispose() {
			texture.dispose();
		}
	}

	private SpriteBatch batch;
	private Tank tank;
	private Circle circle;

	@Override
	public void create() {
		batch = new SpriteBatch();
		tank = new Tank(200, 200);
		circle = new Circle(1000, 600);
	}

	@Override
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0.4f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		tank.render(batch);
		circle.render(batch);
		batch.end();
	}

	public void update(float dt) {
		tank.update(dt);
		circle.update();
	}

	@Override
	public void dispose() {
		batch.dispose();
		tank.dispose();
		circle.dispose();
	}

	// Домашнее задание:
	// - Задать координаты точки, и нарисовать в ней круг (любой круг, радиусом пикселей 50)
	// - Если "танк" подъедет вплотную к кругу, то круг должен переместиться в случайную точку
	// - * Нельзя давать танку заезжать за экран
}