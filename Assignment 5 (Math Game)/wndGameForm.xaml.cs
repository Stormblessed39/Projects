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
using System.Windows.Threading;

namespace Assignment_5__Math_Game_
{
    /// <summary>
    /// Interaction logic for wndGameForm.xaml
    /// </summary>
    public partial class wndGameForm : Window
    {
        /// <summary>
        /// The game class variable.
        /// </summary>
        public clsGame game;

        public clsUser User;

        /// <summary>
        /// The timer.
        /// </summary>
        DispatcherTimer MyTimer;

        /// <summary>
        /// How many seconds have elapsed.
        /// </summary>
        int seconds = 0;

        /// <summary>
        /// How many questions the user has answered.
        /// </summary>
        int questions = 0;

        wndScoreForm wndScoreForm; 

       

        public wndGameForm()
        {
            InitializeComponent();
            //Instantiate the DispatcherTimer
            MyTimer = new DispatcherTimer();
            //Make the timer go off every second
            MyTimer.Interval = TimeSpan.FromSeconds(1);
            //Tell it which method will handle the click event
            MyTimer.Tick += new EventHandler(MyTimer_Tick);
        }

        /// <summary>
        /// Submits and checks the user's answer.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSubmit_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                //run as long as they havent answered more than 10 questions
                if (questions < 9)
                {
                    questions++;
                    //focus the answer box for the user
                    txtAnswer.Focus();
                   
                    //sounds to run with user answers incorrect or correct
                    SoundPlayer correct = new SoundPlayer("Cheerful R2D2.wav");
                    SoundPlayer incorrect = new SoundPlayer("Sad R2D2.wav");
                    int answer = -1;
                    Int32.TryParse(txtAnswer.Text, out answer);
                    if (game.isCorrect(answer))
                    {
                        lblResult.Content = "Correct!";
                        correct.Play();
                        
                    }
                    else
                    {
                        lblResult.Content = "Wrong answer!";
                        incorrect.Play();
                    }
                    //display question number
                    lblNum.Content = questions+1;
                    //clear the answer
                    txtAnswer.Text = "";
                    //generate new question
                    lblQuestion.Content = game.GenerateQuestion();
                }
                else
                {
                    MyTimer.Stop();
                    int answer;
                    Int32.TryParse(txtAnswer.Text, out answer);
                    game.isCorrect(answer);
                    int incorrect = 10 - game.score;
                   
                    //setup the score form
                    wndScoreForm = new wndScoreForm();
                    wndScoreForm.lblName.Content += User.name;
                    wndScoreForm.lblAge.Content += User.age.ToString();
                    wndScoreForm.lblCorrect.Content += game.score.ToString();
                    wndScoreForm.lblIncorrect.Content += incorrect.ToString();
                    wndScoreForm.lblTime.Content += seconds.ToString();

                    wndScoreForm.score = game.score;
                    wndScoreForm.user = User;
                    wndScoreForm.incorrect = 10 - game.score;
                    wndScoreForm.time = seconds;
                    game.score = 0;
                    //make the background and play correct noise
                    wndScoreForm.makeBackground();

                    //Hide the menu
                    this.Close();
                    //Show the game form
                    wndScoreForm.ShowDialog();

                }
            }
            catch (Exception ex)
            {
                //This is the top level method so we want to handle the exception
                HandleError(MethodInfo.GetCurrentMethod().DeclaringType.Name,
                            MethodInfo.GetCurrentMethod().Name, ex.Message);
            }
        }

        /// <summary>
        /// Starts the game.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnStart_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                //display first question number
                lblNum.Content = questions + 1;
              
                //set everything to enabled
                txtAnswer.IsEnabled = true;
                txtAnswer.Focusable = true;
                //focus the answer box
                txtAnswer.Focus();
                //make the submit button visible and the start button not
                btnSubmit.Visibility = Visibility.Visible;
                btnStart.Visibility = Visibility.Hidden;
                //generate the first question
                lblQuestion.Content = game.GenerateQuestion();
                //start the timer
                MyTimer.Start();
            }
            catch (Exception ex)
            {
                //This is the top level method so we want to handle the exception
                HandleError(MethodInfo.GetCurrentMethod().DeclaringType.Name,
                            MethodInfo.GetCurrentMethod().Name, ex.Message);
            }
        }

        /// <summary>
        /// Goes off when the timer ticks
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        void MyTimer_Tick(object sender, EventArgs e)
        {
            try
            {
                seconds++;
                lblTimer.Content = seconds;
            }
            catch (Exception ex)
            {
                //Just throw the exception
                throw new Exception(MethodInfo.GetCurrentMethod().DeclaringType.Name + "." +
                                    MethodInfo.GetCurrentMethod().Name + " -> " + ex.Message);
            }
        }

        /// <summary>
        /// Handles errors.
        /// </summary>
        /// <param name="sClass"></param>
        /// <param name="sMethod"></param>
        /// <param name="sMessage"></param>
        private void HandleError(string sClass, string sMethod, string sMessage)
        {
            try
            {
                //Would write to a file or database here.
                MessageBox.Show(sClass + "." + sMethod + " -> " + sMessage);
            }
            catch (Exception ex)
            {
                System.IO.File.AppendAllText("C:\\Error.txt", Environment.NewLine +
                                             "HandleError Exception: " + ex.Message);
            }
        }
    }
}
