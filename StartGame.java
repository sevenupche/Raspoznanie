
//080816
package AllClass;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
/*{ elsi hv=down i (net bukv snizu ili odna bukva i net bukv sboku) i bukva sverhu - podhodit
{
*/
public class StartGame {

    static class GamePanel {
        JLayeredPane pane;

        protected JTextField textField;
        /** ГЇГ Г­ГҐГ«Гј Г¤Г«Гї ГЎГ Г§Г®ГўГ®ГЈГ® Г±Г«Г®Гї */
        JPanel baseLayer;

        /** ГЇГ®Г«ГҐ */
        Field field;
        /** ГЇГ Г­ГҐГ«Гј Г± ГЎГіГЄГўГ Г¬ГЁ Г¤Г«Гї ГЇГҐГ°ГҐГІГ Г±ГЄГЁГўГ Г­ГЁГї */
        JPanel letterBank;
        //Vector[] vectors = new  Vector[999];
        //public String Vesa_chars[];
   	 	public static String vesa_chars[]={"",
   			"Г ГўГҐГЁГ­Г®Г°Г±ГІ"//1
   			,"Г¤ГЄГ«Г¬ГЇГі"//2
   			,"ГЎГЈГјГї"//3
   			,"Г©Г»"//4
   			,"Г¦Г§ГµГ¶Г·"//5
   			,"",""
   			,"ГґГёГЅГѕ"//8
   			,""
   			,"Г№"//10
   			,"","","",""
   			,"Гє"};// 15
   	 public static int cellcolor[][]=
   		{{3,0,0,2,0,0,0,3,0,0,0,2,0,0,3}//   3 - word *3    4 - word *2
   		,{0,4,0,0,0,1,0,0,0,1,0,0,0,4,0}//  2 - char *2    1 - char *3
   		,{0,0,4,0,0,0,2,0,2,0,0,0,4,0,0}
   		,{2,0,0,4,0,0,0,2,0,0,0,4,0,0,2}
   		,{0,0,0,0,4,0,0,0,0,0,4,0,0,0,0}
   		,{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0}
   		,{0,0,2,0,0,0,2,0,2,0,0,0,2,0,0}
   		,{3,0,0,2,0,0,0,2,0,0,0,2,0,0,3}
   		,{0,0,2,0,0,0,2,0,2,0,0,0,2,0,0}
   		,{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0}
   		,{0,0,0,0,4,0,0,0,0,0,4,0,0,0,0}
   		,{2,0,0,4,0,0,0,2,0,0,0,4,0,0,2}//  2 - char *2    1 - char *3
   		,{0,0,4,0,0,0,2,0,2,0,0,0,4,0,0}//   3 - word *3    4 - word *2
   		,{0,4,0,0,0,1,0,0,0,1,0,0,0,4,0}
   		,{3,0,0,2,0,0,0,3,0,0,0,2,0,0,3}};
   	public static String[][] cellchars=
   		{{"","","","","","","","","","","","","","",""},//x=0
   		 {"u","t","r","o","","","","","","","","","","",""},//x=1
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},
   		 {"","","","","","","","","","","","","","",""},//x=12
   		 {"","","","","","","","","","","","","","",""},//x=13
   		 {"","","","","","","","","","","","","","",""}};//x=14
   	public  String words7="wosnyui";//shablon
   	public static String words[]={"",//array dictionary
   			"first"//1
   			,"two"//2
   			,"sun"//3
   			,"shine"//4
   			,"buki"//5
   			,"vedi"//6
   			,"summer"//7
   			,"winter"};// 8
        public GamePanel() {
            // Г±Г®Г§Г¤Г Г­ГЁГҐ ГЁ Г°Г Г§Г¬ГҐГ№ГҐГ­ГЁГҐ ГЄГ®Г¬ГЇГ®Г­ГҐГ­ГІГ®Гў
        	
            field = createField();
            letterBank = createLetterBank();

            baseLayer = new JPanel( new GridBagLayout() ); 
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.CENTER;
                       
            baseLayer.add( field, gbc );
            
            gbc.gridx = 1;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.NORTHWEST;

            baseLayer.add( letterBank, gbc );
         // Г¤Г®ГЎГ ГўГ«ГїГҐГ¬ ГІГҐГЄГ±ГІГ®ГўГ®ГҐ ГЇГ®Г«ГҐ Г¤Г«Гї 7 ГЎГіГЄГў
            textField = new JTextField(7);
            //textField.set
            JPanel result = new JPanel( new GridLayout( 0, 3 ) );
            gbc.gridx = 1;
            gbc.gridy = 1;
            result.add(textField);
            baseLayer.add( result, gbc );
            
          //Г¤Г®ГЎГ ГўГ«ГїГҐГ¬ ГЄГ­Г®ГЇГЄГі
            result = new JPanel( new GridLayout( 0, 3 ) );
            JButton button = new JButton(">>>", new ImageIcon("1.gif"));
            button.setMargin(new Insets(0, 10, 20, 30));
            button.setBounds(0, 0, 80, 20);
            gbc.gridx = 1;
            gbc.gridy = 2;
            result.add(button);
           baseLayer.add( button, gbc );

            pane = new JLayeredPane();
            // ГЎГ Г§Г®ГўГ»Г© Г±Г«Г®Г© Г°Г Г§Г¬ГҐГ№Г ГҐГІГ±Гї Г­Г  ГЈГ«ГіГЎГЁГ­ГҐ DEFAULT_LAYER
            pane.add( baseLayer, JLayeredPane.DEFAULT_LAYER );
            pane.addComponentListener( new ComponentAdapter() {
                @Override public void componentResized(ComponentEvent e) {
                    baseLayer.setSize( e.getComponent().getSize() );                    
                }
            });
            //System.out.println( "index find word: " + search_word(0,6,5,"r","swinte") );
            int k=is_place(13,0,-1,3);//
             System.out.println( "k="+k);
           search_vectors();
        }
        //-------------------------------------------------search vectors for new words------------------
        public int search_vectors()
        {
        	int count=0;
        	for (int x=0;x<13;x++)
        		for (int y=0;y<13;y++)
        			for (int hv=-1;hv<=1;hv+=2)
        				for (int longhv=3;longhv<=3;longhv++)
        		{
        			int tmp=is_place(x,y,hv,longhv);
        			if (tmp>=0) 
        			{
        				System.out.println( "x,y="+x+" "+y+" "+hv);
        				//vectors[count++] = new Vector(x,y,hv,longhv,tmp);
        				 System.out.println( "count="+count++);
        			}
        		}
        	return 1;
        }
      //check hor. or vert. line for word------------------hv - 
        public int is_place (int x, int y, int hv, int hvlong)
        //proverka vectora na prigodnost for word
        {
        	int xx=x; int yy=y;
        	int flag=-1;//Г­ГҐ ГЇГ®Г¤ГµГ®Г¤ГЁГІ
        	int index=is_line(xx,yy,hv,hvlong);
        	//System.out.println( "is_line="+index);
        	if ((hvlong==3)  && (hv>0))
        	 	{
        		flag=check_matrix3(x,y,hv,hvlong,index);
    			}
        	if ((hvlong==3)&& (hv<0))
    	 	{
        		flag=check_matrix3(y,x,hv,hvlong,index);
			}
        	       	
        	//if (yes==1) return index;
        	return flag;
        }
        //----check line is 1 char------------
        public int is_line(int xx,int yy,int hv, int hvlong)
        {
        	int sh=0,index=-1;
        	for (int i=0;i<hvlong;i++)
        	{
        		if (cellchars[xx][yy]!="") {sh++; index=i;}
        		if (hv>0)xx++; else yy++;
        	}
        	if (sh==1) return index;
        	if (sh==0) return -2;
        	if (sh>1) return -1;
        	return -1;
        }
        //swap array
        public String[][] swap_string_array(String [][] arr,int length_x,int length_y)
        {
        	String tmp;
        	for (int x=0;x<length_x;x++)
        		for (int y=0;y<length_y;y++)
        		{
        			if (x!=y)
        				{
        				tmp=arr[x][y];
        				arr[x][y]=arr[y][x];
        				arr[y][x]=tmp;
        				}
        		}
        	return arr;
        }
        //--------check matrix---------------
        public int check_matrix3(int xx,int yy,int hv,int hvlong,int index)
        {
        	 int [][] matrix3=new int [5][3];
        	 final int [][] matrix3a=//bukva v seredine. long word =3
     		 	{{1,0,1,0,1},
     			 {0,1,1,1,0},
     			 {1,0,1,0,1}};
        	 final int [][] matrix3b=//bukva v konce. long word =3
      		 	{{1,0,0,1,1},
      			 {0,1,1,1,1},
      			 {1,0,0,1,1}};
        	 int xmin=0,xmax=5,ymin=0,ymax=3;
         	 if (index==1) matrix3=matrix3a;
        	 if (index==2) {matrix3=matrix3b; 
        	 if (hv>0) xmax=4; 
        	 if (hv<0) ymax=4;}
        	int flag=1;
        	//proverka borders
        	if ((xx==0)&&(hv>0)) {xmin=1;}
        	if (((xx+hvlong)==14)&&(hv>0)) xmax=4;
        	if ((yy==0)&&(hv<0)) ymin=1;
        	if (((yy+hvlong)==14)&&(hv<0)) xmax=4;
        	//end proverka borders
        	for (int x=xmin;x<xmax;x++)
        		for (int y=ymin;y<ymax;y++)
       		  {
        			if (matrix3[y][x]==0)
        			{
        				//System.out.println( "x,y="+x+" "+y+"..."+flag);
        				switch (hv)
        				{
        				case -1:	if (cellchars[xx+y-1][yy+x-1].length()>0) flag=-1;
        					break;
        				case 1:if (cellchars[xx+x-1][yy+y-1].length()>0) flag=-1;
        					break;
        				default: 
        				}
        			}
       		  }
        	//System.out.println( "flag..."+flag);
        	return flag;
        }
        //search word in dictionary begin 
        public int search_word(int begin_index,int know_length,int pos,String know_chars,String word7)
        {
        	int index=begin_index;
        	//int rez=-1;
        	int flag=1;//
        	while((index<words.length)&&(flag==1))
        	{
        	//while (flag==1)
        	//{
        	String tmp_word=words[index];
        	//String tmp_word7=word7;
        	if (tmp_word.length()==know_length) 
        	 if (tmp_word.indexOf(know_chars)==pos) 
        	 {
        		 int know_char_index=pos;//tmp_word.indexOf(know_chars);
        		 int[] rez_array=new int [know_length];
        		 rez_array[know_char_index]=1;//esli izvestna 1 bukva
        		 for (int i=0;i<word7.length();i++)
        		 {
        			 String tmps=word7.substring(i, i+1);
        			 int j=tmp_word.indexOf(tmps);
        			 //tmp_word7=tmp_word7.substring(0, i+1)+
        			//		 "."+tmp_word7.substring(i+1, know_length-i-1);
        			 tmp_word=tmp_word.replaceFirst(tmps,".");
        			 if ((j>=0)&&(j!=know_char_index))
        				 rez_array[j]=1;
        		 }
        		 int sum=0; for (int j=0;j<know_length;j++) sum+=rez_array[j];
        		 if (word7.indexOf("*")>=0) sum++;
        		 if (sum==know_length) flag=0; // nashli slovo
        	 }//if
        	index++;
        	}//while index
        	if (flag==0) return index-1;
        	 return -1;
        }
         //calc cena word---------------------
            public int calc_cena_word (int x, int y, int v, String str)
            {
            	int flag_word_multiply=1,sum=0,cxy=0;
            	for (int is=0;is<str.length();is++)
            	{
            		int cena_char=calc_cena_char (str.substring(is, is+1));
            		cxy=cellcolor[x][y];
            		if (cxy==2) sum+=cxy*cena_char;
            		if ((cxy==0)||(cxy==4)||(cxy==3)) sum+=cena_char;
            		if (cxy==1) sum+=3*cena_char;
            		System.out.println( "sum: " + sum+" "+cena_char);
            		if (cxy>2) flag_word_multiply=cxy;
            		if (v>0)
            			x+=1;
            		else 
            			y+=1;
            	}
            	return sum*flag_word_multiply;
            }
          //Г±Г·ГЁГІГ ГҐГ¬ Г¶ГҐГ­Гі ГЎГіГЄГўГ»----------------------
            public int calc_cena_char (String str)
            {
            	System.out.println( "char: " + str);
        		
            	int j=0;
            	for ( int iv=0;iv<vesa_chars.length;iv++)
            	{
            		if (vesa_chars[iv].indexOf(str)>-1)
            			j= iv;
            	}
            	return j;
            }

