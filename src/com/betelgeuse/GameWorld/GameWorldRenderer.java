package com.betelgeuse.GameWorld;

import java.util.ArrayList;
import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.betelgeuse.GameHelpers.AssetLoader;
import com.betelgeuse.GameHelpers.InputHandler;
import com.betelgeuse.GameHelpers.ScrollHandler;
import com.betelgeuse.GameObjects.Bar;
import com.betelgeuse.GameObjects.Cat;
import com.betelgeuse.GameObjects.Grass;
import com.betelgeuse.TweenAccessors.Value;
import com.betelgeuse.TweenAccessors.ValueAccessor;
import com.betelgeuse.ui.SimpleButton;

public class GameWorldRenderer {

	public OrthographicCamera camera;
	protected ShapeRenderer shapeRenderer;
	protected SpriteBatch batcher, batcher2;
	protected int gameHeight;
	protected int midPointY;
	protected static int BG;
	protected static int CAT;
	protected static int BAR;
	protected Vector2 scorePostion;
	protected GameWorldUpdate world;
	protected Cat nyanCat;
	protected ScrollHandler scroller;
	protected Grass frontGrass, backGrass;
	protected Bar bar1, bar2, bar3;
	protected int opacity, hitOpacity;

	protected TextureRegion[] bg;
	protected TextureRegion grass;
	protected TextureRegion logo;
	protected TextureRegion localScoreBoard;
	protected TextureRegion[] numberlocalScoreBoard;
	protected static TextureRegion catMid;
	protected Animation sparklingAnimation;
	protected Animation[] catAnimation;
	protected TextureRegion barUp[], barDown[];
	protected TextureRegion[] numberGame;
	protected TextureRegion[] numberScoreBoard;
	protected TextureRegion tap;
	protected TextureRegion prepareNotice;
	protected TextureRegion copyRight;
	protected boolean logoCopyRight;
	protected TextureRegion scoreBoard;
	protected TextureRegion hit;
	protected TextureRegion[] Trophy;
	protected int sparklingX;
	protected int sparklingY;
	protected Random rand;

	// Tween stuff
	private TweenManager manager;
	private static Value alpha = new Value();
	protected ArrayList<SimpleButton> menuButtons;
	private int n;

