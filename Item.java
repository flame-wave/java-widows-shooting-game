package Mygame;
import java.awt.*;

public class Item {
    private int x, y;
	private boolean visible;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
		visible = true;
    }

    public void draw(Graphics g) {
		if(visible){
        	g.setColor(Color.GREEN);
        	g.fillRect(x, y, 20, 20);
		}
	}
	public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {//�Փ˔��胁�\�b�h
        return new Rectangle(x, y, 20, 20);
    }

    public void setVisible(boolean visible) {//���X�g����폜
        this.visible = visible;
    }
}
