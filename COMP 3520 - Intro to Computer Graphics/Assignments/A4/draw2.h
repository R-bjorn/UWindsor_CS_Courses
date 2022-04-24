#pragma once
//shading
void normalize(float* v);
void crossProduct(float* a, float* b, float* c, float* n);
void changeColor(float cx, float cy, float cz);
void drawPlane(float* a, float* b, float* c, float* d);

/*
    Normalize function for the cross product below
*/
void normalize(float* v) {
    float mg;
    int i;

    // Length of vector
    mg = (float)(sqrt(pow((double)v[0], 2.0) + pow((double)v[1], 2.0) + pow((double)v[2], 2.0)));

    if (mg != 0)
        // Divide by length to normalize
        for (i = 0; i < 3; i++)
            v[i] /= mg;
    else {
        printf("division by Zero\n");
        exit(0);
    }
}


/*
    Cross product to find the normal,
    then saved in n after we normalize.
*/
void crossProduct(float* a, float* b, float* c, float* n) {

    n[0] = (b[1] - a[1]) * (c[2] - a[2]) - (b[2] - a[2]) * (c[1] - a[1]);
    n[1] = (b[2] - a[2]) * (c[0] - a[0]) - (b[0] - a[0]) * (c[2] - a[2]);
    n[2] = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);

    normalize(n);
}


/*
    This function applies colour to your lighting
    by changing the diffuse colour.
*/
void changeColor(float cx, float cy, float cz) {

    float amb[] = { 0.2f, 0.2f, 0.2f, 1.0f };
    float diff[] = { cx, cy, cz, 1.0f };
    float spec[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    // Specify reflection parameters
    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, amb);
    glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, diff);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, spec);
    glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, 100);
    //glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, 10); //try with different shininess values

    glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
}


/*
    Draw a quadrilateral and apply the normals for shading.
    This function takes 4 verticles (a, b, c and d)
    each with 3 components (x, y, z).
*/
void drawPlane(float* a, float* b, float* c, float* d) {
    float n[3];
    crossProduct(a, b, c, n);
    glNormal3fv(n);

    glBegin(GL_POLYGON);

    glVertex3fv(a); /* half the quadrilateral (Triangle) */
    glVertex3fv(b);
    glVertex3fv(c);

    glEnd();

    crossProduct(a, c, d, n);
    glNormal3fv(n);

    glBegin(GL_POLYGON);

    glVertex3fv(a); /* Other half of quadrilateral */
    glVertex3fv(c);
    glVertex3fv(d);

    glEnd();
}


/*
    Draw a simple cube.
    For each face of the cube, we do the following:
        1. Define the 4 vertices of the wall (a, b, c and d)
        2. We use "room" as the size of the cube/wall
        3. call "drawPlane" which will draw the wall and apply normals
        4. "changeColor" will apply the colour change to the lighting.
*/
void drawCubeModel(float room) {
    float a[3], b[3], c[3], d[3]; // 4 vertices for each plane

    changeColor(0.4f, 0.6f, 0.7f);


    a[0] = -room; a[1] = room;       a[2] = room;
    b[0] = room;  b[1] = room;       b[2] = room;
    c[0] = room;  c[1] = -room;       c[2] = room;
    d[0] = -room; d[1] = -room;       d[2] = room;
    drawPlane(a, b, c, d);

    a[0] = room; a[1] = room;       a[2] = -room; // (7, 7, -7)
    b[0] = -room;  b[1] = room;     b[2] = -room; // (-7, 7, -7)
    c[0] = -room;  c[1] = -room;    c[2] = -room; // (-7, -7, -7)
    d[0] = room; d[1] = -room;      d[2] = -room; // (7, -7, -7)
    drawPlane(a, b, c, d);

    a[0] = -room; a[1] = room;       a[2] = room;
    b[0] = room;  b[1] = room;       b[2] = room;
    c[0] = room;  c[1] = room;       c[2] = -room;
    d[0] = -room; d[1] = room;       d[2] = -room;
    drawPlane(a, b, c, d);

    a[0] = -room; a[1] = -room;       a[2] = room;
    b[0] = room;  b[1] = -room;       b[2] = room;
    c[0] = room;  c[1] = -room;       c[2] = -room;
    d[0] = -room; d[1] = -room;       d[2] = -room;
    drawPlane(a, b, c, d);

    a[0] = -room; a[1] = room;       a[2] = room;
    b[0] = -room;  b[1] = -room;       b[2] = room;
    c[0] = -room;  c[1] = -room;       c[2] = -room;
    d[0] = -room; d[1] = room;       d[2] = -room;
    drawPlane(a, b, c, d);

    a[0] = room; a[1] = -room;       a[2] = room;
    b[0] = room;  b[1] = room;       b[2] = room;
    c[0] = room;  c[1] = room;       c[2] = -room;
    d[0] = room; d[1] = -room;       d[2] = -room;
    drawPlane(a, b, c, d);
}

