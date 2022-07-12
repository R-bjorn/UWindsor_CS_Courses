using System;
using TMPro;
using UnityEngine;

public class DetectCollision : MonoBehaviour
{
    // public GameObject winTextObject;
    // public TextMeshProUGUI countText;
    // private static int count = 0;
    // public int totalEnemies;
    
    public GameObject enemy;
    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Melee"))
        {
            Debug.Log("Killed an enemy!");
            // SetCountText();
            Destroy(enemy);
            // Destroy(other.gameObject);
            Debug.Log("Killed an enemy!");
        }
    }
    
    // void SetCountText()
    // {
    //     countText.text = $"Count : {++count} / {totalEnemies}";
    //
    //     // if (count >= 10)
    //     // {
    //     //     winTextObject.SetActive(true);
    //     //     count = 0;
    //     // }
    // }
}
