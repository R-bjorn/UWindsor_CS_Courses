using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Respawn : MonoBehaviour
{

    public GameObject myPrefab;
    private GameObject[] coins;

    void Start()
    {
        coins = GameObject.FindGameObjectsWithTag("Coin");
    }

    void OnTriggerEnter(Collider other)
    {

        Destroy(other.gameObject);
        StartOver();
    }

    void StartOver()
    {
        Instantiate(myPrefab, new Vector3(3f, 7.5f, -6f), Quaternion.identity);
        foreach (GameObject coin in coins)
        {
            coin.SetActive(true);
        }
    }

}
