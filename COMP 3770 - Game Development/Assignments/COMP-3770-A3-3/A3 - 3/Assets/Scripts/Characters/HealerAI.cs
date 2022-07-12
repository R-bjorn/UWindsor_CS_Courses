using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HealerAI : MonoBehaviour
{
    public string unitName;
    public string role;

    public int dmgRangeLow;
    public int dmgRangeHigh;

    public int maxHP;
    public int currentHP;

    public int maxMana;
    public int currentMana;

    public bool takeDmg(int dmg)
    {
        currentHP -= dmg;

        if (currentHP <= 0)
            return true;
        return false;
    }

    public int dealDmg()
    {
        return Random.Range(dmgRangeLow, dmgRangeHigh);
    }

    public int smallHeal()
    {
        if (currentMana >= 5)
        {
            currentMana -= 5;
            return 15;
        }
        return 0;
            
    }

    public int bigHeal()
    {
        if (currentMana >= 10)
        {
            currentMana -= 10;
            return 25;
        }
        return 0;

    }

    public int freeHeal()
    {
        if (Random.Range(0, 1) == 0)
            return 15;
        return 25;
    }





}
