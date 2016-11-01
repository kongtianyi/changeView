package src;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*这里如果报错的话，参照http://www.cnblogs.com/Cratical/archive/2011/05/11/2043756.html*/
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;  

public class ChangeView {
	/*定义一些用到的对象*/
	private Image logo; /*logo = =*/
	private Image closeUp; /*关闭窗口按钮*/
	private Image closeDown;  /*关闭窗口按钮按下时*/
	private Image minimizeUp;  /*最小化按钮*/
	private Image minimizeDown;  /*最小哈按钮按下时*/
	private ImageIcon logoIcon;
	private ImageIcon closeUpIcon;
	private ImageIcon closeDownIcon;
	private ImageIcon minimizeUpIcon;
	private ImageIcon minimizeDownIcon;
	private JLabel logoLabel;
	private JLabel closeLabel;
	private JLabel minimizeLabel;
	private JLabel titel;  /*程序名*/
	static Point origin=new Point();  /*用于表示鼠标在窗口上的位置*/
	private Font definedFont = null;  /*接收字体的对象*/
	private AudioStream backMusic;  /*背景音乐*/
	private Image backImg;  /*背景图片*/
	private ImageIcon backImgIcon;
	private JLabel backImgLabel;
	private JPanel backPanel;  /*承载背景图片的panel，用来把图片放置在最下层*/
	
	/**
	 * 根据相对路径加载图片
	 * @param path: 图片的相对路径
	 * @return: 获取到的图片对象
	 */
	public Image getImagePath(String path) {  
        Image image=null;  
        InputStream is = (InputStream) this.getClass().getClassLoader().getResourceAsStream(path);     
        try {
			image=ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return image;  
    } 
	
	
	/**
	 * 根据相对路径加载音频
	 * @param path: 音频文件的相对路径
	 * @return: 获取到的音频对象
	 */
	public AudioStream getAudioPath(String resource){  
        InputStream is = (InputStream) this.getClass().getClassLoader().getResourceAsStream(resource);     
        AudioStream as = null;
		try {
			as = new AudioStream(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return as;  
    } 
	
	/**
	 * 根据相对路径加载字体
	 * @param path: 字体文件的相对路径
	 * @return: 获取到的字体对象
	 */
	public Font getDefinedFont(String path) {  
		if (definedFont == null) {  
            InputStream is = null;  
            BufferedInputStream bis = null;  
            try {  
                is = (InputStream) this.getClass().getClassLoader().getResourceAsStream(path);  
                bis = new BufferedInputStream(is);  
                definedFont = Font.createFont(Font.TRUETYPE_FONT, bis);  
                definedFont = definedFont.deriveFont(25f);  
                definedFont = definedFont.deriveFont(Font.BOLD);
            } catch (FontFormatException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    if (null != bis) {  
                        bis.close();  
                    }  
                    if (null != is) {  
                        is.close();  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return definedFont;  
    } 
	
	/**
	 * 更改Null布局的JFrame的外观 
	 * @param jframe：要更改的JFrame的对象
	 * @param a：logo的横坐标位置
	 * @param b：程序名称的横坐标位置
	 * @param c：最小化按钮的横坐标位置
	 * @param d：关闭按钮的横坐标位置
	 * @param flag：true表示关闭则退出程序，false表示关闭则隐藏程序
	 * @param name：程序名称
	 */
	public ChangeView(final JFrame jframe,int a,int b,int c,int d,final boolean flag,String name) {
		/* 初始化变量 */
		logo = getImagePath("resource/image/logo.png");
		logoIcon = new ImageIcon(logo);
		logoLabel = new JLabel(logoIcon);
		titel = new JLabel(name);
		titel.setFont(getDefinedFont("resource/font/叶根友毛笔特色简体.ttf"));
		closeUp = getImagePath("resource/image/closeUp.png");
		closeUpIcon = new ImageIcon(closeUp);
		closeDown = getImagePath("resource/image/closeDown.png");
		closeDownIcon = new ImageIcon(closeDown);
		closeLabel = new JLabel(closeUpIcon);
		closeLabel.setToolTipText("关闭");
		minimizeUp = getImagePath("resource/image/minimizeUp.png");
		minimizeUpIcon = new ImageIcon(minimizeUp);
		minimizeDown = getImagePath("resource/image/minimizeDown.png");
		minimizeDownIcon = new ImageIcon(minimizeDown);
		minimizeLabel = new JLabel(minimizeUpIcon);
		minimizeLabel.setToolTipText("最小化");
		backMusic = getAudioPath("resource/music/竹苑情歌.au");
		AudioPlayer.player.start(backMusic);  /*播放背景音乐*/
		backImg = getImagePath("resource/image/backgrounding.bmp");
		backImgIcon = new ImageIcon(backImg);
		backImgLabel = new JLabel(backImgIcon);
		backPanel =(JPanel)jframe.getContentPane();
		backPanel.setOpaque(false);
		
		/* 布局资源位置   */
		jframe.setLayout(null);
		/*把背景图片放在最下层*/
		jframe.getLayeredPane().add(backImgLabel, new Integer(Integer.MIN_VALUE));
		backImgLabel.setBounds(0, 0, 640,400);
		jframe.add(logoLabel);
		logoLabel.setBounds(a, 3, 33, 30);
		jframe.add(titel);
		titel.setBounds(b,0,80,30);
		jframe.add(minimizeLabel);
		minimizeLabel.setBounds(c, 0, 33, 30);
		jframe.add(closeLabel);
		closeLabel.setBounds(d, 0, 33, 30);
		
			
		jframe.setUndecorated(true);  /*令边框消失*/
		jframe.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x=e.getX();
				origin.y=e.getY();
			}
		});  /*添加鼠标监听器*/
		jframe.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p=jframe.getLocation();
				jframe.setLocation(p.x+e.getX()-origin.x,p.y+e.getY()-origin.y);
			}
		});  /*使窗体可以由鼠标拖动*/
		
		/* 给关闭按钮添加相应事件 */
		closeLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag==true){
					System.exit(0);
				}else{
					jframe.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				closeLabel.setIcon(closeDownIcon);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				closeLabel.setIcon(closeUpIcon);
			}
			
		});
		
		/* 给最小化按钮添加响应事件 */
		minimizeLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				jframe.setExtendedState(JFrame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				minimizeLabel.setIcon(minimizeDownIcon);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minimizeLabel.setIcon(minimizeUpIcon);
			}
		});

	}
}
