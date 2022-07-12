using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossAI : MonoBehaviour
{
    public string unitName;
    public string role;

    //range of dmg dealt to tank
    public int dmgRangeLowTank;
    public int dmgRangeHighTank;

    //range of dmg dealt to other
    public int dmgRangeLow;
    public int dmgRangeHigh;

    public int maxHP;
    public int currentHP;

    public bool takeDmg(int dmg)
    {
        currentHP -= dmg;

        if (currentHP <= 0)
            return true;
        return false;
    }

    public int dealDmgTank()
    {
        return Random.Range(dmgRangeLowTank, dmgRangeHighTank);
    }

    public int dealDmg()
    {
        return Random.Range(dmgRangeLow, dmgRangeHigh);
    }



}
