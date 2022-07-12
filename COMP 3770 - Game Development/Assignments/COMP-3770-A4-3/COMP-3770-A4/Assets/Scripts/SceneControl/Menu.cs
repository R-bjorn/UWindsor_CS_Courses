using UnityEngine;
using UnityEngine.SceneManagement;

public class Menu : MonoBehaviour
{

    public void Play()
    {
        SceneManager.LoadScene(2);
    }

    public void Quit()
    {
        Debug.Log("Bye");
        Application.Quit();
    }
}

