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

/*�����������Ļ�������http://www.cnblogs.com/Cratical/archive/2011/05/11/2043756.html*/
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;  

public class ChangeView {
	/*����һЩ�õ��Ķ���*/
	private Image logo; /*logo = =*/
	private Image closeUp; /*�رմ��ڰ�ť*/
	private Image closeDown;  /*�رմ��ڰ�ť����ʱ*/
	private Image minimizeUp;  /*��С����ť*/
	private Image minimizeDown;  /*��С����ť����ʱ*/
	private ImageIcon logoIcon;
	private ImageIcon closeUpIcon;
	private ImageIcon closeDownIcon;
	private ImageIcon minimizeUpIcon;
	private ImageIcon minimizeDownIcon;
	private JLabel logoLabel;
	private JLabel closeLabel;
	private JLabel minimizeLabel;
	private JLabel titel;  /*������*/
	static Point origin=new Point();  /*���ڱ�ʾ����ڴ����ϵ�λ��*/
	private Font definedFont = null;  /*��������Ķ���*/
	private AudioStream backMusic;  /*��������*/
	private Image backImg;  /*����ͼƬ*/
	private ImageIcon backImgIcon;
	private JLabel backImgLabel;
	private JPanel backPanel;  /*���ر���ͼƬ��panel��������ͼƬ���������²�*/
	
	/**
	 * �������·������ͼƬ
	 * @param path: ͼƬ�����·��
	 * @return: ��ȡ����ͼƬ����
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
	 * �������·��������Ƶ
	 * @param path: ��Ƶ�ļ������·��
	 * @return: ��ȡ������Ƶ����
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
	 * �������·����������
	 * @param path: �����ļ������·��
	 * @return: ��ȡ�����������
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
	 * ����Null���ֵ�JFrame����� 
	 * @param jframe��Ҫ���ĵ�JFrame�Ķ���
	 * @param a��logo�ĺ�����λ��
	 * @param b���������Ƶĺ�����λ��
	 * @param c����С����ť�ĺ�����λ��
	 * @param d���رհ�ť�ĺ�����λ��
	 * @param flag��true��ʾ�ر����˳�����false��ʾ�ر������س���
	 * @param name����������
	 */
	public ChangeView(final JFrame jframe,int a,int b,int c,int d,final boolean flag,String name) {
		/* ��ʼ������ */
		logo = getImagePath("resource/image/logo.png");
		logoIcon = new ImageIcon(logo);
		logoLabel = new JLabel(logoIcon);
		titel = new JLabel(name);
		titel.setFont(getDefinedFont("resource/font/Ҷ����ë����ɫ����.ttf"));
		closeUp = getImagePath("resource/image/closeUp.png");
		closeUpIcon = new ImageIcon(closeUp);
		closeDown = getImagePath("resource/image/closeDown.png");
		closeDownIcon = new ImageIcon(closeDown);
		closeLabel = new JLabel(closeUpIcon);
		closeLabel.setToolTipText("�ر�");
		minimizeUp = getImagePath("resource/image/minimizeUp.png");
		minimizeUpIcon = new ImageIcon(minimizeUp);
		minimizeDown = getImagePath("resource/image/minimizeDown.png");
		minimizeDownIcon = new ImageIcon(minimizeDown);
		minimizeLabel = new JLabel(minimizeUpIcon);
		minimizeLabel.setToolTipText("��С��");
		backMusic = getAudioPath("resource/music/��Է���.au");
		AudioPlayer.player.start(backMusic);  /*���ű�������*/
		backImg = getImagePath("resource/image/backgrounding.bmp");
		backImgIcon = new ImageIcon(backImg);
		backImgLabel = new JLabel(backImgIcon);
		backPanel =(JPanel)jframe.getContentPane();
		backPanel.setOpaque(false);
		
		/* ������Դλ��   */
		jframe.setLayout(null);
		/*�ѱ���ͼƬ�������²�*/
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
		
			
		jframe.setUndecorated(true);  /*��߿���ʧ*/
		jframe.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				origin.x=e.getX();
				origin.y=e.getY();
			}
		});  /*�����������*/
		jframe.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p=jframe.getLocation();
				jframe.setLocation(p.x+e.getX()-origin.x,p.y+e.getY()-origin.y);
			}
		});  /*ʹ�������������϶�*/
		
		/* ���رհ�ť�����Ӧ�¼� */
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
		
		/* ����С����ť�����Ӧ�¼� */
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
