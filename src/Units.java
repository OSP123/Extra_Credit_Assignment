/*
 Omar Patel
 CS1B - Professor Cecil
 */

abstract class Units
{
   int strength, toughness, attacks, save, wounds;
   String type;
   public final static int TOUGHNESS_CHECK = 6;
   
   Units()
   {
      this("TroopType", 0, 0, 0, 0, 0);
   }

   Units(String unitType, int str, int tough, int atk, int sve, int wnds)
   {
      type = unitType;
      strength = str;
      toughness = tough;
      attacks = atk;
      save = sve;
      wounds = wnds;
   }
   
    public boolean saveCheck()
    {
       int dieRoll = (int)(Math.random()*6) + 1;
       if (dieRoll >= save)
          return true;
       else
          return false;
    }
    
    public boolean attackUnit(Army enemyArmy, Units enemyUnit)
    {
       int hits = 0;
       boolean retVal = false;
       
       for (int i = 0; i < getAttacks(); i++)
          if (toughnessCheck(enemyUnit) == false 
             && enemyUnit.saveCheck() == false)
             hits++;
       
       if (hits > 0)
       {
          retVal = true;
          int theWounds = enemyUnit.damage(enemyArmy, hits);
          if (theWounds == 0)
          {
             Units.dead(enemyUnit);
             enemyArmy.unitsList.remove(enemyUnit);
             enemyArmy.setNumUnits(enemyArmy.getNumUnits() - 1);
          }
          else
             Units.getHit();
             enemyUnit.setWounds(enemyUnit.getWounds() - theWounds);
       }
       else
          retVal = false;
       
       return retVal;
    }
    
    public int damage(Army army, int hits)
    { 
       setWounds(hits);
       if (hits >= getWounds())
       {
          return 0;
       }
       else
          return getWounds();
    }
    
    public static void dead(Units enemyUnit)
    {
       System.out.println("Ahhhhhhhh!" + "\n" + "A " + enemyUnit.type 
          + " has been killed");
    }
    
    public static void getHit()
    {
       System.out.println("Gwah, that hurt!");
    }
    
    public int getStrength()
   {
      return strength;
   }

   public void setStrength(int strength)
   {
      this.strength = strength;
   }

   public int getToughness()
   {
      return toughness;
   }

   public void setToughness(int toughness)
   {
      this.toughness = toughness;
   }

   public int getAttacks()
   {
      return attacks;
   }

   public void setAttacks(int attacks)
   {
      this.attacks = attacks;
   }

   public int getSave()
   {
      return save;
   }

   public void setSave(int save)
   {
      this.save = save;
   }

   public int getWounds()
   {
      return wounds;
   }

   public void setWounds(int wounds)
   {
      this.wounds = wounds;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public boolean toughnessCheck(Units enemyUnit)
    {
       int dieRoll = (int)(Math.random()*6) + 1;
       if (getStrength() >= enemyUnit.getToughness())
          return false;
       else if (dieRoll == TOUGHNESS_CHECK)
          return true;
       else
          return false;
    }
}

class TroopUnit extends Units
{
   TroopUnit()
   {
      super("Basic Troop", 3, 3, 1, 5, 1);
   }
}

class EliteTroopUnit extends Units
{
   EliteTroopUnit()
   {
      super("Elite", 3, 3, 1, 4, 1);
   }
}

class CommandUnit extends Units
{
   CommandUnit()
   {
      super("Command", 5, 4, 3, 5, 3);
   }
}

class CommandTroopUnit extends Units
{
   CommandTroopUnit()
   {
      super("Command Troop", 4, 3, 3, 5, 3);
   }
}

class FastAttackUnit extends Units
{
   FastAttackUnit()
   {
      super("Fast Attack", 4, 4, 3, 4, 3);
   }
}

class HeavyAttackUnit extends Units
{
   HeavyAttackUnit()
   {
      super("Heavy Attack", 5, 5, 3, 2, 3);
   }
}
