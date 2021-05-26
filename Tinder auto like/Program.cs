using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace Tinder_auto_like
{
    class Program
    {
      
        private static readonly HttpClient client1 = new HttpClient();
        static  void  Main(string[] args)
        { 
            Program gram = new Program();
            gram.autoLike();
            Console.ReadLine();
        }

        /// <summary>
        /// Auto likes the user's recs who have bios until they are out of likes
        /// </summary>
        public async void  autoLike()
        {

            //the body of the request
            var values = new Dictionary<string, string>
            {
                {"app_version", "2.78.0" },
                {"platform", "web" },
                {"ContentType", "application/json" },
                {"User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36" },
                { "Accept", "application.json"}
                 
            };

            //putting the key in the header
            //client1.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("fc7e488f-d6c9-433d-a9ac-ef85181277fe");
            client1.DefaultRequestHeaders.Add("app_version", "2.78.0");
            client1.DefaultRequestHeaders.Add("platform", "web");
            client1.DefaultRequestHeaders.Add("ContentType", "application/json");
            
            client1.DefaultRequestHeaders.Add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
           // client1.DefaultRequestHeaders.Add("phone_number", "13852434677");
           var content = new FormUrlEncodedContent(values);
            //  var response = await client1.PostAsync("https://api.gotinder.com/v3/auth/login?locale=en", content);
            //var responseString = await response.Content.ReadAsStringAsync();

            string auth = "99b77fff-45aa-4241-a879-0c668052c86a";

           


            Console.WriteLine("Welcome! Would you like to change your auth token? y/n");
            auth = Console.ReadLine();
            string path = @"auth.txt";
            if (auth == "y")
            {
                Console.WriteLine("Please enter an auth token");
                auth = Console.ReadLine();

                if (File.Exists(path))
                {
                    File.Delete(path);
                }

                using (StreamWriter sw = File.AppendText(path)) {

                  
                    sw.WriteLine(auth);
 
                }
            }
            else
            {
                System.IO.StreamReader file =
                 new System.IO.StreamReader(@"auth.txt");

                auth = file.ReadLine();

            }
          

            //client1.DefaultRequestHeaders.Add("Accept", "application.json");
            client1.DefaultRequestHeaders.Add("X-Auth-Token", auth);

            //formatting the body
            
            string id = "5ef06444effe5901001794a8";
           

            for (int i = 0; i < 1000; i++)
            {
                 content = new FormUrlEncodedContent(values);

                //Get the user recs
                var response2 = await client1.PostAsync("https://api.gotinder.com/user/recs", content);
                if(response2.ReasonPhrase == "Too Many Requests")
                {
                    Console.WriteLine("Too many requests!");
                    break;
                }
                var responseString2 = await response2.Content.ReadAsStringAsync();

                if(response2.StatusCode != System.Net.HttpStatusCode.OK)
                {
                    Console.WriteLine("Whoops something went wrong!");
                    Console.WriteLine(responseString2);
                    break;
                }

                //get the id of the first user
                id = responseString2.Substring(responseString2.IndexOf("_id") + 6, id.Length);

                //get the name of that user
                string name = responseString2.Substring(responseString2.IndexOf("name")+7, id.Length);

                string bio = responseString2.Substring(responseString2.IndexOf("bio"), responseString2.IndexOf("birth_date")-responseString2.IndexOf("bio"));
                Console.WriteLine(bio);

               
                content = new FormUrlEncodedContent(values);

                System.Threading.Thread.Sleep(1000);

                //Like the user

                if (bio != "bio\":\"\",\"")
                {
                    var response1 = await client1.PostAsync("https://api.gotinder.com/like/" + id, content);
                    var responseString1 = await response1.Content.ReadAsStringAsync();
                    if (name.IndexOf('"') != -1)
                    {
                        name = name.Substring(0, name.IndexOf('"'));

                        //print the name
                        Console.WriteLine("\nYou liked: " + name+ "\n");
                    }
                }
                else
                {
                    var response1 = await client1.PostAsync("https://api.gotinder.com/pass/" + id, content);
                    var responseString1 = await response1.Content.ReadAsStringAsync();
                    if (name.IndexOf('"') != -1)
                    {
                        name = name.Substring(0, name.IndexOf('"'));

                        //print the name
                        Console.WriteLine("\nYou disliked: " + name + "\n");
                    }
                }
               
            }
            Console.WriteLine("End");
        }
    }
}
