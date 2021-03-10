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
    /// Holds all of the user's information.
    /// </summary>
    public class clsUser
    {
        /// <summary>
        /// The name of the user.
        /// </summary>
        public string name { get; set; }

        /// <summary>
        /// The age of the user.
        /// </summary>
        public int age { get; set; }

        /// <summary>
        /// Constructor for clsUser.
        /// </summary>
        /// <param name="name"></param>
        /// <param name="age"></param>
        public clsUser(string name, int age)
        {
            try
            {
                this.name = name;
                this.age = age;
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
