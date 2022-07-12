using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlatformMove : MonoBehaviour
{
    public Vector3[] points;
    public int pointNum = 0;
    public Vector3 currentTarget;

    public float tolerance;
    public float speed;

    public bool automatic;

    // Start is called before the first frame update
    void Start()
    {

        if(points.Length > 0)
        {
            currentTarget = points[0];
        }
        tolerance = speed * Time.deltaTime;

        
    }

    // Update is called once per frame
    void Update()
    {

        if (transform.position != currentTarget)
        {
            movePlatform();
        }
        else
        {
            updateTarget();
        }

        
    }

    void movePlatform()
    {

        Vector3 heading = currentTarget - transform.position;
        transform.position += (heading / heading.magnitude) * speed * Time.deltaTime;

        if(heading.magnitude < tolerance)
        {
            transform.position = currentTarget;
        }

    }

    void updateTarget()
    {
        if (automatic)
        {
            nextPlatform();
        }


    }

    public void nextPlatform()
    {
        pointNum++;
        if(pointNum >= points.Length)
        {
            pointNum = 0;
        }

        currentTarget = points[pointNum];
    }

    public void OnTriggerEnter(Collider other)
    {
        other.transform.parent = transform;
    }

    public void OnTriggerExit(Collider other)
    {
        other.transform.parent = null;
    }

}
