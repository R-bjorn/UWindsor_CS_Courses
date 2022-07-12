using UnityEngine;

public class ResizeIt : MonoBehaviour
{
    private bool _direction = true;
    void Update()
    {
        // Updating GameObj Scale
        var localScale = transform.localScale;
        localScale = (_direction)
            ? localScale + new Vector3(1, 1, 1) * Time.deltaTime
            : localScale + new Vector3(-1, -1, -1) * Time.deltaTime;
        transform.localScale = localScale;

        // Changing direction
        if ((int)localScale.x == 4) { _direction = false; }
        if ((int)localScale.x == 0) { _direction = true;  }
    }
}