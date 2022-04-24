#define GLFW_INCLUDE_GLU
#include <GLFW/glfw3.h>

#include <stdio.h>
#include <stdlib.h>

int windowWidth = 1500;
int windowHeight = 800;
bool truckDriving = false;
int speed = 1;
bool accelearate = false;
bool breaking = false;
bool direction = true;
bool directionToggle = false;
bool quitProgram = false;

struct Point2D {
    int x, y;
};

struct Line2D {
    Point2D start, end;
};
Line2D theRode = { {50,600},{1450,600} };

struct Truck2D {
    Line2D truck;
};
Truck2D myTruckContainer = { { { 100,450 }, {400, 450}} };
Truck2D myTruckHead = { { {435 , 470}, {550, 470}} };
// {120, 575}, {130, 555} 
int wheel1Offset = 0;
GLint wheel1[6][2] = { {130 + wheel1Offset, 555}, {160 + wheel1Offset, 555}, {165 + wheel1Offset, 575}, {160 + wheel1Offset, 600}, {130 + wheel1Offset, 600}, {125 + wheel1Offset, 575} };
int wheel2Offset = 200;
GLint wheel2[6][2] = { {130 + wheel2Offset, 555}, {160 + wheel2Offset , 555}, {165 + wheel2Offset , 575}, {160 + wheel2Offset, 600}, {130 + wheel2Offset , 600}, {125 + wheel2Offset , 575} };
int wheel3Offset = 350;
GLint wheel3[6][2] = { {130 + wheel3Offset, 555}, {160 + wheel3Offset, 555}, {165 + wheel3Offset, 575}, {160 + wheel3Offset, 600}, {130 + wheel3Offset, 600}, {125 + wheel3Offset, 575} };


void startDriving() {
    speed = 1;
    myTruckContainer.truck.start.x += speed;
    myTruckContainer.truck.end.x += speed;
    myTruckHead.truck.start.x += speed;
    myTruckHead.truck.end.x += speed;
    wheel1Offset += 1;
    wheel2Offset += 1;
    wheel3Offset += 1;
    GLint wheel1offset[6][2] = { {130 + wheel1Offset, 555}, {160 + wheel1Offset, 555}, {165 + wheel1Offset, 575}, {160 + wheel1Offset, 600}, {130 + wheel1Offset, 600}, {125 + wheel1Offset, 575} };
    int i;
    for (i = 0; i < 6; i++) {
        wheel1[i][0] = wheel1offset[i][0];
        wheel1[i][1] = wheel1offset[i][1];
    }
    GLint wheel2offset[6][2] = { {130 + wheel2Offset, 555}, {160 + wheel2Offset , 555}, {165 + wheel2Offset , 575}, {160 + wheel2Offset, 600}, {130 + wheel2Offset , 600}, {125 + wheel2Offset , 575} };
    for (i = 0; i < 6; i++) {
        wheel2[i][0] = wheel2offset[i][0];
        wheel2[i][1] = wheel2offset[i][1];
    }
    GLint wheel3offset[6][2] = { {130 + wheel3Offset, 555}, {160 + wheel3Offset, 555}, {165 + wheel3Offset, 575}, {160 + wheel3Offset, 600}, {130 + wheel3Offset, 600}, {125 + wheel3Offset, 575} };
    for (i = 0; i < 6; i++) {
        wheel3[i][0] = wheel3offset[i][0];
        wheel3[i][1] = wheel3offset[i][1];
    }
}

void error_callback(int error, const char* description)
{
    fputs(description, stderr);
}

static void key_callback(GLFWwindow* window, int key, int scancode, int action, int mods)
{
    //if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
       // glfwSetWindowShouldClose(window, GL_TRUE);
}
void mouse_button_callback(GLFWwindow* window, int button, int action, int mods)
{
    double xpos, ypos;
    glfwGetCursorPos(window, &xpos, &ypos);

    if (button == GLFW_MOUSE_BUTTON_RIGHT && action == GLFW_PRESS) {
        if (accelearate)
            speed += 1;
        if (breaking)
            speed -= 1;
        if (directionToggle)
            direction = true;
        if (quitProgram) {
            exit(EXIT_SUCCESS);
        }
    }
    else if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS) {
        /*theRode.end.x = xpos;
        theRode.end.y = ypos;*/
        if (!truckDriving) { // if truck is still
            truckDriving = true;
        }
        else {
            truckDriving = false;
        }

    }
}
void drawLine(int x0, int y0, int x1, int y1) {
    // use your line algorithm  (not the buit in GL_LINES premitive )
    glBegin(GL_LINES);
    glVertex2i(x0, y0);
    glVertex2i(x1, y1);

    glEnd();
}

