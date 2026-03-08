package io.github.Zephyrdoestech; // Keep your package name!

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture characterImage;
    private MapCharacter player;

    // Movement speed in Pixels-Per-Second
    private final float SPEED = 300f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        characterImage = createSquareTexture(64, Color.CYAN);

        // Start the player at X: 100, Y: 100 on the screen
        player = new MapCharacter(100f, 100f);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Get the time passed since the last frame (usually ~0.016 seconds)
        float delta = Gdx.graphics.getDeltaTime();

        // Handle input for movement
        handleInput(delta);

        // Draw the Character directly at their exact X and Y coordinates
        batch.begin();
        batch.draw(characterImage, player.getX(), player.getY());
        batch.end();
    }

    private void handleInput(float delta) {
        // Calculate exactly how many pixels to move this frame
        float moveAmount = SPEED * delta;

        // Notice we changed "else if" to just "if".
        // This allows you to press UP and RIGHT at the same time to move Diagonally!
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.up(moveAmount);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.down(moveAmount);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.left(moveAmount);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.right(moveAmount);
        }
    }

    // Helper to generate a placeholder image
    private Texture createSquareTexture(int size, Color color) {
        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture tex = new Texture(pixmap);
        pixmap.dispose();
        return tex;
    }

    @Override
    public void dispose() {
        batch.dispose();
        characterImage.dispose();
    }
}
