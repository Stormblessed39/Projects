using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Assignment_5__Math_Game_
{
   public class clsGame
    {
        /// <summary>
        /// Variable to hold the type of the game.
        /// </summary>
        public string GameType { get; set; }
        /// <summary>
        /// Variable to generate random numbers.
        /// </summary>
        public Random random = new Random();
        /// <summary>
        /// Variable to hold the answer.
        /// </summary>
        public int answer;

        /// <summary>
        /// Holds the user's score.
        /// </summary>
        public int score;
        /// <summary>
        /// Generates a question for the user based off of the game type.
        /// </summary>
        /// <returns></returns>
        public string GenerateQuestion()
        {
            try
            {
                int first;
                int second;
                string question = "What is ";
                switch (GameType)
                {
                    case "Addition":
                        //generate 2 random numbers between 1 and 10 for easy addition
                         first = random.Next(10) + 1;
                         second = random.Next(10) + 1;
                        //score the answer
                        answer = first + second;
                        //creat the question
                        question += first + "+" + second + "?";
                        break;
                    case "Subtraction":
                        first = random.Next(10) + 1;
                        second = random.Next(10) + 1;
                        //make sure the the first number is larger than the second
                        while (first < second)
                        {
                            first = random.Next(10) + 1;
                            second = random.Next(10) + 1;
                        }
                        answer = first - second;
                        question += first + "-" + second + "?";
                        break;
                    case "Multiplication":
                        first = random.Next(10) + 1;
                        second = random.Next(10) + 1;
                        answer = first * second;
                        question += first + "*" + second + "?";
                        break;
                    case "Division":
                        first = random.Next(10) + 1;
                        second = random.Next(10) + 1;
                        //make sure they divide to something even
                        while ((double)first/second % 1 != 0)
                        {
                            first = random.Next(10) + 1;
                            second = random.Next(10) + 1;
                           
                        }
                        answer = first / second;
                        question += first + "/" + second + "?";
                        break;
                    default:
                        break;
                }
                return question;
            }
            catch (Exception ex)
            {
                //Just throw the exception
                throw new Exception(MethodInfo.GetCurrentMethod().DeclaringType.Name + "." +
                                    MethodInfo.GetCurrentMethod().Name + " -> " + ex.Message);
            }
        }
        /// <summary>
        /// Returns true if the user's answer is correct.
        /// </summary>
        /// <param name="ans"></param>
        /// <returns></returns>
        public bool isCorrect(int ans)
        {
            try
            {
                if (ans == answer)
                {
                    score++;
                    return true;
                }
                else
                {
                    return false;
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
