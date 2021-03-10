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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Assignment_5__Math_Game_
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        /// <summary>
        /// The current user.
        /// </summary>
        public clsUser User;
        /// <summary>
        /// The game object.
        /// </summary>
        public clsGame game;
        /// <summary>
        /// The game window.
        /// </summary>
        public wndGameForm wndGameForm;
        public MainWindow()
        {
            InitializeComponent();
            game = new clsGame();
            wndGameForm = new wndGameForm();
            Application.Current.ShutdownMode = ShutdownMode.OnMainWindowClose;
            rdoAdd.IsChecked = true;
            txtName.Focus();
        }

        /// <summary>
        /// Checks to see if the game can start, and then starts the game if so.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnPlay_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                SoundPlayer simpleSound = new SoundPlayer("Happy-Confirmation.wav");
                string name = txtName.Text;
                int age;
                if (Int32.TryParse(txtAge.Text, out age) && name != "" && age > 3 && age < 10)
                {
                    User = new clsUser(name, age);
                    simpleSound.Play();
                    wndGameForm = new wndGameForm();
                    wndGameForm.User = User;
                    wndGameForm.game = game;
                    
                    lblError.Content = "";
                    
                    //Hide the menu
                    this.Hide();
                    //Show the game form
                    wndGameForm.ShowDialog();
                    //Show the main form
                    this.Show();
                }
                else if(name == "")
                {
                    lblError.Content = "Please enter a name";
                }
                else if(age < 3 || age > 10)
                {
                    lblError.Content = "The age must be between 3 and 10";
                }
                else
                {
                    lblError.Content = "Please enter a valid age";
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

        /// <summary>
        /// Changes the game type to Addition.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rdoAdd_Checked(object sender, RoutedEventArgs e)
        {
            try
            {
                game.GameType = "Addition";
            }
            catch (Exception ex)
            {
                //This is the top level method so we want to handle the exception
                HandleError(MethodInfo.GetCurrentMethod().DeclaringType.Name,
                            MethodInfo.GetCurrentMethod().Name, ex.Message);
            }
        }

        /// <summary>
        /// Changes the game type to Subtraction.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rdoSubtract_Checked(object sender, RoutedEventArgs e)
        {
            try
            {
                game.GameType = "Subtraction";
            }
            catch (Exception ex)
            {
                //This is the top level method so we want to handle the exception
                HandleError(MethodInfo.GetCurrentMethod().DeclaringType.Name,
                            MethodInfo.GetCurrentMethod().Name, ex.Message);
            }
        }
        /// <summary>
        /// Changes the game type to Multiplication.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rdoMultiply_Checked(object sender, RoutedEventArgs e)
        {
            try
            {
                game.GameType = "Multiplication";
            }
            catch (Exception ex)
            {
                //This is the top level method so we want to handle the exception
                HandleError(MethodInfo.GetCurrentMethod().DeclaringType.Name,
                            MethodInfo.GetCurrentMethod().Name, ex.Message);
            }
        }

        /// <summary>
        /// Changes the game type to Division.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void rdoDivide_Checked(object sender, RoutedEventArgs e)
        {
            try
            {
                game.GameType = "Division";
            }
            catch (Exception ex)
            {
                //This is the top level method so we want to handle the exception
                HandleError(MethodInfo.GetCurrentMethod().DeclaringType.Name,
                            MethodInfo.GetCurrentMethod().Name, ex.Message);
            }
        }
    }
}
