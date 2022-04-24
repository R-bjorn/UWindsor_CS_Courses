var timerid = 0;
var images = new Array( "image1.jpg",
"image2.jpg",
"image1.jpg" );

var countimages = 0;

function startTime(){

    if(timerid){
        timerid = 0;
    }

    var tDate = new Date();
    if(countimages == images.length){
        countimages = 0;
    }

    if(tDate.getSeconds() % 5 == 0){
        document.getElementById("image-renderer").src = images[countimages];
    }

    countimages++;

    timerid = setTimeout("startTime()", 1500);

}