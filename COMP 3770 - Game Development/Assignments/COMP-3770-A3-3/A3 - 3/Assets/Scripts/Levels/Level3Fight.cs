using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using Random = UnityEngine.Random;

public class Level3Fight : MonoBehaviour
{

    BossAI boss;
    Unit warrior;
    Unit rogue;
    Unit druid;
    Unit mage;
    HealerAI priest;

    int dmg, sHeal, bHeal, wDmg, rDmg, dDmg, mDmg, pDmg, totalDmg, bonusDmg;
    bool dead;
    float dmgFrac;

    public Text bHP, bAction, wHP, wAction, rHP, rAction, dHP, dAction, mHP, mAction, pHP, pMana, pAction;
    public TextMeshProUGUI dmgedBoss, dmgedParty;
    
    public int damageTakeparty = 0;
    public List<int> damageToBoss;
    public List<int> damageToParty;
    // Start is called before the first frame update
    void Start()
    {
        //start setup
        setup();


    }

    void setup()
    {
        //get reference to gameobjs and set text
        GameObject bossObj = GameObject.Find("Boss");
        boss = bossObj.GetComponent<BossAI>();
        bHP.text = "HP: " + boss.maxHP;

        GameObject warObj = GameObject.Find("Warrior");
        warrior = warObj.GetComponent<Unit>();
        wHP.text = "HP: " + warrior.maxHP;

        GameObject rogueObj = GameObject.Find("Rogue");
        rogue = rogueObj.GetComponent<Unit>();
        rHP.text = "HP: " + rogue.maxHP;

        GameObject druidObj = GameObject.Find("Druid");
        druid = druidObj.GetComponent<Unit>();
        dHP.text = "HP: " + druid.maxHP;

        GameObject mageObj = GameObject.Find("Mage");
        mage = mageObj.GetComponent<Unit>();
        mHP.text = "HP: " + mage.maxHP;

        GameObject priestObj = GameObject.Find("Priest");
        priest = priestObj.GetComponent<HealerAI>();
        pHP.text = "HP: " + priest.maxHP;
        pMana.text = "Mana: " + priest.maxMana;

    }

    void FixedUpdate()
    {

        //battle functions. if someone dies , return true and end battle
        if (PlayerAttack())
            EndBattle();
        if (BossAttack())
            EndBattle();
        WriteToCsv();
    }
    
