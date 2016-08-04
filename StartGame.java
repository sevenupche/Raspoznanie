package AllClass;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class StartGame {

    static class GamePanel {
        JLayeredPane pane;

        /** панель для базового слоя */
        JPanel baseLayer;

        /** поле */
        Field field;
        /** панель с буквами для перетаскивания */
        JPanel letterBank;

        public GamePanel() {
            // создание и размещение компонентов
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

            pane = new JLayeredPane();
            // базовый слой размещается на глубине DEFAULT_LAYER
            pane.add( baseLayer, JLayeredPane.DEFAULT_LAYER );
            pane.addComponentListener( new ComponentAdapter() {
                @Override public void componentResized(ComponentEvent e) {
                    baseLayer.setSize( e.getComponent().getSize() );                    
                }
            });
        }

        /**
         * Компонент, рисующий игровое поле
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

                g.setColor( Color.BLACK );
                FontMetrics metrics = g.getFontMetrics();
                // отрисовка установленных букв
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
int [][] cellcolor=new int [15][15];
cellcolor[0][0]=cellcolor[1][1]=cellcolor[2][2]=cellcolor[3][3]=cellcolor[4][4]=1;
cellcolor[14][14]=cellcolor[13][13]=cellcolor[12][12]=cellcolor[11][11]=cellcolor[10][10]=1;
cellcolor[0][14]=cellcolor[1][13]=cellcolor[2][12]=cellcolor[3][11]=cellcolor[4][10]=1;
cellcolor[14][0]=cellcolor[13][1]=cellcolor[12][2]=cellcolor[11][3]=cellcolor[10][4]=1;

                // отрисовка линий сетки
                for ( int row = 1; row < SIZE; row += 1 ) {
                    g.drawLine( 0, row * CELL_SIZE, getWidth(), row * CELL_SIZE );
                }

                for ( int col = 1; col < SIZE; col += 1 ) {
                    g.drawLine( col * CELL_SIZE, 0, col * CELL_SIZE, getHeight() );
                }
                for ( int row = 0; row < SIZE; row += 1 )
                	for ( int col = 0; col < SIZE; col += 1 ) 
                	{
                		if (cellcolor[row][col]==1){                	
                g.setColor(Color.RED);
                g.drawRect(row * CELL_SIZE, col*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.black);
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
         * Класс, обрабатывающий события мыши на компонентах с буквами 
         */
        private final class DragAdapter extends MouseAdapter {
            Letter letterToDrag;
            Point clickLocation;
            Point baseLocation;

            @Override
            public void mousePressed( MouseEvent event ) {
                Letter source = (Letter)event.getSource();
                // при нажатии создаем новый компонент, который будет перетаскиваться
                letterToDrag = new Letter( source.letter, true );
                // пересчитываем его координаты из пространства панели с буквами (source.getParent())
                // в пространство основной панели pane
                letterToDrag.setBounds( SwingUtilities.convertRectangle( source.getParent(), source.getBounds(), pane ) );

                // запоминаем, в какой точке (в координатах буквы) нажата мышь
                clickLocation = event.getPoint();
                // запоминаем стартовые координаты перетаскиваемого объекта
                baseLocation = letterToDrag.getLocation();

                // добавляем букву для перетаскивания на основную панель на слой DRAG_LAYER (выше DEFAULT_LAYER)
                pane.add( letterToDrag, JLayeredPane.DRAG_LAYER );
                pane.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) ); 
            }

            @Override
            public void mouseReleased( MouseEvent event ) {
                Letter source = (Letter)event.getSource();
                // получаем координаты в пространстве буквы
                Point dropPoint = event.getPoint();
                System.out.println( "drop at: " + dropPoint );

                // переводим в пространство игрового поля
                Point pointInFieldCoords = SwingUtilities.convertPoint( source, dropPoint, field );

                // все прячем
                pane.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) ); 
                letterToDrag.setVisible( false );
                pane.remove( letterToDrag );
                letterToDrag = null;

                // сообщаем полю, что буква сброшена
                field.letterDropped( source, pointInFieldCoords );
            }

            @Override
            public void mouseDragged( MouseEvent event ) {
                // при перетаскивании меняем координаты перетаскиваемого объекта
                // clickLocation.x - event.getX - разница между положением мыши 
                //   при нажатии, и текущем

                letterToDrag.setLocation( 
                        baseLocation.x - clickLocation.x + event.getX(),
                        baseLocation.y - clickLocation.y + event.getY()
                    );
            }
        }

        private JPanel createLetterBank() {
            JPanel result = new JPanel( new GridLayout( 0, 3 ) );

            DragAdapter dragAdapter = new DragAdapter();

            for ( String letter : new String[] { "*"," ","А", "Б", "В", "Г", "Д", "Е", "Ж", "З","И","К","Л","М","Н","О","П","Р","С","Т","У","Ф","Х","Ч","Ц","Ш","Щ","Ь","Ъ","Э","Ю","Я" } ) {
                Letter letterComponent = new Letter( letter );

                letterComponent.addMouseMotionListener( dragAdapter );
                letterComponent.addMouseListener( dragAdapter );

                result.add( letterComponent );
            }

            return result;
        }

        /**
         * Класс компонента буквы
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
        EventQueue.invokeLater( StartGame::initUi );
    }

}