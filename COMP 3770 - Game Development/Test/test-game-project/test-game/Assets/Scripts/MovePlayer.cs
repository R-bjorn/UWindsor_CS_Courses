using System;
using UnityEngine;

public class MovePlayer : MonoBehaviour
{
    [SerializeField]private LayerMask playerMask;
    [SerializeField] private Transform groundCheckTransform = null;

    private Rigidbody player;
    private bool _spacePressed = false;
    private float _horizontalInput;
    private int superJump = 0;

    private void Start()
    {
        player = GetComponent<Rigidbody>();
    }

    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Space))
        {
            _spacePressed = true;
        }
        _horizontalInput = Input.GetAxis("Horizontal");
    }

    private void FixedUpdate()
    {
        player.velocity = new Vector3(_horizontalInput, player.velocity.y, 0);
        
        if (Physics.OverlapSphere(groundCheckTransform.position, 0.1f, playerMask).Length == 0)
            return;

        if (_spacePressed)
        {
            float jumpPower = 7f;
            if (superJump > 0)
            {
                jumpPower *= 2;
                superJump--;
            }
            player.AddForce(Vector3.up * jumpPower, ForceMode.VelocityChange);
            _spacePressed = false;
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.gameObject.layer == 7)
        {
            Destroy(other.gameObject);
            superJump++;
        }
    }
}
