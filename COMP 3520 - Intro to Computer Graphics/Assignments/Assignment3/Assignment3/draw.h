void normalize(float* v);
void crossProduct(float* a, float* b, float* c, float* n);
void changeColor(float cx, float cy, float cz);
void drawPlane(float* a, float* b, float* c, float* d);

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

void crossProduct(float* a, float* b, float* c, float* n) {

    n[0] = (b[1] - a[1]) * (c[2] - a[2]) - (b[2] - a[2]) * (c[1] - a[1]);
    n[1] = (b[2] - a[2]) * (c[0] - a[0]) - (b[0] - a[0]) * (c[2] - a[2]);
    n[2] = (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);

    normalize(n);
}

void changeColor(float cx, float cy, float cz) {

    float amb[] = { 0.2f, 0.2f, 0.2f, 1.0f };
    float diff[] = { cx, cy, cz, 1.0f };
    float spec[] = { 1.0f, 1.0f, 1.0f, 1.0f };

    // Specify reflection parameters
    glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, amb);
    glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, diff);
    glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, spec);
    glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, 100);

    glLightModeli(GL_LIGHT_MODEL_LOCAL_VIEWER, GL_TRUE);
}

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

// Draw Room
void drawCubeModel(float room) {
    float a[3], b[3], c[3], d[3]; // 4 vertices for each plane

    changeColor(0.2f, 0.6f, 0.3f);

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

    changeColor(0.5f, 0.5f, 0.5f);

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

// Draw Chair
void drawChairModel(float room)
{
    
    float a[3], b[3], c[3], d[3];

    changeColor(1,1,1);

    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 4+0.5;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 4+0.5;
    c[0] = room / 6;  c[1] = -room - 4;       c[2] = room / 4+0.5;
    d[0] = -room / 6; d[1] = -room - 4;       d[2] = room / 4+0.5;
    drawPlane(a, b, c, d);
    
    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 4 + 2;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 4 + 2;
    c[0] = room / 6;  c[1] = -room - 4;       c[2] = room / 4 + 2;
    d[0] = -room / 6; d[1] = -room - 4;       d[2] = room / 4 + 2;
    drawPlane(a, b, c, d);
    
    a[0] = -room / 6; a[1] = -room / 2 - 4 + 6;       a[2] = room / 4 + 2;
    b[0] = room / 6;  b[1] = -room / 2 - 4 + 6;       b[2] = room / 4 + 2;
    c[0] = room / 6;  c[1] = -room - 4 + 6;       c[2] = room / 4 + 2;
    d[0] = -room / 6; d[1] = -room - 4 + 6;       d[2] = room / 4 + 2;
    drawPlane(a, b, c, d);
    changeColor(0.3, 0.4, 0.5);
    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 8 + 3;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 8 + 3;
    c[0] = room / 6;  c[1] = -room / 6 - 4;       c[2] = -room / 8 + 3;
    d[0] = -room / 6; d[1] = -room / 6 - 4;       d[2] = -room / 8 + 3;
    drawPlane(a, b, c, d);
    changeColor(1, 1, 1);

    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 4 - 5.5;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 4 - 5.5;
    c[0] = room / 6;  c[1] = -room - 4;       c[2] = room / 4 - 5.5;
    d[0] = -room / 6; d[1] = -room - 4;       d[2] = room / 4 - 5.5;
    drawPlane(a, b, c, d);

    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 4 + 2 - 6;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 4 + 2 - 6;
    c[0] = room / 6;  c[1] = -room - 4;       c[2] = room / 4 + 2 - 6;
    d[0] = -room / 6; d[1] = -room - 4;       d[2] = room / 4 + 2 - 6;
    drawPlane(a, b, c, d);

    a[0] = -room / 6; a[1] = -room / 2 - 4 + 6;       a[2] = room / 4 + 2 - 7.5;
    b[0] = room / 6;  b[1] = -room / 2 - 4 + 6;       b[2] = room / 4 + 2 - 7.5;
    c[0] = room / 6;  c[1] = -room - 4 + 6;       c[2] = room / 4 + 2 - 7.5;
    d[0] = -room / 6; d[1] = -room - 4 + 6;       d[2] = room / 4 + 2 - 7.5;
    drawPlane(a, b, c, d);
    changeColor(0.3, 0.4, 0.5);
    a[0] = -room / 6; a[1] = -room / 6 - 4;       a[2] = room / 8 + 3 - 6;
    b[0] = room / 6;  b[1] = -room / 6 - 4;       b[2] = room / 8 + 3 - 6;
    c[0] = room / 6;  c[1] = -room / 6 - 4;       c[2] = -room / 8 + 3 - 6;
    d[0] = -room / 6; d[1] = -room / 6 - 4;       d[2] = -room / 8 + 3 - 6;
    drawPlane(a, b, c, d);
}

// Draw Objects on table
void drawTableThingsModel(float room) {
    float a[3], b[3], c[3], d[3];

    changeColor(0.3, 0.4, 0.5);
    
    a[0] = -room / 15; a[1] = -room / 8 - 4 + 0.5;       a[2] = room / 15;
    b[0] = room / 15;  b[1] = -room / 8 - 4 + 0.5;       b[2] = room / 15;
    c[0] = room / 15;  c[1] = -room - 4 + 0.5;       c[2] = room / 15;
    d[0] = -room / 15; d[1] = -room - 4 + 0.5;       d[2] = room / 15;
    drawPlane(a, b, c, d);
    
    a[0] = room / 15; a[1] = -room / 8 - 4  + 0.5;       a[2] = -room / 15;
    b[0] = -room / 15;  b[1] = -room / 8 - 4 + 0.5;     b[2] = -room / 15;
    c[0] = -room / 15;  c[1] = -room - 4 + 0.5;    c[2] = -room / 15;
    d[0] = room / 15; d[1] = -room - 4 + 0.5;      d[2] = -room / 15;
    drawPlane(a, b, c, d);

    a[0] = -room / 15; a[1] = -room / 8 - 4 + 0.5;       a[2] = room / 15;
    b[0] = room / 15;  b[1] = -room / 8 - 4 + 0.5;       b[2] = room / 15;
    c[0] = room / 15;  c[1] = -room / 8 - 4 + 0.5;       c[2] = -room / 15;
    d[0] = -room / 15; d[1] = -room / 8 - 4 + 0.5;       d[2] = -room / 15;
    drawPlane(a, b, c, d);

    a[0] = -room / 15; a[1] = -room / 8 - 4 + 0.5;       a[2] = room / 15;
    b[0] = -room / 15;  b[1] = -room - 4 + 0.5;       b[2] = room / 15;
    c[0] = -room / 15;  c[1] = -room - 4 + 0.5;       c[2] = -room / 15;
    d[0] = -room / 15; d[1] = -room / 8 - 4 + 0.5;       d[2] = -room / 15;
    drawPlane(a, b, c, d);

    a[0] = room / 15; a[1] = -room - 4 + 0.5;       a[2] = room / 15;
    b[0] = room / 15;  b[1] = -room / 8 - 4 + 0.5;       b[2] = room / 15;
    c[0] = room / 15;  c[1] = -room / 8 - 4 + 0.5;       c[2] = -room / 15;
    d[0] = room / 15; d[1] = -room - 4 + 0.5;       d[2] = -room / 15;
    drawPlane(a, b, c, d);
}

