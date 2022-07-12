using UnityEngine;

namespace Player
{
    [RequireComponent(typeof(CharacterController))]
    public class MovePlayer : MonoBehaviour
    {
        [Header("Player")]
        public CharacterController controller;
        public Transform playerBody;
        public GameObject melee;
        
        [Space(10)]
        [Tooltip("Speed of the player")]
        [Range(10,20)]
        public float speed = 12f;
        [Tooltip("Gravity that applies")]
        [Range(-9,-9*3)]
        public float gravity = -9.81f;
        public float jumpHeight = 80f;
        public float highJump = 480f;
        public float mass = 30;

        [Header("Ground")]
        public Transform groundCheck;
        public float groundDist = 0.4f;
        public LayerMask groundMask;

        
        private Vector3 mousePos;
        private Vector3 objectPos;
        private Vector3 velocity;
        public bool highJ;
        private bool isGrounded;

        void Update()
        {
            isGrounded = Physics.CheckSphere(groundCheck.position, groundDist, groundMask);

            if (isGrounded && velocity.y < 0)
                velocity.y = -2f;
            float x = Input.GetAxis("Horizontal");
            float z = Input.GetAxis("Vertical");

            Vector3 move = transform.forward * z;

            controller.Move(move * speed * Time.deltaTime);
            playerBody.Rotate(Vector3.up * x);
        
            if (Input.GetButtonDown("Jump") && isGrounded)
                Jump();

            velocity.y += mass * gravity * Time.deltaTime;

            controller.Move(velocity * Time.deltaTime);
        
        
            if (Input.GetKeyDown(KeyCode.Mouse0))
            {
                LaunchMelee();
            }
        }

        private void Jump()
        {
            if (highJ)
            {
                velocity.y = Mathf.Sqrt(highJump * -2f * gravity);
                highJ = false;
            }
            else
                velocity.y =  Mathf.Sqrt(jumpHeight * -2f * gravity);
        }
    
        private void LaunchMelee()
        {
            mousePos = Input.mousePosition;
            mousePos.z = 2.0f;
            objectPos = Camera.main.ScreenToWorldPoint(mousePos);
            Instantiate(melee, objectPos, playerBody.transform.rotation);
            // Instantiate(melee, playerBody.transform.position + Vector3.forward * 6, playerBody.transform.rotation);
        }
    }
}