// Draws a table in the room
void drawTableModel(float room)
{
    float a[3], b[3], c[3], d[3]; // 4 vertices for each plane

    changeColor(1.5f, 0.3f, 0.2f);
    //changeColor(1.0f, 0.0f, 0.0f);

    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 6;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 6;
    c[0] = room / 6;  c[1] = -room - 4;       c[2] = room / 6;
    d[0] = -room / 6; d[1] = -room - 4;       d[2] = room / 6;
    drawPlane(a, b, c, d);

    a[0] = room / 6; a[1] = -room / 6 - 4;       a[2] = -room / 6;
    b[0] = -room / 6;  b[1] = -room / 6 - 4;     b[2] = -room / 6;
    c[0] = -room / 6;  c[1] = -room - 4;    c[2] = -room / 6;
    d[0] = room / 6; d[1] = -room - 4;      d[2] = -room / 6;
    drawPlane(a, b, c, d);

    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 6;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 6;
    c[0] = room / 6;  c[1] = -room / 6 - 4;       c[2] = -room / 6;
    d[0] = -room / 6; d[1] = -room / 6 - 4;       d[2] = -room / 6;
    drawPlane(a, b, c, d);

    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 6;
    b[0] = -room / 6;  b[1] = -room - 4;       b[2] = room / 6;
    c[0] = -room / 6;  c[1] = -room - 4;       c[2] = -room / 6;
    d[0] = -room / 6; d[1] = -room / 6 - 4;       d[2] = -room / 6;
    drawPlane(a, b, c, d);

    a[0] = room / 6; a[1] = -room - 4;       a[2] = room / 6;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 6;
    c[0] = room / 6;  c[1] = -room / 6 - 4;       c[2] = -room / 6;
    d[0] = room / 6; d[1] = -room - 4;       d[2] = -room / 6;
    drawPlane(a, b, c, d);
}

