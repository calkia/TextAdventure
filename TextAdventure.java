import java.util.*;

public class TextAdventure 
{
  FancyConsole console;
  Scanner inScanner;
  Player ourHero;
  String input;
  boolean chooseAgain;

  public TextAdventure()
  {
    console = new FancyConsole("Great Text Adventure!", 600, 600);
    inScanner = new Scanner(System.in);

    // feel free to change the player's starting values
    ourHero = new Player("Bob", 100, 0);
  }

  public void play()
  {
    // start of adventure. You can change this if you like
    console.setImage("beach.jpeg");

    // ask the user for their name.
    System.out.println("What is your name?\n");
    input = inScanner.nextLine();

    // Change ourHero's name
    ourHero.changeName(input);
    
    // describe the starting situation. Feel free to change this
    System.out.println("You wake up to find yourself on a peaceful beach with the sun nearly set. \nYou see what loks like a village in the distance. \nWhat would you like to do? \nbeach: stay on the beach for the night\nvillage: walk to the village\nplains: walk to the plains nearby\n" + ourHero.getName() + ": ");

    // get user input and go to the appropriate zone based on their input
    input = inScanner.nextLine();
    if (input.equalsIgnoreCase("village")){
      enterVillage();
    }
    else if (input.equalsIgnoreCase("plains")){
      enterPlains();
    }
    else if (input.equalsIgnoreCase("beach")){
      System.out.println("--------------------------------------------------------------------");
      System.out.println("You decide to stay on the beach for the night. \nHowever you are starting to get hungry. \nYou see a bush of glowing yellow fruit, a bush of regular looking fruit, and a bush of neon green fruit. \nyellow: pick the glowing yellow fruit \nregular: pick the regular fruit \nneon: pick the neon green fruit\n" + ourHero.getName() + ": ");
      input = inScanner.nextLine();
      System.out.println("--------------------------------------------------------------------");
      if (input.equalsIgnoreCase("yellow")){
        console.setImage("glowingFruit.jpg");
        ourHero.setHealth(ourHero.getHealth()+20);
        System.out.println("Congrats, the glowing yellow fruit was a special fruit! You gained 20 extra health! \nYou survived the night.\n"+ourHero.stats()+"\nWhat would you like to do? \nvillage: walk to the village \nplains: walk to the plains nearby\n" + ourHero.getName() + ": ");
      }
      else if (input.equalsIgnoreCase("regular")){
        console.setImage("regFruit.jpg");
        System.out.println("Congrats, regular fruit was infact, regular fruit! \nYou survived the night. \n"+ ourHero.stats()+"\nWhat would you like to do? \nvillage: walk to the village \nplains: walk to the plains nearby\n" + ourHero.getName() + ": ");
      }
      else if (input.equalsIgnoreCase("neon")){
        console.setImage("neonFruit.jpg");
        gameEnd("Survival 101: neon things are always poisonous...why would you eat that?");
      }
      
      input = inScanner.nextLine();
      if (input.equalsIgnoreCase("village")){
        enterVillage();
      }
      else if (input.equalsIgnoreCase("plains")){
        enterPlains();
      }
    }

  }

  private void enterVillage()
  {
    // change image
    console.setImage("village.jpg");

    // describe the area/situation to the user. 
    // Give them options for choices.
    System.out.println("--------------------------------------------------------------------");
    System.out.println("You enter a small quaint village with villagers peacefully walking about. \nYou find some loose change on the ground! Pick it up?\nyes: pick up the money \nno: eww why would I pick up random dirty coins\n"+ ourHero.getName() + ": ");
    input = inScanner.nextLine();
    if (input.equalsIgnoreCase("yes")){
      System.out.println("--------------------------------------------------------------------");
      ourHero.setGold(ourHero.getGold()+20);
      System.out.println("Money is money. \n"+ourHero.stats());
    }
    else if (input.equalsIgnoreCase("no")){
      System.out.println("--------------------------------------------------------------------");
      ourHero.setHealth(ourHero.getHealth()+10);
      System.out.println("Nice you avoided any nasty diseases that could have come from those coins!\n"+ourHero.stats());
    }
    System.out.println("--------------------------------------------------------------------");
    System.out.println("Its starting to get late, time to look for a place to rest. \n You see a nicely furnished inn, but it costs 20 coins for a night. You also see a shady looking inn for 5 coins only! \nYour current gold: "+ourHero.gold+"\nexpensive: pay the price for a luxery inn experiance \ncheap: stay frugal and safe with the cheap inn \noutside: take you chances out in the wild outside the village, its free!\n"+ ourHero.getName() + ": ");
    input = inScanner.nextLine();
    do{
      System.out.println("--------------------------------------------------------------------");
      chooseAgain = false;
      if (input.equalsIgnoreCase("expensive") && ourHero.getGold() >= 20){
        System.out.println("You stayed the night in comfortable luxery!");
        ourHero.setGold(ourHero.getGold()-20);
        System.out.println(ourHero.stats());
      }
      else if (input.equalsIgnoreCase("cheap") && ourHero.getGold() >=5){
        ourHero.setGold(0);
        gameEnd("The shady inn owners killed you and robbed your body! Some things are too good to be true...");
      }
      else if (input.equalsIgnoreCase("outside")){
        System.out.println("While uncomfortable you survived! However due to the cold your health deteriorated.");
        ourHero.setHealth(ourHero.getHealth()-10);
        System.out.println(ourHero.stats());
      }
      else{
        chooseAgain = true;
        System.out.println("You don't have enough gold for this silly! \nChoose again!\n"+ ourHero.getName() + ": ");
        input = inScanner.nextLine();
      }
    }while(chooseAgain == true);

    System.out.println("--------------------------------------------------------------------");
    System.out.println("Its time to explore again! \nYou see plains to the north, a rocky shore to the east, and a tall mountain range in the middle.\nplains: travel to the plains \nrocky: travel to the rocky shore \nmountains: travel to the mountain range\n"+ ourHero.getName() + ": ");
    input = inScanner.nextLine();

    if (input.equalsIgnoreCase("plains")){
      enterPlains();
    }
    else if (input.equalsIgnoreCase("rocky")){
      enterRockyShore();
    }
    else if (input.equalsIgnoreCase("mountains")){
      enterMountains();
    }

    // Take action or go to another zone based on their choice
    // ADD CODE HERE

  }

