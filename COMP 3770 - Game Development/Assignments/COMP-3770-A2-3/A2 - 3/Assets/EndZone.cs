using System;
using System.Collections;
using UnityEngine;

public class EndZone : MonoBehaviour
{
    public GameObject myPrefab;
    Renderer zone;
    private static readonly int WinColor = Shader.PropertyToID("_WinColor");

    void Start()
    {
        zone = GetComponent<Renderer>();
    }
    void OnTriggerEnter(Collider other)
    {
        Debug.Log("Player Entered the End Zones");
        zone.material = Resources.Load<Material>("After");
        Debug.Log($"Material : {zone.material}");
        StartCoroutine(TimeStop(other));
    }
    
    IEnumerator TimeStop(Collider other)
    {
        //yield on a new YieldInstruction that waits for 5 seconds.
        yield return new WaitForSeconds(5);
        Debug.Log("Going back to the starting place");
        Destroy(other.gameObject);
        StartNew();
    }

    void StartNew()
    {
        Instantiate(myPrefab, new Vector3(3f, 7.5f, -6f), Quaternion.identity);
        zone.material = Resources.Load<Material>("Before");
    }
}
