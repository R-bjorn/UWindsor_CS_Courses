window.addEventListener("load", init);

function init(){
	// for update color
	var element_colour = document.getElementById("colour");
	element_colour.addEventListener("click",update_color);

	// for update counter
	var element_counter = document.getElementById("counter");
	element_counter.addEventListener("click",update_counter);

	// for update background Color
	var element_bg = document.getElementById("background");
	element_bg.addEventListener("click",update_bg);
}

// function for updating colour
var last_colour = 0;
function update_color(evt){
	// For color
	var red = (last_colour & 4)?'0':'F';
	var blue = (last_colour & 2)?'0':'F';
	var green = (last_colour & 1)?'0':'F';

	// For background color
	var rbg = (red=='F')?'0':'F';
	var bbg = (blue == 'F')?'0':'F';
	var gbg = (green == 'F')?'0':'F';

	// Adding all the colors 
	var color = '#'+red+green+blue;
	var bgcolor = '#'+rbg+gbg+bbg;

	// Changing CSS 
	this.style.color = color;
	this.style.backgroundColor = bgcolor;

	// So that last_color stays between 0 to 7
	last_colour = ++last_colour%8;
}

// function for updating counter
var counter = 0;
function update_counter(){
	this.innerHTML = "<p> Count="+counter+".</p>";
	counter++;
}

// function for updating background color
var bcolor = 0;
function update_bg(){
	var color;
	if(bcolor == 0){
		color = "#000";
		bcolor = 1;
	}else{
		color = "#fff";
		bcolor = 0;
	}
	document.getElementById("background").style.backgroundColor = color;
}