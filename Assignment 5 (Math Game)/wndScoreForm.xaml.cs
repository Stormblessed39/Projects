using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Assignment_5__Math_Game_
{
    /// <summary>
    /// Interaction logic for wndScoreForm.xaml
    /// </summary>
    public partial class wndScoreForm : Window
    {
        /// <summary>
        /// Holds the user's current score.
        /// </summary>
        public int score;

        public int incorrect;

        public int time;

        public clsUser user;

        /// <summary>
        /// Holds the sounds that play when the window opens.
        /// </summary>
        SoundPlayer[] sounds = {
            new SoundPlayer("Amaze.wav"),
            new SoundPlayer("Dont get cocky.wav"),
            new SoundPlayer("Great shot.wav")
        };

        clsScores scores = new clsScores();
        public wndScoreForm()
        {
            InitializeComponent();
          
        }

        /// <summary>
        /// Makes the background and plays the sounds.
        /// </summary>
        public void makeBackground()
        {
            try
            {
                ImageBrush myBrush;
                Random random = new Random();
                //display a background depending on their score
                if (score < 5)
                {
                    myBrush = new ImageBrush(new BitmapImage(new Uri(@"../../Images/podracing.jpg", UriKind.Relative)));

                    wndMain.Background = myBrush;
                }
                if (score > 4 && score < 8)
                {
                    myBrush = new ImageBrush(new BitmapImage(new Uri(@"../../Images/han.jpg", UriKind.Relative)));

                    wndMain.Background = myBrush;
                }
                if (score > 7)
                {
                    myBrush = new ImageBrush(new BitmapImage(new Uri(@"../../Images/award.jpg", UriKind.Relative)));

                    wndMain.Background = myBrush;
                }
                //play a random sound from the sound array
                sounds[random.Next(3)].Play();

                scores.addScore(user.name, score, incorrect, time);

                lblBoard.Content = scores.formatList();

                if (scores.onBoard(user.name, score, incorrect, time))
                {
                    lblOnBoard.Content = "You are on the board!";
                }
                else
                {
                    lblOnBoard.Content = "You are not on the board, better luck next time!";
                }
            }
            catch (Exception ex)
            {
                //Just throw the exception
                throw new Exception(MethodInfo.GetCurrentMethod().DeclaringType.Name + "." +
                                    MethodInfo.GetCurrentMethod().Name + " -> " + ex.Message);
            }
        }
    }
}
