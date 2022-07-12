using System;
using System.Collections;
using System.Collections.Generic;
using Player;
using UnityEngine;

public class highJump : MonoBehaviour
{
    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Player"))
        {
            Debug.Log("SuperJump");   
            var script = other.GetComponent<MovePlayer>();
            script.highJ = true;
        }
    }
}
