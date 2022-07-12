using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BossUI : MonoBehaviour
{
    public string unitName;
    public string role;

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

    public int dealDmg()
    {
        return Random.Range(dmgRangeLow, dmgRangeHigh);
    }



}
