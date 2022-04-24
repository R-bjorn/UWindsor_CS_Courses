// assignment3help.c

/* Keyboard Controls:        */
/* ------------------------- */
// Press ESC or Q to exit the program
// Press R or 1 to reset to initial camera position
// Press 2 to shift to Camera 2
// Press 3 to shift to Camera 2
// Press 4 to shift to Camera 2
// Press 5 to disable light source 2
// Press 6 to enable light source 2
// Press 7 to disable light source 1
// Press 8 to enable light source 1
// Press Z to decrement eyeZ
// Press X to increment eyeZ

#define GLFW_INCLUDE_GLU
#include <GLFW/glfw3.h>

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

#include "draw.h"

/* Global Variables to handle movements */

#define W 1000      // Width of the screen
#define H 1000      // Height of the screen
#define room 7      // Side length of the room's (cube) wall 

// Lighting variables
float ambient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
float diffuse[] = { 0.9f, 0.8f, 0.5f, 1.0f };
float specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
float global_ambient[] = { 0.1f, 0.1f, 0.1f, 1.0f };

// First light source
float light0_ambient[] = { 0.3f, 0.3f, 0.3f, 1.0f };
float light0_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
float light0_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
float light0_position[] = { room - 1, room, -room + 1, 1.0 };

//Assign 4 start 

//Second light source with changed color

float light1_ambient[] = { 0.3f, 0.3f, 0.3f, 1.0f };
//float light1_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
//float light1_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
float light1_diffuse[] = { 2.0f, 2.0f, 2.0f, 2.0f }; //try with different colours and parameters of light
float light1_specular[] = { 2.0f, 2.0f, 2.0f, 2.0f };
float light1_position[] = { -room + 1, room, room - 1, 0.0 }; //Changed position of light

// Third light source
float light2_ambient[] = { 0.3f, 0.3f, 0.3f, 1.0f };
float light2_diffuse[] = { 1.0f, 1.0f, 1.0f, 1.0f };
float light2_specular[] = { 1.0f, 1.0f, 1.0f, 1.0f };
float light2_position[] = { room - 3, room , -room + 3, 0 };

//Assign 4 end

struct coords {
    int x;     // X-axis rotation
    int y;     // Y-axis rotation
} cube = { 0, 0 }; // Cube default position is at (0, 0)


// Position of the camera
float eyeX = room - 3, eyeY = room - 3, eyeZ = -room + 3;
float centerX = 0, centerY = 0, centerZ = 0;
float upX = 0, upY = 1, upZ = 0;

void drawFrame();
static void keyboardManager(GLFWwindow*, int, int, int, int);
void makeWindow(GLFWwindow* window);

int main(void)
{

    if (!glfwInit())
        exit(EXIT_FAILURE);

    GLFWwindow* window = glfwCreateWindow(W, H, "assignment4help.c", NULL, NULL);
    makeWindow(window);

    while (!glfwWindowShouldClose(window))
    {
        // Draw
        drawFrame();

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwDestroyWindow(window);
    glfwTerminate();
    exit(EXIT_SUCCESS);
}

void drawFrame() {
    // GL_PROJECTION defines the properties of the camera that views the objects in the world coordinate frame
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(80, W / (float)H, 1, 25);
    gluLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);  // Used to "point the eye" based on camera position / center view / up direction

    // GL_MODELVIEW defines how your objects are transformed (meaning translation, rotation and scaling) in the world coordinate frame
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    glPushMatrix();
    glRotatef((GLfloat)cube.x, 1, 0, 0);
    glRotatef((GLfloat)cube.y, 0, 1, 0);
    drawCubeModel((float)room); /* see draw.h file */
    drawTableModel((float)room);
    drawchair1((float)room);
    glPopMatrix();

    glFlush();
}

