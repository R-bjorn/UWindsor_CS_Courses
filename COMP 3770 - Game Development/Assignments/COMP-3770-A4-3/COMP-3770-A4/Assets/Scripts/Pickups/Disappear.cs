using System;
using System.Collections;
using UnityEngine;

namespace Pickups
{
    public class Disappear : MonoBehaviour
    {
        public float reAppear = 3.0f;

        void OnTriggerEnter(Collider other)
        {
            if (other.CompareTag("Player"))
            {
                Debug.Log($"Player Pickup up something \n{other}");
                StartCoroutine(Magic());
            }
        }
        IEnumerator Magic()
        {
            // Destroy(gameObject);
            gameObject.SetActive(false);
            Debug.Log("Disappeared");
            yield return new WaitForSeconds(reAppear);
            gameObject.SetActive(true);
            Debug.Log("Reappear");

        }
    }
    
}
