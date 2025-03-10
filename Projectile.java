import java.awt.*;

public class Projectile {
    private int x, y;
    private int speed = 5;  // 球の速度

    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= speed;  // 球を上方向に移動させる
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);  // 球を描画する
    }

    public int getY() {
        return y;
    }
}
