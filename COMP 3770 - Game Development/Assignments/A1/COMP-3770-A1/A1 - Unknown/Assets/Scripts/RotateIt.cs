using UnityEngine;

public class RotateIt : MonoBehaviour
{
    // private float _duration = 100f;

    private Vector3 _endPos = new Vector3( 30, 60, 90);

    private bool _dir = true;
    // Update is called once per frame
    void Update()
    {
        // transform.Rotate(Vector3.down, _duration * Time.deltaTime);
        transform.Rotate(_endPos * Time.deltaTime);
        
        _endPos = (_dir) 
            ? new Vector3( 30, 60, 90)
            : new Vector3( 0, 0, 0);
        _dir = !_dir;
    }
}