void drawchair1(float room) {
    float a[3], b[3], c[3], d[3]; // 4 vertices for each plane

    changeColor(0.1f, 0.0f, 0.0f);

    //front 2 legs:
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 2; //facing table top right
    b[0] = -0.6;  b[1] = -room / 6 - 4;       b[2] = 2; //top left corner
    c[0] = -0.6;  c[1] = -room - 4;       c[2] = 2; //bottom left
    d[0] = -0.8; d[1] = -room - 4;       d[2] = 2; //bottom right
    drawPlane(a, b, c, d);

    a[0] = 0.6; a[1] = -room / 6 - 4;       a[2] = 2; //facing table top right
    b[0] = 0.8;  b[1] = -room / 6 - 4;       b[2] = 2; //top left corner
    c[0] = 0.8;  c[1] = -room - 4;       c[2] = 2; //bottom left
    d[0] = 0.6; d[1] = -room - 4;       d[2] = 2; //bottom right
    drawPlane(a, b, c, d);

    //back side of the front 2 legs
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 2.2; // top right
    b[0] = -0.6;  b[1] = -room / 6 - 4;       b[2] = 2.2; //top left corner
    c[0] = -0.6;  c[1] = -room - 4;       c[2] = 2.2; //bottom left
    d[0] = -0.8; d[1] = -room - 4;       d[2] = 2.2; //bottom right
    drawPlane(a, b, c, d);

    a[0] = 0.6; a[1] = -room / 6 - 4;       a[2] = 2.2; // top right
    b[0] = 0.8;  b[1] = -room / 6 - 4;       b[2] = 2.2; //top left corner
    c[0] = 0.8;  c[1] = -room - 4;       c[2] = 2.2; //bottom left
    d[0] = 0.6; d[1] = -room - 4;       d[2] = 2.2; //bottom right
    drawPlane(a, b, c, d);

    //left side of front legs
    a[0] = -0.6;  a[1] = -room / 6 - 4;       a[2] = 2;
    b[0] = -0.6;  b[1] = -room / 6 - 4;       b[2] = 2.2;
    c[0] = -0.6;  c[1] = -room - 4;       c[2] = 2.2;
    d[0] = -0.6;  d[1] = -room - 4;       d[2] = 2;
    drawPlane(a, b, c, d);

    a[0] = 0.8;  a[1] = -room / 6 - 4;       a[2] = 2;
    b[0] = 0.8;  b[1] = -room / 6 - 4;       b[2] = 2.2; //top left corner
    c[0] = 0.8;  c[1] = -room - 4;       c[2] = 2.2;
    d[0] = 0.8;  d[1] = -room - 4;       d[2] = 2;
    drawPlane(a, b, c, d);

    //right side
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 2;
    b[0] = -0.8; b[1] = -room / 6 - 4;       b[2] = 2.2;
    c[0] = -0.8; c[1] = -room - 4;       c[2] = 2.2; //bottom right
    d[0] = -0.8; d[1] = -room - 4;       d[2] = 2;
    drawPlane(a, b, c, d);

    a[0] = 0.6; a[1] = -room / 6 - 4;       a[2] = 2;
    b[0] = 0.6; b[1] = -room / 6 - 4;       b[2] = 2.2;
    c[0] = 0.6; c[1] = -room - 4;       c[2] = 2.2; //bottom right
    d[0] = 0.6; d[1] = -room - 4;       d[2] = 2;
    drawPlane(a, b, c, d);

    //back legs
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 3; //facing table top right
    b[0] = -0.6;  b[1] = -room / 6 - 4;       b[2] = 3; //top left corner
    c[0] = -0.6;  c[1] = -room - 4;       c[2] = 3; //bottom left
    d[0] = -0.8; d[1] = -room - 4;       d[2] = 3; //bottom right
    drawPlane(a, b, c, d);

    a[0] = 0.6; a[1] = -room / 6 - 4;       a[2] = 3; //facing table top right
    b[0] = 0.8;  b[1] = -room / 6 - 4;       b[2] = 3; //top left corner
    c[0] = 0.8;  c[1] = -room - 4;       c[2] = 3; //bottom left
    d[0] = 0.6; d[1] = -room - 4;       d[2] = 3; //bottom right
    drawPlane(a, b, c, d);

    //back side of the back 2 legs
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 3.2; // top right
    b[0] = -0.6;  b[1] = -room / 6 - 4;       b[2] = 3.2; //top left corner
    c[0] = -0.6;  c[1] = -room - 4;       c[2] = 3.2; //bottom left
    d[0] = -0.8; d[1] = -room - 4;       d[2] = 3.2; //bottom right
    drawPlane(a, b, c, d);

    a[0] = 0.6; a[1] = -room / 6 - 4;       a[2] = 3.2; // top right
    b[0] = 0.8;  b[1] = -room / 6 - 4;       b[2] = 3.2; //top left corner
    c[0] = 0.8;  c[1] = -room - 4;       c[2] = 3.2; //bottom left
    d[0] = 0.6; d[1] = -room - 4;       d[2] = 3.2; //bottom right
    drawPlane(a, b, c, d);

    //left sides
    a[0] = -0.6;  a[1] = -room / 6 - 4;       a[2] = 3;
    b[0] = -0.6;  b[1] = -room / 6 - 4;       b[2] = 3.2; //top left corner
    c[0] = -0.6;  c[1] = -room - 4;       c[2] = 3.2;
    d[0] = -0.6;  d[1] = -room - 4;       d[2] = 3;
    drawPlane(a, b, c, d);

    a[0] = 0.8;  a[1] = -room / 6 - 4;       a[2] = 3;
    b[0] = 0.8;  b[1] = -room / 6 - 4;       b[2] = 3.2; //top left corner
    c[0] = 0.8;  c[1] = -room - 4;       c[2] = 3.2;
    d[0] = 0.8;  d[1] = -room - 4;       d[2] = 3;
    drawPlane(a, b, c, d);

    //right side
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 3;
    b[0] = -0.8; b[1] = -room / 6 - 4;       b[2] = 3.2;
    c[0] = -0.8; c[1] = -room - 4;       c[2] = 3.2;
    d[0] = -0.8; d[1] = -room - 4;       d[2] = 3;
    drawPlane(a, b, c, d);

    a[0] = 0.6; a[1] = -room / 6 - 4;       a[2] = 3;
    b[0] = 0.6; b[1] = -room / 6 - 4;       b[2] = 3.2;
    c[0] = 0.6; c[1] = -room - 4;       c[2] = 3.2;
    d[0] = 0.6; d[1] = -room - 4;       d[2] = 3;
    drawPlane(a, b, c, d);


    //top
    a[0] = -0.8; a[1] = -room / 6 - 4;       a[2] = 2; //front right
    b[0] = -0.8; b[1] = -room / 6 - 4;       b[2] = 3.2; //back right
    c[0] = 0.8;  c[1] = -room / 6 - 4;       c[2] = 3.2; //back left
    d[0] = 0.8;  d[1] = -room / 6 - 4;       d[2] = 2; //front left
    drawPlane(a, b, c, d);

    //back rest
    a[0] = -0.8; a[1] = -3.5;       a[2] = 3.2; //back right
    b[0] = 0.8;  b[1] = -3.5;       b[2] = 3.2; //back left
    c[0] = 0.8;  c[1] = -room / 6 - 4;       c[2] = 3.2; //back left
    d[0] = -0.8; d[1] = -room / 6 - 4;       d[2] = 3.2; //back right
    drawPlane(a, b, c, d);

    a[0] = -0.8; a[1] = -3.5;       a[2] = 3.1; //back right
    b[0] = 0.8;  b[1] = -3.5;       b[2] = 3.1; //back left
    c[0] = 0.8;  c[1] = -room / 6 - 4;       c[2] = 3.1; //back left
    d[0] = -0.8; d[1] = -room / 6 - 4;       d[2] = 3.1; //back right
    drawPlane(a, b, c, d);

    a[0] = -0.8; a[1] = -3.5;       a[2] = 3.1;
    b[0] = -0.8; b[1] = -3.5;       b[2] = 3.2;
    c[0] = -0.8; c[1] = -room / 6 - 4;       c[2] = 3.2;
    d[0] = -0.8; d[1] = -room / 6 - 4;       d[2] = 3.1;
    drawPlane(a, b, c, d);

    a[0] = 0.8;  a[1] = -3.5;       a[2] = 3.1;
    b[0] = 0.8;  b[1] = -3.5;       b[2] = 3.2;
    c[0] = 0.8;  c[1] = -room / 6 - 4;       c[2] = 3.2;
    d[0] = 0.8;  d[1] = -room / 6 - 4;       d[2] = 3.1;
    drawPlane(a, b, c, d);

    a[0] = -0.8; a[1] = -3.5;       a[2] = 3.1;
    b[0] = -0.8; b[1] = -3.5;       b[2] = 3.2;
    c[0] = 0.8;  c[1] = -3.5;       c[2] = 3.2;
    d[0] = 0.8;  d[1] = -3.5;       d[2] = 3.1;
    drawPlane(a, b, c, d);

}