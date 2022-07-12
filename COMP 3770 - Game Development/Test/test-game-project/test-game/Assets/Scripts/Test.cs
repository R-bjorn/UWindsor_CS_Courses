using UnityEngine;

public class Test : MonoBehaviour
{
    private void OnCollisionEnter(Collision other)
    {
        Debug.Log($"Entered {other.gameObject.name}");
    }
    
    private void OnCollisionStay(Collision other)
    {
        Debug.Log($"Stayed {other.gameObject.name}");
    }
    private void OnCollisionExit(Collision other)
    {
        Debug.Log($"Exited {other.gameObject.name}");
    }
}
