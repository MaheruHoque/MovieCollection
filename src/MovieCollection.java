import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MovieCollection
{
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName)
  {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies()
  {
    return movies;
  }
  
  public void menu()
  {
    String menuOption = "";
    
    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");
    
    while (!menuOption.equals("q"))
    {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();
      
      if (!menuOption.equals("q"))
      {
        processOption(menuOption);
      }
    }
  }
  
  private void processOption(String option)
  {
    if (option.equals("t"))
    {
      searchTitles();
    }
    else if (option.equals("c"))
    {
      searchCast();
    }
    else if (option.equals("k"))
    {
      searchKeywords();
    }
    else if (option.equals("g"))
    {
      listGenres();
    }
    else if (option.equals("r"))
    {
      listHighestRated();
    }
    else if (option.equals("h"))
    {
      listHighestRevenue();
    }
    else
    {
      System.out.println("Invalid choice!");
    }
  }

  private void searchTitles()
  {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++)
    {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1)
      {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    // sort the results by title
    sortResults(results);

    // now, display them all to the user
    for (int i = 0; i < results.size(); i++)
    {
      String title = results.get(i).getTitle();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;

      System.out.println("" + choiceNum + ". " + title);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");

    int choice = scanner.nextInt();
    scanner.nextLine();

    Movie selectedMovie = results.get(choice - 1);

    displayMovieInfo(selectedMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

  private void sortResults(ArrayList<Movie> listToSort)
  {
    for (int j = 1; j < listToSort.size(); j++)
    {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
      {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie)
  {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }
  
  private void searchCast()
  {
    System.out.print("Who would you like to search for? ");;
    String person = scanner.nextLine();

    person = person.toLowerCase();

    ArrayList<Movie> outcome = new ArrayList<Movie>();
    ArrayList<String> actors = new ArrayList<String>();

    for(int i = 0; i < movies.size(); i++)
    {
      String[] actorList = movies.get(i).getCast();

      for(int j = 0; j < actorList.length; j++)
      {
        String lower = actorList[j].toLowerCase();
        if(lower.indexOf(person) != -1)
        {
          if(actors.indexOf(actorList[j]) == -1)
          {
            if(outcome.indexOf(movies.get(i)) == -1)
            {
              outcome.add(movies.get(i));
            }
            actors.add(actorList[j]);
          }
        }
      }
    }

    for(int i = 0; i < actors.size(); i++)
    {
      String actor = actors.get(i);

      int choice = i + 1;

      return choice + actor;
    }

    for(int i = 0; i < movies.size(); i++)
    {
      String[] cast = movies.get(i).getCast();
      for(int j = 0; j < cast.length; j++)
      {
        if(cast[j].equals(actors.get(choice - 1)));
        {
          outcome.add(movies.get(i));
        }
      }
    }

    sortResults(outcome);

    for(int i = 0; i < outcome.size(); i++)
    {
      String name = outcome.get(i).getTitle();
      int choice = i + 1;
      return choice + name;
    }
  }

  private void searchKeywords()
  {
      System.out.println("Enter keyword: ");;
      String search = scanner.nextLine();

      search = search.toLowerCase();
      ArrayList<Movie> result = new ArrayList<Movie>();
      for(int i = 0; i < movies.size(); i++)
      {
        String key = movies.get(i).getKeywords();
        if(key.indexOf(search) != -1)
        {
          result.add(movies.get(i));
        }
      }

      sortResults(result);
      for(int i = 0; i < result.size(); i++)
      {
        String mName =  result.get(i);

        int choice = i;
      }

      System.out.println("Pick another movie: ");
      int chosenMovie = scanner.nextInt();

      Movie selection  = result.get(chosenMovie - 1);
  }
  
  private void listGenres()
  {
    ArrayList<String> genreList = new ArrayList‹String>();
    ArrayList‹Movie› results = new ArrayList<Movie>();
    for (int i = 0; i < movies.size(); i++)
    {
      String[] genres = movies.get(i).getGenres();
      for (int j = 0; j < genres .length; j++)
      {
        if (genreList.index0f(genres[j) == -1)
        genreList.add(genres([j]);
      }
    }

    sortString List(genreList);

    for (int i = 0; i < genreList.size(); i++)
    {
      String genre= genrelist.get(i);
      int choiceNum = i + 1;
      System.out.println("* + choiceNum + " " + genre));
              System.out.println("Which would you like to see all movies for?");

      int choice = scanner. nextInt();
      scanner.nextLine();

      for (int i = 0; i ‹ movies.size(); i++)
      String[] genres = movies.get(i).getGenres();
      for (int j = 0; j < genres.length; j++)
      {
        if (genres[3].equals(genreList.get (choice - 1)))
        results.add(movies.get(1));
      }

      for (int j = 0; j < genres.length; j++)
      {
        if (genres[j]. equals(genreList.get (choice - 1)))
        {
          results.add(movies .get(i));
        }
// put results in alphabetical order
        sortResults(results);

        for (int i = 0; i < results.size(); i++)
        {
          String movie = results.get(i).getTitle();
          int choice  = i + 1;
          System.out.println("* + choiceNum + " + movie);

        }
        System.out.println("Which movie would you like to learn more about?"),
        int choiced = scanner.nextInt();
        scanner. nextLine();
        Movie selectedMov = results.get (choiced - 1);
        displayMovieInfo(selectedMov);
        System.out.println("in ** Press Enter to Return to Main Menu *em);
                scanner.nextLine());
      }
  
  private void listHighestRated()
  {
    /* TASK 6: IMPLEMENT ME! */
  }
  
  private void listHighestRevenue()
  {
    /* TASK 6: IMPLEMENT ME! */
  }

  private void importMovieList(String fileName)
  {
    FileReader fileReader = new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileName);
    String line = bufferedReader.readLine();

    while ((line = bufferedReader.readLine()) != null)
    {
      String[] moviesFromCSV = line.split(", ");

      String title = moviesFromCSV[0];
      String cast = moviesFromCSV[1];
      String director = moviesFromCSV[2];
      String tagLine = moviesFromCSV[3];
      String keyWords = moviesFromCSV[4];
      String overview = moviesFromCSV[5];
      int runTime = moviesFromCSV[6];
      String genre = moviesFromCSV[7];
      double userRating = moviesFromCSV[8];
      int year = moviesFromCSV[9];
      int revenue = moviesFromCSV[10];

      Movie movies = new Movie(title, cast, director, tagLine, keyWords, overview, runTime, genre, userRating, year, revenue);
    }
    bufferedReader.close();

    catch(IOException exception)
    {
      System.out.println("Unable to access " + exception.getMessage());
    }
  }
  
  // ADD ANY ADDITIONAL PRIVATE HELPER METHODS you deem necessary

}