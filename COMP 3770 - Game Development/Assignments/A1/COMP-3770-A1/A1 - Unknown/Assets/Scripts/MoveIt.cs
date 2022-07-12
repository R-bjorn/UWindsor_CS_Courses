using UnityEngine;

public class MoveIt : MonoBehaviour
{
    // Time per Unit
    private bool _direction = true;

    private void Update()
    {
        // Changing position of GameObj
        var position = transform.position;
        position = (_direction) 
            ? position + new Vector3(1, 0, 0).normalized * Time.deltaTime
            : position + new Vector3(-1, 0, 0).normalized * Time.deltaTime;
        transform.position = position;
        
        // To Change the direction when gameObj is at two points
        if (position.x > 3.1) { _direction = false; }
        if (position.x < -3.1) { _direction = true; }
    }
}