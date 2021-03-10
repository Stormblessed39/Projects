using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace Assignment_5__Math_Game_
{
    /// <summary>
    /// player struct that holds all the player's info.
    /// </summary>
    public struct player
    {
        public string name;
        public int correct;
        public int incorrect;
        public int time;
    }
    /// <summary>
    /// Class for the IComparer, which is used to sort the score board.
    /// </summary>
    class comp : IComparer<player>
    {
        /// <summary>
        /// Logic for sorting the list.
        /// </summary>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <returns></returns>
        public int Compare(player x, player y)
        {
            try
            {
                if (x.correct == y.correct)
                {
                    return y.time.CompareTo(x.time);
                }

                return x.correct.CompareTo(y.correct);
            }
            catch (Exception ex)
            {
                //Just throw the exception
                throw new Exception(MethodInfo.GetCurrentMethod().DeclaringType.Name + "." +
                                    MethodInfo.GetCurrentMethod().Name + " -> " + ex.Message);
            }
        }
    }

    /// <summary>
    /// Holds the logic for the score board.
    /// </summary>
    public class clsScores
    {
        /// <summary>
        /// The score board object.
        /// </summary>
        public static List<player> board = new List<player>();

        /// <summary>
        /// Adds the player's score to the board, and then sorts the list.
        /// </summary>
        /// <param name="name"></param>
        /// <param name="correct"></param>
        /// <param name="incorrect"></param>
        /// <param name="time"></param>
        public void addScore(string name, int correct, int incorrect, int time)
        {
            try
            {
                player p1 = new player();
                p1.name = name;
                p1.correct = correct;
                p1.incorrect = incorrect;
                p1.time = time;
                board.Add(p1);
                comp sorter = new comp();
                board.Sort(sorter);
                board.Reverse();
            }
            catch (Exception ex)
            {
                //Just throw the exception
                throw new Exception(MethodInfo.GetCurrentMethod().DeclaringType.Name + "." +
                                    MethodInfo.GetCurrentMethod().Name + " -> " + ex.Message);
            }
        }

        /// <summary>
        /// Returns a string that is the formatted list for the score board.
        /// </summary>
        /// <returns>string</returns>
        public string formatList()
        {
            try
            {
                string result = "";
                if (board.Count() < 10)
                {
                    for (int i = 0; i < board.Count(); i++)
                    {
                        result += board[i].name + "\t\t " + board[i].correct + "\t       " + board[i].incorrect + "\t\t   " + board[i].time + "\n";
                    }
                }
                else
                {
                    for (int i = 0; i < 10; i++)
                    {
                        result += board[i].name + "\t\t " + board[i].correct + "\t       " + board[i].incorrect + "\t\t   " + board[i].time + "\n";
                    }
                }
                return result;
            }
            catch (Exception ex)
            {
                //Just throw the exception
                throw new Exception(MethodInfo.GetCurrentMethod().DeclaringType.Name + "." +
                                    MethodInfo.GetCurrentMethod().Name + " -> " + ex.Message);
            }
        }

        public bool onBoard(string name, int correct, int incorrect, int time)
        {
            try
            {
                for (int i = 0; i < 10; i++)
                {
                    if (board[i].name == name && board[i].correct == correct && board[i].incorrect == incorrect && board[i].time == time)
                    {
                        return true;
                    }

                }
                return false;
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