        /**
         * ГЉГ®Г¬ГЇГ®Г­ГҐГ­ГІ, Г°ГЁГ±ГіГѕГ№ГЁГ© ГЁГЈГ°Г®ГўГ®ГҐ ГЇГ®Г«ГҐ
         * 
         */
        static class Field extends JPanel {
            static final int CELL_SIZE = 30;
            static final int SIZE = 15;

            String[][] letters = new String[SIZE][SIZE];
           
            @Override
            public void paintComponent( Graphics g ) {
                g.setColor( Color.WHITE );
                g.fillRect( 0, 0, getWidth(), getHeight() );
                letters = cellchars;
                g.setColor( Color.BLACK );
                FontMetrics metrics = g.getFontMetrics();
                // Г®ГІГ°ГЁГ±Г®ГўГЄГ  ГіГ±ГІГ Г­Г®ГўГ«ГҐГ­Г­Г»Гµ ГЎГіГЄГў
                for ( int row = 0; row < SIZE; row += 1 ) {
                    for ( int col = 0; col < SIZE; col += 1 ) {
                        if ( letters[row][col] != null ) {
                            Rectangle2D bounds = metrics.getStringBounds( letters[row][col], g );
                            Font myFont = new Font ("Courier New", 1, 17);
                            g.setFont(myFont);
                            g.drawString( letters[row][col],
                                    col * CELL_SIZE + (int)(CELL_SIZE - bounds.getWidth()) / 2,
                                    row * CELL_SIZE + metrics.getAscent() + (int)(CELL_SIZE - bounds.getHeight()) / 2
                                );
                        }
                    }
                }
//int [][] cellcolor=new int [15][15];


                // Г®ГІГ°ГЁГ±Г®ГўГЄГ  Г«ГЁГ­ГЁГ© Г±ГҐГІГЄГЁ
g.setColor(Color.LIGHT_GRAY);
                for ( int row = 1; row < SIZE; row += 1 ) {
                    g.drawLine( 0, row * CELL_SIZE, getWidth(), row * CELL_SIZE );
                }

                for ( int col = 1; col < SIZE; col += 1 ) {
                    g.drawLine( col * CELL_SIZE, 0, col * CELL_SIZE, getHeight() );
                }
                for ( int row = 0; row < SIZE; row += 1 )
                	for ( int col = 0; col < SIZE; col += 1 ) 
                	{
                		int i=GamePanel.cellcolor[row][col];
                		if (i>=1){                	
                if (i==3)g.setColor(Color.RED);
                if (i==1)g.setColor(Color.YELLOW);
                if (i==2)g.setColor(Color.GREEN);
                if (i==4)g.setColor(Color.BLUE);
                
                g.drawRect(row * CELL_SIZE, col*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                		}
            }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension( SIZE * CELL_SIZE, SIZE * CELL_SIZE );
            }

            @Override
            public Dimension getMinimumSize() {
                return getPreferredSize();
            }
//--------------------------------------------------------------
            public void letterDropped( Letter letter, Point dropLocation ) {
                if ( new Rectangle( getSize() ).contains( dropLocation ) ) {
                    letters[dropLocation.y / CELL_SIZE][dropLocation.x / CELL_SIZE] = letter.letter;
                    System.out.println( "letter: " + letter.letter );
                    //System.out.println( "x: " + dropLocation.x / CELL_SIZE );
                    
                    repaint();
                }
            }
        }