void drawWheel(GLint wheel[6][2]) {
    int i;
    glBegin(GL_POLYGON); // glBegin(GL_LINE_LOOP);
    for (i = 0; i < 6; i++) {
        glVertex2i(wheel[i][0], wheel[i][1]);

        //glVertex2i(wheel3[i][0], wheel3[i][1]);
    }
    glEnd();
}

void drawTruck(Line2D truck) {
    glLineWidth(50);
    glColor3f(0.0f, 1.0f, 0.0f); // green 
    drawLine(truck.start.x, truck.start.y, truck.end.x, truck.end.y);
    drawLine(truck.start.x, truck.start.y + 30, truck.end.x, truck.end.y + 30);
    drawLine(truck.start.x, truck.start.y + 60, truck.end.x, truck.end.y + 60);
    drawLine(truck.start.x, truck.start.y + 90, truck.end.x, truck.end.y + 90);
    glLineWidth(10);
    // containerConnector
    glColor3f(0.0f, 0.0f, 0.0f); // black
    drawLine(truck.start.x + 300, truck.start.y + 80, truck.end.x + 35, truck.end.y + 80);
    // truck head
    glLineWidth(50);
    glColor3f(1.0f, 0.0f, 0.0f); // red
    drawLine(myTruckHead.truck.start.x, myTruckHead.truck.end.y, myTruckHead.truck.end.x, myTruckHead.truck.end.y);
    drawLine(myTruckHead.truck.start.x, myTruckHead.truck.end.y + 30, myTruckHead.truck.end.x, myTruckHead.truck.end.y + 30);
    drawLine(myTruckHead.truck.start.x, myTruckHead.truck.end.y + 60, myTruckHead.truck.end.x, myTruckHead.truck.end.y + 60);
    drawLine(myTruckHead.truck.start.x, myTruckHead.truck.end.y + 70, myTruckHead.truck.end.x, myTruckHead.truck.end.y + 70);
    // window
    glColor3f(1.0f, 1.0f, 1.0f); // white
    drawLine(myTruckHead.truck.start.x + 30, myTruckHead.truck.end.y + 20, myTruckHead.truck.end.x - 20, myTruckHead.truck.end.y + 20);
    // door
    glColor3f(0.0f, 0.0f, 1.0f); // white
    drawLine(myTruckHead.truck.start.x + 60, myTruckHead.truck.end.y + 60, myTruckHead.truck.end.x - 30, myTruckHead.truck.end.y + 60);
    // wheels
    glColor3f(0.3f, 0.3f, 0.0f); // gray
    drawWheel(wheel1);
    drawWheel(wheel2);
    drawWheel(wheel3);
}

void Draw() {


    glClear(GL_COLOR_BUFFER_BIT);

    int LineWidth = 20;
    glLineWidth(LineWidth);
    glColor3f(0.0f, 0.0f, 0.0f); //Black 

    drawLine(theRode.start.x, theRode.start.y, theRode.end.x, theRode.end.y);
    drawTruck(myTruckContainer.truck);
    ////setting points attributes (color and size)
    int PointSize = 4;
    glPointSize(PointSize);


    glBegin(GL_POINTS);



    //glColor3f(0.0f, 0.0f, 1.0f);     //point 1 color
    //glVertex2i(theLine.start.x, theLine.start.y);  // point 1 position

    //glColor3f(0.4f, 1.0f, 0.5f);      //point 2 color
    //glVertex2i(theLine.end.x, theLine.end.y);   //point 2 position

    glEnd();

    glFlush();
}



void buildWindow() {
    GLFWwindow* window = glfwCreateWindow(windowWidth, windowHeight, "Some Title", NULL, NULL);

    if (!window)
    {
        //If we fail to initialize a window!
        glfwTerminate();
        exit(EXIT_FAILURE);
    }

    glfwMakeContextCurrent(window);
    glfwSwapInterval(1);

    glfwSetKeyCallback(window, key_callback);

    glfwSetMouseButtonCallback(window, mouse_button_callback);


    glClearColor(1.0, 1.0, 1.0, 0.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glViewport(0, 0, windowWidth, windowHeight);
    gluOrtho2D(0.0, windowWidth, windowHeight, 0.0);


    while (!glfwWindowShouldClose(window))
    {
        Draw();
        if (truckDriving) {
            startDriving();
        }
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glfwDestroyWindow(window);
    glfwTerminate();
}

int main(int argc, const char* argv[]) {

    glfwSetErrorCallback(error_callback);
    if (!glfwInit())
        exit(EXIT_FAILURE);

    //Creating a window
    buildWindow();
    return 0;
}

