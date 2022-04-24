// chapter3.c (Rolling Polygon)

#define GLFW_INCLUDE_GLU
#include <GLFW/glfw3.h>

#include <stdio.h>
#include <stdlib.h>

GLint tx, ty, angle, moving;
GLint pol[8][2] = { {150, 50}, {200, 50}, {250, 100}, {250, 150}, {200, 200}, {150, 200}, {100, 150}, {100, 100} };
// The centroid of these 8 points is : (175, 125)

void mouseManager(GLFWwindow*, int, int, int);
void displayFunc(void);
void timer(int);

// NOTE: GLFW does not have an equilvalent to glutTimerFunc(), so we must implement timers manually.
// See: https://discourse.glfw.org/t/is-there-any-timer-callback-in-glfw/1155/4
float leftButtonTimer = 0;
float rightButtonTimer = 0;
float timerFunctionTimer = 0;
int timerFunctionValue = 0;

void mouseManager(GLFWwindow* window, int button, int action, int mods)
{
    switch (button) {
    case GLFW_MOUSE_BUTTON_LEFT:
        if (action == GLFW_PRESS)
            if (moving == 1)
                moving = 0;
            else {
                moving = 1;
                leftButtonTimer = 0.1f; // Reset timer to 100 milliseconds or 0.1 seconds
            }
        break;
    case GLFW_MOUSE_BUTTON_RIGHT:
        if (action == GLFW_PRESS)
            if (moving == 2)
                moving = 0;
            else {
                moving = 2;
                rightButtonTimer = 0.1f; // Reset timer to 100 milliseconds or 0.1 seconds
            }
        break;
    case GLFW_MOUSE_BUTTON_MIDDLE:
        exit(0);
        break;
    }
}


void timer(int v) {
    if (moving == 0)
        return;
    if (moving == 1)
        tx = (tx + 3) % 640;
    else
        ty = (ty + 3) % 480;
    angle = (angle + 3) % 360;
    displayFunc();
    timerFunctionTimer = 0.1f; // Reset timer to 100 milliseconds or 0.1 seconds
    timerFunctionValue = v; // Set parameter value for recursive timer function call
}

void displayFunc(void) {
    int i;
    glClear(GL_COLOR_BUFFER_BIT);
    glLoadIdentity();
    gluOrtho2D(0.0, 640.0, 0.0, 480.0);
    glTranslatef(175 + tx, 125.0 + ty, 0.0);
    glRotatef(angle, 0.0, 0.0, 1.0);
    glTranslatef(-(175 + tx), -(125.0 + ty), 0.0);
    glTranslatef(tx, ty, 0.0);
    glBegin(GL_POLYGON); // glBegin(GL_LINE_LOOP);
    for (i = 0; i < 8; i++)
        glVertex2i(pol[i][0], pol[i][1]);
    glEnd();
    glFlush();
}


int main(int argc, const char* argv[]) {

   

    // The main event loop
    while (!glfwWindowShouldClose(window))
    {
        // Keep running

        /** Timer related logic for GLFW. GLFW does not have an equilvalent to glutTimerFunc(), so
            we must implement our own timers manually **/

            // Common code to run for all timers
        float now = glfwGetTime();
        float delta = now - previous;
        previous = now;

        // For each timer, check if timer callback function needs to run
        leftButtonTimer -= delta;
        if (leftButtonTimer <= 0.f)
        {
            timer(1);
        }

        rightButtonTimer -= delta;
        if (rightButtonTimer <= 0.f)
        {
            timer(2);
        }

        timerFunctionTimer -= delta;
        if (timerFunctionTimer <= 0.f && timerFunctionValue != 0)
        {
            timer(timerFunctionValue);
            timerFunctionValue = 0;
        }

        //Draw
        displayFunc();

        //Display
        glfwSwapBuffers(window);

        //Handle the possible user input etc.
        glfwPollEvents();
    }

    //Destroy the window that you are no longer needed
    glfwDestroyWindow(window);

    //terminate glfw
    //Just befor exiting the your program, you should shut down glfw
    glfwTerminate();
    return 0;
}