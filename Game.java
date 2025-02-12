package Mygame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends JPanel implements ActionListener {

    private Timer timer;
    private Player player;
    private List<Enemy> enemies;
    private List<Bullet> bullets;
	private List<Player> players;
	private List<Item> items;
	private int life = 5;
	
	private Dimension windowSize;
    private JFrame frame;
    private long lastResizeTime;
	private long lastEnemySpawnTime = 0;

    public Game(JFrame frame) {
        this.frame = frame;
		windowSize = new Dimension(500, 500);
		lastResizeTime = System.currentTimeMillis();
		
		setPreferredSize(windowSize);
        setBackground(Color.BLACK);
        player = new Player(400, 300, this);
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
		items = new ArrayList<>();
       

        for (int i = 0; i < 10; i++) {
            enemies.add(new EnemyType3((int)(Math.random()*800), (int)(Math.random()*600), player));
        }
		
		for (int d = 0; d < 5; d++){
			items.add(new Item((int)(Math.random()*800), (int)(Math.random()*600)));
		}

        timer = new Timer(16, this);
        timer.start();

        addKeyListener(new KeyAdapter() { //�������������������m�F
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {//�G��e�̓���
        player.move();
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        for (Bullet bullet : bullets) {
            bullet.move();
        }

        checkBulletCollisions();
		checkPlayerCollisions();
		spawnEnemies();
        bullets.removeIf(bullet -> !bullet.isVisible());//�e��G�A�A�C�e���̃��X�g�폜�Ɏg��
        enemies.removeIf(enemy -> !enemy.isVisible());
		items.removeIf(item -> !item.isVisible());
		removeOffscreenEntities();
		updateWindowSize();
        repaint();
    }

    private void checkBulletCollisions() {
		List<Enemy> newEnemies = new ArrayList<>();
		List<Item> newItems = new ArrayList<>();
        for (Bullet bullet : bullets) {
            Rectangle bulletBounds = bullet.getBounds();//�e�ɏՓˌ��o���\�b�h
            for (Enemy enemy : enemies) {
                Rectangle enemyBounds = enemy.getBounds();//�G�ɏՓˌ��o���\�b�h
                if (bulletBounds.intersects(enemyBounds)) {
                    bullet.setVisible(false);
                    enemy.setVisible(false);
				/*	if(Math.random() < 0.01) {
						newEnemies.add(new EnemyType3((int)(Math.random()*800), (int)(Math.random()*600), player));
					}*/
                	increaseWindowSize(25, 25);
				}
            }
        }
		enemies.addAll(newEnemies);
    }
	 private void checkPlayerCollisions() {
	 	List<Enemy> newEnemies = new ArrayList<>();
		List<Item> newItems = new ArrayList<>();
    	Rectangle playerBounds = player.getBounds();
		
    	for (Enemy enemy : enemies) {
        	Rectangle enemyBounds = enemy.getBounds();
        	if (playerBounds.intersects(enemyBounds)) {
            	// �v���C���[�ƓG���Փ˂����ꍇ�̏���
            	System.out.println("Player hit by enemy!");
				life = life - 1;
				System.out.println(life);
				enemy.setVisible(false);
				//newEnemies.add(new EnemyType3((int)(Math.random()*windowSize.width), (int)(Math.random()*windowSize.height), player));
				decreaseWindowSize(200,200);
				if(life < 0){
					enemy.setVisible(false);
					player.setVisible(false);
					timer.stop();
					System.out.println("Game Over");
				}
			
        	}
    	}
		for (Item item : items){
			Rectangle itemBounds = item.getBounds();
			if (playerBounds.intersects(itemBounds)) {
            	// �v���C���[�ƃA�C�e�����Փ˂����ꍇ�̏���
            	System.out.println("Player hit by item!");
				item.setVisible(false);
				newItems.add(new Item((int)(Math.random()*windowSize.width), (int)(Math.random()*windowSize.height)));
				
				increaseWindowSize(50,50);
				
        	}
		}
		
		enemies.removeIf(enemy -> !enemy.isVisible());
        items.removeIf(item -> !item.isVisible());
		enemies.addAll(newEnemies);
		items.addAll(newItems);
	}
	
	private void updateWindowSize() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResizeTime > 1000) { // 1�b���ƂɃE�B���h�E�������k��
            windowSize.width -= 2;
            windowSize.height -= 2;
            lastResizeTime = currentTime;
            if (windowSize.width < 200 || windowSize.height < 200) {
                System.out.println("Window size too small, Game Over");
                timer.stop();
            }
			if (windowSize.width > 1200 || windowSize.height > 1200) {
                System.out.println("Window size too big, Game Clear");
                timer.stop();
            }
        }
        frame.setSize(windowSize);//�E�B���h�E�̓�����
        frame.setLocation(player.getX() - windowSize.width / 4, player.getY() - windowSize.height / 4 );
    }
	private void spawnEnemies() {
    
        if (Math.random() < 0.005) {
            for (int i = 0; i < 5; i++) {
                enemies.add(new EnemyType1(player.getX() - 250, player.getY() + i * 50));
            }
        }
        if (Math.random() < 0.005) {
            for (int i = 0; i < 5; i++) {
                enemies.add(new EnemyType2(player.getX() + i * 50, player.getY() - 150));
            }
        }
        if (Math.random() < 0.01) {
            for (int i = 0; i < 3; i++) {
                enemies.add(new EnemyType3((int) (Math.random() * windowSize.width), (int) (Math.random() * windowSize.height), player));
            }
   	 	}
		}
	
	private void increaseWindowSize(int width, int height) { //�E�B���h�E���傫���Ȃ郁�\�b�h
        windowSize.width += width;
        windowSize.height += height;
        frame.setSize(windowSize);
    }
	
	private void decreaseWindowSize(int width, int height){ //�E�B���h�E���������Ȃ郁�\�b�h
		windowSize.width -= width;
        windowSize.height -= height;
        frame.setSize(windowSize);
	}
	private void removeOffscreenEntities() {
    	int buffer = 1; // �����o�b�t�@���������č폜

    	bullets.removeIf(bullet -> bullet.getX() < -buffer || bullet.getX() > windowSize.width + buffer || bullet.getY() < -buffer || bullet.getY() > windowSize.height + buffer);
   	 	enemies.removeIf(enemy -> enemy.getX() < -buffer || enemy.getX() > windowSize.width + buffer || enemy.getY() < -buffer || enemy.getY() > windowSize.height + buffer);
	}

	@Override
	protected void paintComponent(Graphics g) { //�G��e�A�v���C���[�̕`�敔��
    	super.paintComponent(g);
    	BufferedImage offscreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g2d = offscreen.createGraphics();

    	player.draw(g2d);
    	for (Enemy enemy : enemies) {
        	if (enemy.isVisible()) {
            	enemy.draw(g2d);
        	}
    	}
    	for (Bullet bullet : bullets) {
        	if (bullet.isVisible()) {
            	bullet.draw(g2d);
        	}
    	}
    	for (Item item : items) {
        	if (item.isVisible()) {
            	item.draw(g2d);
        	}
    	}

    	// �E�B���h�E�T�C�Y�̕\��
    	g2d.setColor(Color.WHITE);
		g2d.drawString("yourlife: " + life,10, 60 );
    	g2d.drawString("Width: " + windowSize.width, 10, 20); // ����
    	g2d.drawString("Height: " + windowSize.height, 10, 40); // ����
		g2d.drawString("yourlife: " + life, getWidth() -100, 60 );
    	g2d.drawString("Width: " + windowSize.width, getWidth() - 100, 20); // �E��
    	g2d.drawString("Height: " + windowSize.height, getWidth() - 100, 40); // �E��
		g2d.drawString("yourlife: " + life, 10, getHeight() - 10 );
    	g2d.drawString("Width: " + windowSize.width, 10, getHeight() - 30); // ����
    	g2d.drawString("Height: " + windowSize.height, 10, getHeight() - 50); // ����
		g2d.drawString("yourlife: " + life,  getWidth() - 100, getHeight() - 10 );
    	g2d.drawString("Width: " + windowSize.width, getWidth() - 100, getHeight() - 30); // �E��
    	g2d.drawString("Height: " + windowSize.height, getWidth() - 100, getHeight() - 50); // �E��

    	g.drawImage(offscreen, 0, 0, this);
	}

    public void addBullet(Bullet bullet) { //�e�̒ǉ�
        bullets.add(bullet);
    }

    public static void main(String[] args) { //���s����
        JFrame frame = new JFrame("My game");
        Game game = new Game(frame);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
