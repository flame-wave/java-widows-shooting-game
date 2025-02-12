import java.awt.*;

public class Projectile {
    private int x, y;
    private int speed = 5;  // ���̑��x

    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        y -= speed;  // ����������Ɉړ�������
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);  // ����`�悷��
    }

    public int getY() {
        return y;
    }
}