    private void WriteToCsv()
    {
        string path = Application.dataPath + "/CSV/Level3.csv";
        
        try
        {
            using (StreamWriter writer = new StreamWriter(@path))
            {
                // Damage to Party by Boss
                damageTakeparty = (warrior.maxHP - warrior.currentHP) +
                    (rogue.maxHP - rogue.currentHP) + (mage.maxHP - mage.currentHP) + (druid.maxHP - druid.currentHP) - damageToParty.Sum();
                damageToParty.Add(damageTakeparty);
                
                // Damage to Boss By Party
                damageToBoss.Add(warrior.dmgToBoss + rogue.dmgToBoss + mage.dmgToBoss + druid.dmgToBoss);
                
                // Writing Max in CSV file
                writer.WriteLine($"{damageToParty.Max()}");
                writer.WriteLine($"{damageToBoss.Max()}\n");

                // Closing the csv file
                writer.Flush();
                writer.Close();
            }
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    bool PlayerAttack()
    {
        // Total Damage Update
        if (damageToParty.Count != 0 && damageToParty.Count != 0)
        {
            dmgedBoss.text = $"Total Damage to Boss : {damageToParty[damageToParty.Count - 1]}";
            dmgedParty.text = $"Total Damage to Party : {damageToBoss[damageToBoss.Count - 1]} ";
        }
        //war attack
        dmg = warrior.dealDmg();
        dead = boss.takeDmg(dmg);
        wAction.text = "Action: Deal " + dmg + " damage to Boss.";
        bHP.text = "HP: " + boss.currentHP;
        if (dead)
            return true;

        //rogue attk
        dmg = rogue.dealDmg();
        dead = boss.takeDmg(dmg);
        rAction.text = "Action: Deal " + dmg + " damage to Boss.";
        bHP.text = "HP: " + boss.currentHP;
        if (dead)
            return true;

        //dru attk
        dmg = druid.dealDmg();
        dead = boss.takeDmg(dmg);
        dAction.text = "Action: Deal " + dmg + " damage to Boss.";
        bHP.text = "HP: " + boss.currentHP;
        if (dead)
            return true;

        //mage attk
        dmg = mage.dealDmg();
        dead = boss.takeDmg(dmg);
        mAction.text = "Action: Deal " + dmg + " damage to Boss.";
        bHP.text = "HP: " + boss.currentHP;
        if (dead)
            return true;


        //priest heal tank
        //overheal is a feature
        bHeal = priest.bigHeal();
        warrior.currentHP += bHeal;
        wHP.text = "HP: " + warrior.currentHP;

        //priest heal other
        int target = Random.Range(1, 6);
        switch (target)
        {
            case 1:
                sHeal = priest.smallHeal();
                rogue.currentHP += sHeal;
                pAction.text = "Action: Heal Rogue by " + sHeal + " HP & Warrior by " + bHeal + " HP.";
                rHP.text = "HP: " + rogue.currentHP;
                break;
            case 2:
                sHeal = priest.smallHeal();
                druid.currentHP += sHeal;
                pAction.text = "Action: Heal Druid by " + sHeal + " HP & Warrior by " + bHeal + " HP.";
                dHP.text = "HP: " + druid.currentHP;
                break;
            case 3:
                sHeal = priest.smallHeal();
                mage.currentHP += sHeal;
                pAction.text = "Action: Heal Mage by " + sHeal + " HP & Warrior by " + bHeal + " HP.";
                mHP.text = "HP: " + mage.currentHP;
                break;
            default:
                sHeal = priest.smallHeal();
                priest.currentHP += sHeal;
                pAction.text = "Action: Heal self by " + sHeal + " HP & Warrior by " + bHeal + " HP.";
                pHP.text = "HP: " + priest.currentHP;
                break;
        }

        //regen n update priest mana
        priest.currentMana += 3;
        pMana.text = "Mana: " + priest.currentMana;

        //boss didnt die
        return false;

    }

    bool BossAttack()
    {
        //boss attack war
        wDmg = boss.dealDmgTank();
        dead = warrior.takeDmg(wDmg);
        //wHP.text = "HP: " + warrior.currentHP; dont need this in this level interation (written below)
        if (dead)
            return true;

        //boss attack rogue
        rDmg = boss.dealDmg();
        dead = rogue.takeDmg(rDmg);
        rHP.text = "HP: " + rogue.currentHP;
        if (dead)
            return true;

        //boss attack druid
        dDmg = boss.dealDmg();
        dead = druid.takeDmg(dDmg);
        dHP.text = "HP: " + druid.currentHP;
        if (dead)
            return true;

        //boss attack mage
        mDmg = boss.dealDmg();
        dead = mage.takeDmg(mDmg);
        mHP.text = "HP: " + mage.currentHP;
        if (dead)
            return true;

        //boss attack priest
        pDmg = boss.dealDmg();
        dead = priest.takeDmg(pDmg);
        pHP.text = "HP: " + priest.currentHP;
        if (dead)
            return true;

        //add total dmg deal to party
        totalDmg += wDmg + rDmg + dDmg + mDmg + pDmg;
        dmgFrac = totalDmg * 0.01f;
        bonusDmg = Mathf.RoundToInt(dmgFrac);
        
        //boss attack war again
        dead = warrior.takeDmg(bonusDmg);
        wHP.text = "HP: " + warrior.currentHP;
        if (dead)
            return true;


        //display dmg dealt by boss in this timestep
        bAction.text = "Action: Dealing damage. " + wDmg + " to War, " + rDmg + " to Rge, " + dDmg + " to Dru, " + mDmg + " to Mge, " + pDmg + " to Pst. Deal " + bonusDmg + " bonus dmg to War.";
        

        //no one died
        return false;



    }

    void EndBattle()
    {
        //load game over scene (check build settings)
        //SceneManager.LoadScene(2);

        //currently just pauses the update
        this.enabled = false;
        // Game Over Scene
        SceneManager.LoadScene(5);
    }
}
