using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Level3Button : MonoBehaviour
{
  
    public void Play()
    {
        SceneManager.LoadScene(3);
    }


}