void makeWindow(GLFWwindow* window) {
     

    if (!window)
    {
        glfwTerminate();
        exit(EXIT_FAILURE);
    }

    glfwMakeContextCurrent(window);
    glfwSwapInterval(1);

    glfwSetKeyCallback(window, keyboardManager);

    glEnable(GL_DEPTH_TEST);

    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glViewport(0, 0, (GLsizei)W, (GLsizei)H);
    glClearColor(1.0, 1.0, 1.0, 0.0);
    glColor3f(0, 0, 0);
    //glColor3f(1.0f, 0.0f, 0.0f);
    glPointSize(1.0);


    /* Add perspective, adjust camera position */
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(80, W / (float)H, 1, 25);
    gluLookAt(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);


    /* Apply information for first light */
    glLightfv(GL_LIGHT0, GL_AMBIENT, light0_ambient);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, light0_diffuse);
    glLightfv(GL_LIGHT0, GL_SPECULAR, light0_specular);
    glLightfv(GL_LIGHT0, GL_POSITION, light0_position);

    //Assign 4 start

    ///* Apply information for Second light */
    glLightfv(GL_LIGHT1, GL_AMBIENT, light1_ambient);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, light1_diffuse);
    glLightfv(GL_LIGHT1, GL_SPECULAR, light1_specular);
    glLightfv(GL_LIGHT1, GL_POSITION, light1_position);

    //Assign 4 end 

    /* Apply global ambient lighting */
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, global_ambient);

    /* More options as needed */
    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, ambient);
    glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, diffuse);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, specular);
    glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, 100);
    glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);


    /* Enable lighting */
    glEnable(GL_LIGHTING);

    /* Enable first light */
    glEnable(GL_LIGHT0);
    //glDisable(GL_LIGHT0);

     /* Enable Second light */
    glEnable(GL_LIGHT1);
    //glDisable(GL_LIGHT1);

    /* Set the shader to smooth */
    glShadeModel(GL_SMOOTH);
}

static void keyboardManager(GLFWwindow* window, int key, int scancode, int action, int mods)
{
    if (action != GLFW_PRESS && action != GLFW_REPEAT)
        return;

    // ESC to quit
    if (key == GLFW_KEY_ESCAPE || key == 'q' || key == 'Q')
    {
        glfwSetWindowShouldClose(window, GL_TRUE);
        return;
    }

    // Changing camara using keys 1,2,3,4
    // Camara 1
    else if (key == 'r' || key == 'R' || key == '1') {
        eyeX = room - 3; eyeY = room - 3; eyeZ = -room + 3; //4,4,-4
        centerX = 0; centerY = 0; centerZ = 0;
        upX = 0; upY = 1; upZ = 0;
    }
    // Camara 2
    else if (key == '2') {
        eyeX = room - 3; eyeY = room - 3; eyeZ = room - 3; //4,4,4
        centerX = 0; centerY = 0; centerZ = 0;
        upX = 0; upY = 1; upZ = 0;
    }
    // Camara 3
    else if (key == '3') {
        eyeX = -room + 3; eyeY = room - 3; eyeZ = room - 3; //-4,4,4
        centerX = 0; centerY = 0; centerZ = 0;
        upX = 0; upY = 1; upZ = 0;
    }
    // Camara 4
    else if (key == '4') {
        eyeX = -room + 3; eyeY = room - 3; eyeZ = -room + 3; //-4,4,-4
        centerX = 0; centerY = 0; centerZ = 0;
        upX = 0; upY = 1; upZ = 0;
    }

    // Changing lights using keys 5,6,7,8
    // Light 1 off
    else if (key == '5') {
        glDisable(GL_LIGHT1);
    }
    // Light 1 on
    else if (key == '6') {
        glEnable(GL_LIGHT1);
    }
    // Light 2 off
    else if (key == '7') {
        glDisable(GL_LIGHT0);
    }
    // Light 2 on
    else if (key == '8') {
        glEnable(GL_LIGHT0);
    }
    // light 3 off
    else if (key == '9') {
        glDisable(GL_LIGHT2);
    }
    // light 3 on
    else if (key == '0') {
        glEnable(GL_LIGHT2);
    }
    
}