  private void enterPlains()
  {
    // change image
    console.setImage("plains.jpg");

    // describe the area/situation to the user. 
    // Give them options for choices.
    System.out.println("--------------------------------------------------------------------");
    System.out.println("You enter a flat plain with bristling tall grass. \nWhile walking through the grass you trip on... \n...what is that actually?\nIt's a grass-dweller! \nescape: run run run away!! \nfight: lets battle!!\n"+ ourHero.getName() + ": ");
    input = inScanner.nextLine();
    System.out.println("--------------------------------------------------------------------");
    if (input.equalsIgnoreCase("fight")){
      battleGrassDweller();
    }
    else if (input.equalsIgnoreCase("escape")){
      System.out.println("Whew you escaped the grass dweller!");
    }

    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    System.out.println("--------------------------------------------------------------------");
    System.out.println("It's time to move on again. \nYou see a small village to the south, a rocky shore to the east, and a tall mountain range inbetween. \nvillage: travel to the village \nrocky: travel to the rocky shore \nmountains: travel to the mountain range\n"+ ourHero.getName() + ": ");
    if (input.equalsIgnoreCase("village")){
      enterVillage();;
    }
    else if (input.equalsIgnoreCase("rocky")){
      enterRockyShore();
    }
    else if (input.equalsIgnoreCase("mountains")){
      enterMountains();
    }
    
  }

  private void enterRockyShore()
  {
    // change image
    // ADD CODE HERE

    // describe the area/situation to the user. 
    // Give them options for choices.
    // ADD CODE HERE

    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    
  }

  private void enterMountains()
  {
    // change image
    // ADD CODE HERE

    // describe the area/situation to the user. 
    // Give them options for choices.
    // ADD CODE HERE

    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    
  }

  private void enterSkyCity()
  {
    // change image
    // ADD CODE HERE

    // describe the area/situation to the user. 
    // Give them options for choices.
    // ADD CODE HERE

    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    
  }

  private void enterUndergroundCity()
  {
    // change image
    // ADD CODE HERE

    // describe the area/situation to the user. 
    // Give them options for choices.
    // ADD CODE HERE

    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    
  }

  private void gameEnd(String message)
  {
    System.out.println("YOU DIED");
    if (ourHero.getGold() > 100 && ourHero.getMonstersDefeated() > 2){
      System.out.println("You lived an honorable and rich life! (Best Ending)");
    }
    else if (ourHero.getGold() > 100){
      System.out.println("Your relatives rejoiced! You left them a hefty inheritence with all the wealth you collected. (Good Ending)");
    }
    else if (ourHero.getMonstersDefeated() > 2){
      System.out.println("You relatives are proud to be related to you! You lived the life of a true warrior. (Good Ending)");
    }
    else{
      System.out.println("You lived a meaningless and sad life! Poor and no acomplishments to show. (Bad Ending)");
    }
    System.out.println(message);
    System.out.println("Gold: "+ourHero.getGold()+"  Monsters Defeated: "+ourHero.getMonstersDefeated());

    inScanner.close();
  }

  private void battleGrassDweller(){
    int grassDwellerHealth = 30;
    boolean battling = true;
    boolean win = false, lose = false, escape = false;
    int rand1, rand2;
    System.out.println("You've challenged the grass-dweller!");
    while (battling){
      System.out.println("GrassDweller: "+grassDwellerHealth+" health\nYou: "+ourHero.getHealth()+" health\nattack: attack the grass dweller with a random attack amount (5-15)\nescape: escape the grass dweller!\n"+ ourHero.getName() + ": ");
      input = inScanner.nextLine();
      if (input.equalsIgnoreCase("attack")){
        rand1 = (int)((Math.random()*11)+5);
        rand2 = (int)((Math.random()*11)+5);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("You attacked grass-dweller and dealt "+rand1+" damage!");
        grassDwellerHealth -= rand1;
        System.out.println("Grass Dweller attacks back! Dealing: "+rand2+" damage!");
        ourHero.setHealth(ourHero.getHealth()-rand2);
      }
      else if (input.equalsIgnoreCase("escape")){
        escape = true;
      }
      if (ourHero.getHealth()<= 0){
        lose = true;
      }
      else if (grassDwellerHealth <= 0){
        win = true;
      }
      if (win || lose || escape){
        battling = false;
      }
    }
    System.out.println("--------------------------------------------------------------------");
    if (win){
      ourHero.setGold(ourHero.getGold()+50);
      ourHero.defeatMonster();
      System.out.println("You've won!\nYou gained 50 gold!\n"+ourHero.stats());;
    }
    else if (lose){
      gameEnd("You lost the fight with the grass-dweller. Should have escaped while you had the chance!!");
    }
    else{
      System.out.println("Whew, you escaped the land-dweller!");
    }
    
  }
}