	public GameWorldRenderer(GameWorldUpdate world, int gameWidth,
			int gameHeight, int midPointY, int BG, int CAT, int BAR) {

		this.gameHeight = gameHeight;
		this.midPointY = midPointY;
		this.world = world;
		GameWorldRenderer.BG = BG;
		GameWorldRenderer.CAT = CAT;
		GameWorldRenderer.BAR = BAR;
		BAR = new Random().nextInt(2);
		scorePostion = new Vector2(131.00f, 87.00f);
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 288, 512);
		logoCopyRight = true;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();
		batcher = new SpriteBatch();
		batcher2 = new SpriteBatch();
		batcher.setProjectionMatrix(camera.combined);
		batcher2.setProjectionMatrix(camera.combined);
		opacity = -1;
		shapeRenderer = new ShapeRenderer();
		hitOpacity = 45;
		rand = new Random();
		sparklingX = rand.nextInt(190 - 96) + 96;
		sparklingY = rand.nextInt(330 - 253) + 253;
		shapeRenderer.setProjectionMatrix(camera.combined);
		initGameObjects();
		initAssets();
		setupTweens();
	}

	private void initGameObjects() {
		nyanCat = world.getNyanCat();
		scroller = world.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		bar1 = scroller.getBar1();
		bar2 = scroller.getBar2();
		bar3 = scroller.getBar3();
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		catAnimation = AssetLoader.catAnimation;
		if (CAT == 0) {
			catMid = AssetLoader.cat1[1];
		} else if (CAT == 1) {
			catMid = AssetLoader.cat2[1];
		} else if (CAT == 2) {
			catMid = AssetLoader.cat3[1];
		}
		barUp = AssetLoader.barUp;
		barDown = AssetLoader.barDown;
		numberGame = AssetLoader.numberGame;
		tap = AssetLoader.tap;
		prepareNotice = AssetLoader.prepareNotice;
		copyRight = AssetLoader.copyRight;
		logo = AssetLoader.logo;
		scoreBoard = AssetLoader.scoreBoard;
		hit = AssetLoader.hit;
		numberScoreBoard = AssetLoader.numberScoreBoard;
		Trophy = new TextureRegion[4];
		Trophy = AssetLoader.Trophy;
		sparklingAnimation = AssetLoader.sparklingAnimation;
		localScoreBoard = AssetLoader.LocalScoreBoard;
		numberlocalScoreBoard = AssetLoader.numberLocalScoreBoard;
	}

	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
				.start(manager);
	}

	private void setupTweensReady() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager.killAll();
		Tween.to(alpha, -1, .3f).target(1).ease(TweenEquations.easeInOutQuad)
				.repeatYoyo(1, .5f).start(manager);
	}

	public void render(float delta, float renderTime) {
		// System.out.println("GameRenderer - render");
		scorePostion.x = 131 + ((world.getScore().toString().length() - 1) * 11);
		batcher.begin();
		batcher.draw(bg[BG], 0, 0, 288, 512);
		batcher.enableBlending();
		drawBar();

		drawCat(renderTime);

		drawGrass();
		if ((world.isReady() || world.isDead() || world.isPlaying())
				&& opacity > -1) {
			drawScore();
		}
		if (world.isMenu() || world.isGameOver()) {
			for (SimpleButton button : menuButtons) {
				button.draw(batcher);
			}
		}
		if ((world.isMenu() || logoCopyRight) && alpha.getValue() < 1) {
			drawMenu();
		}
		if (world.isShowGO() || world.isGameOver() || world.isShowSB()
				|| alpha.getValue() < 1) {
			world.getGameOver().draw(batcher);
		}
		if ((world.isShowSB() || world.isGameOver()) || alpha.getValue() < 1) {
			world.getScoreBoard().draw(batcher);
			int n = 0;
			// System.out.println(world.getScore().toString());
			for (int i = world.getScoreBoard().getScore().toString().length() - 1; i >= 0; i--) {
				batcher.draw(numberScoreBoard[(int) world.getScoreBoard()
						.getScore().toString().charAt(i)
						- (int) '0'], world.getScoreBoard().getScorePositionX()
						- (n * 12), world.getScoreBoard().getScorePositionY(),
						12, 14);
				n++;
			}
			n = 0;
			for (int i = Integer.toString(
					AssetLoader.getHighScore("highscore1")).length() - 1; i >= 0; i--) {
				batcher.draw(
						numberScoreBoard[(int) Integer.toString(
								AssetLoader.getHighScore("highscore1")).charAt(
								i)
								- (int) '0'], world.getScoreBoard()
								.getHighScorePositionX() - (n * 12), world
								.getScoreBoard().getHighScorePositionY(), 12,
						14);
				n++;
			}
		}
		if (world.isShowTrophy() && alpha.getValue() < 1) {
			batcher.draw(Trophy[world.getScoreBoard().getTrophy()], 96, 252,
					95, 78);
			batcher.draw(sparklingAnimation.getKeyFrame(renderTime),
					sparklingX, sparklingY);
			if (sparklingAnimation.getKeyFrameIndex(renderTime) == 7) {
				sparklingX = rand.nextInt(190 - 96) + 96;
				sparklingY = rand.nextInt(330 - 253) + 253;
			}
		}
		world.getLocalScoreBoard().draw(batcher);
		if (world.getLocalScoreBoard().isTrophy_1()) {
			batcher.draw(
					Trophy[world.getLocalScoreBoard().getTrophy_1_Color()],
					world.getLocalScoreBoard().getCup_1X(), world
							.getLocalScoreBoard().getCup_1Y());
		}
		if (world.getLocalScoreBoard().isTrophy_2()) {
			batcher.draw(
					Trophy[world.getLocalScoreBoard().getTrophy_2_Color()],
					world.getLocalScoreBoard().getCup_2X(), world
							.getLocalScoreBoard().getCup_2Y());
		}
		if (world.getLocalScoreBoard().isTrophy_3()) {
			batcher.draw(
					Trophy[world.getLocalScoreBoard().getTrophy_3_Color()],
					world.getLocalScoreBoard().getCup_3X(), world
							.getLocalScoreBoard().getCup_3Y());
		}
		n = 0;
		for (int i = 0; i < world.getLocalScoreBoard().getScore1().toString()
				.length(); i++) {
			batcher.draw(numberlocalScoreBoard[(int) world.getLocalScoreBoard()
					.getScore1().toString().charAt(i)
					- (int) '0'], world.getLocalScoreBoard().getScore1X() + n
					* 12, world.getLocalScoreBoard().getScore1Y());
			n++;
		}
		n = 0;
		for (int i = 0; i < world.getLocalScoreBoard().getScore2().toString()
				.length(); i++) {
			batcher.draw(numberlocalScoreBoard[(int) world.getLocalScoreBoard()
					.getScore2().toString().charAt(i)
					- (int) '0'], world.getLocalScoreBoard().getScore2X() + n
					* 12, world.getLocalScoreBoard().getScore2Y());
			n++;

		}
		n = 0;
		for (int i = 0; i < world.getLocalScoreBoard().getScore3().toString()
				.length(); i++) {
			batcher.draw(numberlocalScoreBoard[(int) world.getLocalScoreBoard()
					.getScore3().toString().charAt(i)
					- (int) '0'], world.getLocalScoreBoard().getScore3X() + n
					* 12, world.getLocalScoreBoard().getScore3Y());
			n++;

		}

		batcher.end();
		if ((world.isReady() || world.isPlaying()) && opacity > -1) {
			batcher2.begin();
			batcher2.enableBlending();
			batcher2.setColor(1, 1, 1,
					(float) Math.cos(Math.toRadians(opacity)));
			batcher2.draw(prepareNotice, 94, 145, 102, 67);
			batcher2.draw(tap, 85, 217, 114, 100);
			batcher2.end();
		}
		if (world.isHit()) {
			if (hitOpacity <= 90) {
				Gdx.gl.glEnable(GL10.GL_BLEND);
				Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA,
						GL10.GL_ONE_MINUS_SRC_ALPHA);
				shapeRenderer.begin(ShapeType.Filled);
				shapeRenderer.setColor(1, 1, 1,
						(float) Math.cos(Math.toRadians(hitOpacity)));
				shapeRenderer.rect(0, 0, 288, 512);
				shapeRenderer.end();
				hitOpacity += 5;
			}
		}
		if (world.isPlaying()) {
			if (opacity < 90) {
				opacity += 3;
				// System.out.println("      " + opacity);
			}

		}

		if (world.isToReady()) {
			world.setToReady(false);
			setupTweensReady();
			alpha.setValue(0);
		}
		if (world.isReady() && alpha.getValue() == 1) {
			opacity = 0;
			hitOpacity = 45;
			logoCopyRight = false;
			onRestart();
			world.restart();
			// System.out.println(alpha.getValue());
		}
		if (world.isGameOver()) {
			opacity = -1;
		}
		drawTransition(delta);

	}

	private void drawMenu() {
		batcher.draw(logo, 32, 109, 224, 50);
		batcher.draw(copyRight, 42, 420, 203, 24);
	}

	private void drawCat(float renderTime) {
		if (nyanCat.shouldntFlap()) {
			batcher.draw(catMid, nyanCat.getX(), nyanCat.getY(),
					nyanCat.getWidth() / 2.0f - 1,
					nyanCat.getHeight() / 2.0f - 1, nyanCat.getWidth(),
					nyanCat.getHeight(), 1, 1, nyanCat.getRotation());

		} else {
			batcher.draw(catAnimation[CAT].getKeyFrame(renderTime),
					nyanCat.getX(), nyanCat.getY(), nyanCat.getWidth() / 2.0f,
					nyanCat.getHeight() / 2.0f, nyanCat.getWidth(),
					nyanCat.getHeight(), 1, 1, nyanCat.getRotation());
		}

	}

	private void drawTransition(float delta) {
		if (alpha.getValue() >= 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(0, 0, 0, alpha.getValue());
			shapeRenderer.rect(0, 0, 288, 512);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);
		}
	}

	private void drawScore() {
		int n = 0;
		// System.out.println(world.getScore().toString());
		for (int i = world.getScore().toString().length() - 1; i >= 0; i--) {
			batcher.draw(numberGame[(int) world.getScore().toString().charAt(i)
					- (int) '0'], scorePostion.x - (n * 22), scorePostion.y,
					24, 40);
			n++;
		}
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(),
				frontGrass.getWidth(), frontGrass.getHeight());
		batcher.draw(grass, backGrass.getX(), backGrass.getY(),
				backGrass.getWidth(), backGrass.getHeight());
	}

	private void drawBar() {
		// System.out.println(BAR);
		batcher.draw(barDown[BAR], bar1.getX(), bar1.getY(), bar1.getWidth(),
				bar1.getHeight());
		batcher.draw(barUp[BAR], bar1.getX(),
				bar1.getY() + 100 + bar1.getHeight(), bar1.getWidth(),
				bar1.getHeight());

		batcher.draw(barDown[BAR], bar2.getX(), bar2.getY(), bar2.getWidth(),
				bar2.getHeight());
		batcher.draw(barUp[BAR], bar2.getX(),
				bar2.getY() + 100 + bar1.getHeight(), bar2.getWidth(),
				bar2.getHeight());

		batcher.draw(barDown[BAR], bar3.getX(), bar3.getY(), bar3.getWidth(),
				bar3.getHeight());
		batcher.draw(barUp[BAR], bar3.getX(),
				bar3.getY() + 100 + bar1.getHeight(), bar3.getWidth(),
				bar3.getHeight());
	}

	private void onRestart() {
		sparklingX = rand.nextInt(190 - 96) + 96;
		sparklingY = rand.nextInt(330 - 253) + 253;
		world.restart();
		BG = new Random().nextInt(2);
		CAT = new Random().nextInt(3);
		BAR = new Random().nextInt(2);
		if (CAT == 0) {
			catMid = AssetLoader.cat1[1];
		} else if (CAT == 1) {
			catMid = AssetLoader.cat2[1];
		} else if (CAT == 2) {
			catMid = AssetLoader.cat3[1];
		}
	}

	/**
	 * @return the alpha
	 */
	public static Value getAlpha() {
		return alpha;
	}
}