        private Field createField() {
            return new Field();
        }


        /**
         * ГЉГ«Г Г±Г±, Г®ГЎГ°Г ГЎГ ГІГ»ГўГ ГѕГ№ГЁГ© Г±Г®ГЎГ»ГІГЁГї Г¬Г»ГёГЁ Г­Г  ГЄГ®Г¬ГЇГ®Г­ГҐГ­ГІГ Гµ Г± ГЎГіГЄГўГ Г¬ГЁ 
         */
        private final class DragAdapter extends MouseAdapter {
            Letter letterToDrag;
            Point clickLocation;
            Point baseLocation;

            @Override
            public void mousePressed( MouseEvent event ) {
                Letter source = (Letter)event.getSource();
                // ГЇГ°ГЁ Г­Г Г¦Г ГІГЁГЁ Г±Г®Г§Г¤Г ГҐГ¬ Г­Г®ГўГ»Г© ГЄГ®Г¬ГЇГ®Г­ГҐГ­ГІ, ГЄГ®ГІГ®Г°Г»Г© ГЎГіГ¤ГҐГІ ГЇГҐГ°ГҐГІГ Г±ГЄГЁГўГ ГІГјГ±Гї
                letterToDrag = new Letter( source.letter, true );
                // ГЇГҐГ°ГҐГ±Г·ГЁГІГ»ГўГ ГҐГ¬ ГҐГЈГ® ГЄГ®Г®Г°Г¤ГЁГ­Г ГІГ» ГЁГ§ ГЇГ°Г®Г±ГІГ°Г Г­Г±ГІГўГ  ГЇГ Г­ГҐГ«ГЁ Г± ГЎГіГЄГўГ Г¬ГЁ (source.getParent())
                // Гў ГЇГ°Г®Г±ГІГ°Г Г­Г±ГІГўГ® Г®Г±Г­Г®ГўГ­Г®Г© ГЇГ Г­ГҐГ«ГЁ pane
                letterToDrag.setBounds( SwingUtilities.convertRectangle( source.getParent(), source.getBounds(), pane ) );

                // Г§Г ГЇГ®Г¬ГЁГ­Г ГҐГ¬, Гў ГЄГ ГЄГ®Г© ГІГ®Г·ГЄГҐ (Гў ГЄГ®Г®Г°Г¤ГЁГ­Г ГІГ Гµ ГЎГіГЄГўГ») Г­Г Г¦Г ГІГ  Г¬Г»ГёГј
                clickLocation = event.getPoint();
                // Г§Г ГЇГ®Г¬ГЁГ­Г ГҐГ¬ Г±ГІГ Г°ГІГ®ГўГ»ГҐ ГЄГ®Г®Г°Г¤ГЁГ­Г ГІГ» ГЇГҐГ°ГҐГІГ Г±ГЄГЁГўГ ГҐГ¬Г®ГЈГ® Г®ГЎГєГҐГЄГІГ 
                baseLocation = letterToDrag.getLocation();

                // Г¤Г®ГЎГ ГўГ«ГїГҐГ¬ ГЎГіГЄГўГі Г¤Г«Гї ГЇГҐГ°ГҐГІГ Г±ГЄГЁГўГ Г­ГЁГї Г­Г  Г®Г±Г­Г®ГўГ­ГіГѕ ГЇГ Г­ГҐГ«Гј Г­Г  Г±Г«Г®Г© DRAG_LAYER (ГўГ»ГёГҐ DEFAULT_LAYER)
                pane.add( letterToDrag, JLayeredPane.DRAG_LAYER );
                pane.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) ); 
            }

