using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Minesweeper
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        /// <summary>
        /// If the game is started or not.
        /// </summary>
        public bool gameStarted;

        /// <summary>
        /// Storing the first tile color.
        /// </summary>
        public Brush color1;

        /// <summary>
        /// Storing the second tile color.
        /// </summary>
        public Brush color2;

        /// <summary>
        /// The sound that plays when the player wins.
        /// </summary>
        SoundPlayer victory = new SoundPlayer("Victory.wav");

        /// <summary>
        /// The sound that plays when the player clicks a square.
        /// </summary>
        SoundPlayer bomb = new SoundPlayer("Bomb.wav");

        public MainWindow()
        {
            InitializeComponent();

            
            gameStarted = false;

            //Set the colors of color1 and color2
            Label l1 = (Label)wpGameBoard.Children[0];
            Label l2 = (Label)wpGameBoard.Children[1];

            color1 = l1.Background;
            color2 = l2.Background;

            //set the names of all of the tiles
            int i = 0;
            foreach (var item in wpGameBoard.Children)
            {
                Label button = (Label)item;

                button.Name = "button" + i.ToString();
                i++;
            }
        }

        /// <summary>
        /// Sets up the game or checks to see if a spot has a bomb depending on if the game is started or not.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Label_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            Random random = new Random();
            Label send = (Label)sender;
            int bombs = 13;

            //set up the game
            if (gameStarted == false)
            {
               
                //randomly place bombs
                foreach (var item in wpGameBoard.Children)
                {
                    Label button = (Label)item;
                    int ran = random.Next(6);
                    if (button != send && bombs > 0 && ran == 0)
                    {
                        button.Tag = 1;
                        bombs--;
                    }
                    else
                    {
                        button.Tag = -1;
                    }
                }

                //set the values of the spots by bombs
                foreach (var item in wpGameBoard.Children)
                {
                    Label button = (Label)item;

                    if(getBombs(button) == 0 && Convert.ToInt32(button.Tag) != 1)
                    {
                        button.Background = Brushes.White;
                        button.Content = "";
                        List<Label> neighbors = getNeighbors(button);
                        foreach (var value in neighbors)
                        {
                            value.Content = getBombs(value);
                            value.Background = Brushes.White;
                        }
                    }
                    
                }
                    gameStarted = true;

                //set the spots with 0 bombs by them to empty
                    updateBoard();
              
            }
            if (gameStarted == true && getBombs(send) != 0)
            {

                if (send.Background != Brushes.White){
                    bomb.Play();
                }

                //if the user clicked a bomb reveal the bombs and lock the board
                if (Convert.ToInt32(send.Tag) > 0)
                {
                    foreach (var item in wpGameBoard.Children)
                    {
                        Label button = (Label)item;
                        button.IsEnabled = false;
                        if(Convert.ToInt32(button.Tag) > 0)
                        {
                            button.Background = Brushes.Red;
                        }
                    }
                }
                //otherwise set the tile to a white background and check to see if they won
                else
                {
                    send.Background = Brushes.White;
                    send.Foreground = Brushes.Black;
                    send.Content = getBombs(send);
                    bool win = true;

                    foreach (var item in wpGameBoard.Children)
                    {
                        Label button = (Label)item;

                        if(button.Background != Brushes.White && button.Background != Brushes.Black && Convert.ToInt32(button.Tag) < 1)
                        {
                            win = false;
                        }
                    }

                    if(win == true)
                    {
                        

                        victory.Play();
                        lblWin.Visibility = Visibility.Visible;
                    }
                   
                }
            }
        }
        /// <summary>
        /// Turns the spots with 0 bombs near them to empty spots.
        /// </summary>
        private void updateBoard()
        {
            foreach (var item in wpGameBoard.Children)
            {
                Label button = (Label)item;

                if(getBombs(button) <= 0)
                {
                    button.Content = "";
                }
            }
        }
        /// <summary>
        /// Gets how many bombs are around a spot.
        /// </summary>
        /// <param name="send"></param>
        /// <returns></returns>
        private int getBombs(Label send)
        {
            int count = 0;
            List<Label> buttons = getNeighbors(send);
            foreach (var item in buttons)
            {
                if (Convert.ToInt32(item.Tag) > 0)
                {
                    count++;
                }
            }
            return count;
        }

        /// <summary>
        /// Returns the neighbors of a square.
        /// </summary>
        /// <param name="lbl"></param>
        /// <returns></returns>
        private List<Label> getNeighbors(Label lbl)
        {
            List<Label> neighbors = new List<Label>();

            string name = lbl.Name.ToString();

            int iname = Convert.ToInt32(name.Substring(name.IndexOf('n')+1));

            foreach (var item in wpGameBoard.Children)
            {
                Label button = (Label)item;

                string name1 = button.Name.ToString();

                int iname1 = Convert.ToInt32(name1.Substring(name1.IndexOf('n') + 1));

                if (iname % 10 == 0 && iname != 0)
                {
                    if (iname1 == iname - 10 || iname1 == iname - 9)
                    {
                        neighbors.Add(button);
                    }
                    if (iname1 == iname + 1 || iname1 == iname + 10 || iname1 == iname + 11)
                    {
                        neighbors.Add(button);
                    }


                }

                else if((iname+1) % 10 == 0 && iname != 9)
                {
                    if (iname1 == iname - 10 || iname1 == iname - 11)
                    {
                        neighbors.Add(button);
                    }
                    if (iname1 == iname - 1 || iname1 == iname + 10 || iname1 == iname + 9)
                    {
                        neighbors.Add(button);
                    }
                }

                else
                {

                    if (iname > 9)
                    {
                        if (iname1 == iname - 10 || (iname1 == iname - 9 && iname != 79))
                        {
                            neighbors.Add(button);
                        }

                        if (iname1 == iname - 11 && iname != 70)
                        {
                            neighbors.Add(button);
                        }
                    }

                    if (iname < 70)
                    {

                        if (iname1 == iname + 10 || (iname1 == iname + 9 && iname != 0))
                        {
                            neighbors.Add(button);
                        }

                        if (iname1 == iname + 11 && iname != 9)
                        {
                            neighbors.Add(button);
                        }
                    }

                    if ((iname != 9) && (iname != 79) && (iname1 == iname + 1))
                    {
                        neighbors.Add(button);
                    }

                    if (iname != 0 && iname != 70 && iname1 == iname - 1)
                    {
                        neighbors.Add(button);
                    }

                }
            }

            return neighbors;
        }

        /// <summary>
        /// Marks the spot as a bomb.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Label_MouseRightButtonDown(object sender, MouseButtonEventArgs e)
        {
            
            Label send = (Label)sender;
            if (send.Background != Brushes.White && send.Background != Brushes.Black)
            {
                send.Foreground = send.Background;
                send.Background = Brushes.Black;
                
            }

           else if(send.Background == Brushes.Black)
            {
                send.Background = send.Foreground;
            }

        }

        /// <summary>
        /// Resets the board.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Button_Click(object sender, RoutedEventArgs e)
        {
            lblWin.Visibility = Visibility.Hidden;
            gameStarted = false;
            int count = 0;
            bool odd = false;
            foreach (var item in wpGameBoard.Children)
            {
                Label button = (Label)item;
                button.Content = "";
                button.IsEnabled = true;
                button.Foreground = Brushes.Black;

                if (odd == false)
                {
                    if (count % 2 == 0)
                    {
                        button.Background = color1;
                    }

                    else
                    {
                        button.Background = color2;
                    }
                }

                if (odd == true)
                {
                    if (count % 2 == 0)
                    {
                        button.Background = color2;
                    }

                    else
                    {
                        button.Background = color1;
                    }
                }



                if (count == 9)
                {
                    count = -1;
                    odd = !odd;
                }

                count++;
            }
        }

  

        private void Window_KeyDown(object sender, KeyEventArgs e)
        {
            if(e.Key == Key.W)
            {
                foreach (var item in wpGameBoard.Children)
                {
                    Label button = (Label)item;
                    // button.IsEnabled = false;
                    if (Convert.ToInt32(button.Tag) > 0)
                    {
                        button.Background = Brushes.Red;
                    }
                }
            }
        }
    }
}
