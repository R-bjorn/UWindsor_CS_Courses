var img1 = new Image();
img1.src = "image1.jpg";

var img2 = new Image();
img2.src = "image2.jpg";

var pixelW = 50;
var pixelH = 50;

const canvas = document.getElementById("image-renderer");
canvas.width = img2.width;
canvas.height = img2.height;
const ctx = canvas.getContext("2d");
ctx.drawImage(img1, 0 , 0 , img1.width, img1.height);
let toggle = true;

function renderImage(){
    // toggling between two images
    if(toggle)
        draw(img1, img2);
    else
        draw(img2, img1);
    toggle = !toggle;
}

function draw(bg,fg){
    ctx.drawImage(bg, 0 , 0 , bg.width, bg.height);
    // drawing background image
    for(let row = 50; row < 1000 ; row += 100){
        for(let col = 50 ; col < 1800 ; col += 100) {
            // drawing pieces of foreground image at specific locations
            // var imgData = ctx.getImageData(col, row, w, h);
            ctx.drawImage(fg, col, row, pixelW, pixelH ,col, row, pixelW, pixelH);
            
        }
    }
}

// setInterval(draw, 3000);
// // starting timer
setInterval(renderImage, 2000);