            @Override
            public void mouseReleased( MouseEvent event ) {
                Letter source = (Letter)event.getSource();
                // ГЇГ®Г«ГіГ·Г ГҐГ¬ ГЄГ®Г®Г°Г¤ГЁГ­Г ГІГ» Гў ГЇГ°Г®Г±ГІГ°Г Г­Г±ГІГўГҐ ГЎГіГЄГўГ»
                Point dropPoint = event.getPoint();
                System.out.println( "drop at: " + dropPoint );

                // ГЇГҐГ°ГҐГўГ®Г¤ГЁГ¬ Гў ГЇГ°Г®Г±ГІГ°Г Г­Г±ГІГўГ® ГЁГЈГ°Г®ГўГ®ГЈГ® ГЇГ®Г«Гї
                Point pointInFieldCoords = SwingUtilities.convertPoint( source, dropPoint, field );

                // ГўГ±ГҐ ГЇГ°ГїГ·ГҐГ¬
                pane.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) ); 
                letterToDrag.setVisible( false );
                pane.remove( letterToDrag );
                letterToDrag = null;

                // Г±Г®Г®ГЎГ№Г ГҐГ¬ ГЇГ®Г«Гѕ, Г·ГІГ® ГЎГіГЄГўГ  Г±ГЎГ°Г®ГёГҐГ­Г 
                field.letterDropped( source, pointInFieldCoords );
            }

            @Override
            public void mouseDragged( MouseEvent event ) {
                // ГЇГ°ГЁ ГЇГҐГ°ГҐГІГ Г±ГЄГЁГўГ Г­ГЁГЁ Г¬ГҐГ­ГїГҐГ¬ ГЄГ®Г®Г°Г¤ГЁГ­Г ГІГ» ГЇГҐГ°ГҐГІГ Г±ГЄГЁГўГ ГҐГ¬Г®ГЈГ® Г®ГЎГєГҐГЄГІГ 
                // clickLocation.x - event.getX - Г°Г Г§Г­ГЁГ¶Г  Г¬ГҐГ¦Г¤Гі ГЇГ®Г«Г®Г¦ГҐГ­ГЁГҐГ¬ Г¬Г»ГёГЁ 
                //   ГЇГ°ГЁ Г­Г Г¦Г ГІГЁГЁ, ГЁ ГІГҐГЄГіГ№ГҐГ¬

                letterToDrag.setLocation( 
                        baseLocation.x - clickLocation.x + event.getX(),
                        baseLocation.y - clickLocation.y + event.getY()
                    );
            }
        }

        private JPanel createLetterBank() {
            JPanel result = new JPanel( new GridLayout( 0, 3 ) );

            DragAdapter dragAdapter = new DragAdapter();

            for ( String letter : new String[] { "*"," ","ГЂ", "ГЃ", "Г‚", "Гѓ", "Г„", "Г…", "Г†", "Г‡","Г€","ГЉ","Г‹","ГЊ","ГЌ","ГЋ","ГЏ","Гђ","Г‘","Г’","Г“","Г”","Г•","Г—","Г–","Г�","Г™","Гњ","Гљ","Гќ","Гћ","Гџ" } ) {
                Letter letterComponent = new Letter( letter );

                letterComponent.addMouseMotionListener( dragAdapter );
                letterComponent.addMouseListener( dragAdapter );

                result.add( letterComponent );
            }

            return result;
        }

        /**
         * ГЉГ«Г Г±Г± ГЄГ®Г¬ГЇГ®Г­ГҐГ­ГІГ  ГЎГіГЄГўГ»
         */
        static class Letter extends JComponent {
            static final int SIZE = 30;
            String letter;
            boolean dragged;

            public Letter( String letter ) {
                this( letter, false );
            }

            public Letter( String letter, boolean dragged ) {
                this.letter = letter;
                this.dragged = dragged;
            }

            @Override
            public void paintComponent( Graphics g ) {
                g.setColor( dragged ? Color.RED : Color.BLACK );
                g.fillRect( 0, 0, getWidth(), getHeight() );

                FontMetrics metrics = g.getFontMetrics();
                Rectangle2D bounds = metrics.getStringBounds( letter, g );

                g.setColor( Color.WHITE );
                Font myFont = new Font ("Courier New", 1, 17);
                g.setFont(myFont);
                g.drawString( letter, 
                        (int)(SIZE - bounds.getWidth()) / 2,
                        metrics.getAscent() + (int)(SIZE - bounds.getHeight()) / 2
                     );
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension( SIZE, SIZE );
            }
        }
    }

    static void initUi() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        final GamePanel gamePanel = new GamePanel();
        frame.add( gamePanel.pane );

        frame.setSize( 800, 600 );

        frame.setVisible( true );
    }


    public static void main(String[] args) {
       // EventQueue.invokeLater( StartGame::initUi );
    	initUi();
    }

}

