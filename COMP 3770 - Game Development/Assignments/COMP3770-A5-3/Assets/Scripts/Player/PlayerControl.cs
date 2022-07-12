using System;
using UnityEngine;

public class PlayerControl : MonoBehaviour
{
    public float mouseSensitivity = 100f;
    public Transform playerBody;
    public GameObject melee;
    
    
    private float xRotation = 0f;
    
    // Start is called before the first frame update
    void Start()
    {
        Cursor.lockState = CursorLockMode.Locked;
    }

    // Update is called once per frame
    void Update()
    {
        float mouseX = Input.GetAxis("Mouse X") * mouseSensitivity * Time.deltaTime;
        float mouseY = Input.GetAxis("Mouse Y") * mouseSensitivity * Time.deltaTime;

        xRotation -= mouseY;
        xRotation = Mathf.Clamp(xRotation, -30f, 30f);
        
        transform.localRotation = Quaternion.Euler(xRotation, 0f, 0f);
        // playerBody.localRotation = Quaternion.Euler(0f, mouseX, 0f);
        playerBody.Rotate(Vector3.up * mouseX);
    }
}
