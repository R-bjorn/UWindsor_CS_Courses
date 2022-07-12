using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class rig : MonoBehaviour
{
    private bool _dir = true;
    public float speed = 2f;
    private void LateUpdate()
    {
        if (_dir)
        {
            transform.Translate(Vector3.forward * speed * Time.deltaTime);
            // _dir = false;
        }
        else
        {
            transform.Translate(Vector3.back * speed * Time.deltaTime);
            // _dir = true;
        }

        if(transform.position.z > 2.9f)
            _dir = false;
        else if(transform.position.z < 0f)
            _dir = true;
    }

    private void OnTriggerEnter(Collider other)
    {
        Debug.Log("Entered");
        if (other.CompareTag("Player"))
        {
            gameObject.SetActive(false);
        }
        // speed -= 1;
    }

    private void OnTriggerStay(Collider other)
    {
        Debug.Log("Stayed");
    }

    private void OnTriggerExit(Collider other)
    {
        Debug.Log("Exited");
    }
}
