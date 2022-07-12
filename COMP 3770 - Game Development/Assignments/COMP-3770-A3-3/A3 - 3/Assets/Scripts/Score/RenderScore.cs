using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using TMPro;
using UnityEngine;

public class RenderScore : MonoBehaviour
{
    public TextMeshProUGUI L1B, L2B, L3B;
    public TextMeshProUGUI L1P, L2P, L3P;
    private string level1Path;
    private string level2Path;
    private string level3Path;
    private void Start()
    {
        level1Path = Application.dataPath + "/CSV/Level1.csv";
        level2Path = Application.dataPath + "/CSV/Level2.csv";
        level3Path = Application.dataPath + "/CSV/Level3.csv";
    }

    private void FixedUpdate()
    {
        
        using (StreamReader reader = new StreamReader(level1Path))
        {
            L1B.text = reader.ReadLine();
            L1P.text = reader.ReadLine();
            reader.Close();
        }

        using (StreamReader reader = new StreamReader(level2Path))
        {
            L2B.text = reader.ReadLine();
            L2P.text = reader.ReadLine();
            reader.Close();
        }
        
        using (StreamReader reader = new StreamReader(level3Path))
        {
            L3B.text = reader.ReadLine();
            L3P.text = reader.ReadLine();
            reader.Close();
        }
    }
}
