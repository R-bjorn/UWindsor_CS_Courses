using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Unit : MonoBehaviour
{
    public string unitName;
    public string role;

    public int dmgRangeLow;
    public int dmgRangeHigh;
    public int dmgToBoss;

    public int maxHP;
    public int currentHP;

    public bool takeDmg(int dmg)
    {
        currentHP -= dmg;

        if (currentHP <= 0)
            return true;
        return false;
    }

    public int dealDmg()
    {
        dmgToBoss = Random.Range(dmgRangeLow, dmgRangeHigh);
        return dmgToBoss;
    }



}
