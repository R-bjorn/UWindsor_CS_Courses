using UnityEngine;

namespace Pickups
{
    public class Movement : MonoBehaviour
    {
        public float smooth = 0.5f;
        public float smoothRotation = 0.5f;
        public float lowerBound = 4.5f;
        public float upperBound = 5.3f;

        public bool shouldMove;
        // private bool _dir = true;
        private Vector3 _endPos = new Vector3( 30, 15, 45);

        void Update()
        {
            // Rotate
            transform.Rotate(_endPos * Time.deltaTime * smoothRotation);
            _endPos += new Vector3(_endPos.x + 30, _endPos.y + 15, _endPos.z + 45);
            
            if (_endPos.x > 360f)
            {
                _endPos.x = 0;
            }

            if (_endPos.y > 360f)
            {
                _endPos.y = 0;
            }

            if (_endPos.z > 360f)
            {
                _endPos.z = 0;
            }
        }
    }
}
