using UnityEngine;

namespace Pickups
{
    public class Movement : MonoBehaviour
    {
        public float smooth = 0.5f;
        public float smoothRotation = 0.5f;
        public float lowerBound = 4.5f;
        public float upperBound = 5.3f;
        private bool _direction = true;
        // private bool _dir = true;
        private Vector3 _endPos = new Vector3( 30, 15, 45);

        void Update()
        {
            // Changing position of GameObj
            // var coin = gameObject;
            // if (_direction)
            // {
            //     coin.transform.position = Vector3.Slerp(Vector3.zero, new Vector3(0, 1, 0), smooth * Time.deltaTime);
            //     _direction = false;
            // }
            // else
            // {
            //     coin.transform.position = Vector3.Slerp(new Vector3(0, 1, 0), Vector3.zero, smooth * Time.deltaTime);
            //     _direction = true;
            // }

            var position = transform.position;
            position = (_direction) 
                ? position + new Vector3(0, 0.6f, 0) * Time.deltaTime * smooth
                : position + new Vector3(0, -0.6f, 0) * Time.deltaTime * smooth;
            transform.position = position;
            
            // To Change the direction when gameObj is at two points
            if (position.y < lowerBound) { _direction = true; }
            if (position.y > upperBound) { _direction = false; }
            
            
            // Rotate 
            
            transform.Rotate(_endPos * Time.deltaTime * smoothRotation);
            _endPos += new Vector3(_endPos.x + 30, _endPos.y + 15, _endPos.z + 45);
            // _endPos = (_dir) 
            //     ? new Vector3( 0, 90,0)
            //     : new Vector3( 0, 0, 0);
            // _dir = !_dir;
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
